
package il.ac.shenkar.test;

import static org.junit.Assert.*;
import il.ac.shenkar.model.Coupon;
import il.ac.shenkar.model.CouponException;
import il.ac.shenkar.model.MySQLCouponsDAO;

import java.sql.Timestamp;

import org.junit.Test;

/**
 * Junit test for updating a coupon
 * @author ruben5jami
 *
 */
public class UpdateCouponTest {

	@Test
	public void testUpdateCoupon() throws CouponException {
		MySQLCouponsDAO DAOtest = null;
		Coupon coupon = new Coupon(22,"testName","testDescription",new Timestamp(6000),432,555,"home",33.7);
		DAOtest.getInstance().addCoupon(coupon);
		Coupon updateCoupon = DAOtest.getInstance().getCoupon(coupon.getId()); 
		updateCoupon.setName("updatedName");
		updateCoupon.setCategory("health");
		updateCoupon.setDescription("updatedDescription");
		updateCoupon.setExpiryDate(new Timestamp(7000));
		updateCoupon.setLatitude(111);
		updateCoupon.setLongitude(222);
		updateCoupon.setPrice(45);
		int updateCouponId = DAOtest.getInstance().updateCoupon(updateCoupon);
		assertEquals(coupon.getId(), updateCouponId);
	}

}
