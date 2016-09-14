package com.ucap.toolkit.web;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ucap.toolkit.type.BoolUtil;
import com.ucap.toolkit.type.DateUtil;
import com.ucap.toolkit.type.IntUtil;

public class ServletUtil {

    private HttpServletRequest req;
    private HttpServletResponse resp;

    public ServletUtil(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    /** get http servlet request */
    public HttpServletRequest req() {
        return req;
    }

    /** get http servlet response */
    public HttpServletResponse resp() {
        return resp;
    }

    public void setAttr(String k, Object v) {
        req.setAttribute( k, v );
    }

    public Object getAttr(String k) {
        return req.getSession().getAttribute( k );
    }

    /**
     * read an integer from http request
     * @return integer or null
     */
    public Integer getInt(String k) {
        String v = req.getParameter( k );
        return IntUtil.toInt( v );
    }

    /**
     * read an integer from http request
     * @return integer or value on_failed
     */
    public Integer getInt(String k, Integer on_failed) {
        Integer v = getInt( k );
        return v == null ? on_failed : v;
    }

    public String getStr(String k) {
        return req.getParameter( k );
    }

    public String getStr(String k, String on_failed) {
        String v = getStr( k );
        return v == null ? on_failed : v;
    }

    /**
     * <li>toBool("1") == true
     * <li>toBool("true") == true
     * <li> else false
     */
    public boolean getBool(String key) {
        return BoolUtil.toBool( getStr( key ) );
    }

    /**
     * <li>supported format：</li>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy-MM-dd HH:mm:ss</li>
     */
    public Date getDate(String k) {
        String v = getStr( k );
        return DateUtil.parse( v );
    }

    public void writeJson(String jsonStr) {
        try {
            resp.setCharacterEncoding( "utf-8" );
            resp.setContentType( "application/json;charset=utf-8" );
            PrintWriter w = resp.getWriter();
            w.print( jsonStr );
            w.flush();
            w.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void writeText(String plain_text) {
        try {
            resp.setCharacterEncoding( "utf-8" );
            resp.setContentType( "text/html;charset=utf-8" );
            PrintWriter w = resp.getWriter();
            w.print( plain_text );
            w.flush();
            w.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public String realPath() {
        return req.getSession().getServletContext().getRealPath( "/" );
    }

}
