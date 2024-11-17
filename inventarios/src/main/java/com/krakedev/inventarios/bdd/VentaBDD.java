package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentaBDD {
	
		public void insertar(Venta venta) throws KrakedevException {
			Connection con = null;
			PreparedStatement ps = null;
			PreparedStatement psDet = null;
			PreparedStatement psHis = null;
			// FECHA DEL SISTEMA
			Date fechaActual = new Date();
			java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
			ResultSet rsClave = null;
			int codigoCabecera = 0;
			try {
				con = ConexionBDD.obtenerConexion();// ESA CONSTANTE SIRVE PARA QUE RETORNE LAS CLAVES GENERADS
				ps = con.prepareStatement(
						"insert into cabecera_venta(fecha,total_sin_iva,iva,total_con_iva)values(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, fechaSQL);
				ps.setBigDecimal(2, venta.getTotalSinIva());
				ps.setInt(3, venta.getIva()); // SE CREA Y SE PONE DE ESTADO SOLICITADO PORQUE RECIEN ESTA CREADO
				ps.setBigDecimal(4, venta.getTotalConIva());
				ps.executeUpdate();

				rsClave = ps.getGeneratedKeys();

				if (rsClave.next()) {
					codigoCabecera = rsClave.getInt(1);
				}
				ArrayList<DetalleVenta> detallesventa = venta.getDetallesV();
				DetalleVenta det;
				for (int i = 0; i < detallesventa.size(); i++) {
					det = detallesventa.get(i);
					psDet = con.prepareStatement(
							"insert into detalle_venta(cabecera_Venta,producto,cantidad,precio_venta,subtotal,subtotal_iva) "
									+ "values(?,?,?,?,?,?)");
					psDet.setInt(1, codigoCabecera);
					psDet.setInt(2, det.getProducto().getCodigo());
					psDet.setInt(3, det.getCantidad());
					psDet.setBigDecimal(4, det.getProducto().getPrecioVenta());
					BigDecimal pv = det.getProducto().getPrecioVenta();
					BigDecimal cantidad = new BigDecimal(det.getCantidad());
					BigDecimal subTotal = pv.multiply(cantidad);
					psDet.setBigDecimal(5, subTotal);					
					if (det.getProducto().isTieneIVA() ==true) {
						BigDecimal constante = new BigDecimal("1.12");
						BigDecimal subtotalReal = subTotal.multiply(constante);
						psDet.setBigDecimal(6, subtotalReal);	
					}else {
						psDet.setBigDecimal(6, subTotal);
					}
					psDet.executeUpdate();
					 psHis = con.prepareStatement(
				                "insert into historial_stock(fecha,referencia,producto,cantidad) values(?,?,?,?)");
				            psHis.setDate(1, fechaSQL);
				            psHis.setString(2, "VENTA " + codigoCabecera);
				            psHis.setInt(3, det.getProducto().getCodigo());
				            psHis.setInt(4, det.getCantidad() * -1); // Cantidad negativa para indicar venta
				            psHis.executeUpdate();
				}
				
				
				BigDecimal totalSinIva = new BigDecimal("0");
		        BigDecimal totalIva = new BigDecimal("0");
		        BigDecimal totalConIva = new BigDecimal("0");

		        for (int i = 0; i < detallesventa.size(); i++) {
		            det = detallesventa.get(i);
		            BigDecimal subTotal = det.getProducto().getPrecioVenta().multiply(new BigDecimal(det.getCantidad()));
		            totalSinIva = totalSinIva.add(subTotal);

		            if (det.getProducto().isTieneIVA()) {
		                BigDecimal ivaProducto = subTotal.multiply(new BigDecimal("0.12"));
		                totalIva = totalIva.add(ivaProducto);
		            }
		        }

		        totalConIva = totalSinIva.add(totalIva);

		        // Actualizar la cabecera con los valores calculados
		        ps = con.prepareStatement("update cabecera_venta set total_sin_iva=?, iva=?, total_con_iva=? where codigo=?");
		        ps.setBigDecimal(1, totalSinIva);
		        ps.setInt(2, totalIva.intValue());
		        ps.setBigDecimal(3, totalConIva);
		        ps.setInt(4, codigoCabecera);
		        ps.executeUpdate();
				
				System.out.println("codigo generado---> " + codigoCabecera);
			} catch (KrakedevException e) {
				e.printStackTrace();
				throw e;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new KrakedevException("Error al insertar una venta. Detalle: " + e.getMessage());
			}
		}
		
		
		
	}


