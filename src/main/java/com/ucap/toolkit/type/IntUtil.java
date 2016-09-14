package com.ucap.toolkit.type;


public class IntUtil {

    /**
     * <li>toInt("2") = 2
     * <li>toInt("a") = null
     * <li>toInt(null) = null
     */
    public static Integer toInt(Object o) {
        if ( o == null ) return null;
        try {
            return Integer.parseInt( o.toString() );
        } catch ( NumberFormatException e ) {
        }
        return null;
    }

    /**
     * <li>toInt("2", 1) = 2
     * <li>toInt("a", 1) = 1
     * <li>toInt(null, 1) = 1
     */
    public static int toInt(Object o, int on_failed) {
        Integer v = toInt( o );
        return ( v == null ) ? on_failed : v;
    }

}
