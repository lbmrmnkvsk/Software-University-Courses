function solve(text) {
    let matches = text.match(/#[A-Za-z]+/gm);

    for (let match of matches) {
        let word = match.substring(1);
        console.log(word);
    }
}

solve('Nowadays everyone uses # to tag a #special word in #socialMedia');
solve('The symbol # is known #variously in English-speaking #regions as the #number sign');