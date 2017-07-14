package mr.others;

import mr.others.HdfsUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/6/7 22:17
 *
 * @author leiguang
 */
public class HdfsFsTest {
    
    public static void main(String[] args) throws IOException {
        String fileUri = "/TestDir/test.txt";
        String fileOutputUrl = "/home/test/out.txt";
        try {
            writeFileToHdfs(fileUri, fileOutputUrl);
            System.out.println("DONE!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileToHdfs(String pOpenUri, String pOutputUrl) throws Exception {
        FileSystem fileSystem = null;
        FSDataInputStream fsDataInputStream = null;
        FSDataOutputStream fsDataOutputStream = null;
        int buffSize = 4096;
        try {
            fileSystem = HdfsUtils.getFileSystem();
            System.out.println(fileSystem.getClass().getName());
            //fsDataInputStream = fileSystem.open(new Path(pOpenUri));
            //fsDataOutputStream = fileSystem.create(new Path(pOutputUrl));
            //IOUtils.copyBytes(fsDataInputStream, fsDataOutputStream, buffSize, false);
        }catch (Exception e){
            throw  e;
        }finally {
            if (fileSystem!=null)
                fileSystem.close();
            //IOUtils.closeStream(fsDataInputStream);
            //IOUtils.closeStream(fsDataOutputStream);
        }
    }
}
