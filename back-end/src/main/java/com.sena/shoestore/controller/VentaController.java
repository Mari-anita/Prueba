package com.sena.shoestore.controller;

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

import com.sena.shoestore.interfaceService.IVentaService;




@RequestMapping("/api/shoes/venta")
@RestController
@CrossOrigin
public class VentaController {
	
	
	@Autowired
	private IVentaService ventaService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("Venta") VentaController venta) {

        // verificar que no exista el documento de identidad
        var listaVenta = ventaService.findAll()
                .stream().filter(venta -> VentaController.getId_venta()
                        .equals(Venta.getId_venta()));
        if (listaVenta.count() != 0) {
            return new ResponseEntity<>("La venta ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        //no cambiar nada
        if (venta.getId_venta().equals("")) {

            return new ResponseEntity<>("El id venta es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (venta.getIddelCliente().equals("")) {
            
            return new ResponseEntity<>("El id del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (venta.getTotal().equals("")) {
            
            return new ResponseEntity<>("El total es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (venta.getFecha_venta().equals("")) {
            
            return new ResponseEntity<>("La fecha es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (venta.getEstado_venta().equals("")) {
            
            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        // todo bien
        ventaService.save(venta);
        return new ResponseEntity<>(venta, HttpStatus.OK);

    }

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
	var listaVenta=ventaService.findAll();
	return new ResponseEntity<>(listaVenta,HttpStatus.OK);
	}


@GetMapping("/busqueda/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	var listaVenta=ventaService.filtroProducto(filtro); 
	return new ResponseEntity<>(listaVenta,HttpStatus.OK);
	}
	
	@GetMapping("/{id_venta}")
	public ResponseEntity<Object> findOne(@PathVariable String id_venta){
		var venta=ventaService.findOne(id_venta);
		return new ResponseEntity<>(venta,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id_venta}")
	public ResponseEntity<Object> delete(@PathVariable String id_venta){
		ventaService.delete(id_venta);
		return new ResponseEntity<>("Registro Dehabilitado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("venta") VentaController ventaUpdate){
		var venta=ventaService.findOne(id).get();
		if (venta != null) {
			venta.setId_venta(ventaUpdate.getId_venta());
			venta.setId_cliente_venta(ventaUpdate.getId_cliente_venta());
			venta.setTotal_venta(ventaUpdate.getTotal_venta());
			venta.setFecha_venta(ventaUpdate.getFecha_venta());
			venta.setEstado_venta(ventaUpdate.getEstado_venta());

			ventaService.save(venta);
			return new ResponseEntity<>(venta,HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>("Error producto no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

    public String getId_venta() {
        //  Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId_venta'");
    }

    public void setEstado_produc(String string) {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado_venta'");
    }

}


