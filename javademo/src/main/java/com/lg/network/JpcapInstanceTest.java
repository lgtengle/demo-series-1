package com.lg.network;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/8/4 14:44
 *
 * @author leiguang
 */
public abstract class JpcapInstanceTest {

    protected static final int MAX_NUMBER_OF_INSTANCE = 255;
    protected static boolean[] instanciatedFlag = new boolean[255];
    protected int ID;

    JpcapInstanceTest() {
    }

    protected int reserveID() {
        this.ID = -1;

        for(int i = 0; i < 255; ++i) {
            if(!instanciatedFlag[i]) {
                this.ID = i;
                instanciatedFlag[i] = true;
                break;
            }
        }

        return this.ID;
    }

    protected void unreserveID() {
        instanciatedFlag[this.ID] = false;
    }
}
