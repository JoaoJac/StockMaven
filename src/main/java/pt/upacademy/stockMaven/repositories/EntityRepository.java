package pt.upacademy.stockMaven.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pt.upacademy.stockMaven.models.Entity_;

public abstract class EntityRepository <E extends Entity_>{
	
	@PersistenceContext(unitName = "database")
	protected EntityManager em;
	
	protected abstract Class<E> getEntityClass();
	
	public E createEntity(E entity) {
		entity.setDate_inserted(getCurrentTime());
		return em.merge(entity);
	}

	public E getEntityById(long id) {
		return em.find(getEntityClass(), id);
	}

	public E editEntity(E entity) {
		entity.setDate_inserted(getEntityById(entity.getId()).getDate_inserted());
		entity.setDate_updated(getCurrentTime());
		return em.merge(entity);
	}

	public void removeEntityById(long id) {
		E entity = getEntityById(id);
		if(entity != null) {
			em.remove(entity);
		}
	}
	
	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();	 
	}
	
	protected abstract String getAll();
	public List<E> getAllEntities() {
		return em.createNamedQuery(getAll(), getEntityClass()).getResultList();
	}
	
	protected abstract String getAllIds();
	public List<Long> getAllEntitiesIds() {
		return em.createNamedQuery(getAllIds(), Long.class).getResultList();
	}

	protected abstract String removeAll(); 
	public void removeAllEntities(){
		em.createNamedQuery(removeAll()).executeUpdate();
	}
}
