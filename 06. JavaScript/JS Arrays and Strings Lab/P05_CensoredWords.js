function solve(text, word) {
    let asterisks = "*".repeat(word.length);

    while(text.includes(word)) {
        text = text.replace(word, asterisks);
    }

    console.log(text);
}

solve('A small sentence with some words', 'small');
solve('Find the hidden word', 'hidden');