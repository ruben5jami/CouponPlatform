package il.ac.shenkar.model;

/**
 * this class is for a new exception that will occurred if any false URL will be typed
 * @author ruben5jami
 *
 */
public class CouponException extends Exception {
	
	public CouponException(String msg) {
		super(msg);
	}
	
	public CouponException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
