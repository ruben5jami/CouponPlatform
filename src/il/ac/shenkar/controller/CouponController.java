package il.ac.shenkar.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import il.ac.shenkar.model.Coupon;
import il.ac.shenkar.model.CouponException;
import il.ac.shenkar.model.MySQLCouponsDAO;
import il.ac.shenkar.model.ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CouponController
 * defining a string array with all the categories
 */
@WebServlet("/controller/*")
public class CouponController extends HttpServlet {
	static Logger log = Logger.getLogger(CouponController.class);
	private static final long serialVersionUID = 1L;
	private static final String[] categories = {"Home", "Electronics", "Health", "Sports", "Food", "Vacation"};
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    	getServletContext().setAttribute("categories", categories);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * defining every URL typed by the user or admin and dispatching to the appropiate place
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		PrintWriter out = response.getWriter();
		
		
		/**
		 * See all Coupons Method	Administrator Menu
		 */
		if(path.endsWith("coupons")) {	
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
			try {
				List<Coupon> list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCoupons();	//import list to check expiry date
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getExpiryDate().before(new Date())){		//check if coupon has expired
						MySQLCouponsDAO.getInstance().deleteCoupon(list.get(i).getId());
					}
				}
				log.info("Admin Entered All Coupon");
				list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCoupons();	//updated list
				request.setAttribute("coupons", list);
				dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				dispatcher.include(request, response);
			}
			catch (CouponException e) {
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.include(request, response);
				}	
			} 
		}
		
		/**
		 * Add Coupon Web Page dispatcher
		 */
		else if(path.endsWith("addcoupon")) {
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
				dispatcher = getServletContext().getRequestDispatcher("/addcoupon.jsp");
				request.setAttribute("dateFormat", true);
				dispatcher.include(request, response);
			}
		}
		
		/**
		 * sign outusing invalidate
		 */
		else if(path.endsWith("signout")) {
				request.getSession().invalidate();
				log.info("Admin Loged Out");
				dispatcher = getServletContext().getRequestDispatcher("/signin.jsp");
				dispatcher.include(request, response);
		}
		
		
		else if(path.endsWith("payment")) {
			log.info("Client Entered To Pay");
			dispatcher = getServletContext().getRequestDispatcher("/payment.jsp");
			dispatcher.include(request, response);
	}
		
		/**
		 * search by category using a hql query
		 */
		else if (path.contains("couponbycategory")) {
			try {
				String category = request.getParameter("coupon_category");
				List<Coupon> list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCouponsByCategory(category);	
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getExpiryDate().before(new Date())){		//check if coupon has expired
						MySQLCouponsDAO.getInstance().deleteCoupon(list.get(i).getId());
					}
				}
				request.setAttribute("couponscategory", list);
				dispatcher = getServletContext().getRequestDispatcher("/couponscategory.jsp");
				dispatcher.include(request, response);
			}
			catch (CouponException e) {
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.include(request, response);
				}	
			} 

		/**
		 * search by location using a hql query
		 */
		else if (path.contains("couponbylocation")) {
			try {
				int longitude = Integer.parseInt(request.getParameter("longitude"));
				int laitutde = Integer.parseInt(request.getParameter("latitude"));
				List<Coupon> list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCouponsByLocation(longitude, laitutde);	
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getExpiryDate().before(new Date())){		//check if coupon has expired
						MySQLCouponsDAO.getInstance().deleteCoupon(list.get(i).getId());
					}
				}
				request.setAttribute("couponslocation", list);
				dispatcher = getServletContext().getRequestDispatcher("/couponslocation.jsp");
				dispatcher.include(request, response);
			}
			catch (CouponException e) {
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.include(request, response);
				}	
			} 
			

		
		/**
		 * sign in web page when the controller is first turned on
		 */
		else if(path.endsWith("signin")||path.endsWith("*")){
			dispatcher = getServletContext().getRequestDispatcher("/signin.jsp");
			dispatcher.include(request, response);
		}
		
		/**
		 * the main menu for the simple user
		 */
		else if(path.endsWith("index")){
			log.info("User "+request.getSession().getId() + " Entered");
			dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}
		
		/**
		 * send to the shopping cart
		 */
		else if(path.endsWith("shoppingcart")){
			log.info("User Is In Shopping Cart");
			dispatcher = getServletContext().getRequestDispatcher("/shoppingcart.jsp");
			dispatcher.include(request, response);
		}
		
		/**
		 * dispatches to the jsp file for the admin to update a certain coupon
		 */
		else if(path.endsWith("updatecoupon")){
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
				String id = request.getParameter("id");
				Coupon coupon = null;
				try {
					coupon = MySQLCouponsDAO.getInstance().getCoupon(Integer.parseInt(id));
					request.setAttribute("coupon", coupon);
					dispatcher = getServletContext().getRequestDispatcher("/updatecoupon.jsp");
					request.setAttribute("dateFormatUpdate", true);
					dispatcher.include(request, response);
				} catch (NumberFormatException | CouponException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		/**
		 * after the user press submit when adding a coupon 
		 * here is where it will add it to the DB 
		 */
		else if (path.contains("insert")) {
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
				try {
					int couponId = 5; //mock id, the real id comes from the generator
					String couponName = request.getParameter("coupon_name");
					String couponDescription = request.getParameter("coupon_description");
					String couponExpiryDate = request.getParameter("coupon_expiry_date");
					String[] parts = couponExpiryDate.split("/");
					couponExpiryDate = "";
					couponExpiryDate = parts[2] + "-" + parts[1] + "-" + parts[0] + " 00:00:00.0";
					int couponlongitude = Integer.parseInt(request.getParameter("coupon_longitude"));
					int couponlatitude = Integer.parseInt(request.getParameter("coupon_latitude"));
					String couponCategory = request.getParameter("coupon_category");
					double couponPrice = Double.parseDouble(request.getParameter("coupon_price"));
					Timestamp timestamp = null;
					log.info("admin has added a coupon");
					try{ 
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					    Date parsedDate = dateFormat.parse(couponExpiryDate);
					    timestamp = new java.sql.Timestamp(parsedDate.getTime());
					}catch(Exception e){//this generic but you scan control another types of exception
						dispatcher = getServletContext().getRequestDispatcher("/addcoupon.jsp");
						request.setAttribute("dateFormat", false);
						dispatcher.include(request, response);
						return;
					} 
					if (couponName.trim() != "") {
						Coupon coupon = new Coupon(couponId, couponName, couponDescription, timestamp, couponlongitude, couponlatitude,couponCategory, couponPrice);
						MySQLCouponsDAO.getInstance().addCoupon(coupon); //add the coupon to the data base
						dispatcher = getServletContext().getRequestDispatcher("/added.jsp");
						dispatcher.include(request, response);
					}
					else {
						request.setAttribute("exception", new CouponException("you must enter coupon name",new Exception()));
						dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
						dispatcher.include(request, response);
					}
				}
				catch (CouponException e) {
					request.setAttribute("exception", e);
					dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.include(request, response);
				}	
			}
		}
		
		
		/**
		 * when the admin will press on the delete button this is where 
		 * it will be deleted from the DB
		 */
		else if (path.contains("delete")) {
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
				try {
					String sCouponId = request.getParameter("coupon_id");
					if (sCouponId.trim() != "") {
						int couponId = Integer.parseInt(sCouponId);
						MySQLCouponsDAO.getInstance().deleteCoupon(couponId);
						dispatcher = getServletContext().getRequestDispatcher("/deleted.jsp");
						dispatcher.include(request, response);
						log.info("admin has deleted a coupon");
					}
					else {
						request.setAttribute("exception", new CouponException("you must enter coupon id",new Exception()));
						dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
						dispatcher.include(request, response);
					}
				}			
				catch (CouponException e) {
					request.setAttribute("exception", e);
					dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.include(request, response);
				}
			}
		}
		
		/**
		 * when administrator type on the submit button in the update coupon jsp file 
		 * this is where it will connect to the DB and update that 
		 * specific coupon 
		 */
		else if (path.contains("update")) {
			String user = (String)request.getSession().getAttribute("username");
			if(user==null){
				response.sendRedirect("signin");
				return;
			}else{
				try {
					String couponId = request.getParameter("coupon_id");
					String couponName = request.getParameter("coupon_name");
					if (couponName.trim() != "") {
						String couponDescription = request.getParameter("coupon_description");
						String couponExpiryDate = request.getParameter("coupon_expiry_date");
						int couponlongitude = Integer.parseInt(request.getParameter("coupon_longitude"));
						int couponlatitude = Integer.parseInt(request.getParameter("coupon_latitude"));
						String couponCategory = request.getParameter("coupon_category");
						double couponPrice = Double.parseDouble(request.getParameter("coupon_price"));
						Timestamp timestamp = null;
						try{ 
						    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
						    Date parsedDate = dateFormat.parse(couponExpiryDate);
						    timestamp = new java.sql.Timestamp(parsedDate.getTime());
						}catch(Exception e){//this generic but you can control another types of exception
							dispatcher = getServletContext().getRequestDispatcher("/updatecoupon.jsp");
							request.setAttribute("dateFormatUpdate", false);
							dispatcher.include(request, response);
							return;
						}
						Coupon updateCoupon = MySQLCouponsDAO.getInstance().getCoupon(Integer.parseInt(couponId));
						updateCoupon.setName(couponName);
						updateCoupon.setDescription(couponDescription);
						updateCoupon.setLongitude(couponlongitude);
						updateCoupon.setLatitude(couponlatitude);
						updateCoupon.setExpiryDate(timestamp);
						updateCoupon.setPrice(couponPrice);
						updateCoupon.setCategory(couponCategory);
						MySQLCouponsDAO.getInstance().updateCoupon(updateCoupon);
						dispatcher = getServletContext().getRequestDispatcher("/updated.jsp");
						dispatcher.include(request, response);
						log.info("admin has updated a coupon");
					}
					else {
						request.setAttribute("exception", new CouponException("you must enter the mandatory fields",new Exception()));
						dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
						dispatcher.include(request, response);
					}
				}
				catch (CouponException e) {
					request.setAttribute("exception", e);
					dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.include(request, response);
				}	
			}
		}
		/**
		 * when user press the add to cart icon it will be added to his current shopping cart
		 * and will sent him to see his cart
		 */
		else if (path.contains("addtocart")) {
			try {
				String couponId = request.getParameter("id");
				HttpSession session = request.getSession();
				// we first need to verify that a shopping cart already exists
				// on the session
				if(session.getAttribute("cart")==null) {
					session.setAttribute("cart", new ShoppingCart());
				}
				if (couponId != null) {
					// getting the coupon object
					Coupon coupon = MySQLCouponsDAO.getInstance().getCoupon(Integer.parseInt(couponId));
					// adding product to shopping cart
					ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
					cart.addProduct(coupon);
				}
				dispatcher = getServletContext().getRequestDispatcher("/shoppingcart.jsp");
				dispatcher.include(request, response);
				
			}			
			catch (CouponException e) {
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.include(request, response);
			}	
		}
		
		/**
		 * if the user continue as a user and not an administrator he will arrive 
		 * to his UI here
		 */
		else if (path.endsWith("couponsuser")) {
			try {
				List<Coupon> list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCoupons();	
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getExpiryDate().before(new Date())){		//check if coupon has expired
						MySQLCouponsDAO.getInstance().deleteCoupon(list.get(i).getId());
					}
				}
				log.info("user has entered all coupons");
				list = (List<Coupon>)MySQLCouponsDAO.getInstance().getCoupons();
				request.setAttribute("couponuser", list);
				dispatcher = getServletContext().getRequestDispatcher("/couponsuser.jsp");
				dispatcher.include(request, response);
			}
			catch (CouponException e) {
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.include(request, response);
			}	
		}
		
		
		
		
		/**
		 * if any error occur during the user or admin navigation 
		 * through the pages he will end up in this error page with the explanation why
		 */
		else {
			request.setAttribute("exception", new CouponException("request path is not supported",new Exception()));
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.include(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * this is where the password will be sent for authentication
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		if(path.endsWith("login")){
			String username = request.getParameter("name");
			String password = request.getParameter("password");
			
			try {
				if (MySQLCouponsDAO.getInstance().userAuthentication(username, password)) {
					request.getSession().setAttribute("username", username);
					log.info("Admin "+username+" Has Loged In");
					response.sendRedirect("coupons");
				}
				else {
					response.sendRedirect("signin");
				}
			} catch (CouponException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
