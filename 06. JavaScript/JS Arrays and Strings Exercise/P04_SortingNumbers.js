function solve(array) {
    array.sort((a,b) => a - b);
    let result = [];

    while (array.length > 0) {
        result.push(array.shift());
        if (array.length > 0) {
            result.push(array.pop());
        }
    }

    return result;
}

console.log(solve([1, 65, 3, 52, 48, 63, 31, -3, 18, 56]));