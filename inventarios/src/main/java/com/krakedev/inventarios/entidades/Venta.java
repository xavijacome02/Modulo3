package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Venta {
	private int codigo;
	private Date fecha;
	private BigDecimal totalSinIva= new BigDecimal("0");
	private int iva= 0;
	private BigDecimal totalConIva= new BigDecimal("0");
	
	private ArrayList<DetalleVenta> detallesV;
	
	public ArrayList<DetalleVenta> getDetallesV() {
		return detallesV;
	}
	public void setDetallesV(ArrayList<DetalleVenta> detallesV) {
		this.detallesV = detallesV;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getTotalSinIva() {
		return totalSinIva;
	}
	public void setTotalSinIva(BigDecimal totalSinIva) {
		this.totalSinIva = totalSinIva;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public BigDecimal getTotalConIva() {
		return totalConIva;
	}
	public void setTotalConIva(BigDecimal totalConIva) {
		this.totalConIva = totalConIva;
	}
	public Venta() {
		super();
	}
	public Venta(int codigo, Date fecha, BigDecimal totalSinIva, int iva, BigDecimal totalConIva,
			ArrayList<DetalleVenta> detallesV) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.totalSinIva = totalSinIva;
		this.iva = iva;
		this.totalConIva = totalConIva;
		this.detallesV = detallesV;
	}
	@Override
	public String toString() {
		return "Venta [codigo=" + codigo + ", fecha=" + fecha + ", totalSinIva=" + totalSinIva + ", iva=" + iva
				+ ", totalConIva=" + totalConIva + ", detallesV=" + detallesV + "]";
	}
	
	
}
