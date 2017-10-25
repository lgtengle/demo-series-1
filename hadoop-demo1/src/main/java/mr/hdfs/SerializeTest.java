package mr.hdfs;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.*;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/20 19:21
 *
 * @author leiguang
 */
public class SerializeTest {
    
    public static void main(String[] args) throws IOException {

        byte[] bytes = serialize(new IntWritable(163));
        System.out.println("序列化长度："+bytes.length);

        IntWritable iw = new IntWritable();
        diserialize(iw, bytes);
        System.out.println("反序列化结果："+iw.get());
    }

    public static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(bo);
        writable.write(dataOut);
        dataOut.close();
        return bo.toByteArray();
    }

    public static byte[] diserialize(Writable writable, byte[] bytes) throws IOException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        DataInputStream dataIn = new DataInputStream(bi);
        writable.readFields(dataIn);
        dataIn.close();
        return bytes;
    }

    public static void text(){
        Text t = new Text();
        t.getBytes();
        t.getLength();
    }
}
