package com.ucap.toolkit.type;

public class BoolUtil {

    /**
     * <li>toBool("1") = true
     * <li> toBool("true") = true
     * <li> else = false
     */
    public static boolean toBool(String v) {
        return "1".equals( v ) || "true".equals( v );
    }

}
