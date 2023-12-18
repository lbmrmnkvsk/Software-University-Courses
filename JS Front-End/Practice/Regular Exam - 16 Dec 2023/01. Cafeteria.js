function solve(array) {
    let n = parseInt(array[0]);
    let baristas = {};

    for (let i = 1; i <= n; i++) {
        let [name, shift, coffeeArray] = array[i].split(/\s/gm);
        baristas[name] = {
            shift: shift,
            coffees: []
        };
        coffeeArray = coffeeArray.split(",");
        for (let coffee of coffeeArray) {
            baristas[name].coffees.push(coffee);
        }
    }

    let i = n + 1;
    while (i < array.length && array[i] !== "Closed") {
        let [command, name, arg1, arg2] = array[i].split(" / ");

        if (command === "Prepare") {
            let shift = arg1;
            let coffee = arg2;
            if (baristas[name].shift === shift && baristas[name].coffees.includes(coffee)) {
                console.log(`${name} has prepared a ${coffee} for you!`);
            } else {
                console.log(`${name} is not available to prepare a ${coffee}.`);
            }
        } else if (command === "Change Shift") {
            let newShift = arg1;
            baristas[name].shift = newShift;
            console.log(`${name} has updated his shift to: ${newShift}`);
        } else if (command === "Learn") {
            let newCoffee = arg1;
            if (baristas[name].coffees.includes(newCoffee)) {
                console.log(`${name} knows how to make ${newCoffee}.`);
            } else {
                baristas[name].coffees.push(newCoffee);
                console.log(`${name} has learned a new coffee type: ${newCoffee}.`);
            }
        }

        i++;
    }

    for (let name of Object.keys(baristas)) {
        console.log(`Barista: ${name}, Shift: ${baristas[name].shift}, Drinks: ${baristas[name].coffees.join(", ")}`);
    }
}