package mr.weather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * description: 传统方式分析数据
 * </p>
 * Created on 2017/7/14 17:35
 *
 * @author leiguang
 */
public class TraditionMaxTemp {

    static int MISSING = 9999;

    class TraditionThread extends Thread{
        FileReader fr;
        BufferedReader br;
        String path;

        CountDownLatch countDownLatch;

        public TraditionThread(CountDownLatch countDownLatch, String path) {
            this.countDownLatch = countDownLatch;
            this.path = path;
        }

        @Override
        public void run() {
            try {
                fr = new FileReader(path);

                int result = Integer.MIN_VALUE;
                br = new BufferedReader(fr);
                String line = null;
                String year = null;
                while ((line=br.readLine())!=null){
                    if (!line.startsWith("STN")){
                        String[] words = line.split("\\s+");
                        year = words[2].substring(0,4);
                        String max = words[17].substring(0, words[17].lastIndexOf("."));
                        int temp = Integer.parseInt(max);
                        if (temp != MISSING){
                            if (result<Integer.parseInt(max))
                                result = Integer.parseInt(max);
                        }
                        break;
                    }

                }
                while ((line=br.readLine())!=null){
                    if (!line.startsWith("STN")){
                        try {
                            String[] words = line.split("\\s+");
                            String max = words[17].substring(0, words[17].lastIndexOf("."));
                            int temp = Integer.parseInt(max);
                            if (temp != MISSING){
                                if (result<Integer.parseInt(max))
                                    result = Integer.parseInt(max);
                            }
                        }catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                }
                System.out.println(year + " : " + result);
                fr.close();
                br.close();
                countDownLatch.countDown();
            }catch (IOException e1){

                e1.printStackTrace();
                if (fr!=null)
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (br!=null)
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                countDownLatch.countDown();
            }
        }
    }
    
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        TraditionMaxTemp tmt = new TraditionMaxTemp();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        TraditionThread tt1 = tmt.new TraditionThread(countDownLatch, "D:\\迅雷下载\\天气数据\\result\\gsod_1989.txt");
        TraditionThread tt2 = tmt.new TraditionThread(countDownLatch, "D:\\迅雷下载\\天气数据\\result\\gsod_1990.txt");
        tt1.start();
        tt2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("时间："+(System.currentTimeMillis()-startTime)/1000+"s");

        /*TraditionThread tt1 = tmt.new TraditionThread(countDownLatch, "D:\\迅雷下载\\天气数据\\sample.txt");
        tt1.start();*/
    }
}
