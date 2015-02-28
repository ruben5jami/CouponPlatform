package il.ac.shenkar.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import il.ac.shenkar.controller.CouponController;

import java.sql.Timestamp;

/**
 * this class contains all of the information about a specific coupon
 * those are excaclty the columns in the data base
 * @author ruben5jami
 *
 */
public class Coupon {

	/**
	 * the id is the primary key and will not be showed in the user UI
	 */
	private int id;
	private String name;
	private String description;
	/**
	 * the coupon with an expiry date smaller than the current date
	 * will be automaticely removed from the list of all coupons
	 */
	private Timestamp expiryDate;
	/**
	 * coordinates is for the user UI that will show him close by deals
	 */
	private int longitude;
	private int latitude;
	private String category;
	private double price;	


	public Coupon(){		//default c'tor to communicate with hibernate
		super();
	}
	/**
	 * the Coupon Class Constructor
	 * @param id
	 * @param name
	 * @param description
	 * @param expiryDate
	 * @param longitude
	 * @param latitude
	 * @param category
	 * @param price
	 */
	public Coupon(int id, String name, String description, Timestamp expiryDate,
			int latitude, int longitude, String category,  double price) {
		super();
		setId(id);
		setName(name);
		setDescription(description);
		setExpiryDate(expiryDate);
		setLatitude(latitude);
		setLongitude(longitude);
		setCategory(category);
		setPrice(price);	
	}

	public int getId() {
		return id;
	}
	/**
	 * the set id method will check that the id is not negative
	 * @param id
	 */
	public void setId(int id) {
		if(id>0){
			this.id = id;
		}
		else {
			return;	//not in the int range 
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	public int getLongitude() {
		return longitude;
	}
	
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	public int getLatitude() {
		return latitude;
	}
	
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}


	public double getPrice() {
		return price;
	}
	/**
	 * the set price method will check that the price is not negative
	 * @param price
	 */
	public void setPrice(double price) {
		if(price>0){
			this.price = price;
		}
		else{
			return;	//if the price is negative
		}
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * to string for log4j purposes
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", description="
				+ description + ", expiryDate=" + expiryDate + ", longitude="
				+ longitude + ", latitude=" + latitude + ", category="
				+ category + ", price=" + price + "]";
	}
	
	/**
	 * hascode for the shopping cart
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + id;
		result = prime * result + latitude;
		result = prime * result + longitude;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id != other.id)
			return false;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
