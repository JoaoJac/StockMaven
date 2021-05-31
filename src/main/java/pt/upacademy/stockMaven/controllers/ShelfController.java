package pt.upacademy.stockMaven.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.upacademy.stockMaven.models.Shelf;
import pt.upacademy.stockMaven.repositories.ShelfRepository;
import pt.upacademy.stockMaven.services.ShelfService;

@RequestScoped
@Path("shelves")
public class ShelfController extends EntityController<ShelfService, ShelfRepository, Shelf> {

	@GET
	@Path("emptyShelves")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getEmptyShelvesIds() {
		return service.getEmptyShelvesIds();
	}
	
	@GET
	@Path("byProdId/{pr_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getShelvesIdByProdId(@PathParam("pr_id") long pr_id) {
		return service.getShelvesIdByProdId(pr_id);
	}
	
	@PUT
	@Path("addProduct/{pr_id}/toShelf/{sh_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shelf addProductToShelf(@PathParam("pr_id") long pr_id, @PathParam("sh_id") long sh_id) {
		return service.addProductToShelf(pr_id, sh_id);
	}

	@Override
	public String validateEntity(Shelf s) {
		ArrayList<String> invalids = new ArrayList<>();
		if(s.getCapacity() == 0 && s.getPr() != null) {
			invalids.add("Uma prateleira vazia(capacidade = 0) não pode ter um produto associado!");
		}
		if(s.getCapacity() == 1 && s.getPr() == null) {
			invalids.add("Uma prateleira ocupada(capacidade = 1) têm de ter um produto associado!");
		}
		return String.join(" ", invalids);
	}
}
