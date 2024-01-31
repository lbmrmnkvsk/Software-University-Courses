function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {

      let rows = document.querySelectorAll('tbody tr');
      let searchField = document.getElementById('searchField');
      let searchText = searchField.value.toLowerCase();

      for (let i = 0; i < rows.length; i++) {
         let cells = rows[i].getElementsByTagName('td');
         let rowMatch = false;

         for (let cell of cells) {
            if (cell.textContent.toLowerCase().includes(searchText)) {
               rowMatch = true;
               break;
            }
         }

         if (rowMatch) {
            rows[i].classList.add('select');
         } else {
            rows[i].classList.remove('select');
         }
      }

      document.getElementById('searchField').value = '';
   }

}