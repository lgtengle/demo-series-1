package mr.weather;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 * description: 解压天气数据
 * </p>
 * Created on 2017/7/13 16:30
 *
 * @author leiguang
 */
public class MergeWeatherData {

    /**
     * 解压天气数据
     * @param sourcedir 待解压文件路径
     * @param outDirectory 输出文件名
     */
    public static void unGzipFile(String sourcedir, String outDirectory) {
        String ouputfile = "";
        FileInputStream fin = null;
        GZIPInputStream gzin = null;
        FileOutputStream fout = null;
        try {
            //建立gzip压缩文件输入流
            fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = HOME_PATH + "result\\" + outDirectory;
            fout = new FileOutputStream(ouputfile, true);

            int num;
            byte[] buf=new byte[1024];

            while ((num = gzin.read(buf,0,buf.length)) != -1)
            {
                fout.write(buf,0,num);
            }

            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex){
            System.err.println(ex.toString());

            if (fin!=null)
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (fout!=null)
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (gzin!=null)
                try {
                    gzin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
        return;
    }


    class MergeThread extends   Thread{

        private String path;

        public MergeThread(String s) {
            this.path = s;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+"启动");
            long startTime = System.currentTimeMillis();
            File file = new File(path);
            File[] files = file.listFiles();
            for (File file1 : files) {
                unGzipFile(file1.getAbsolutePath(), file.getName()+".txt");
            }
            System.out.println(Thread.currentThread().getName() + "耗时：" + (System.currentTimeMillis()-startTime)/1000 + "s");
        }
    }

    private static String HOME_PATH = "D:\\迅雷下载\\天气数据\\";

    public static void main(String[] args){

        MergeWeatherData mwd = new MergeWeatherData();
        String[] listPath = {"gsod_1998","gsod_1993","gsod_1997","gsod_1996","gsod_1992","gsod_1991","gsod_1990","gsod_1989"};
        for (String s : listPath) {
            MergeThread mt = mwd.new MergeThread(HOME_PATH + s);
            mt.start();
        }
    }
}
