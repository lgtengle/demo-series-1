package mr.weather;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/3 20:44
 *
 * @author leiguang
 */

public class WcReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (IntWritable i : values) {
            if (max<i.get())
                max = i.get();
            /*if (i.get()<min)
                min = i.get();*/
        }
        context.write(key, new IntWritable(max));
        //context.write(key, new IntWritable(min));
    }
}
