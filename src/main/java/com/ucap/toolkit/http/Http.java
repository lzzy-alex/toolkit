package com.ucap.toolkit.http;

import com.github.kevinsawicki.http.HttpRequest;

/**
 * https://github.com/kevinsawicki/http-request
 */
public class Http {

    /** http status code */
    public static int code(String url) {
        return HttpRequest.get( url ).code();
    }

    /** send http get request */
    public static String get(String url) {
        return HttpRequest.get( url ).body();
    }

    /**
     * <li> get("http://google.com", 'q', "baseball", "size", 100);
     * <li> GET http://google.com?q=baseball&size=100
     */
    public static String get(String url, Object... params) {
        return HttpRequest.get( url, true, params ).body();
    }

}
