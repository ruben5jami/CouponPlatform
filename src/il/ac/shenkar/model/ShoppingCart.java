package il.ac.shenkar.model;

import java.util.*;

/**
 * creating the shopping cart for the user
 * @author ruben5jami
 *
 */
public class ShoppingCart {
	private Map<Coupon,ShoppingCartLine> lines = new Hashtable<Coupon,ShoppingCartLine>();
	
	public void addProduct(Coupon coupon) {
		if(lines.containsKey(coupon)) {
			//a shopping cart line for this product already exists
			ShoppingCartLine line = lines.get(coupon);
			line.setQuantity(line.getQuantity()+1);			
		}
		else {
			//we need to create a new shopping cart line
			ShoppingCartLine line = new ShoppingCartLine(coupon, 1);
			lines.put(coupon, line);
		}
			
	}
	
	/**
	 * method that adds up the total cost of the shopping cart
	 * @return
	 */
	public double getTotal() {
		Collection<ShoppingCartLine> shoppingCartLines = this.lines.values();
		double total =0;
		Iterator<ShoppingCartLine> iterator = shoppingCartLines.iterator();
		while(iterator.hasNext()) {
			ShoppingCartLine line = iterator.next();
			total += (line.getQuantity()*line.getCoupon().getPrice());
		}		
		return total;
	}
	
	/**
	 * creates the entire html code to transfer it to the jsp file
	 * @return
	 */
	public String toHTML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table  id='keywords'>");
		sb.append("<thead><tr><th>coupon</th><th>quantity</th><th>price per item</th><th>total</th></tr></thead><tbody>");
		Collection<ShoppingCartLine> shoppingCartLines = this.lines.values();
		double cartTotal = 0;
		Iterator<ShoppingCartLine> iterator = shoppingCartLines.iterator();
		while(iterator.hasNext()) {
			ShoppingCartLine line = iterator.next();
			String coupon = line.getCoupon().getName();
			int quantity = line.getQuantity();
			double price = line.getCoupon().getPrice();
			double total = price * quantity;
			cartTotal += total;
			sb.append("<tr><td>"+ coupon +"</td><td>"+quantity+"</td><td>"+price+"</td><td>"+total+"</td></tr>");
		}
		sb.append("</tbody></table>");
		sb.append("<h3 class='center'>Total:"+cartTotal+"</h3>");
		return sb.toString();
	}
}
