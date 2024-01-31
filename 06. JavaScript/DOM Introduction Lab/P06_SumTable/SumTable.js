function sumTable() {
    let tds = document.querySelectorAll('td');
    let total = 0;
    for (let i = 1; i <= tds.length - 3; i += 2) {
        total += parseFloat(tds[i].textContent);
    }
    let sumField = document.querySelector('#sum');
    sumField.textContent = total;
}