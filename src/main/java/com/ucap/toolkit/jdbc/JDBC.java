package com.ucap.toolkit.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ucap.toolkit.type.StringUtil;

/**
 * usage:
 * <li> JDBC jdbc = new JDBC(driver, url, uname, pwd);
 * <li> jdbc.uniqueObject()/list()/update();
 */
public class JDBC {

    private String driver;
    private String url;
    private String uname;
    private String pwd;

    public JDBC(String driver, String url, String uname, String pwd) {
        this.driver = driver;
        this.url = url;
        this.uname = uname;
        this.pwd = pwd;
    }

    /**
     * usage :
     * <li> String sql = "update tb set f1=? where f2=?";
     * <li> List args = Arrays.asList("v1", "v2");
     * <li> update(sql, args);
     */
    @SuppressWarnings("unchecked")
    public void update(String sql, List args) {
        Connection conn = conn();
        PreparedStatement st = null;
        try {
            int cursor = 1;
            st = conn.prepareStatement( sql );
            for ( Object o : args ) {
                st.setObject( cursor++, o );
            }
            st.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            close( st, conn );
        }
    }

    /**
     * usage :
     * <li> String sql = "select f1, f2 from tb where f3=?";
     * <li> List args = Arrays.asList("v3");
     * <li> int fieldCount = 2; // f1, f2
     * <li> List<Object[]> rec = list(sql, args, fieldCount);
     */
    @SuppressWarnings("unchecked")
    public List<Object []> list(String sql, List args, int fieldCount) {
        Connection conn = conn();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Object []> ret = new ArrayList<Object []>();
        try {
            int cursor = 1;
            st = conn.prepareStatement( sql );
            for ( Object o : args ) {
                st.setObject( cursor++, o );
            }

            rs = st.executeQuery();
            while ( rs.next() ) {
                Object [] row = new Object [ fieldCount ];
                for ( int i = 0; i < fieldCount; i++ ) {
                    row[i] = rs.getObject( i + 1 );
                }
                ret.add( row );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            close( rs, st, conn );
        }
        return ret;
    }

    /**
     * usage :
     * <li> call("sp_name", Arrays.asList("arg1", "arg2"), 2)
     */
    @SuppressWarnings("unchecked")
    public List<Object []> call(String spName, List args, int retCol) {
        Connection conn = conn();
        CallableStatement c = null;
        ResultSet rs = null;
        List<Object []> ret = new ArrayList<Object []>();
        try {
            // { call sp_name ( ?, ?, ?) }
            String nia = StringUtil.dup( "?", ",", args.size() );
            c = conn.prepareCall( "{call " + spName + "(" + nia + ")}" );

            // arg
            int cursor = 1;
            for ( Object o : args ) {
                c.setObject( cursor++, o );
            }

            // query
            rs = c.executeQuery();
            while ( rs.next() ) {
                Object [] row = new Object [ retCol ];
                for ( int i = 0; i < retCol; i++ ) {
                    row[i] = rs.getObject( i + 1 );
                }
                ret.add( row );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            close( rs, c, conn );
        }
        return ret;

    }

    /**
     * usage :
     * <li> String sql = "select f1 from tb where f2=?";
     * <li> List args = Arrays.asList("v2");
     * <li> Object v = uniqueObject(sql, args);
     */
    @SuppressWarnings("unchecked")
    public Object uniqueObject(String sql, List args) {
        Connection conn = conn();
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            int cursor = 1;
            st = conn.prepareStatement( sql );
            for ( Object o : args ) {
                st.setObject( cursor++, o );
            }
            rs = st.executeQuery();
            while ( rs.next() ) {
                return rs.getObject( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            close( rs, st, conn );
        }
        return null;
    }

    private Connection conn() {
        try {
            Class.forName( driver );
            return DriverManager.getConnection( url, uname, pwd );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(ResultSet rs, PreparedStatement st, Connection conn) {
        try {
            if ( rs != null ) rs.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        close( st, conn );
    }

    private void close(PreparedStatement st, Connection conn) {
        try {
            if ( st != null ) st.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        try {
            if ( conn != null ) conn.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
