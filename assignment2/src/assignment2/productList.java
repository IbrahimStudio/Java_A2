package assignment2;

import java.util.List;

public class productList {
	
	
	public List<String> name;
	public List<Double> price;
	public List<String> id;
	public int rows;
	public int index = 0;	
	
	//sizing of arrays without Lists
	/*productList(int rows){
		name = new String[rows];
		price = new double[rows];
		id = new String[rows];
	}*/
	
	public void addProduct(String name, double price, String id) {
		this.name.add(name);	
		this.price.add(price);
		this.id.add(id);
	}
	
	public void removeProduct(String name, double price, String id) {
		int i = 0;
		System.out.println("\nThe lenght of the list is: " + this.name.size());
		while(i < this.name.size()) {
//maybe use contains() -> need to check if the general element of the list is considered an object, check docs
			if(this.name.get(i).compareTo(name) == 0) {
				
			}
		}
	}
	
}
