package pt.upacademy.stockMaven.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pt.upacademy.stockMaven.models.Product;
import pt.upacademy.stockMaven.repositories.ProductRepository;

@RequestScoped
public class ProductService extends EntityService<ProductRepository,Product>{
	
	@Inject
	ShelfService SS;
	
	public List<Product> getProductsWithDiscount() {
		return repository.getProductsWithDiscount();
	}
	
	public List<Product> getProductsWithPricesBetween(double min, double max){
		return repository.getProductsWithPricesBetween(min, max);
	}

	@Override
	public void removeEntityById(long id) {
		SS.removeProductFromAllShelvesByProdId(id);
		repository.removeEntityById(id);
	}
	
	@Override
	public void removeAllEntities() {
		SS.removeAllProductsFromShelves();
		repository.removeAllEntities();
	}
}


