package com.ucap.toolkit.deploy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.ucap.toolkit.file.FileUtil;
import com.ucap.toolkit.file.PropertyUtil;
import com.ucap.toolkit.type.StringUtil;

public class FilePicker {

    private static PropertyUtil pu = new PropertyUtil( "/deploy/config.properties" );
    private static String srcDir = pu.get( "src" );
    private static String destDir = pu.get( "dest" );
    private static String chgDir = pu.get( "changed_list" );

    public static void main(String [] args) throws Exception {
        FileUtil.remove( new File( destDir ) );

        BufferedReader r = new BufferedReader( new InputStreamReader( new FileInputStream( chgDir ), "UTF-8" ) );

        String line = null;
        while ( ( line = r.readLine() ) != null ) {

            // skip annotations
            if ( StringUtil.isEmpty( line ) || line.startsWith( "#" ) ) continue;

            // web resource, etc jsp, js, html
            if ( line.indexOf( "/" ) == 0 ) {
                String fname = line.substring( 1 );
                File src = new File( srcDir + fname );
                File dest = new File( destDir + fname );
                copy( src, dest );
            }

            // java file
            else if ( line.indexOf( ".java" ) > 0 ) {

                int idx = line.lastIndexOf( "/" ) + 1;
                String clzDir = line.substring( 0, idx );
                String clzName = line.substring( idx, line.lastIndexOf( ".java" ) );
                String base = "WEB-INF/classes/";

                // WEB-INF/classes/com/ucap/Foo.class
                String path = base + clzDir + clzName + ".class";
                File src = new File( srcDir, path );
                File dest = new File( destDir, path );
                copy( src, dest );

                // inner class
                int i = 1;
                while ( true ) {
                    path = base + clzDir + clzName + "$" + i + ".class";
                    src = new File( srcDir, path );
                    if ( !src.exists() ) break;

                    dest = new File( destDir, path );
                    copy( src, dest );
                    i++;
                }

            }
        }

        r.close();
    }

    private static void copy(File src, File dest) {
        if ( src.exists() && src.isFile() ) {
            FileUtil.copy( src, dest );
            System.out.println( "File:" + src.getAbsolutePath() + " -> " + dest.getAbsolutePath() );
        }
    }

}
