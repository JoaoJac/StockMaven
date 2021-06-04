package pt.upacademy.stockMaven.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.upacademy.stockMaven.models.Product;
import pt.upacademy.stockMaven.models.Shelf;
import pt.upacademy.stockMaven.repositories.ShelfRepository;
import pt.upacademy.stockMaven.services.ShelfService;

@RequestScoped
@Path("shelves")
public class ShelfController extends EntityController<ShelfService, ShelfRepository, Shelf> {
	
	@Inject
	ProductController PC;
	
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
	public Response addProductToShelf(@PathParam("pr_id") long pr_id, @PathParam("sh_id") long sh_id) {
		Product p = PC.getEntityById(pr_id);
		Shelf s = service.getEntityById(sh_id);
		if(p != null && s != null) {
			return Response.ok(service.addProductToShelf(pr_id, sh_id)).build();
		}else {
			ArrayList<String> error = new ArrayList<>();
			if(p == null) error.add("Produto não encontrado!");
			if(s == null) error.add("Shelf não encontrada!");
			return Response.status(406).entity(String.join(" ", error)).build();
		}	
	}

	@Override
	public String validateEntity(Shelf s) {
		ArrayList<String> invalids = new ArrayList<>();
		if(s.getCapacity() != 0 && s.getCapacity() != 1) {
			invalids.add("Capacidade inválida!");
		}
		if(s.getDailyPrice() <= 0) {
			invalids.add("Preço diário de aluguer não pode ser negativo!");
		}
		if(s.getCapacity() == 1 && s.getPr() == null) {
			invalids.add("Uma prateleira ocupada(capacidade = 1) têm de ter um produto associado!");
		}
		if(s.getCapacity() == 0 && s.getPr() != null) {
			invalids.add("Uma prateleira vazia(capacidade = 0) não pode ter um produto associado!");
		}
		if(s.getPr() != null && PC.getEntityById(s.getPr().getId()) == null) {
			invalids.add("Produto não encontrado");
		}
		return String.join(" ", invalids);
	}
}
