package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentaBDD;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevException;
@Path("ventas")
public class ServiciosVenta {
	@Path("guardar")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response insertar(Venta venta) {
		VentaBDD ventasBDD= new VentaBDD();
		try {
			ventasBDD.insertar(venta);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
