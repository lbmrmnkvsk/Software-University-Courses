function solve(entries) {
    let addressBook = {};

    for (let entry of entries) {
        let [name, street] = entry.split(":");
        addressBook[name] = street;
    }

    let sorted = Object.entries(addressBook);
    sorted.sort((a, b) => a[0].localeCompare(b[0]));

    for (let entry of sorted) {
        console.log(`${entry[0]} -> ${entry[1]}`);
    }
}

solve(['Tim:Doe Crossing',
    'Bill:Nelson Place',
    'Peter:Carlyle Ave',
    'Bill:Ornery Rd']
);
