function solve(product, quantity) {
    let sum = 0;
    let ppu = 0;

    if (product === "coffee") {
        ppu = 1.50;
    } else if (product === "water") {
        ppu = 1.00;
    } else if (product === "coke") {
        ppu = 1.40;
    } else if (product === "snacks") {
        ppu = 2.00;
    }

    sum = ppu * quantity;
    console.log(sum.toFixed(2));
}

solve("water", 5);
solve("coffee", 2);