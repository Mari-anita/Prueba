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

import com.sena.ShoeStore.interfaceService.IVentaService;
import com.sena.ShoeStore.models.venta;



@RequestMapping("/api/shoes/venta")
@RestController
@CrossOrigin
public class VentaController {
	
	
	@Autowired
	private IVentaService ventaService;
	
	@PostMapping("/")
    public ResponseEntity<Object> save(@ModelAttribute("venta") venta venta) {

        // verificar que no exista el documento de identidad
        var listaVenta = ventaService.findAll()
                .stream().filter(ventaExistence -> ventaExistence.getId_venta()
                        .equals(venta.getId_venta()));
        if (listaVenta.count() != 0) {
            return new ResponseEntity<>("La venta ya existe", HttpStatus.BAD_REQUEST);
        }
        //verificar que el campo documento de identidad sea diferente vacio
        //Añadir campos obligatorios
        //no cambiar nada
        if (venta.getId_venta().equals("")) {

            return new ResponseEntity<>("El id venta es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (venta.getTIddelCliente().equals("")) {
            
            return new ResponseEntity<>("El id del cliente es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (venta.getTotal_venta().equals("")) {
            
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
	var listaVenta=ventaService.filtroVenta(filtro); 
	return new ResponseEntity<>(listaVenta,HttpStatus.OK);
	}
	
	@GetMapping("/{idVenta}")
	public ResponseEntity<Object> findOne(@PathVariable String idVenta){
		var venta=ventaService.findOne(idVenta);
		return new ResponseEntity<>(venta,HttpStatus.OK);
	}
	
	@DeleteMapping("/{idVenta}")
	public ResponseEntity<Object> delete(@PathVariable String idVenta){
		ventaService.delete(idVenta);
		return new ResponseEntity<>("Registro Dehabilitado",HttpStatus.OK);
	}
	
	@PutMapping("/{idVenta}")
	public ResponseEntity<Object> update(@PathVariable String idVenta, @ModelAttribute("venta") venta ventaUpdate){
	    var venta = ventaService.findOne(idVenta);
	    if (venta.isPresent()) {
	        venta.get().setId_venta(ventaUpdate.getId_venta());
	        venta.get().setIddelCliente(ventaUpdate.getTIddelCliente());
	        venta.get().setTotal_venta(ventaUpdate.getTotal_venta());
	        venta.get().setFechaVenta(ventaUpdate.getFecha_venta());
	        venta.get().setEstado_venta(ventaUpdate.getEstado_venta());

	        ventaService.save(venta.get());
	        return new ResponseEntity<>(venta.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error: venta no encontrado", HttpStatus.BAD_REQUEST);
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


