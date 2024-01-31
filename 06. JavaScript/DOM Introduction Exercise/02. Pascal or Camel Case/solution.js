function solve() {
    let text = document.getElementById('text').value;
    let convention = document.getElementById('naming-convention').value;
    text = text.toLowerCase();
    let textResult = "";
    let words = text.split(" ");

    for (let i = 0; i < words.length; i++) {
        let wordResult = "";
        let chars = words[i].split("");
        for (let j = 0; j < chars.length; j++) {
            if (i === 0 && convention === "Pascal Case" && j === 0) {
                wordResult += chars[j].toUpperCase();
            } else if (i > 0 && j === 0) {
                wordResult += chars[j].toUpperCase();
            } else {
                wordResult += chars[j];
            }
        }
        textResult += wordResult;
    }

    if (convention === "Pascal Case" || convention === "Camel Case") {
        document.getElementById('result').textContent = textResult;
    } else {
        document.getElementById('result').textContent = "Error!";
    }
}