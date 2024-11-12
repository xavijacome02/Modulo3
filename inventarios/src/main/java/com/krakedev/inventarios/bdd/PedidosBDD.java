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
	public void insertar(Pedidos pedido) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		// FECHA DEL SISTEMA
		Date fechaActual = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		try {
			con = ConexionBDD.obtenerConexion();// ESA CONSTANTE SIRVE PARA QUE RETORNE LAS CLAVES GENERADS
			ps = con.prepareStatement("insert into cabecera_pedido(proveedor,fecha,estado)values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S"); // SE CREA Y SE PONE DE ESTADO SOLICITADO PORQUE RECIEN ESTA CREADO

			ps.executeUpdate();

			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			ArrayList<DetallePedido> detallesPedidos = pedido.getDetalles();
			DetallePedido det;
			for (int i = 0; i < detallesPedidos.size(); i++) {
				det = detallesPedidos.get(i);
				psDet = con.prepareStatement(
						"insert into detalle_pedido(cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida) "
								+ "values(?,?,?,?,?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicidad());
				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getCantidadSolicidad());
				BigDecimal subTotal = pv.multiply(cantidad);
				psDet.setBigDecimal(4, subTotal);
				psDet.setInt(5, 0);

				psDet.executeUpdate();

			}
			System.out.println("codigo generado---> " + codigoCabecera);

		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al insertar un pedido. Detalle: " + e.getMessage());
		}
	}

	public void recibir(Pedidos pedido) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		DetallePedido det = null;
		ResultSet rs = null;
		PreparedStatement psHis=null;
		
		Date fechaActual = new Date();
		java.sql.Timestamp finsertechaHoraActual = new java.sql.Timestamp(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("update cabecera_pedido set estado=? where numero=?");
			ps.setString(1, "R");
			ps.setInt(2, pedido.getCodigo());
			ps.executeUpdate();
			
			
			
			ArrayList<DetallePedido> detallesPedidos = pedido.getDetalles();

			for (int i = 0; i < detallesPedidos.size(); i++) {
				det = detallesPedidos.get(i);

				psDet = con.prepareStatement("select cantidad_solicitada from detalle_pedido where codigo=?");
				psDet.setInt(1, pedido.getCodigo());
				rs = psDet.executeQuery();
				BigDecimal cantidadSolicitada = null;
				if (rs.next()) {
					cantidadSolicitada = rs.getBigDecimal("cantidad_solicitada");
				}

				psDet = con.prepareStatement(
						"update detalle_pedido set cantidad_recibida=?, subtotal=? " + "where codigo=?");
				psDet.setInt(1, det.getCantidadRecibida());

				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal subTotal = pv.multiply(cantidadSolicitada);
				psDet.setBigDecimal(2, subTotal);
				psDet.setInt(3, det.getCodigo());
				psDet.executeUpdate();
				System.out.println("cantidad recibe" + pv);
				System.out.println("cantidad solicitada" + cantidadSolicitada);
				System.out.println("subtotal" + subTotal);
				
			}
			psHis=con.prepareStatement("insert into historial_stock(fecha,referencia,producto,cantidad) values(?,?,?,?)");
			psHis.setTimestamp(1, finsertechaHoraActual);
			psHis.setString(2, "PEDIDO "+ pedido.getCodigo());
			psHis.setInt(3,det.getCodigo() );
			psHis.setInt(4,det.getCantidadRecibida());
			psHis.executeUpdate();

		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			throw new KrakedevException("Error al actualizar el estado del pedido. Detalle: " + e.getMessage());
		}

	}
	
	
}
