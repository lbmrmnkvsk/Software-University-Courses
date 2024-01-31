function solve(num) {
    let number = num.toString();
    let sum = 0;

    for(let i = 0; i <= number.length - 1; i++) {
        let digit = parseInt(number[i]);
        sum += digit;
    }

    console.log(sum);
}

solve(245678);
solve(97561);
solve(543);