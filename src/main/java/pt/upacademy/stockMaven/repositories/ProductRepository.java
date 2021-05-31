package pt.upacademy.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import pt.upacademy.stockMaven.models.Product;

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product>{
		
	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	protected String getAll() {
		return Product.GET_ALL_PRODUCTS;
	}

	@Override
	protected String getAllIds() {
		return Product.GET_ALL_PRODUCTS_IDS;
	}

	@Override
	protected String removeAll() {
		return Product.REMOVE_ALL;
	}

	public List<Product> getProductsWithDiscount() {
		return em.createNamedQuery(Product.GET_PRODUCTS_WITH_DISCOUNT, Product.class).getResultList();
	}
	
	public List<Product> getProductsWithPricesBetween(double min, double max){
		return em.createNamedQuery(Product.GET_PRODUCTS_WITH_PRICES_BETWEEN, Product.class).setParameter("min", min).setParameter("max", max).getResultList();
	}
}
