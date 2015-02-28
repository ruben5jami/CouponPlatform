package il.ac.shenkar.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import il.ac.shenkar.model.Coupon;
import il.ac.shenkar.model.CouponException;
import il.ac.shenkar.model.MySQLCouponsDAO;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

/**
 * Junit testing for the add coupon method
 * @author ruben5jami
 *
 */
public class AddCouponTest {

	@Test
	public void testAddCoupon() throws CouponException {
		MySQLCouponsDAO DAOtest = null;
		Coupon copuon = new Coupon(22,"testName","testDescription",new Timestamp(60000),432,555,"home",33.7);
		Coupon testCoupon = DAOtest.getInstance().addCoupon(copuon);
		assertNotNull(testCoupon);	
	}

}

