function search() {
    let searchText = document.getElementById('searchText').value;
    let resultField = document.getElementById('result');
    if (resultField.textContent !== '') {
        resultField.textContent === '';
    }

    let lis = document.getElementsByTagName('li');

    let matches = 0;
    for (let li of lis) {
        if (li.textContent.includes(searchText)) {
            li.style.fontWeight = 'bold';
            li.style.textDecoration = 'underline';
            matches++;
        } else {
            li.style.fontWeight = 'normal';
            li.style.textDecoration = 'none';
        }
    }

    resultField.textContent = `${matches} matches found`
}
