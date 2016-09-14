package com.ucap.toolkit.type;

import java.math.BigDecimal;

public class FloatUtil {

    /**
     * <li>toFloat("1.2", 0f) = 1.2
     * <li>toFloat("foo", 0f) = 0
     */
    public static float toFloat(Object o, float on_failed) {
        try {
            return Float.parseFloat( o.toString() );
        } catch ( Exception e ) {
        }
        return on_failed;
    }

    /**
     * <li>round(0.3456f, 1) = 0.3
     * <li>round(0.3456f, 2) = 0.35
     * <li>round(0.3456f, 3) = 0.346
     * <li>round(0.3456f, 4) = 0.3456
     */
    public static float round(float val, int accuracy) {
        BigDecimal b = new BigDecimal( val );
        return b.setScale( accuracy, BigDecimal.ROUND_HALF_UP ).floatValue();
    }

    public static int multiply(float a, float b) {
        BigDecimal ax = new BigDecimal( a + "" );
        BigDecimal bx = new BigDecimal( b + "" );
        return ax.multiply( bx ).intValue();
    }

}
