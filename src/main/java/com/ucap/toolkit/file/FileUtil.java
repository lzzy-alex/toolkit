package com.ucap.toolkit.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ucap.toolkit.ex.ToolkitException;

public class FileUtil {

    /**
     * copy [file] to [destDir/fname]
     * @throws RuntimeException
     */
    public static void copy(File file, String destDir, String fname) {
        FileOutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream( file );
            File dir = new File( destDir );
            if ( !dir.exists() ) dir.mkdirs();
            out = new FileOutputStream( new File( dir, fname ) );

            byte [] buf = new byte [ 1024 ];
            int len = 0;
            while ( ( len = in.read( buf ) ) != -1 ) {
                out.write( buf, 0, len );
            }
        } catch ( Exception e ) {
            throw new ToolkitException( e );
        } finally {
            try {
                in.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

    }

    /**
     * generate unique filename
     */
    public static synchronized String generateFilename() {
        try {
            Thread.sleep( 10 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmssSSS" );
        return sdf.format( new Date() ) + String.format( "%05d", new Random().nextInt( 99999 ) );
    }

    /**
     * getSuffix("foo.jpg") = jpg
     */
    public static String getSuffix(String fname) {
        if ( fname != null ) {
            int idx = fname.lastIndexOf( "." );
            int len = fname.length();
            return ( idx < 0 ) ? null : fname.substring( idx, len );
        }
        return null;
    }

}
