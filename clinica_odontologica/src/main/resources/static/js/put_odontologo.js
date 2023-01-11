window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_odontologo_form');
    formulario.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#odontologo_id').value;


        const formData = {
            id: document.querySelector('#odontologo_id').value,
            name: document.querySelector('#nombre').value,
            lastname: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };


        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })


    function findBy(id) {
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#nombre').value = odontologo.name;
              document.querySelector('#apellido').value = odontologo.lastname;
              document.querySelector('#matricula').value = odontologo.matricula;


              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
    }