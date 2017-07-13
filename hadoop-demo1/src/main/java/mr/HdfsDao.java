package mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;
import java.net.URI;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/6/7 17:32
 *
 * @author leiguang
 */
public class HdfsDao {

    private static  String HDFS = "hdfs://192.168.217.5:9000/";


    private static String hdfsPath;

    private static  Configuration conf;

    public HdfsDao(Configuration conf){
        this(HDFS, conf);
    }

    public HdfsDao(String hdfs, Configuration conf){
        this.hdfsPath = hdfs;
        this.conf = conf;
    }
    public static JobConf config(){
        JobConf conf = new JobConf(HdfsDao.class);
        conf.setJobName("HdfsDao");
        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");
        conf.addResource("classpath:/hadoop/yarn-site.xml");
        return  conf;
    }
    
    public static void main(String[] args) throws Exception {
        JobConf conf = config();
        HdfsDao hdfsDao = new HdfsDao(conf);
        hdfsDao.mkdir("/tmp/new/two");
    }

    public  void mkdir(String folder) throws Exception {
        Path path = new Path(folder);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            if (!fs.exists(path)){
                fs.mkdirs(path);
                System.out.println("Create : "+folder );
            }
        }catch (Exception e){
           throw e;
        } finally {
            if (fs!=null)
                fs.close();
        }
    }
}
