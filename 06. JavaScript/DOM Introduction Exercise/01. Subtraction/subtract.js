function subtract() {
    let num1 = parseFloat(document.getElementById('firstNumber').value);
    let num2 = parseFloat(document.getElementById('secondNumber').value);
    let answer = num1 - num2;
    document.getElementById('result').textContent = answer;
}