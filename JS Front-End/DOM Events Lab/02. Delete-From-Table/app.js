function deleteByEmail() {
    let input = document.querySelector("input[name='email']").value;
    let tds = document.querySelectorAll('tbody tr td');
    let found = false;
    for (let td of tds) {
        if (td.textContent.includes(input)) {
            let row = td.parentNode;
            row.parentNode.removeChild(row);
            found = true;
        }
    }

    if (found) {
        document.getElementById('result').textContent = "Deleted.";
    } else {
        document.getElementById('result').textContent = "Not found.";
    }
}