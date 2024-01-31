function solve(entries) {
    class Hero {
        constructor(name, level, ...items) {
            this.name = name;
            this.level = level;
            this.items = items;
        }

        print() {
            console.log(`Hero: ${this.name}`);
            console.log(`level => ${this.level}`);
            console.log(`items => ${this.items.join(", ")}`);
        }
    }

    let heroes = [];

    for (let entry of entries) {
        let [name, level, itemsString] = entry.split(" / ");
        let items = itemsString.split(", ");
        let hero = new Hero(name, level, ...items);
        heroes.push(hero);
    }

    heroes.sort((a, b) => a.level - b.level);
    heroes.forEach(h => h.print());
}

solve([
        'Isacc / 25 / Apple, GravityGun',
        'Derek / 12 / BarrelVest, DestructionSword',
        'Hes / 1 / Desolator, Sentinel, Antara'
    ]
);
console.log("");
solve([
        'Batman / 2 / Banana, Gun',
        'Superman / 18 / Sword',
        'Poppy / 28 / Sentinel, Antara'
    ]
);