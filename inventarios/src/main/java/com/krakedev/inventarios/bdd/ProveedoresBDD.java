package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	
	public ArrayList<Proveedores> buscar(String subcadena) throws KrakedevException {
		ArrayList<Proveedores> proveedores = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedores proveedor = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select identificador, tipo_documento, nombre, telefono, correo, direccion from proveedores "
			        + "where upper(nombre) like ?");

			ps.setString(1, "%"+subcadena.toUpperCase()+"%"); //touppercase MAYUSCULA/MINUSCULA ES COMO EL UPPER EN SQL
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String tipoDocumento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				proveedor = new Proveedores(identificador,tipoDocumento,nombre,telefono,correo,direccion);
				proveedores.add(proveedor);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return proveedores;
	}
	
	
	public ArrayList<TipoDocumento> recuperarTodos() throws KrakedevException {
		ArrayList<TipoDocumento> arregloTipoDocumento = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento tipoDocumento = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select codigo,descripcion from tipo_documento");
			rs = ps.executeQuery();

			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				
				tipoDocumento = new TipoDocumento(codigo,descripcion);
				arregloTipoDocumento.add(tipoDocumento);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return arregloTipoDocumento;
	}
}
