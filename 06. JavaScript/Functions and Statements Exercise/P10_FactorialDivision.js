function solve(one, two) {
    function factorial(number) {
        if (number === 1 || number === 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    console.log((factorial(one) / factorial(two)).toFixed(2));
}



solve(5, 2);
solve(6, 2);