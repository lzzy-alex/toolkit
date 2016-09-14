package com.ucap.toolkit.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    /**
     * <li>isNotEmpty(null) = false
     * <li> isNotEmpty("") = false
     * <li> else = true
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty( o );
    }

    /**
     * <li> isEmpty(null) = true
     * <li> isEmpty("") = true
     * <li>else = false
     */
    public static boolean isEmpty(Object o) {
        return ( o == null || o.toString().length() <= 0 );
    }

    /**
     * <li>toStr(null) = null
     * <li> toStr("foo") = foo
     */
    public static String toStr(Object o) {
        return o == null ? null : o.toString();
    }

    /**
     * <li>toStr(null, "foo") = foo
     * <li> toStr("bar", "foo") = bar
     */
    public static String toStr(Object o, String on_failed) {
        String str = toStr( o );
        return str == null ? on_failed : str;
    }

    /**
     * <li> split("a_b", "_") = (a, b)
     * <li> split("", "_") = emptyList()
     * <li> split(null, "_") = emptyList()
     */
    public static List<String> split(String str, String splitor) {
        if ( isEmpty( str ) ) { return Collections.emptyList(); }
        List<String> sp = new ArrayList<String>();
        for ( String ax : str.split( splitor ) ) {
            if ( isNotEmpty( ax ) ) sp.add( ax );
        }
        return sp;
    }

    /**
     * reverse("foo") = oof
     */
    public static String reverse(String str) {
        StringBuffer ret = new StringBuffer();
        char [] arr = str.toCharArray();
        for ( int i = arr.length - 1; i >= 0; i-- ) {
            ret.append( arr[i] );
        }
        return ret.toString();
    }

}
