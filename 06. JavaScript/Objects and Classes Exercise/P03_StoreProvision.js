function solve(current, ordered) {
    let stock = {};

    for (let i = 0; i < current.length; i += 2) {
        let product = current[i];
        let quantity = parseInt(current[i + 1]);
        stock[product] = quantity;
    }

    for (let i = 0; i < ordered.length; i += 2) {
        let product = ordered[i];
        let quantity = parseInt(ordered[i + 1]);

        if (stock.hasOwnProperty(product)) {
            stock[product] += quantity;
        } else {
            stock[product] = quantity;
        }
    }

    for (let key in stock) {
        console.log(`${key} -> ${stock[key]}`);
    }
}

solve([
        'Chips', '5', 'CocaCola', '9', 'Bananas', '14', 'Pasta', '4', 'Beer', '2'
    ],
    [
        'Flour', '44', 'Oil', '12', 'Pasta', '7', 'Tomatoes', '70', 'Bananas', '30'
    ]
);