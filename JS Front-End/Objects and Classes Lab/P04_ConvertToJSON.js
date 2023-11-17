function solve(name, lastName, hairColor) {
    let person = {
        name: name,
        lastName: lastName,
        hairColor: hairColor
    };
    console.log(JSON.stringify(person));
}

solve('George', 'Jones', 'Brown');
solve('Peter', 'Smith', 'Blond');