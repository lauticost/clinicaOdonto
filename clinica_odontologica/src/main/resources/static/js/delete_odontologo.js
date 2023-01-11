function deleteBy(id)
{
          const url = '/odontologos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())

          //Borrar la fila del odontólogo eliminado
          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();

}