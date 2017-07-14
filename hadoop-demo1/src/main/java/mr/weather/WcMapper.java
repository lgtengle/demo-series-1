package mr.weather;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;


/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/3 20:44
 *
 * @author leiguang
 */

public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    static int MISSING = 9999;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (!line.startsWith("STN")){
            try {
                String[] words = line.split("\\s+");
                //if (!words[17].endsWith("*")){
                    String max = words[17].substring(0, words[17].lastIndexOf("."));
                    int temp = Integer.parseInt(max);
                    if (temp != MISSING)
                        context.write(new Text(words[2].substring(0,4)), new IntWritable(Integer.parseInt(max)));
                //}
            }catch (Exception e){
                e.printStackTrace();
                //System.out.println(line);
            }
        }
    }
}