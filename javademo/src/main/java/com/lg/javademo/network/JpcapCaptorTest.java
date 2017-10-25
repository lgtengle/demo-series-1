package com.lg.javademo.network;

import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;

import java.io.IOException;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/8/4 14:42
 *
 * @author leiguang
 */
public class JpcapCaptorTest  extends JpcapInstanceTest {
    public int received_packets;
    public int dropped_packets;

    static {
        System.loadLibrary("jpcap");
    }

    public static void main(String[] args){
        System.out.println(getDeviceList().length);
    }
    private native String nativeOpenLive(String var1, int var2, int var3, int var4);

    private native String nativeOpenOffline(String var1);

    private native void nativeClose();

    private JpcapCaptorTest() throws IOException {
        if(this.reserveID() < 0) {
            throw new IOException("Unable to open a device: 255 devices are already opened.");
        }
    }

    public static native NetworkInterface[] getDeviceList();

    public static JpcapCaptorTest openDevice(NetworkInterface intrface, int snaplen, boolean promisc, int to_ms) throws IOException {
        JpcapCaptorTest jpcap = new JpcapCaptorTest();
        String ret = jpcap.nativeOpenLive(intrface.name, snaplen, promisc?1:0, to_ms);
        if(ret != null) {
            throw new IOException(ret);
        } else {
            return jpcap;
        }
    }

    public static JpcapCaptorTest openFile(String filename) throws IOException {
        JpcapCaptorTest jpcap = new JpcapCaptorTest();
        String ret = jpcap.nativeOpenOffline(filename);
        if(ret != null) {
            throw new IOException(ret);
        } else {
            return jpcap;
        }
    }

    public void close() {
        System.out.println("close:" + this.ID);
        this.nativeClose();
        this.unreserveID();
    }

    public native Packet getPacket();

    public native int processPacket(int var1, PacketReceiver var2);

    public native int loopPacket(int var1, PacketReceiver var2);

    public native int dispatchPacket(int var1, PacketReceiver var2);

    public native void setNonBlockingMode(boolean var1);

    public native boolean isNonBlockinMode();

    public native void breakLoop();

    public native boolean setPacketReadTimeout(int var1);

    public native int getPacketReadTimeout();

    public native void setFilter(String var1, boolean var2) throws IOException;

    public native void updateStat();

    public native String getErrorMessage();

    /*public JpcapSender getJpcapSenderInstance() {
        return new JpcapSender(this.ID);
    }*/
}
