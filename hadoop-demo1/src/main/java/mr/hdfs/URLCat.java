package mr.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/18 20:58
 *
 * @author leiguang
 */
public class URLCat {


    static String path = "hdfs://192.168.217.5:9000/user/hadoop/test.txt";

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    
    public static void main(String[] args) throws Exception{
        showFileStatus();
    }

    /**
     * 通过java.net.url访问hdfs
     * @throws Exception
     */
    public static void visitHDFSByJavaUrl() throws Exception{
        InputStream in = null;
        try {
            in = new URL("hdfs://192.168.217.5:9000/user/hadoop/test.txt").openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }

    public static void visitHDFSByFS() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(path), conf);
        InputStream in = null;

        try {
            in = fs.open(new Path(path));
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }


    static String dst = "hdfs://192.168.217.5:9000/user/hadoop/weather/gsod_1989.txt";

    public static void copyLocalFileToHdfs() throws IOException{

        InputStream in = new BufferedInputStream(new FileInputStream(new File("D:\\迅雷下载\\天气数据\\result\\gsod_1989.txt")));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);

        OutputStream out =  fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.println(".");
            }
        });

        IOUtils.copyBytes(in, out, 4094, true);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);

    }


    public static void showFileStatus() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);

        Path file = new Path(dst);
        FileStatus status = fs.getFileStatus(file);
        System.out.println(status.getLen());
        System.out.println(status.getReplication());
        System.out.println(status.getBlockSize());
        System.out.println(status.getOwner());
        System.out.println(status.getPermission().toString());


    }

    public static void delete() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        System.out.println(fs.delete(new Path(dst), true));
    }

}
