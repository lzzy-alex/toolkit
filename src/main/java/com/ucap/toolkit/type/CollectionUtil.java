package com.ucap.toolkit.type;

import java.util.Collection;

public class CollectionUtil {

    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Collection c) {
        return null == c || c.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(Collection c) {
        return !isEmpty( c );
    }

}
