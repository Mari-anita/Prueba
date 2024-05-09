package com.sena.shoestore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.shoestore.controller.VentaController;
import com.sena.shoestore.models.venta;

@Service
public interface IVentaService {
	
	public String save(venta venta);
	public List<venta> findAll();
	public Optional<venta> findOne(String id_venta);
	public int delete(String id_venta);
	List<venta> filtroVenta(String filtro);
	
	
}


