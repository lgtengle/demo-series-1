package mr.hdfs;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
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

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    
    public static void main(String[] args) throws Exception{
        InputStream in = null;
        try {
            in = new URL("hdfs://192.168.217.5:9000/user/lg/test.txt").openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
