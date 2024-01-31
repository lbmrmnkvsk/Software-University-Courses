function solve(number) {
    if (number === 100) {
        console.log("100% Complete!");
        console.log("[%%%%%%%%%%]");
    } else {
        n = number / 10;
        let result = "";

        for (let i = 1; i <= n; i++) {
            result += "%";
        }

        for (let i = n + 1; i <= 10; i++) {
            result += ".";
        }

        console.log(`${number}% [${result}]`);
        console.log("Still loading...");
    }
}

solve(30);
solve(50);
solve(100);