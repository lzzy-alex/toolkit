package com.ucap.toolkit.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    /**
     * <li> isEmpty(null) = true
     * <li> isEmpty("") = true
     * <li> else = false
     */
    public static boolean isEmpty(Object o) {
        return ( o == null || o.toString().length() <= 0 );
    }

    /**
     * <li>str(null) = null
     * <li> str("foo") = foo
     */
    public static String str(Object o) {
        return o == null ? null : o.toString();
    }

    /**
     * <li>str(null, "foo") = foo
     * <li> str("bar", "foo") = bar
     */
    public static String str(Object o, String on_failed) {
        String str = str( o );
        return str == null ? on_failed : str;
    }

    /**
     * <li> subStr(null, 2) = null
     * <li> subStr("a", 2) = a
     * <li> subStr("ab", 2) = ab
     * <li> subStr("abc", 2) = ab
     */
    public static String sub(Object o, int len) {
        if ( o == null ) return null;

        String str = str( o );
        if ( len < str.length() ) { return str.substring( 0, len ); }
        return str;
    }

    /**
     * <li> cast("a_b", "_") = (a, b)
     * <li> cast("", "_") = emptyList()
     * <li> cast(null, "_") = emptyList()
     */
    public static List<String> cast(String str, String splitor) {
        if ( isEmpty( str ) ) { return Collections.emptyList(); }
        List<String> sp = new ArrayList<String>();
        for ( String ax : str.split( splitor ) ) {
            if ( !isEmpty( ax ) ) sp.add( ax );
        }
        return sp;
    }

    /**
     * <li> cast( null, ",", "'" ) = null
     * <li> cast( Arrays.asList( "a", "b", "c" ), ",", "'" ) = 'a','b','c'
     */
    @SuppressWarnings("unchecked")
    public static String cast(List list, String splitor, String wrapWith) {
        if ( CollectionUtil.isEmpty( list ) ) return null;
        StringBuffer buf = new StringBuffer();
        for ( Object obj : list )
            buf.append( wrapWith + obj + wrapWith + splitor );
        return buf.substring( 0, buf.length() - 1 );
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

    /**
     * <li> dup( "?", ",", 0 ) =
     * <li> dup( "?", ",", 1 ) = ?
     * <li> dup( "?", ",", 2 ) = ?,?
     */
    public static String dup(String dupStr, String splitor, int num) {
        if ( num <= 0 ) return "";
        StringBuffer buf = new StringBuffer();
        for ( int i = 0; i < num; i++ ) {
            buf.append( dupStr + splitor );
        }
        return buf.substring( 0, buf.length() - 1 );
    }

}
