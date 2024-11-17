package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductoBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("productos")
public class ServiciosProductos {
	@Path("buscar/{sub}")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("sub") String subcadena) {
		ProductoBDD prodBDD = new ProductoBDD();
		ArrayList<Producto> productos = null;
		try {
			productos = prodBDD.buscar(subcadena);
			return Response.ok(productos).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("crear")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response crear(Producto prod) {
		ProductoBDD prodBDD = new ProductoBDD();

		try {
			prodBDD.crear(prod);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Path("actualizar")
	@PUT
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response actualizar(Producto prod) {
		ProductoBDD prodBDD = new ProductoBDD();

		try {
			prodBDD.actualizar(prod);;
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	
	
}
