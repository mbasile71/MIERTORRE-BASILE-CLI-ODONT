
window.addEventListener('load', function() {
/* ---------------------- obtenemos variables globales ---------------------- */
const form = document.querySelector("form")
const nombre = document.querySelector("#nombre");
const apellido = document.querySelector("#apellido");
const matricula = document.querySelector("#matricula");

//const url = "localhost:8081/"

const btn = document.querySelector('button');

//console.log(nombre);
//console.log(apellido);
//console.log(matricula);



/* -------------------------------------------------------------------------- */
/*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
/* -------------------------------------------------------------------------- */
form.addEventListener('submit', function (event){
event.preventDefault()

//Creamos el cuerpo de la request (petición al servidor)
const payload = {
    numeromatricula: matricula.value,
    nombre: nombre.value,
    apellido: apellido.value    
  }

 //vemos el objeto que recibimos del formulario
 console.log(payload)  

 //configuramos la request del Fetch
 const settings = {
    method: "POST",
    body: JSON.stringify(payload),
    headers: {
        "Content-Type": "application/json"
    }
}

//Lanzamos la consulta del login a la API
realizarLogin(settings)


})


/* -------------------------------------------------------------------------- */
/*                     FUNCIÓN 2: Realizar el login [POST]                    */
/* -------------------------------------------------------------------------- */
function realizarLogin(settings) {
console.log("Lanzar la consulta a la API")

fetch("http://localhost:8081/odontologos/registrar", settings)
/*.then(response => {

        

    if(response.ok){
        return response.json()
    }
    else{
        alert("Promesa incumplida")
    }
})*/

.then(data => {
    console.log("Promesa cumplida💍");
    //Redireccionamos al DashBoard
    //location.replace("./mis-tareas.html") 
})

.catch(err => {
    console.warn("Promesa rechazada...")
    console.log(err.status)
    if(err.status == 400){
        console.warn("contraseña incorrecta...")
            alert("La contraseña es incorrecta...")
        }else if(err.status == 404){
            alert("el usuario no existe...")
        }else{
            alert("Error del servidor | URL inexistente...")
        }

})

}

})

