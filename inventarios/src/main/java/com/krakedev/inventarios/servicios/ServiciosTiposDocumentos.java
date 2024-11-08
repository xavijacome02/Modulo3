package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("tiposdocumento")
public class ServiciosTiposDocumentos {
	@Path("recuperar")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response recuperarTodos() {
		ProveedoresBDD provBDD=new ProveedoresBDD();
		ArrayList<TipoDocumento> arregloTipoDocumento=null;
		try {
			arregloTipoDocumento=provBDD.recuperarTodos();
			 return Response.ok(arregloTipoDocumento).build();

		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();

		}
	}
}
