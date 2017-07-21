package mr.taxi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * 
 * @author leiguang
 * Created on 2017年7月21日 下午12:07:54
 */
public class TaxiMapReduce {

	public static class TaxiMapper extends Mapper<Object, Text, Text, IntWritable>{
		
		private final IntWritable one = new IntWritable(1);
		
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
        	String string = value.toString();
        	if (!string.startsWith("medallion")) {
        		String[] array = string.split(",");
        		try {
        			word.set(array[5].substring(0, 13));
        			context.write(word, one);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        }
        
	}
	
	public static class TaxiReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
		private IntWritable result = new IntWritable();

        @Override
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
	}
	
	
	static String FS_PATH = "hdfs://192.168.217.5:9000";
	
	static String IN_PATH = FS_PATH + "/user/hadoop/taxi/trip_data_5.csv";
	
	static String OUT_PATH = FS_PATH + "/user/hadoop/output/";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", FS_PATH);
        Job job = Job.getInstance(conf, "taxi count");
        job.setJarByClass(TaxiMapReduce.class);
        //设置mapper类
        job.setMapperClass(TaxiMapper.class);
        //设置
        job.setCombinerClass(TaxiReducer.class);
        job.setReducerClass(TaxiReducer.class);
        //设置reduce输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(IN_PATH));
        FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
