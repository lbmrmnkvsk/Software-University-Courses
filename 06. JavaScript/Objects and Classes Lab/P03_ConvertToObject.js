function solve(jsonString) {
    let person = JSON.parse(jsonString);
    for (let key in person) {
        console.log(`${key}: ${person[key]}`);
    }
}

solve('{"name": "George", "age": 40, "town": "Sofia"}');
solve('{"name": "Peter", "age": 35, "town": "Plovdiv"}');