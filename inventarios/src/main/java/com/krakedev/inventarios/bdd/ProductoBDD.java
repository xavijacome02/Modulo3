package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadMedida;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductoBDD {

	public ArrayList<Producto> buscar(String subcadena) throws KrakedevException {
		ArrayList<Producto> arregloProductos = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto productos=null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("select p.codigo as codigo_prod,p.nombre as nombre_producto,um.nombre as nombre_udm, "
					+ "um.descripcion as descripcion_udm, "
					+ "cast(p.precio_venta as decimal(6,2)),p.tiene_iva, cast(p.coste as decimal(6,2)),p.categoria,c.nombre as nombre_categoria,p.stock "
					+ "from productos p, unidades_medida um, categorias c " 
					+ "where p.udm=um.nombre "
					+ "and p.categoria=c.codigo_cat " 
					+ "and upper(p.nombre) like ?");

			ps.setString(1, "%" + subcadena.toUpperCase() + "%"); // touppercase MAYUSCULA/MINUSCULA ES COMO EL UPPER EN
																	// SQL
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("codigo_prod");
				String nombreProducto = rs.getString("nombre_producto");
				String nombreUdm = rs.getString("nombre_udm");
				String descripcion = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int categoria = rs.getInt("categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");
				
				UnidadMedida udm = new UnidadMedida();
				udm.setNombre(nombreUdm);
				udm.setDescripcion(descripcion);
				Categoria cat= new Categoria();
				cat.setNombre(nombreCategoria);
				cat.setCodigo(categoria);
				productos=new Producto();
				productos.setCodigo(codigo);
				productos.setNombre(nombreProducto);
				productos.setPrecioVenta(precioVenta);
				productos.setTieneIVA(tieneIva);
				productos.setCoste(coste);
				productos.setStock(stock);
				arregloProductos.add(productos);
			}
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return arregloProductos;
	}
	
	public void crear(Producto prod) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"insert into productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,stock) values(?,?,?,?,?,?,?)");
			ps.setString(1, prod.getNombre());
			ps.setString(2, prod.getUdm().getNombre());
			ps.setBigDecimal(3, prod.getPrecioVenta());
			ps.setBoolean(4, prod.isTieneIVA());
			ps.setBigDecimal(5, prod.getCoste());
			ps.setInt(6, prod.getCategoria().getCodigo());
			ps.setInt(7, prod.getStock());
			ps.executeUpdate();
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al crear Producto " + e.getMessage());
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
	
	
	public void actualizar(Producto producto)  throws KrakedevException{
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con=ConexionBDD.obtenerConexion();
			ps=con.prepareStatement("update productos set nombre=?,udm=?,precio_venta=?,tiene_iva=?,coste=?,categoria=?,stock=? where codigo=?");
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUdm().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.isTieneIVA());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7,producto.getStock());
			ps.setInt(8,producto.getCodigo());
			
			ps.executeUpdate();
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al actualizar Producto " + e.getMessage());

		}catch (SQLException e) {
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
