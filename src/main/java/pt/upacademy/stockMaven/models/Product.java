package pt.upacademy.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = Product.GET_ALL_PRODUCTS, query = "SELECT p FROM Product p"),
	@NamedQuery(name = Product.GET_ALL_PRODUCTS_IDS, query = "SELECT p.id FROM Product p"),
	@NamedQuery(name = Product.GET_PRODUCTS_WITH_DISCOUNT, query = "SELECT p FROM Product p WHERE p.discount > 0"),
	@NamedQuery(name = Product.GET_PRODUCTS_WITH_PRICES_BETWEEN, query = "SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max"),
	@NamedQuery(name = Product.REMOVE_ALL, query = "DELETE FROM Product")
})
public class Product extends Entity_{
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_PRODUCTS = "getAllProducts";
	public static final String GET_ALL_PRODUCTS_IDS = "getAllProductsIds";
	public static final String GET_PRODUCTS_WITH_DISCOUNT = "getProductsWithDiscount";
	public static final String GET_PRODUCTS_WITH_PRICES_BETWEEN = "getProductsWithPricesBetween";
	public static final String REMOVE_ALL = "removeAllProducts";
	
	private int discount;
	private int iva;
	private double price;
	
	public Product() {
		
	}
	
	public Product(int discount, int iva, double price) {
		this.discount = discount;
		this.iva = iva;
		this.price = price;
	}

	public Product(int discount, double price, int iva) {
		this.discount = discount;
		this.iva = iva;
		this.price = price;
	}
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [ID="+this.getId()+", discount=" + discount + "%" +", iva=" + iva + "%" + ", price=" + price +"€"+"]";
	}		
}
