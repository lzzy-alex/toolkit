package com.ucap.toolkit.ex;

@SuppressWarnings("serial")
public class ToolkitException extends RuntimeException {

    public ToolkitException(String message) {
        super( message );
    }

    public ToolkitException(Throwable e) {
        super( e );
    }

}
