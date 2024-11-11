package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Pedidos;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(Pedidos pedido) throws KrakedevException{
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet=null;
		//FECHA DEL SISTEMA
		Date fechaActual = new Date();
		java.sql.Date fechaSQL= new java.sql.Date(fechaActual.getTime());
		ResultSet rsClave=null;
		int codigoCabecera=0;
		try {
			con=ConexionBDD.obtenerConexion();//ESA CONSTANTE SIRVE PARA QUE RETORNE LAS CLAVES GENERADS
			ps=con.prepareStatement("insert into cabecera_pedido(proveedor,fecha,estado)values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S"); //SE CREA Y SE PONE DE ESTADO SOLICITADO PORQUE RECIEN ESTA CREADO
			
			ps.executeUpdate();
			
			rsClave= ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera= rsClave.getInt(1);
			}
			ArrayList<DetallePedido> detallesPedidos=pedido.getDetalles();
			DetallePedido det;
			for(int i=0;i<detallesPedidos.size();i++) {
				det=detallesPedidos.get(i);
				psDet=con.prepareStatement("insert into detalle_pedido(cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida) "
						+ "values(?,?,?,?,?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicidad());
				BigDecimal pv= det.getProducto().getPrecioVenta();
				BigDecimal cantidad=new BigDecimal(det.getCantidadSolicidad());
				BigDecimal subTotal=pv.multiply(cantidad);
				psDet.setBigDecimal(4, subTotal);
				psDet.setInt(5, 0);
				
				psDet.executeUpdate();
				
			}
			System.out.println("codigo generado---> "+codigoCabecera);
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al insertar un pedido. Detalle: "+e.getMessage());
		}
	}
}
