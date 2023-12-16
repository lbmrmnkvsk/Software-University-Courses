function solve(array) {
    let n = parseInt(array[0]);
    let astronauts = {};

    for (let i = 1; i <= n; i++) {
        let [name, oxygen, energy] = array[i].split(/\s+/g);
        astronauts[name] = {
            oxygen: parseFloat(oxygen), energy: parseFloat(energy)
        };
    }

    let i = n + 1;
    while (i < array.length && array[i] !== "End") {
        let [command, name, amount] = array[i].split(" - ");
        amount = parseFloat(amount);

        if (command === "Explore") {
            if (astronauts[name].energy >= amount) {
                astronauts[name].energy -= amount;
                console.log(`${name} has successfully explored a new area and now has ${astronauts[name].energy} energy!`);
            } else {
                console.log(`${name} does not have enough energy to explore!`);
            }
        } else if (command === "Refuel") {
            let oldEnergy = astronauts[name].energy;
            astronauts[name].energy += amount;
            if (astronauts[name].energy > 200) {
                astronauts[name].energy = 200;
            }
            let newEnergy = astronauts[name].energy;
            let diff = newEnergy - oldEnergy;
            console.log(`${name} refueled their energy by ${diff}!`);
        } else if (command === "Breathe") {
            let oldOxygen = astronauts[name].oxygen;
            astronauts[name].oxygen += amount;
            if (astronauts[name].oxygen > 100) {
                astronauts[name].oxygen = 100;
            }
            let newOxygen = astronauts[name].oxygen;
            let difference = newOxygen - oldOxygen;
            console.log(`${name} took a breath and recovered ${difference} oxygen!`);
        }

        i++;
    }

    for (let name of Object.keys(astronauts)) {
        let oxygen = astronauts[name].oxygen;
        let energy = astronauts[name].energy;
        console.log(`Astronaut: ${name}, Oxygen: ${oxygen}, Energy: ${energy}`);
    }
}

solve(['3', 'John 50 120', 'Kate 80 180', 'Rob 70 150', 'Explore - John - 50', 'Refuel - Kate - 30', 'Breathe - Rob - 20', 'End']);
console.log("-----------");
solve(['4', 'Alice 60 100', 'Bob 40 80', 'Charlie 70 150', 'Dave 80 180', 'Explore - Bob - 60', 'Refuel - Alice - 30', 'Breathe - Charlie - 50', 'Refuel - Dave - 40', 'Explore - Bob - 40', 'Breathe - Charlie - 30', 'Explore - Alice - 40', 'End']);