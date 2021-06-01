package pt.upacademy.stockMaven.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pt.upacademy.stockMaven.models.Product;
import pt.upacademy.stockMaven.repositories.ProductRepository;
import pt.upacademy.stockMaven.services.ProductService;

@RequestScoped
@Path("products")
public class ProductController extends EntityController<ProductService, ProductRepository, Product>{
	
	@GET
	@Path("withDiscount")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsWithDiscount() {
		return service.getProductsWithDiscount();
	}
	
	@GET
	@Path("pricesBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsWithPricesBetween(@QueryParam("min") double min, @QueryParam("max") double max){
		return service.getProductsWithPricesBetween(min, max);
	}
	
	@Override
	public String validateEntity(Product p) {
		ArrayList<String> invalids = new ArrayList<>();
		Integer[] validIvas = {6,13,23};
		if(!Arrays.asList(validIvas).contains(p.getIva())) {
			invalids.add("Iva inválido!");
		}
		if(p.getDiscount()<0 || p.getDiscount()>=100) {
			invalids.add("Desconto inválido!");
		}
		if(p.getPrice()<=0) {
			invalids.add("Preço inválido");
		}
		return String.join(" ", invalids);
	}	
}
