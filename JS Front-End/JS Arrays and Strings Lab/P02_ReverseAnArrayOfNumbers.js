function solve(count, array) {
    let result = [];

    for(let i = 0; i < count; i++) {
        result.push(array[i]);
    }

    let reversed = result.reverse();
    let output = "";

    for(let num of reversed) {
        output += num + " ";
    }

    console.log(output);
}

solve(3, [10, 20, 30, 40, 50]);
solve(4, [-1, 20, 99, 5]);
solve(2, [66, 43, 75, 89, 47]);