package pt.upacademy.stockMaven.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.upacademy.stockMaven.models.Entity_;
import pt.upacademy.stockMaven.repositories.EntityRepository;
import pt.upacademy.stockMaven.services.EntityService;

public abstract class EntityController <S extends EntityService<R,E>, R extends EntityRepository<E>, E extends Entity_> {
	
	@Inject
	protected S service;
	
	public abstract String validateEntity(E e);
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> getAllEntities() {
		return service.getAllEntities();
	}
	
	@GET
	@Path("getAllIds")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getAllEntitiesIds() {
		return service.getAllEntitiesIds();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEntity(E entity) {
		String error = validateEntity(entity);
		if(error.equals("")) {
			return Response.ok(service.createEntity(entity)).build();
		}else {
			return Response.status(422).entity(error).build();
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public E getEntityById(@PathParam("id") long id) {
		return service.getEntityById(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editEntity(@PathParam("id") long id, E newEntity) {
		if(service.getEntityById(id) == null) { 
			return Response.status(406).entity("Entidade não encontrada!").build();
		}
		String error = validateEntity(newEntity);
		if(error.equals("")) {
			newEntity.setId(service.getEntityById(id).getId());
			service.editEntity(newEntity);
			return Response.ok("Entidade actualizada!").build();
		}else {
			return Response.status(422).entity(error).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response removeEntityById(@PathParam("id") long id) {
		if(service.getEntityById(id) != null) {
			service.removeEntityById(id);
			return Response.ok("Entidade apagada!").build();
		}else {
			return Response.status(406).entity("Entidade não encontrada!").build();
		}
	}
	
	@DELETE
	public String removeAllEntities() {
		service.removeAllEntities();
		return "Todas as entidades apagadas!";
	}
}
