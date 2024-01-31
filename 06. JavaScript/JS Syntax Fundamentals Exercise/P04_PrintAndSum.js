function solve(start, end) {
    let result = '';
    let sum = 0;

    for(let i = start; i <= end; i++) {
        result = result + i + ' ';
        sum += i;
    }

    console.log(result.trimEnd());
    console.log(`Sum: ${sum.toFixed(0)}`);
}

solve(5, 10);
solve(0, 26);
solve(50, 60);