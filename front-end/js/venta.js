var url = "http://localhost:8081/api/shoes/venta/";

function listarProducto() {
    //METODO PARA LISTAR LOS CLIENTES

    // cual es la diferencia entre busqueda normal
    // y con FileSystemDirectoryHandle
    // normal url= http://localhost:8080/api/hospital/medico/
    // con filtro url= http://localhost:8080/api/hospital/medico/busqueda/parametro

    // si el campo filtro es diferente a vacio haga busqueda con filtro

    // si no haga busqueda normal

    var urlLocal = url;
    var filtro = document.getElementById("texto").value
    if (filtro != "")
        urlLocal += "busqueda/" + filtro;


    //SE CREA LA PETICION AJAX
    $.ajax({
        url: urlLocal,
        type: "GET",
        success: function (result) {
            //success: funcion que se ejecuta
            //cuando la peticion tiene exito
            //console.log(result);

            var cuerpoTablaVenta= document.getElementById("cuerpoTablaVenta");
            //Se limpia el cuepro de la tabla
            cuerpoTablaVenta.innerHTML = "";
            //se hace un ciclo que recorra l arreglo con los datos
            for (var i = 0; i < result.length; i++) {
                //UNA ETIQUETA tr por cada registro
                var trResgistro = document.createElement("tr");

                var celdaIdVenta = document.createElement("tr");
                let celdaIdClienteVenta = document.createElement("td")
                let celdanTotalVenta = document.createElement("td")
                let celdaFechaVenta = document.createElement("td")
                let celdaEstado = document.createElement("td")



                celdaIdVenta.innerText = result[i]["id_venta"];
                celdaIdClienteVenta.innerText = result[i]["id_cliente_venta"];
                celdanTotalVenta.innerText = result[i]["total_venta"];
                celdaFechaVenta.innerText = result[i]["fecha_venta"];
                celdaEstado.innerText = result[i]["estado_venta"];


                trResgistro.appendChild(celdaIdVenta);
                trResgistro.appendChild(celdaIdClienteVenta);
                trResgistro.appendChild(celdanTotalVenta);
                trResgistro.appendChild(celdaFechaVenta);
                trResgistro.appendChild(celdaEstado);

                //botones editar y deshabilitar
                let celdaOpcion = document.createElement("td");
                let botonEditarProducto = document.createElement("button");
                botonEditarVenta.value = result[i]["id_venta"];
                botonEditarVenta.innerHTML = "Editar";

                botonEditarVenta.onclick = function (e) {
                    $('#exampleModal').modal('show');
                    consultarVentaID(this.value);
                }
                botonEditarVenta.className = "btn btn-warning editar-producto";

                let botonDeshabilitarVenta = document.createElement("button");
                botonDeshabilitarVenta.innerHTML = "Deshabilitar";
                botonDeshabilitarVenta.className = "btn btn-danger deshabilitar-venta";

                let productoIdParaDeshabilitar = result[i]["id_venta"];
                botonDeshabilitarVenta.onclick = function () {
                    deshabilitarVenta(ventaIdParaDeshabilitar);
                };


                celdaOpcion.appendChild(botonEditarVenta);
                celdaOpcion.appendChild(botonDeshabilitarVenta);

                trResgistro.appendChild(celdaOpcion)
                cuerpoTablaVenta.appendChild(trResgistro);


                //creamos un td por cada campo de resgistro

            }
        },
        error: function (error) {
            /*
            ERROR: funcion que se ejecuta cuando la peticion tiene un error
            */
            alert("Error en la petición " + error);
        }
    })
}


//1.Crear petición que traiga la información del medico por id
function consultarVentaID(id) {
    //alert(id);
    $.ajax({
        url: url + id,
        type: "GET",
        success: function (result) {
            document.getElementById("id_venta").value = result["id_venta"];
            document.getElementById("id_cliente_venta").value = result["id_cliente_venta"];
            document.getElementById("total_venta").value = result["total_venta"];
            document.getElementById("fecha_venta").value = result["fecha_venta"];
            document.getElementById("estado_venta").value = result["estado_venta"];
        }
    });
}
//2.Crear petición que actualice la información del medico

function actualizarVenta() {
    let formData = {
        "id_venta": document.getElementById("id_venta").value,
        "id_cliente_venta": document.getElementById("id_cliente_venta").value,
        "total_venta": document.getElementById("total_venta").value,
        "fecha_venta": document.getElementById("fecha_venta").value,
        "estado_venta": document.getElementById("estado_venta").value
    };

    if (validarCampos()) {
        $.ajax({
            url: url + id_venta,
            type: "PUT",
            data: formData,
            success: function (result) {
                // Manejar la respuesta exitosa según necesites
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
                // Puedes hacer algo adicional como recargar la lista de médicos
                listarVenta();
            },
            error: function (error) {
                // Manejar el error de la petición
                Swal.fire({
                    title: "¡Error!",
                    text: "No se guardó",
                    icon: "error"
                });
            }
        });
    } else {
        Swal.fire({
            title: "¡Error!",
            text: "Llene todos los campos correctamente",
            icon: "error"
        });
    }
}


// funcion de deshabilitar medico
function deshabilitarVenta(id) {
    Swal.fire({
        title: '¿Está seguro?',
        text: "Esta acción no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, deshabilitar!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: url + id,
                type: "DELETE",
                success: function (result) {
                    Swal.fire(
                        'Deshabilitado!',
                        'El registro ha sido deshabilitado.',
                        'success'
                    );
                    listarVenta(); // Recarga la lista de médicos
                },
                error: function (error) {
                    Swal.fire(
                        'Error!',
                        'No se pudo deshabilitar el registro.',
                        'error'
                    );
                }
            });
        }
    });
}


function registrarVenta() {

    let formData = {
        "id_venta": document.getElementById("id_venta").value,
        "id_cliente_venta": document.getElementById("id_cliente_venta").value,
        "total_venta": document.getElementById("total_venta").value,
        "fecha_venta": document.getElementById("fecha_venta").value,
        "estado_venta": document.getElementById("estado_venta").value

    };

    if (validarCampos()) {
        $.ajax({
            url: url,
            type: "POST",
            data: formData,
            success: function (result) {
                //
                Swal.fire({
                    title: "¡Excelente!",
                    text: "Se guardó correctamente",
                    icon: "success"
                });
            },
        })
    } else {
        Swal.fire({
            title: "¡Error!",
            text: "Llene todos los campos correctamente",
            icon: "error"
        });
    }
}

//validación número de documento medico
function validarCampos() {
    var id_venta = document.getElementById("id_venta");

    return validarId(id_venta) && 
    validarIdClienteVenta(id_cliente_venta) && 
    validarTotalVenta(total_venta) && 
    validarFechaVenta(fecha_venta);
    


}
function validarId(cuadroId) {
    var valor = cuadroId.value;
    var valido = true;
    if (valor.length < 1 || valor.length > 11) {
        valido = false
    }

    if (valido) {
        cuadroId.className = "form-control is-valid";
    } else {
        cuadroId.className = "form-control is-invalid";
    }
    return valido;
}

function validarIdClienteVenta(cuadroIdClienteVenta) {
    var valor = cuadroIdClienteVenta.value;
    var valido = true;
    if (valor.length < 0 || valor.length > 21) {
        valido = false
    }

    if (valido) {
        cuadroIdClienteVenta.className = "form-control is-valid";
    } else {
        cuadroIdClienteVenta.className = "form-control is-invalid";
    }
    return valido;
}

function validarTotalVenta(cuadroTotalVenta) {
    var valor = cuadroTotalVenta.value;
    var valido = true;
    if (valor.length < 0 || valor.length > 16) {
        valido = false
    }

    if (valido) {
        cuadroTotalVenta.className = "form-control is-valid";
    } else {
        cuadroTotalVenta.className = "form-control is-invalid";
    }
    return valido;
}

function validarFechaVenta(cuadroFechaVenta) {
    var valor = cuadroFechaVenta.value;
    var valido = true;
    if (valor.length < 0 || valor.length > 16) {
        valido = false
    }

    if (valido) {
        cuadroFechaVenta.className = "form-control is-valid";
    } else {
        cuadroFechaVenta.className = "form-control is-invalid";
    }
    return valido;
}

function limpiar() {
    document.getElementById("id_venta").value = "";
    document.getElementById("id_cliente_venta").value = "";
    document.getElementById("total_venta").value = "";
    document.getElementById("fecha_venta").value = "";
    document.getElementById("estado_venta").value = "";
}


