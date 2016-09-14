package com.ucap.toolkit.codec;

import java.security.MessageDigest;

import com.ucap.toolkit.ex.ToolkitException;

public class MD5 {

    public static String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            md.update( str.getBytes( "utf-8" ) );
            byte [] arr = md.digest();
            return byte2Hex( arr );
        } catch ( Exception e ) {
            throw new ToolkitException( e );
        }
    }

    private static String byte2Hex(byte [] arr) {
        char [] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char [] tmp = new char [ arr.length * 2 ];

        int pos = 0;
        for ( byte b : arr ) {
            tmp[pos++] = hex[b >>> 4 & 0xf]; // low
            tmp[pos++] = hex[b & 0xf]; // high
        }
        return new String( tmp );
    }

}
