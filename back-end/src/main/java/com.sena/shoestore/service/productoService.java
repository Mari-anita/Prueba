package com.sena.shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.shoestore.controller.ProductoController;
import com.sena.shoestore.interfaceService.IProductoService;
import com.sena.shoestore.interfaces.IProducto;
import com.sena.shoestore.models.producto;


@Service
public class productoService implements IProductoService{
	@Autowired
	private IProducto data;

	@Override
	public String save(producto producto) {
		this.producto = producto;
		data.save(producto);
		return producto.getId_produc();
	}

	@Override
	public List<producto> findAll() {
		List<producto> listaProducto=
				(List<producto>) data.findAll();
		//(List<paciente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <paciente>
		//- se convierte a list <paciente>
		return listaProducto;
	}
	
	@Override
	public List<producto> filtroProducto(String filtro) {
		List<producto> listaProducto=data.filtroProducto(filtro);
		return listaProducto;
	}
	
	@Override
	public Optional<producto> findOne(String id_produc) {
		Optional<producto> producto=data.findById(id_produc);
		return producto;

	}


	@Override
	public int delete(String id_produc) {
		var producto=data.findById(id_produc).get();
		producto.setEstado_produc("Inactivo"); 
        data.save(producto); 
		return 0;
	}

	@Override
	public String save(ProductoController producto) {
		//  Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

}


