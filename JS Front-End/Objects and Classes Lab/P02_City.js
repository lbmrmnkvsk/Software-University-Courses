function printCityInfo(city) {
    for (let key in city) {
        console.log(`${key} -> ${city[key]}`);
    }
}

printCityInfo({
    name: "Sofia", area: 492, population: 1238438, country: "Bulgaria", postCode: "1000"
});
printCityInfo({
    name: "Plovdiv", area: 389, population: 1162358, country: "Bulgaria", postCode: "4000"
});