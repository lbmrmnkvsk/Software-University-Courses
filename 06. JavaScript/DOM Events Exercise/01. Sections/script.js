function create(words) {
   let contentBox = document.getElementById('content');

   for (let word of words) {
      let div = document.createElement('div');
      let p = document.createElement('p');
      p.textContent = word;
      p.style.display = 'none';

      div.appendChild(p);

      div.addEventListener('click', function () {
         if (p.style.display === 'none') {
            p.style.display = 'block';
         } else {
            p.style.display = 'none';
         }
      });

      contentBox.appendChild(div);
   }

}