function solve(input) {
    let dictionary = {};

    for (let json of input) {
        let data = JSON.parse(json);

        for (let term in data) {
            dictionary[term] = data[term];
        }
    }

    let sortedTerms = Object.keys(dictionary).sort();
    for (let term of sortedTerms) {
        console.log(`Term: ${term} => Definition: ${dictionary[term]}`);
    }
}