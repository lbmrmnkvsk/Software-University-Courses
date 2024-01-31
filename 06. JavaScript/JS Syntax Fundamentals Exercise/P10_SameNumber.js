function solve(number) {
    let numberStr = number.toString();
    let firstDigit = numberStr[0];
    let result = true;
    let sum = 0;

    for(let i = 0; i < numberStr.length; i++) {
        if (numberStr[i] !== firstDigit && result === true) {
            result = false;
        }

        sum += parseInt(numberStr[i]);
    }

    console.log(result);
    console.log(sum);
}

solve(2222222);
solve(1234);