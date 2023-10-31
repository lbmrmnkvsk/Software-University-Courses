function solve(count, type, day) {
    let price;
    let individualPrice;

    if (type === 'Students') {
        if (day === 'Friday') {
            individualPrice = 8.45;
        } else if (day === 'Saturday') {
            individualPrice = 9.80;
        } else if (day === 'Sunday') {
            individualPrice = 10.46;
        }

        price = count * individualPrice;
        if (count >= 30) {
            price = 0.85 * price;
        }
    } else if (type === 'Business') {
        if (day === 'Friday') {
            individualPrice = 10.90;
        } else if (day === 'Saturday') {
            individualPrice = 15.60;
        } else if (day === 'Sunday') {
            individualPrice = 16;
        }

        price = count * individualPrice;
        if (count >= 100) {
            price = (count - 10) * individualPrice;
        }
    } else if (type === 'Regular') {
        if (day === 'Friday') {
            individualPrice = 15;
        } else if (day === 'Saturday') {
            individualPrice = 20;
        } else if (day === 'Sunday') {
            individualPrice = 22.50;
        }

        price = count * individualPrice;
        if (count >= 10 && count <= 20) {
            price = 0.95 * price;
        }
    }

    console.log(`Total price: ${price.toFixed(2)}`);
}

solve(30,
    "Students",
    "Sunday"
);

solve(40,
    "Regular",
    "Saturday"
)