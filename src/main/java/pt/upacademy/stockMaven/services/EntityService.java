package pt.upacademy.stockMaven.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import pt.upacademy.stockMaven.models.Entity_;
import pt.upacademy.stockMaven.repositories.EntityRepository;

@Transactional
public abstract class EntityService<R extends EntityRepository<E>, E extends Entity_> {

	@Inject
	protected R repository;
		
	public abstract void removeEntityById(long id);
	public abstract void removeAllEntities();
	
	public E createEntity(E entity) {
		return repository.createEntity(entity);
	}

	public E getEntityById(long id) {
		return repository.getEntityById(id);
	}

	public E editEntity(E entity) {
		return repository.editEntity(entity);
	}

	public List<E> getAllEntities() {
		return repository.getAllEntities();
	}

	public List<Long> getAllEntitiesIds() {
		return repository.getAllEntitiesIds();
	}
}
