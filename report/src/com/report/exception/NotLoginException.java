package com.report.exception;
/**
 * 未登陆异常
 * @author hujunhui
 *
 */
public class NotLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8258461804209555461L;

	public NotLoginException(Throwable e) {
        super(e);
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}


