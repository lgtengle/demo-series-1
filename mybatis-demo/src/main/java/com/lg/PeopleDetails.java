package com.lg;

import oracle.sql.BLOB;

import java.sql.SQLException;

/**
 * <p>
 * description:f_hx_api_people_details table model class
 * </p>
 * Created on 2017/9/29 16:33
 *
 * @author leiguang
 */
public class PeopleDetails {

    private long  create_time;

    private Object  realtime_people_details;

    private String cgi;

    public String getCgi() {
        return cgi;
    }

    public void setCgi(String cgi) {
        this.cgi = cgi;
    }


    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public Object getRealtime_people_details() {
        return realtime_people_details;
    }

    public void setRealtime_people_details(Object  realtime_people_details) {
        this.realtime_people_details = realtime_people_details;
    }

    public byte[] getRealtimePeopleDetailsForBytes(){
        BLOB blob = (BLOB)realtime_people_details;
        try {
            return blob.getBytes((long)1, (int)blob.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
