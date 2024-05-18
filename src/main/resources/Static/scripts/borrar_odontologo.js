/* -------------------------------------------------------------------------- */
/*                      Obtenemos variables globales                      */
/* -------------------------------------------------------------------------- */
// Vamos a reimplementar la escucha del click lanzar las nuevas funciones.
window.addEventListener('load', function() {

const form = this.document.querySelector("form");
const boton = document.querySelector('button');
let id = document.querySelector("#id");
const endpoint = 'http://localhost:8081/odontologos/eliminar/3';
console.log("aca" + endpoint)



/* -------------------------------------------------------------------------- */
/*            FUNCIÓN: Escuchamos el submit y preparamos el envío           */
/* -------------------------------------------------------------------------- */
form.addEventListener('submit', function (event){
    event.preventDefault()
    
       
//configuramos la request del Fetch
    const settings = {
        method: "DELETE",
    }

    fetch(endpoint, settings)
    .then(response => {
      console.log("Odontologo eliminado correctamente... ")
    })
    
    .then(data => {
      //data = JSON.parse(data)  
      renderizarTareas

      

    })
})

/* -------------------------------------------------------------------------- */
/*                  FUNCIÓN 5 - Renderizar tareas en pantalla                 */
/* -------------------------------------------------------------------------- */
function renderizarTareas(data) {

// Crear una nueva fila en la tabla
var tabla = document.getElementById('tabla');
var fila = tabla.insertRow();

// Insertar celdas en la fila
var celdaNombre = fila.insertCell(0);
var celdaApellido = fila.insertCell(1);
var celdaDireccion = fila.insertCell(2);

// Asignar valores a las celdas
celdaNombre.textContent = data.nombre;
celdaApellido.textContent = data.apellido;
celdaDireccion.textContent = data.numeroMatricula;

// // Limpiar los campos del formulario
// document.getElementById('nombre').value = '';
// document.getElementById('apellido').value = '';
// document.getElementById('direccion').value = '';






// console.log("Ya en Renderizado: " + data);

// const formOdoEncontrado = document.querySelector(".datosOdo")

// formOdoEncontrado.innerHTML = ""

// formOdoEncontrado.innerHTML +=

//     `<fieldset class="datosOdo">
//     <h4>Nombre: <spam>${data.nombre}</spam></h4>
//     <h4>Apellido: <spam>${data.apellido}</spam></h4>
//     <h4>Nuemro Matricula: <spam>${data.numeroMatricula}</spam></h4>

// </fieldset>
// `

};

})