function solve(number) {
    let evenSum = 0;
    let oddSum = 0;

    number = number.toString();

    for (let i = 0; i < number.length; i++) {
        let digit = parseInt(number[i]);
        if (digit % 2 === 0) {
            evenSum += digit;
        } else  {
            oddSum += digit;
        }
    }

    console.log(`Odd sum = ${oddSum}, Even sum = ${evenSum}`);
}

solve(1000435);
solve(3495892137259234);