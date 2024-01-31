function addItem() {
    let newText = document.getElementById('newItemText').value;
    let newLi = document.createElement('li');
    let list = document.getElementById('items');

    newLi.textContent = newText;
    let deleteLink = document.createElement('a');
    deleteLink.textContent = "[Delete]";
    deleteLink.href = "#";
    deleteLink.addEventListener('click', deleteItem);

    newLi.appendChild(deleteLink);
    list.appendChild(newLi);

    newText = "";
    // document.getElementById('newItemText').value = "";

    function deleteItem(e) {
        let row = e.currentTarget;
        row.parentNode.remove();
    }
}