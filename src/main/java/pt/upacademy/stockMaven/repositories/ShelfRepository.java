package pt.upacademy.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import pt.upacademy.stockMaven.models.Shelf;

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf>{

	@Override
	protected Class<Shelf> getEntityClass() {
		return Shelf.class;
	}
	
	public List<Long> getEmptyShelvesIds() {
		return em.createNamedQuery(Shelf.GET_EMPTY_SHELVES_IDS, Long.class).getResultList();
	}
	
	public List<Long> getShelvesIdByProdId(long pr_id){
		return em.createNamedQuery(Shelf.GET_SHELVESID_BY_PROD_ID, Long.class).setParameter("pr_id", pr_id).getResultList();
	}
	
	public void removeProductFromAllShelvesByProdId(long pr_id){
		em.createNamedQuery(Shelf.REMOVE_PRODUCT_FROM_ALL_SHELFS_BY_PROD_ID).setParameter("pr_id", pr_id).executeUpdate();
	}

	@Override
	protected String getAll() {
		return Shelf.GET_ALL_SHELVES;
	}

	@Override
	protected String getAllIds() {
		return Shelf.GET_ALL_SHELVES_IDS;
	}

	@Override
	protected String removeAll() {
		return Shelf.REMOVE_ALL;
	}
}
