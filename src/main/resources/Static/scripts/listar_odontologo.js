/* -------------------------------------------------------------------------- */
/*                      Obtenemos variables globales                      */
/* -------------------------------------------------------------------------- */
// Vamos a reimplementar la escucha del click lanzar las nuevas funciones.
window.addEventListener('load', function() {

const form = this.document.querySelector("form");
const boton = document.querySelector('button');
//const id = document.querySelector("#id");
const endpoint = "http://localhost:8081/odontologos/listar";
console.log("aca:  " + endpoint)



/* -------------------------------------------------------------------------- */
/*            FUNCIÓN: Escuchamos el submit y preparamos el envío             */
/* -------------------------------------------------------------------------- */
form.addEventListener('submit', function (event){
    event.preventDefault()
    
    console.log("aca settings")    
//configuramos la request del Fetch
    const settings = {
        method: "GET",
    }

    fetch(endpoint, settings)
    
    .then(response => {
    if(response.ok)
      //console.log(response)
      return response.json()
    })
    
    .then(data => {
      //data = JSON.parse(data)  
      console.log("Odontologo: "+ data)

      renderizarTareas(data)

    })
})

/* -------------------------------------------------------------------------- */
/*                  FUNCIÓN 5 - Renderizar tareas en pantalla                 */
/* -------------------------------------------------------------------------- */
function renderizarTareas(data) {

//alert("renderizando")

//Recorremos el listado
data.forEach(element => {

  // Crear una nueva fila en la tabla
  var tabla = document.getElementById('tabla');
  var fila = tabla.insertRow();

  // Insertar celdas en la fila
  var celdaId = fila.insertCell(0);
  var celdaNombre = fila.insertCell(1);
  var celdaApellido = fila.insertCell(2);
  var celdaMatricula = fila.insertCell(3);
  

  // Asignar valores a las celdas
  celdaId.textContent = element.id;
  celdaNombre.textContent = element.nombre;
  celdaApellido.textContent = element.apellido;
  celdaMatricula.textContent = element.numeroMatricula;
  
});

}

})