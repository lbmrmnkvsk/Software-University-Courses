function addItem() {
    let menu = document.getElementById('menu');
    let textInput = document.getElementById('newItemText');
    let valueInput = document.getElementById('newItemValue');

    let newOption = document.createElement('option');
    newOption.textContent = textInput.value;
    newOption.value = valueInput.value;

    menu.appendChild(newOption);
    textInput.value = '';
    valueInput.value = '';
}