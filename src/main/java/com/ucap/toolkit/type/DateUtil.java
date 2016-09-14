package com.ucap.toolkit.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat fmt1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
    private static SimpleDateFormat fmt2 = new SimpleDateFormat( "yyyy-MM-dd" );

    /**
     * null if format is not on the list :
     * <li>yyyy-MM-dd</li>
     * <li>yyyy-MM-dd HH:mm:ss</li>
     */
    public static Date parse(String v) {
        if ( v != null ) {
            try {
                return fmt1.parse( v );
            } catch ( ParseException e ) {
            }

            try {
                return fmt2.parse( v );
            } catch ( ParseException e ) {
            }
        }
        return null;
    }

    /**
     * pattern like :
     * <li>yyyy-MM-dd
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>or what ever you like
     */
    public static String format(Date date, String pattern) {
        if ( date == null || pattern == null ) { return null; }
        try {
            return new SimpleDateFormat( pattern ).format( date );
        } catch ( Exception e ) {
        }
        return null;
    }

    /**
     * @returns d1 - d2 (ms)
     */
    public static long diff(Date d1, Date d2) {
        return d1.getTime() - d2.getTime();
    }

    public static Calendar calendar() {
        Calendar c = Calendar.getInstance();
        c.setTime( new Date() );
        return c;
    }

}
