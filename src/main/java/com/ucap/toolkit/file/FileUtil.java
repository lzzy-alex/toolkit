package com.ucap.toolkit.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ucap.toolkit.ex.ToolkitException;

public class FileUtil {

    public static void copy(File src, File dest) {

        FileOutputStream out = null;
        FileInputStream in = null;
        try {

            if ( !dest.exists() ) {
                dest = touch( dest );
            }

            in = new FileInputStream( src );
            out = new FileOutputStream( dest );

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

    private static File touch(File dest) throws IOException {
        String p = dest.getCanonicalPath();
        int dx = p.lastIndexOf( File.separator );

        String dir = p.substring( 0, dx );
        String fname = p.substring( dx + 1 );

        File tmp = new File( dir );
        if ( !tmp.exists() ) tmp.mkdirs();

        File ret = new File( tmp, fname );
        return ret;
    }

    /**
     * copy [src] to [destDir/fname]
     * @throws RuntimeException
     */
    public static void copy(File src, String destDir, String fname) {
        File dir = new File( destDir );
        if ( !dir.exists() ) dir.mkdirs();

        File dest = new File( dir, fname );
        copy( src, dest );
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

    /**
     * remove file or directory
     */
    public static void remove(File file) {
        if ( !file.exists() ) return;
        if ( file.isDirectory() ) {
            for ( File f : file.listFiles() ) {
                remove( f );
            }
        }
        file.delete();
    }

}
