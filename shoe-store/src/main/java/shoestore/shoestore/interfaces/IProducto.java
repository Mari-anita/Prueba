package shoestore.shoestore.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import shoestore.shoestore.controller.producto;


@Repository
public interface IProducto extends CrudRepository<producto,String>{
	
	//?1 es la primera variable
	@Query("SELECT p FROM producto p WHERE p.nombre LIKE %?1% OR"
			+ " p.estado_produc LIKE %?1%")
	
	List<producto> filtroMedico(String filtro);

	
}

