function solve(text, word) {
    let array = text.split(/\s+/gm);
    let count = 0;

    for(let current of array) {
        if(current === word) {
            count++;
        }
    }

    console.log(count);
}

solve('This is a word and it also is a sentence',
'is'
);

solve('softuni is great place for learning new programming languages',
'softuni'
);