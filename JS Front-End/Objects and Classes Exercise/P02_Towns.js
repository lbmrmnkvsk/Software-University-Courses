function solve(entries) {
    class Town {
        constructor(town, latitude, longitude) {
            this.town = town;
            this.latitude = parseFloat(latitude).toFixed(2);
            this.longitude = parseFloat(longitude).toFixed(2);
        }

        print() {
            console.log(`{ town: '${this.town}', latitude: '${this.latitude}', longitude: '${this.longitude}' }`)
        }
    }

    let towns = [];

    for (let entry of entries) {
        let [town, latitude, longitude] = entry.split(" | ");
        let current = new Town(town, latitude, longitude);
        towns.push(current);
        current.print();
    }


}

console.log(solve(['Sofia | 42.696552 | 23.32601',
    'Beijing | 39.913818 | 116.363625']
));
console.log(solve(['Plovdiv | 136.45 | 812.575']));