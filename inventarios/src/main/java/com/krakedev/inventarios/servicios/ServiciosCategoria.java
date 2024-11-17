package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.CategoriaBDD;
import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakedevException;
@Path("categoria")

public class ServiciosCategoria {
	@Path("insertar")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response insertar(Categoria categoria) {
		CategoriaBDD catBDD= new CategoriaBDD();
		try {
			catBDD.insertar(categoria);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("actualizar")
	@PUT
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response actualizar(Categoria categoria) {
		CategoriaBDD catBDD = new CategoriaBDD();

		try {
			catBDD.actualizar(categoria);;
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
