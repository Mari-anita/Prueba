package com.sena.ShoeStore.controller;

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

import com.sena.ShoeStore.interfaceService.IProductoService;
import com.sena.ShoeStore.models.producto;



@RequestMapping("/api/shoes/producto")
@RestController
@CrossOrigin
public class ProductoController { 
	
	
	@Autowired
	private IProductoService productoService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("producto") producto producto) {

        // verificar que no exista el documento de identidad
        var listaProducto = productoService.findAll()
                .stream().filter(productoExistence -> productoExistence.getId_produc()
                        .equals(producto.getId_produc()));
        if (listaProducto.count() != 0) {
            return new ResponseEntity<>("El producto ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        //no cambiar nada

        if (producto.getNombre_produc().equals("")) {
            
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
	var listaProducto=productoService.filtroProducto(filtro); 
	return new ResponseEntity<>(listaProducto,HttpStatus.OK);
	}
	
	@GetMapping("/{idProduc}")
	public ResponseEntity<Object> findOne(@PathVariable String id_produc){
		var producto=productoService.findOne(id_produc);
		return new ResponseEntity<>(producto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{idProduc}")
	public ResponseEntity<Object> delete(@PathVariable String id_produc){
		productoService.delete(id_produc);
		return new ResponseEntity<>("Registro Dehabilitado",HttpStatus.OK);
	}
	
	@PutMapping("/{idProducto}")
	public ResponseEntity<Object> update(@PathVariable String idProducto, @ModelAttribute("producto") producto productoUpdate){
		var producto = productoService.findOne(idProducto);
		if (producto.isPresent()) {
			producto.get().setNombre_produc(productoUpdate.getNombre_produc());
			producto.get().setDescripcion(productoUpdate.getDescripcion());
			producto.get().setDescripcion(productoUpdate.getDescripcion());
			producto.get().setDescripcion(productoUpdate.getDescripcion());
			producto.get().setCantidad(productoUpdate.getCantidad());
			producto.get().setPrecio(productoUpdate.getPrecio());
			producto.get().setIva(productoUpdate.getIva());
			producto.get().setDescuento(productoUpdate.getDescuento());
			producto.get().setEstado_produc(productoUpdate.getEstado_produc());

			productoService.save(producto.get());
			return new ResponseEntity<>(producto.get(),HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error producto no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

    public String getId_produc() {
        //  Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre_produc'");
    }

    public void setEstado_produc(String string) {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado_produc'");
    }

}


