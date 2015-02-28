
package il.ac.shenkar.test;

import static org.junit.Assert.*;
import il.ac.shenkar.model.Coupon;
import il.ac.shenkar.model.CouponException;
import il.ac.shenkar.model.MySQLCouponsDAO;

import java.sql.Timestamp;

import org.junit.Test;

/**
 * Junit testing for getting a single coupon
 * @author ruben5jami
 *
 */
public class GetCouponTest {

	@Test
	public void testGetCoupon() throws CouponException {
		MySQLCouponsDAO DAOtest = null;
		Coupon copuon = new Coupon(22,"testName","testDescription",new Timestamp(6000),432,555,"home",33.7);
		Coupon addedCoupon = DAOtest.getInstance().addCoupon(copuon);
		assertNotNull(DAOtest.getInstance().getCoupon(addedCoupon.getId()));
	}
}

