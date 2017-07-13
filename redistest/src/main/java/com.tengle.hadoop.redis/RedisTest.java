package com.tengle.hadoop.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
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
public class RedisTest {

    private static Logger logger = Logger.getLogger(RedisTest.class);

    private static String KEY_PREFIX = "key:";

    private static JedisCluster jedisCluster;

    private static int WRITETIME =60, READTIME =60;

    private static byte[] value = new byte[4096];

    //args[0] redis集群的配置
    public static void main(String[] args) throws Exception {

        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        String redis = null;
        Properties properties = new Properties();
        try {
            properties.load(RedisTest.class.getClassLoader().getResourceAsStream("redis.properties"));
            redis = properties.getProperty("redis");
        }catch (IOException e){
            throw new IOException(" redis.properties is no corrorect");
        }

        if(redis!=null){
            String[] hostList = redis.split(",");
            for(String host:hostList){
                String[] h = host.split(":");
                nodes.add(new HostAndPort(h[0], Integer.parseInt(h[1])));
            }
        }else {
            throw new Exception(" no input redisCluster ip:port ");
        }
        jedisCluster = new JedisCluster(nodes);
        long startTime = System.currentTimeMillis();
        //logger.info("删除的键值对个数："+ deleteKeys());
        deleteKeys();
        System.out.println("共用时：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            //logger.info("redis集群中key..的个数："+ getKeysCount());
    }


    public static void start(String[] args) throws Exception{
        if(args==null){

        }else if("1".equals(args[0])) {//单线程测试
            if ("write".equals(args[1]))
                logger.info("单个写入的4K个数：" + (singleWriteTest(1000000) - 1000000) + "/每分钟");
            else if ("read".equals(args[0])) {
                logger.info("单个写入的4K个数：" + (singleReadTest(getAllKeys())) + "/每分钟");
            } else if ("n".equals(args[0])) {//多线程测试
                int[] multiStarts = {2000000, 3000000, 4000000};
                if ("write".equals(args[1]))
                    logger.info("多个写入的4K个数：" + multiWriteTest(multiStarts) + "/每分钟");
                else
                    logger.info("多个读入的4K个数：" + multiReadTest(getAllKeys(KEY_PREFIX + "4*")
                            , getAllKeys(KEY_PREFIX + "2*"), getAllKeys(KEY_PREFIX + "3*")) + "/每分钟");

            } else if (args[0].equals("delete")) {//删除所有测试数据
                deleteKeys();
            } else if (args[0].equals("count")) {//计算测试数据的量
                logger.info("redis集群中key..的个数：" + getKeysCount());
            }
        }
    }

    public static int singleWriteTest(int start) throws InterruptedException {
        Timer timer = new Timer();
        AtomicInteger r = new AtomicInteger(start);
        timer.schedule(new RedisTest().new WriteTask(r),0);
        TimeUnit.SECONDS.sleep(WRITETIME);
        timer.cancel();
        return r.intValue();
    }

    class WriteTask extends TimerTask{

        private AtomicInteger single_inital;
        public WriteTask(AtomicInteger single_inital){
            this.single_inital = single_inital;
        }
        public void run(){
            System.out.println("单个线程开始写入");
            while (single_inital.get() <Integer.MAX_VALUE){
                try {
                    jedisCluster.set((KEY_PREFIX + single_inital.incrementAndGet()).getBytes(), value);
                }catch (Exception e){
                    break;
                }
            }
        }
    }

    public static int singleReadTest(Set<byte[]> keys) throws InterruptedException {
        Timer timer = new Timer();
        AtomicInteger r = new AtomicInteger(0);
        timer.schedule(new RedisTest().new ReadTask(r, keys),0);
        TimeUnit.SECONDS.sleep(READTIME);
        timer.cancel();
        return r.intValue();
    }

    class ReadTask extends TimerTask{
        private AtomicInteger single_inital;
        private Set<byte[]> keys;
        public ReadTask(AtomicInteger single_inital, Set<byte[]> keys){
            this.single_inital = single_inital;
            this.keys = keys;
        }
        public void run(){
            System.out.println("单个线程开始读入");
            for(byte[] key : keys){
                try {
                    jedisCluster.get(key);
                    single_inital.incrementAndGet();
                }catch (Exception e){
                    break;
                }
            }
        }
    }

    /**
     * 多个写测试
     * @param starts 写的键值起始数组
     * @return 写入的总数
     */
    public static int multiWriteTest(int[] starts){
        int result = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(starts.length);
        Set<MultiWrite> multiWrites = new HashSet<MultiWrite>();
        for (int start:starts){
            multiWrites.add(new RedisTest().new MultiWrite(start));
        }
        try {
            List<Future<Integer>> futureList = threadPool.invokeAll(multiWrites);
            if(futureList!=null){
                for(Future<Integer> future:futureList){
                    result += future.get();
                }
            }
        }catch (Exception e){
            return result;
        }
        return result;
    }

    class MultiWrite implements Callable<Integer>{

        private int start;

        public MultiWrite(int start){
            this.start = start;
        }
        public Integer call() throws Exception {
            return singleWriteTest(start);
        }
    }

    /**
     * 多个读测试
     * @param keysSet 读的键值起始数组
     * @return 读入的总数
     */
    public static int multiReadTest(Set<byte[]> ...keysSet){
        int result = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(keysSet.length);
        Set<MultiRead> multiReads = new HashSet<MultiRead>();
        for (Set<byte[]> keys:keysSet){
            multiReads.add(new RedisTest().new MultiRead(keys));
        }
        try {
            List<Future<Integer>> futureList = threadPool.invokeAll(multiReads);
            if(futureList!=null){
                for(Future<Integer> future:futureList){
                    result += future.get();
                }
            }
        }catch (Exception e){
            return result;
        }
        return result;
    }

    class MultiRead implements Callable<Integer>{

        private Set<byte[]> keys;

        public MultiRead(Set<byte[]> keys){
            this.keys = keys;
        }
        public Integer call() throws Exception {
            return singleReadTest(keys);
        }
    }
    /**
     * 测试redis集群创建
     * @return
     */
    public static Set<HostAndPort> createTestRedis(){
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        String ip = "192.168.217.5";
        nodes.add(new HostAndPort(ip, 7000));
        nodes.add(new HostAndPort(ip, 7001));
        nodes.add(new HostAndPort(ip, 7002));
        nodes.add(new HostAndPort(ip, 7003));
        nodes.add(new HostAndPort(ip, 7004));
        nodes.add(new HostAndPort(ip, 7005));
        return  nodes;
    }

    /**
     * 删除redis中测试数据
     * @return 删除的键值对个数
     */
    public static int deleteKeys(){
        Set<byte[]> allkeys = getAllKeys();
        for(byte[] k:allkeys)
            jedisCluster.del(k);
        return allkeys.size();
    }

    /**
     * 获取redis数据库所有的key
     * @return
     */
    public static Set<byte[]> getAllKeys(){
        return getAllKeys("key:*");
    }

    /**
     * 获取redis数据库所有的key
     * @return
     */
    public static Set<byte[]> getAllKeys(String pattern){
        Set<byte[]> allkeys = new HashSet<byte[]>();
        Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
        for(Map.Entry<String ,JedisPool> node: nodes.entrySet()){
            Jedis jedis = node.getValue().getResource();
            Set<byte[]> keys = jedis.keys(pattern.getBytes());
            allkeys.addAll(keys);
            jedis.close();
        }
        return allkeys;
    }
    /**
     * 获取redis库中键值对个数
     * @return
     */
    public static int getKeysCount(){
        return getAllKeys().size();
    }
}
