package com.http.wchat.exception;

public class DAOException extends Exception {

    public DAOException(String s) {
        super(s);
    }

    public DAOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DAOException(Throwable throwable) {
        super(throwable);
    }

    public DAOException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
