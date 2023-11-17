function solve(entries) {
    let phoneBook = {};

    for (let entry of entries) {
        let [name, number] = entry.split(' ');
        name = name.trim();
        phoneBook[name] = number;
    }

    for (let name in phoneBook) {
        console.log(`${name} -> ${phoneBook[name]}`);
    }
}

solve(['Tim 0834212554',
    'Peter 0877547887',
    'Bill 0896543112',
    'Tim 0876566344']
);
solve(['George 0552554',
    'Peter 087587',
    'George 0453112',
    'Bill 0845344']
);