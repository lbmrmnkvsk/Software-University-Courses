function solve(one, two, three) {
    const negativesCount = [one, two, three].filter(num => num < 0).length;

    if (negativesCount % 2 === 0) {
        console.log("Positive");
    } else {
        console.log("Negative");
    }
}

solve(5, 12, -15);
solve(-6, -12, 14);
solve(-1, -2, -3);
solve(-5, 1, 1);