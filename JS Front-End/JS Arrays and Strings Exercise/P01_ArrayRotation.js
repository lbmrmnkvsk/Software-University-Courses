function solve(array, rotations) {
    for (let i = 1; i <= rotations; i++) {
        let temp = array.shift();
        array.push(temp);
    }

    let result = '';
    for (let num of array) {
        result += num + ' ';
    }

    console.log(result.trim());
}

solve([51, 47, 32, 61, 21], 2);
solve([32, 21, 61, 1], 4);
solve([2, 4, 15, 31], 5);