package pt.upacademy.stockMaven.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pt.upacademy.stockMaven.models.Product;
import pt.upacademy.stockMaven.models.Shelf;
import pt.upacademy.stockMaven.repositories.ShelfRepository;

@RequestScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {
	
	@Inject
	ProductService PS;
	
	public List<Long> getEmptyShelvesIds() {
		return repository.getEmptyShelvesIds();
	}
	
	public List<Long> getShelvesIdByProdId(long pr_id) {
		return repository.getShelvesIdByProdId(pr_id);
	}

	public Shelf addProductToShelf(long pr_id, long sh_id) {
		Product prEntity = PS.getEntityById(pr_id);
		Shelf shEntity = repository.getEntityById(sh_id);
		shEntity.setPr(prEntity);
		shEntity.setCapacity(1);
		return shEntity;
	}
	
	public void removeProductFromAllShelvesByProdId(long pr_id){
		repository.removeProductFromAllShelvesByProdId(pr_id);
	}
	
	@Override
	public void removeEntityById(long id) {
		repository.removeEntityById(id);	
	}
}