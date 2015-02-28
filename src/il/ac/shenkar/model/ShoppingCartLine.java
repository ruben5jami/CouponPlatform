package il.ac.shenkar.model;

/**
 * shopping cart line that includes the coupon and the quantity of a certain coupon
 * @author ruben5jami
 *
 */
public class ShoppingCartLine {
	private Coupon coupon;
	private int quantity;
	
	/**
	 * Shopping Cart Line contractor
	 * @param coupon
	 * @param quantity
	 */
	public ShoppingCartLine(Coupon coupon, int quantity) {
		super();
		this.setCoupon(coupon);
		this.setQuantity(quantity);
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
