function solve(fruit, grams, priceKg) {
    let kg = grams / 1000;
    let price = kg * priceKg;
    console.log(`I need $${price.toFixed(2)} to buy ${kg.toFixed(2)} kilograms ${fruit}.`);
}

solve('orange', 2500, 1.80);
solve('apple', 1563, 2.35);