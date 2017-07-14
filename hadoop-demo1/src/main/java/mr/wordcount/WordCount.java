package mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/3 20:16
 *
 * @author leiguang
 */
public class WordCount {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
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

    private static  String HADOOP_HOME = "D:/software_install/hadoop/hadoop-2.7.3";

    public static void deleteOutput(String path){
        File file = new File(path);
        if (file.exists()){
            File[] tmp=file.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    deleteOutput(path+"/"+tmp[i].getName());
                }
                 else{
                    tmp[i].delete();
                }
            }
            file.delete();
        }
    }
    public static void main(String[] args) throws Exception {
        /*deleteOutput(HADOOP_HOME + "/output");
        //System.setProperty("hadoop.home.dir", HADOOP_HOME);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        //设置mapper类
        job.setMapperClass(TokenizerMapper.class);
        //设置
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        //设置reduce输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);*/
        String s = "010010 99999  19890101    31.3  8    29.9  8  1000.6  8  9999.9  0    7.2  8   13.6  8   28.9   47.0    37.4    21.6   0.20E 999.9  111000";
        /*StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }*/

        String[] split = s.split("\\s+");
        System.out.println(split[17].substring(0, split[17].lastIndexOf(".")));
    }
}
