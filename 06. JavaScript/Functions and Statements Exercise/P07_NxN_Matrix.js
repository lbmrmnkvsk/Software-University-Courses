function solve(n) {
    for (let i = 0; i < n; i++) {
        let result = "";
        for (let j = 0; j < n; j ++) {
            result += n + " ";
        }
        console.log(result.trim());
    }
}

solve(3);
solve(7);
solve(2);