function addItem() {
    let newText = document.getElementById('newItemText').value;
    let newLi = document.createElement('li');
    newLi.textContent = newText;
    let list = document.getElementById('items');
    list.appendChild(newLi);

    document.getElementById('newItemText').value = '';
}