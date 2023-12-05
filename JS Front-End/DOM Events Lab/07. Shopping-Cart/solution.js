function solve() {
   let addButtons = document.querySelectorAll('button.add-product');
   let checkoutButton = document.querySelector('button.checkout');
   let textArea = document.querySelector('textarea');
   let boughtProducts = [];
   let totalPrice = 0;

   for (let button of addButtons) {
       button.addEventListener('click', addProduct);
   }
   checkoutButton.addEventListener('click', checkout);

   function addProduct(e) {
       let product = e.currentTarget.parentNode.parentNode;
       let name = product.querySelector('.product-title').textContent;
       let price = product.querySelector('.product-line-price').textContent;

       totalPrice += parseFloat(price);
       if (!boughtProducts.includes(name)) {
           boughtProducts.push(name);
       }
       textArea.value += `Added ${name} for ${parseFloat(price).toFixed(2)} to the cart.\n`;
   }

   function checkout(e) {
       textArea.value += `You bought ${boughtProducts.join(", ")} for ${totalPrice.toFixed(2)}.`;
       for (let button of addButtons) {
           button.removeEventListener('click', addProduct);
           button.disabled = true;
       }
   }
}