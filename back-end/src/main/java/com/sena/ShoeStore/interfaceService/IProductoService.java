package com.sena.ShoeStore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.ShoeStore.models.producto;

@Service
public interface IProductoService {
	
	public String save(producto producto);
	public List<producto> findAll();
	public Optional<producto> findOne(String id_produc);
	public int delete(String id_produc);
	List<producto> filtroProducto(String filtro);
	
	
}


