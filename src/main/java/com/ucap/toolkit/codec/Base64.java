package com.ucap.toolkit.codec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.ucap.toolkit.ex.ToolkitException;

public class Base64 {

    public static String encode(String str) {
        BASE64Encoder en = new BASE64Encoder();
        return en.encode( str.getBytes() );
    }

    public static String decode(String str) {
        try {
            BASE64Decoder de = new BASE64Decoder();
            byte [] bt = de.decodeBuffer( str );
            return new String( bt );
        } catch ( Exception e ) {
            throw new ToolkitException( e );
        }
    }

}
