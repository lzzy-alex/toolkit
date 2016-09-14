package com.ucap.toolkit.file;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ucap.toolkit.ex.ToolkitException;

public class ImageUtil {

    /**
     * <li>src : /opt/foo.jpg
     * <li>dest : /opt/thumbnail_foo.jpg
     */
    public static void thumbnail(String dir, String fname) {
        try {
            ImageIcon icon = new ImageIcon( new File( dir, fname ).getCanonicalPath() );
            int w = 200;
            int h = ( w * icon.getIconHeight() ) / icon.getIconWidth();

            BufferedImage img = new BufferedImage( w, h, BufferedImage.TYPE_INT_RGB );
            Graphics g = img.getGraphics();
            g.drawImage( icon.getImage(), 0, 0, w, h, null );
            ImageIO.write( img, "jpeg", new FileOutputStream( new File( dir, "thumbnail_" + fname ) ) );

        } catch ( Exception e ) {
            throw new ToolkitException( e );
        }
    }

    /**
     * we check suffix name only
     */
    public static boolean isImage(String fname) {
        if ( fname == null ) return false;
        int len = fname.length();
        int pos = fname.lastIndexOf( "." );
        if ( pos == -1 || pos >= len ) return false;
        String suffix = fname.substring( pos + 1, len );
        return Pattern.matches( "jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF", suffix );
    }
}
