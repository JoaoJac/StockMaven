package pt.upacademy.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = Shelf.GET_ALL_SHELVES, query = "SELECT s FROM Shelf s"),
	@NamedQuery(name = Shelf.GET_ALL_SHELVES_IDS, query = "SELECT s.id FROM Shelf s"),
	@NamedQuery(name = Shelf.GET_EMPTY_SHELVES_IDS, query = "SELECT s.id FROM Shelf s WHERE s.pr = null"),
	@NamedQuery(name = Shelf.GET_SHELVESID_BY_PROD_ID, query = "SELECT s.id FROM Shelf s WHERE s.pr.id = :pr_id"),
	@NamedQuery(name = Shelf.REMOVE_PRODUCT_FROM_ALL_SHELFS_BY_PROD_ID, query = "UPDATE Shelf s SET s.pr = null, s.capacity = 0 WHERE s.pr.id = :pr_id"),
	@NamedQuery(name = Shelf.REMOVE_ALL, query = "DELETE FROM Shelf")
})
public class Shelf extends Entity_{
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_SHELVES = "getAllShelves";
	public static final String GET_ALL_SHELVES_IDS = "getAllShelvesIds";
	public static final String GET_EMPTY_SHELVES_IDS = "getEmptyShelvesIds";
	public static final String GET_SHELVESID_BY_PROD_ID = "getShelvesIdByProdId";
	public static final String REMOVE_PRODUCT_FROM_ALL_SHELFS_BY_PROD_ID = "removeProductFromAllShelvesByProdId";
	public static final String REMOVE_ALL = "removeAllShelves";
	
	private int capacity;
	@ManyToOne
	private Product pr;
	private double dailyPrice;
	
	public Shelf() {
		
	}

	public Shelf(int capacity, double dailyPrice) {
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}
	
	public Shelf(int capacity, Product pr, double dailyPrice) {
		this.capacity = capacity;
		this.pr = pr;
		this.dailyPrice = dailyPrice;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Product getPr() {
		return pr;
	}
	public void setPr(Product pr) {
		this.pr = pr;
	}
	public double getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	@Override
	public String toString() {
		return "Shelf [ID="+this.getId()+", capacity=" + capacity + ", " + pr + ", dailyPrice=" + dailyPrice + "€" + "]";
	}
}
