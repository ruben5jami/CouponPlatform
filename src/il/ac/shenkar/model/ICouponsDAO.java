package il.ac.shenkar.model;

import java.util.Collection;

/**
 * this interface is for the communication with the DB
 * @author ruben5jami
 *
 */
public interface ICouponsDAO {
    public Coupon addCoupon(Coupon coupon) throws CouponException;
    public Collection<Coupon> getCoupons() throws CouponException;
    public Collection<Coupon> getCouponsByCategory(String category) throws CouponException;
    public Collection<Coupon> getCouponsByLocation(int longitude, int latitude) throws CouponException;
    public Coupon getCoupon(int id) throws CouponException;
    public boolean deleteCoupon(int id) throws CouponException;
    public int updateCoupon(Coupon coupon) throws CouponException;
    public boolean userAuthentication(String user, String password) throws CouponException;
}

