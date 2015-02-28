package il.ac.shenkar.test;

import static org.junit.Assert.*;
import il.ac.shenkar.model.Coupon;
import il.ac.shenkar.model.CouponException;
import il.ac.shenkar.model.MySQLCouponsDAO;

import java.sql.Timestamp;

import org.junit.Test;

/**
 * Junit testing for deleting a coupon
 * @author ruben5jami
 *
 */
public class DeleteCouponTest {

	@Test
	public void testDeleteCoupon() throws CouponException {
		MySQLCouponsDAO DAOtest = null;
		Coupon copuon = new Coupon(22,"testName","testDescription",new Timestamp(60000),432,555,"home",33.7);
		DAOtest.getInstance().addCoupon(copuon);
		assertTrue(DAOtest.getInstance().deleteCoupon(copuon.getId()));
	}

}

