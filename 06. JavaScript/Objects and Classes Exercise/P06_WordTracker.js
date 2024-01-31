function solve(input) {
    class Word {
        constructor(word) {
            this.word = word;
            this.count = 0;
        }

        print() {
            console.log(`${this.word} - ${this.count}`);
        }
    }

    let words = [];
    let wordsWords = input[0].split(" ");
    wordsWords.forEach(w => words.push(new Word(w)));

    for (let word of words) {
        for (let i = 1; i < input.length; i++) {
            if (word.word === input[i]) {
                word.count++;
            }
        }
    }

    words.sort((a, b) => b.count - a.count);
    words.forEach(w => w.print());
}

solve([
        'this sentence',
        'In', 'this', 'sentence', 'you', 'have', 'to', 'count', 'the', 'occurrences', 'of', 'the', 'words', 'this', 'and', 'sentence', 'because', 'this', 'is', 'your', 'task'
    ]
);
console.log("");
solve([
    'is the',
    'first', 'sentence', 'Here', 'is', 'another', 'the', 'And', 'finally', 'the', 'the', 'sentence']
);