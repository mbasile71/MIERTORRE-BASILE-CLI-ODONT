window.addEventListener('load', function() {

    /* ---------------------- obtenemos variables globales ---------------------- */
    const form = document.querySelector("form")
    const paciente_id = document.querySelector("#pacienteid");
    const odontologo_id = document.querySelector("#odontologoid");
    const fechayhora = document.querySelector("#fechayhora");
    
    const url = "http://localhost:8081"
    
    const btn = document.querySelector('button');
    
        
    
    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    form.addEventListener('submit', function (event){
    event.preventDefault()
    
    //Creamos el cuerpo de la request (petición al servidor)
    const payload = {
        paciente_id: paciente_id.value,
        odontologo_id: odontologo_id.value,
        fechaYHora: fechayhora.value    
      }
    
     //vemos el objeto que recibimos del formulario
     console.log(payload)  
     alert("Este de arriba el el payload")
    
     //configuramos la request del Fetch
     const settings = {
        method: "POST",
        body: JSON.stringify(payload),
        headers: {
            "Content-Type": "application/json"
        }
    }
    console.log(settings)
    alert("arriba settings")
    
    
    //Lanzamos la consulta del login a la API
    realizarLogin(settings)
    
    //Limpiar Formulario
    form.reset()
    
    })
    
    
    /* -------------------------------------------------------------------------- */
    /*                     FUNCIÓN 2: Realizar el login [POST]                    */
    /* -------------------------------------------------------------------------- */
    function realizarLogin(settings) {
    console.log("Lanzar la consulta a la API")
    
    fetch(`${url}/turnos/registrar`, settings)
    .then(response => {
        
        if(response.ok){
            return response.json()
        }
        else{
            alert("Promesa incumplida")
        }
    })
    
    .then(data => {
        console.log("Promesa cumplida💍");
        console.log(data);
        
    })
    
    .catch(err => {
        console.warn("Promesa rechazada...")
        console.log(err.status)
        if(err.status == 400){
            console.warn("Error 400...")
                alert("Error 400...")
            }else if(err.status == 404){
                alert("Error 404......")
            }else{
                alert("Error del servidor | URL inexistente...")
            }
    
    })
    
    }
    
    })
    
    