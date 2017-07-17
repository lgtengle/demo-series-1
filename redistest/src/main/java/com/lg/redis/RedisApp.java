package com.lg.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/6/14 16:35
 *
 * @author leiguang
 */
public class RedisApp {

    private static Logger logger = Logger.getLogger(RedisApp.class);

    private static String KEY_PREFIX = "key:";

    private static JedisCluster jedisCluster;

    private static int WRITETIME = 60, READTIME = 60;

    private static byte[] value = new byte[4096];

    //args[0] redis集群的配置
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        createDefaultRedis();
        //initRedis();
        start(args);
        System.out.println("共用时：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
        System.exit(0);
    }

    public static void start(String[] args) throws Exception {
        if (args.length==0) {
            System.out.println("please input params.");
        }else if("write".equals(args[0])){
            if(args.length>1&&Integer.parseInt(args[1])>1){//多线程写
                int n = Integer.parseInt(args[1]);
                int[] multiStarts = new int[n];
                for (int i=0; i<n; i++)
                    multiStarts[i] = (i + 2)*1000000;
                multiWriteTest(multiStarts);
            }else{//单线程写
                singleWrite();
            }
        }else if("read".equals(args[0])){
            if(args.length>1&&Integer.parseInt(args[1])>1){//多线程读
                int n = Integer.parseInt(args[1]);
                Set<byte[]> params = new HashSet<byte[]>();
                for (int i=0; i<n; i++)
                    params.addAll(getAllKeys(KEY_PREFIX + (i+2) +"*"));
                multiReadTest(params);
            }else{//单线程读
                singleRead();
            }
        }else if ("delete".equals(args[0])) {//删除所有测试数据
            String deleteParams = "key:*";
            if (args.length>1&&args[1]!=null)
                deleteParams = args[1];
            int n = deleteKeys(args[1]);
            System.out.println("total delete keys is ："+n);
        } else if ("count".equals(args[0])) {//计算测试数据的量
            logger.info("redis集群中key..的个数：" + getKeysCount());
        }else if("help".equals(args[0])){
            System.out.println();
        }

    }


    public static void singleWrite() throws InterruptedException {
        logger.info("test single-Thread to write ...");
        singleWriteTest(1000000);
        logger.info("single-Thread write rate is " + getKeysCount(KEY_PREFIX + "1*") + "/min");
    }

    public static void singleRead() throws InterruptedException {
        logger.info("test single-Thread to read ...");
        logger.info("single-Thread read rate is " + singleReadTest(getAllKeys(KEY_PREFIX + "1*")) + "/min");
    }

    public static void initRedis() throws Exception {
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        String redis = null;
        Properties properties = new Properties();
        try {
            properties.load(RedisApp.class.getClassLoader().getResourceAsStream("redis.properties"));
            redis = properties.getProperty("redis");
        } catch (IOException e) {
            throw new IOException(" redis.properties is no corrorect");
        }

        String[] hostList = redis.split(",");
        for (String host : hostList) {
            String[] h = host.split(":");
            nodes.add(new HostAndPort(h[0], Integer.parseInt(h[1])));
        }
        jedisCluster = new JedisCluster(nodes);
    }

    /**
     * 单线程写入测试
     * @param start 开始的底数
     * @throws InterruptedException
     */
    public static void singleWriteTest(final int start) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int n = start;
            @Override
            public void run() {
                while (n < Integer.MAX_VALUE) {
                    try {
                        jedisCluster.set((KEY_PREFIX + (n++)).getBytes(), value);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        },0);
        TimeUnit.SECONDS.sleep(WRITETIME);
        timer.cancel();
    }


    public static int singleReadTest(Set<byte[]> keys) throws InterruptedException {
        Timer timer = new Timer();
        AtomicInteger r = new AtomicInteger(0);
        ReadTask readTask = new RedisApp().new ReadTask(r, keys);
        timer.schedule(readTask, 0);
        TimeUnit.SECONDS.sleep(READTIME);
        timer.cancel();
        return r.intValue();
    }

    class ReadTask extends TimerTask {
        private AtomicInteger single_inital;
        private Set<byte[]> keys;

        public ReadTask(AtomicInteger single_inital, Set<byte[]> keys) {
            this.single_inital = single_inital;
            this.keys = keys;
        }

        public void run() {
            for (byte[] key : keys) {
                try {
                    jedisCluster.get(key);
                    single_inital.incrementAndGet();
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    /**
     * 多个写测试
     *
     * @param starts 写的键值起始数组
     * @return 写入的总数
     */
    public static void multiWriteTest(int[] starts) {

        final CountDownLatch countDownLatch = new CountDownLatch(starts.length);

        int result = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(starts.length);
        logger.info("test multi-Thread to write ...");
        for (final int start:starts) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+" start...");
                        singleWriteTest(start);
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("multi-Thread write rate is ：" + (getKeysCount(KEY_PREFIX + "2*") +
                getKeysCount(KEY_PREFIX + "3*") + getKeysCount(KEY_PREFIX + "4*"))
                +"/min");
    }

    /**
     * 多个读测试
     *
     * @param keysSet 读的键值起始数组
     * @return 读入的总数
     */
    public static void multiReadTest(Set<byte[]>... keysSet) {
        logger.info("test multi-Thread to read ...");
        int result = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(keysSet.length);
        Set<MultiRead> multiReads = new HashSet<MultiRead>();
        for (Set<byte[]> keys : keysSet) {
            multiReads.add(new RedisApp().new MultiRead(keys));
        }
        try {
            List<Future<Integer>> futureList = threadPool.invokeAll(multiReads);
            if (futureList != null) {
                for (Future<Integer> future : futureList) {
                    result += future.get();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("multi-Thread read rate is " + result + "/min");
    }

    class MultiRead implements Callable<Integer> {

        private Set<byte[]> keys;

        public MultiRead(Set<byte[]> keys) {
            this.keys = keys;
        }

        @Override
        public Integer call() throws Exception {
            return singleReadTest(keys);
        }
    }

    /**
     * 测试redis集群创建
     *
     * @return
     */
    public static void createDefaultRedis() {
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        String ip = "192.168.217.5";
        nodes.add(new HostAndPort(ip, 7000));
        nodes.add(new HostAndPort(ip, 7001));
        nodes.add(new HostAndPort(ip, 7002));
        nodes.add(new HostAndPort(ip, 7003));
        nodes.add(new HostAndPort(ip, 7004));
        nodes.add(new HostAndPort(ip, 7005));
        jedisCluster = new JedisCluster(nodes);
        //return nodes;
    }

    /**
     * 删除redis中测试数据
     *
     * @return 删除的键值对个数
     */
    public static int deleteKeys(byte[] pattern) {
        Set<byte[]> allkeys = getAllKeys(pattern);
        for (byte[] k : allkeys)
            jedisCluster.del(k);
        return allkeys.size();
    }

    /**
     * 删除redis中测试数据
     *
     * @return 删除的键值对个数
     */
    public static int deleteKeys(String pattern) {
        return deleteKeys(pattern.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * 获取redis数据库所有的key
     * "key:*"
     * @return
     */
    public static Set<byte[]> getAllKeys(String pattern) {
        return getAllKeys(pattern.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * 获取redis数据库所有的key
     *
     * @return
     */
    public static Set<byte[]> getAllKeys(byte[] pattern) {
        Set<byte[]> allkeys = new HashSet<byte[]>();
        Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
        for (Map.Entry<String, JedisPool> node : nodes.entrySet()) {
            Jedis jedis = node.getValue().getResource();
            Set<byte[]> keys = jedis.keys(pattern);
            allkeys.addAll(keys);
            jedis.close();
        }
        return allkeys;
    }

    /**
     * 获取redis库中键值对个数
     *
     * @return
     */
    public static int getKeysCount() {
        return getAllKeys("key:*").size();
    }

    /**
     * 获取redis库中键值对个数
     *@param pattern
     * @return
     */
    public static int getKeysCount(String pattern) {
        return getAllKeys(pattern).size();
    }
}
