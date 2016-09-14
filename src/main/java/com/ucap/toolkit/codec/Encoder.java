package com.ucap.toolkit.codec;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoder {

    /**
     * encode with utf-8
     * @return encoded value or null if something wrong
     */
    public static String encode(final String val) {
        if ( val == null ) { return null; }
        try {
            return URLEncoder.encode( val, "utf-8" );
        } catch ( UnsupportedEncodingException e ) {
        }
        return null;
    }

}
