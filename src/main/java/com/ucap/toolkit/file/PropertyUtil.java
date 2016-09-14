package com.ucap.toolkit.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.ucap.toolkit.ex.ToolkitException;

/**
 * usage:
 * <li> PropertyUtil p = new PropertyUtil( "/foo.properties" );
 * <li> String name = p.get( "name" );
 */
public class PropertyUtil {

    private Properties p = new Properties();

    public PropertyUtil(String fname) {
        try {
            InputStream in = PropertyUtil.class.getResourceAsStream( fname );// class path
            if ( in == null ) in = new FileInputStream( new File( fname ) );// file system
            p.load( in );
            in.close();
        } catch ( Exception e ) {
            throw new ToolkitException( "file not found : " + fname );
        }
    }

    public String get(String key) {
        return p.getProperty( key );
    }

}
