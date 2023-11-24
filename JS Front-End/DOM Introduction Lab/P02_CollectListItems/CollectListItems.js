function extractText() {
    let lis = document.getElementsByTagName('li');
    let textArea = document.getElementById('result');
    let text = "";

    for (let li of lis) {
        text += li.textContent + "\n";
    }

    textArea.textContent = text;
}