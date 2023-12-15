function solve(array) {
    let n = parseInt(array[0]);
    let riders = {};

    for (let i = 1; i <= n; i++) {
        let [name, fuelCapacity, position] = array[i].split("|");
        riders[name] = {
            fuelCapacity: parseFloat(fuelCapacity),
            position: parseInt(position)
        };
    }

    let index = n + 1;
    while (index < array.length && array[index] !== "Finish") {
        let [command, name, arg1, arg2] = array[index].split(" - ");

        if (command === 'StopForFuel') {
            let minFuel = parseFloat(arg1);
            let changedPosition = parseInt(arg2);

            if (riders[name].fuelCapacity < minFuel) {
                riders[name].position = changedPosition;
                console.log(`${name} stopped to refuel but lost his position, now he is ${changedPosition}.`);
            } else {
                console.log(`${name} does not need to stop for fuel!`);
            }
        } else if (command === "Overtaking") {
            let name1 = name;
            let name2 = arg1;
            let pos1 = riders[name1].position;
            let pos2 = riders[name2].position;
            if (pos1 < pos2) {
                riders[name1].position = pos2;
                riders[name2].position = pos1;
                console.log(`${name1} overtook ${name2}!`);
            }
        } else  if (command === "EngineFail") {
            let laps = parseInt(arg1);
            console.log(`${name} is out of the race because of a technical issue, ${laps} laps before the finish.`);
            delete riders[name];
        }

        index++;
    }

    for (let name of Object.keys(riders)) {
        console.log(`${name}`);
        console.log(`  Final position: ${riders[name].position}`);
        delete riders[name];
    }
}