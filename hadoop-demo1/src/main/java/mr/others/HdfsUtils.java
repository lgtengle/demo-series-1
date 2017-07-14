package mr.others;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/6/7 22:10
 *
 * @author leiguang
 */
public class HdfsUtils {

    private static  String HDFS = "hdfs://192.168.217.5:9000";
    public static FileSystem getFileSystem() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", HDFS);
        FileSystem fileSystem = FileSystem.get(conf);
        return fileSystem;
    }


    public static FileSystem getFileSystemByUser(String pOpenUri, String pUser) throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", HDFS);
        FileSystem fileSystem = FileSystem.get(new URI(pOpenUri), conf, pUser);
        return  fileSystem;
    }

    public static FileSystem getFileSystemByUser(String pUser) throws Exception, InterruptedException, URISyntaxException{

        String fileUri = "/home/test/test.txt" ;
        Configuration conf = new Configuration() ;
        conf.set("fs.defaultFS", HDFS);
        FileSystem fileSystem = FileSystem.get(new URI(fileUri), conf, pUser) ;
        return fileSystem ;
    }
}
