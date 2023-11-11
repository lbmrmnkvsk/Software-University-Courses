function solve(numbers) {
    for (let number of numbers) {
        number = number.toString();
        let reversed = number.split("").reverse().join("");
        if (number === reversed) {
            console.log("true");
        } else {
            console.log("false");
        }
    }
}

solve([123,323,421,121]);
solve([32,2,232,1010]);