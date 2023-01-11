window.addEventListener('load', function () {

    (function(){

      const url = '/odontologos/lista';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
         //Recorre la colección de odontólogos del JSON
         for(odontologo of data){
          //Por cada odontólogo se crea una fila de la tabla. Cada fila tendrá un id que permite borrar la fila si se elimina el odontólogo

          var table = document.getElementById("odontologoTable");
          var odontologoRow =table.insertRow();
          let tr_id = 'tr_' + odontologo.id;
          odontologoRow.id = tr_id;

           let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';


          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                      ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                      odontologo.id +
                                      '</button>';


         odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_first_name\">' + odontologo.name.toUpperCase() + '</td>' +
                              '<td class=\"td_last_name\">' + odontologo.lastname.toUpperCase() + '</td>' +
                              '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                              '<td>' + deleteButton + '</td>';

         };

        })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/listaOdontologo.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })

})