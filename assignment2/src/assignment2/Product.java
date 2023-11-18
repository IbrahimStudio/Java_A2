package assignment2;

import java.io.Serializable;

public /*final*/ class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String name;
	public double price;
	public String id;
	
	Product(String name, double price, String id) {
		this.name = name;
		this.price = price;
		this.id = id;
	}
	
	public String getname() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String id() {
		return this.id;
	}
}
