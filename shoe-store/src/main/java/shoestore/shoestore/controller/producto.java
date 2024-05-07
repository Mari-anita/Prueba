package shoestore.shoestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shoestore.shoestore.Service.productoService;
import shoestore.shoestore.interfaceService.IProductoService;



@RequestMapping("/api/shoes/producto")
@RestController
@CrossOrigin
public class producto { 
	
	
	@Autowired
	private IProductoService productoServiceService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("Producto") producto producto) {

        // verificar que no exista el documento de identidad
        var listaProducto = productoService.findAll()
                .stream().filter(producto -> producto.getId_produc()
                        .equals(Producto.getId_produc()));
        if (listaProducto.count() != 0) {
            return new ResponseEntity<>("El producto ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        //no cambiar nada
        if (producto.getId_produc().equals("")) {

            return new ResponseEntity<>("El id producto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getNombre().equals("")) {
            
            return new ResponseEntity<>("El nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getDescripcion().equals("")) {
            
            return new ResponseEntity<>("La descripcion es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getCantidad().equals("")) {
            
            return new ResponseEntity<>("La cantidad es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (producto.getPrecio().equals("")) {
            
            return new ResponseEntity<>("El precio es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getIva().equals("")) {
            
            return new ResponseEntity<>("El iva es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
		if (producto.getDescuento().equals("")) {
            
            return new ResponseEntity<>("El descuento es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (producto.getEstado_produc().equals("")) {
            
            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        // todo bien
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);

    }

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
	var listaProducto=productoService.findAll();
	return new ResponseEntity<>(listaProducto,HttpStatus.OK);
	}


@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	var listaProducto=productoService.filtroMedico(filtro); 
	return new ResponseEntity<>(listaProducto,HttpStatus.OK);
	}
	
	@GetMapping("/{id_produc}")
	public ResponseEntity<Object> findOne(@PathVariable String id_produc){
		var medico=productoService.findOne(id_produc);
		return new ResponseEntity<>(medico,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_produc}")
	public ResponseEntity<Object> delete(@PathVariable String id_produc){
		productoService.delete(id_produc);
		return new ResponseEntity<>("Registro Dehabilitado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("producto") producto productoUpdate){
		var medico=productoService.findOne(id).get();
		if (medico != null) {
			medico.setId_produc(productoUpdate.getId_produc());
			medico.setNombre_produc(productoUpdate.getNombre_produc());
			medico.setDescripcion(productoUpdate.getDescripcion());
			medico.setCantidad(productoUpdate.getCantidad());
			medico.setPrecio(productoUpdate.getPrecio());
			medico.setIva(productoUpdate.getIva());
			medico.setDescuento(productoUpdate.getDescuento());
			medico.setEstado_produc(productoUpdate.getEstado_produc());

			productoService.save(producto);
			return new ResponseEntity<>(producto,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error producto no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}


