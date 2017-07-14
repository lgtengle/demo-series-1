package mr.weather;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/3 20:42
 *
 * @author leiguang
 */
public class RunJob {

    public static void main(String[] args) throws Exception {
        System.setProperty("hadoop.home.dir", "D:/software_install/hadoop/hadoop-2.7.3");
        Configuration config = new Configuration();
        //设置hdfs的通讯地址
        //config.set("fs.defaultFS", "hdfs://localhost:9000");
        //设置RN的主机
        //config.set("yarn.resourcemanager.hostname", "localhost");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(config);

            Job job = Job.getInstance(config);
            job.setJarByClass(RunJob.class);

            job.setJobName("wc");

            job.setMapperClass(WcMapper.class);
            job.setReducerClass(WcReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, new Path("D:\\迅雷下载\\天气数据\\result\\gsod_1989.txt"));
            FileInputFormat.addInputPath(job, new Path("D:\\迅雷下载\\天气数据\\result\\gsod_1990.txt"));
            Path outpath = new Path("output");
            /*if (fs.exists(outpath)) {
                fs.delete(outpath, true);
            }*/
            FileOutputFormat.setOutputPath(job, outpath);
            long startTime = System.currentTimeMillis();
            boolean f = job.waitForCompletion(true);
            if (f) {
                System.out.println("job任务执行成功");
                System.out.println("用时："+(System.currentTimeMillis()-startTime)/1000+"s");
            }
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fs!=null)
                fs.close();
        }
    }
}
