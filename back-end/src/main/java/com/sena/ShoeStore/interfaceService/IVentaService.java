package com.sena.ShoeStore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.ShoeStore.models.venta;

@Service
public interface IVentaService {
	
	public String save(venta venta);
	public List<venta> findAll();
	public Optional<venta> findOne(String id_venta);
	public int delete(String id_venta);
	List<venta> filtroVenta(String filtro);
	
	
}


