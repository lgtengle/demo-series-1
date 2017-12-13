package com.lg.javademo.io.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/11/29 14:57
 *
 * @author leiguang
 */
public class ChannelTest {

    @Test
    public void fileChannelTest() throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile(ChannelTest.class.getClassLoader().getResource("logback.xml").getPath(), "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read  "+bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println((char)buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        accessFile.close();
    }


}
