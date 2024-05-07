package shoestore.shoestore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import shoestore.shoestore.controller.producto;

@Service
public interface IProductoService {
	
	public String save(producto producto);
	public List<producto> findAll();
	public Optional<producto> findOne(String id_produc);
	public int delete(String id_produc);
	List<producto> filtroMedico(String filtro);
	
	
}


