package com.sena.ShoeStore.models;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="venta")
public class venta {

	/*
	 * ID
	 * DOC
	 * PRI NOM
	 * SEG NOM
	 * PRI APE
	 * SEG APE
	 * CELULAR
	 * CORREO
	 * ESTADO HAB/DES
	 */
	
	
	@Id	
	@GeneratedValue
	@Column(name="id_venta", nullable=false, length = 36)
	private String id_venta;

	@Column(name="id_cliente_venta",nullable=false,length = 36)
	private String id_cliente_venta;

	@Column(name="total_venta",nullable=false,length = 11)
	private String total_venta;
	
	@Column(name="fecha_venta",nullable=false,length = 20)
	private String fecha_venta;

	@Column(name="estado_venta",nullable=false,length = 40)
	private String estado_venta;

	public venta() {
		super();
	}

	public venta(String id_venta, String id_cliente_venta, String total_venta, String fecha_venta,
			String estado_venta) {
		super();
		this.id_venta = id_venta;
		this.id_cliente_venta = id_cliente_venta;
		this.total_venta = total_venta;
		this.fecha_venta = fecha_venta;
		this.estado_venta = estado_venta;
	}

	public String getId_venta() {
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}


	public String getTIddelCliente() {
		return id_cliente_venta;
	}

	public void setIddelCliente(String id_cliente_venta) {
		this.id_cliente_venta = id_cliente_venta;
	}

	public String getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(String total_venta) {
		this.total_venta = total_venta;
	}

	public String getFecha_venta() {
		return fecha_venta;
	}

	public void setFechaVenta(String fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public String getEstado_venta() {
		return estado_venta;
	}

	public void setEstado_venta(String estado_venta) {
		this.estado_venta = estado_venta;
	}


}

