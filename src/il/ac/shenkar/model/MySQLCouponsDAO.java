package il.ac.shenkar.model;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;













import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * add, delete, show all coupons, show one coupon and update a coupon
 * 
 * @author ruben5jami
 *
 */
public class MySQLCouponsDAO implements ICouponsDAO
{
	static Logger log = Logger.getLogger(MySQLCouponsDAO.class);
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * implemented as a singleton
     */
    private static MySQLCouponsDAO instance;
    private SessionFactory factory;  

    public static MySQLCouponsDAO getInstance()		//implementation of a singleton
    {
        if(instance==null)
        {
            instance = new MySQLCouponsDAO();
        }
        return instance;
    }

    private MySQLCouponsDAO() {}
    
    /**
     * implementation of getting every coupon from the DB
     */
	@Override
	public Collection<Coupon> getCoupons() throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try 
		{
			session.beginTransaction();
			List coupons = session.createQuery("from Coupon").list();
			session.getTransaction().commit();
			log.info("There are " + coupons.size() + " Coupon(s)");
			Iterator i = coupons.iterator();
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
			return coupons; 
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return null;
		}
		finally{
			session.close();
		}
		
    }
	
	/**
	 * implementation of the add, after reciving a coupon from user
	 * adding it to the DB
	 */
	@Override
	public Coupon addCoupon(Coupon coupon) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.save(coupon);
			session.getTransaction().commit();
			log.info(coupon + "Added Sucessfully");
			return coupon;
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return null;
		}
		finally{
			session.close();
		}
	}

	/**
	 * when the administrator presses the delete icon the id is sent here and 
	 * deleted from the DB
	 */
	@Override
	public boolean deleteCoupon(int id) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			Coupon coupon =  (Coupon)session.get(Coupon.class, id); 
			log.info("Coupon number " +id+" Deleted Sucessfully");
			session.delete(coupon);
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return false;
		}
		finally{
			session.close();
		}
	}
	/**
	 * this method is to get one coupon using its ID
	 * this method is not used in this UI but still useful
	 */
	@Override
	public Coupon getCoupon(int id) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try 
		{
			session.beginTransaction();
			Coupon coupon =  (Coupon)session.get(Coupon.class, id);
			session.getTransaction().commit();
			return coupon; 
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return null;
		}
		finally{
			session.close();
		}
	}
	
	/**
	 * after receiving from the administrator the new detail on a coupon and his id
	 * this information will be sent to the DB and change this specific coupon
	 */
	@Override
	public int updateCoupon(Coupon updateCoupon) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try 
		{
			session.beginTransaction();
			
			session.update(updateCoupon);
			log.info("Coupon number "+ updateCoupon.getId()+" Was Changed");
			session.getTransaction().commit();
			return updateCoupon.getId(); 
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return -1;
		}
		finally{
			session.close();
		}
	}
	
	/**
	 * the function to encrypt the password for authentication
	 * @param input
	 * @return
	 */
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	/**
	 * function that receives the user name and password and log in to the user DB
	 * and check if an existing user and password exist 
	 */
	@Override
	public boolean userAuthentication(String user, String password)
			throws CouponException {
        	Connection connection = null;
        	Statement statement = null;
			ResultSet rs = null;
			try{
	        	connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/couponsplatform",
	                    "shenkar",
	                    "shenkar");
	            //add the coupon to the coupons table
	            statement = connection.createStatement();
	            String text = "SELECT name, password FROM users";
	            password = getMD5(password);
	            rs = statement.executeQuery(text);
	            while(rs.next())
	            {
	                if((rs.getString("name").equals(user)) && (rs.getString("password").equals(password))){
	                	return true;
	                }
	            }
	            return false;
	        
			}
			catch(SQLException e)
	        {

	            e.printStackTrace();
	            throw new CouponException("problem with adding a coupon");
	        }
	        finally
	        {
	            if(rs!=null)
	            {
	                try
	                {
	                    rs.close();
	                }
	                catch(SQLException e) {}
	            }
	            if(statement!=null)
	            {
	                try
	                {
	                    statement.close();
	                }
	                catch(SQLException e) {}
	            }
	            if(connection!=null)
	            {
	                try
	                {
	                    connection.close();
	                }
	                catch(SQLException e) {}
	            }
	      }	       
    }
	
	/**
	 * get coupon by category recieves the category and searches for matching coupon category 
	 * in the database
	 */
	@Override
	public Collection<Coupon> getCouponsByCategory(String category) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		try 
		{
			session.beginTransaction();
			List coupons = session.createQuery("from Coupon where category='" + category + "'").list();
			session.getTransaction().commit();
			log.info("There are " + coupons.size() + " Coupon(s) in" + category);
			Iterator i = coupons.iterator();
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
			return coupons; 
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return null;
		}
		finally{
			session.close();
		}
	}
	
	/**
	 * get coupon by location recieves a longitude and latitude calculates a predetermined radius
	 * and searches for close by coupons 
	 */
	@Override
	public Collection<Coupon> getCouponsByLocation(int longitude,
			int latitude) throws CouponException {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		//creating a new session for getting all products
		Session session = factory.openSession();
		int radius = 100;
		int minLongitude = longitude - radius;
		int maxLongitude = longitude + radius;
		int minLatitude = latitude - radius;
		int maxLatitude = latitude + radius;
		try 
		{
			session.beginTransaction();
			List coupons = session.createQuery("from Coupon where longitude between '" + minLongitude + "' and '" + 
					maxLongitude + "' and latitude between '" + 
					minLatitude + "' and '" + maxLatitude + "'").list();
			session.getTransaction().commit();
			log.info("There are " + coupons.size() + " Coupon(s) in this Location "+longitude+"/"+latitude);
			Iterator i = coupons.iterator();
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
			return coupons; 
		}
		catch (HibernateException e){
			if ( session.getTransaction() != null )
				session.getTransaction().rollback();
			e.printStackTrace(); 
			return null;
		}
		finally{
			session.close();
		}
	}
}
	