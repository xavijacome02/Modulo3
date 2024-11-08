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
			ps = con.prepareStatement(
					"select identificador,tipo_documento,tp.descripcion,nombre,telefono,correo,direccion "
							+ "from proveedores prov,tipo_documento tp " + "where prov.tipo_documento=tp.codigo "
							+ "and upper(nombre) like ?");

			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // touppercase MAYUSCULA/MINUSCULA ES COMO EL UPPER EN
																	// SQL
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String codigoTipoDocumento = rs.getString("tipo_documento");
				String descripcionTD = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				TipoDocumento tipDoc = new TipoDocumento(codigoTipoDocumento, descripcionTD);
				proveedor = new Proveedores(identificador, tipDoc, nombre, telefono, correo, direccion);
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

	public void crear(Proveedores prov) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"insert into proveedores(identificador,tipo_documento,nombre,telefono,correo,direccion)values(?,?,?,?,?,?)");
			ps.setString(1, prov.getIdentificador());
			ps.setString(2, prov.getTipoDocumento().getCodigo());
			ps.setString(3, prov.getNombre());
			ps.setString(4, prov.getTelefono());
			ps.setString(5, prov.getCorreo());
			ps.setString(6, prov.getDireccion());
			ps.executeUpdate();
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al crear Proveedor " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
