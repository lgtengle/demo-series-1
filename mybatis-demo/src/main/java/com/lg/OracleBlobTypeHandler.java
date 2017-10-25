package com.lg;

import oracle.sql.BLOB;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.OutputStream;
import java.sql.*;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/9/29 16:29
 *
 * @author leiguang
 */
@MappedTypes(byte[].class)
public class OracleBlobTypeHandler extends BaseTypeHandler<byte[]> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, byte[] bytes, JdbcType jdbcType) throws SQLException {
        try {
            if (bytes != null) {
                //prepareLob
                BLOB blob = BLOB.createTemporary(preparedStatement.getConnection(), true, BLOB.DURATION_SESSION);

                OutputStream os = blob.getBinaryOutputStream();
                try {
                    os.write(bytes);
                } catch (Exception e) {
                    throw new SQLException(e);
                } finally {
                    try {
                        os.close();
                    } catch (Exception e) {
                        e.printStackTrace();//ignore
                    }
                }
                preparedStatement.setBlob(i, blob);
            } else {
                preparedStatement.setBlob(i, (Blob) null);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /** see getBlobAsBytes method from https://jira.spring.io/secure/attachment/11851/OracleLobHandler.java */
    private byte[] getBlobAsBytes(BLOB blob) throws SQLException {

        //initializeResourcesBeforeRead
        if(!blob.isTemporary()) {
            blob.open(BLOB.MODE_READONLY);
        }

        //read
        byte[] bytes = blob.getBytes(1L, (int)blob.length());

        //releaseResourcesAfterRead
        if(blob.isTemporary()) {
            blob.freeTemporary();
        } else if(blob.isOpen()) {
            blob.close();
        }

        return bytes;
    }

    @Override
    public byte[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        try {
            //use a custom oracle.sql.BLOB
            BLOB blob = (BLOB) resultSet.getBlob(columnName);
            return getBlobAsBytes(blob);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public byte[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        try {
            //use a custom oracle.sql.BLOB
            BLOB blob = (BLOB) resultSet.getBlob(i);
            return getBlobAsBytes(blob);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public byte[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            //use a custom oracle.sql.BLOB
            BLOB blob = (BLOB) callableStatement.getBlob(i);
            return getBlobAsBytes(blob);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
