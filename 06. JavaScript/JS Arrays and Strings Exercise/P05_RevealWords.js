function solve(search, sentence) {
    // let words = [];
    // let text = [];
    let words = search.split(', ');
    let text = sentence.split(' ');

    for (let i = 0; i < text.length; i++) {
        if (text[i].startsWith('*')) {
            for (let word of words) {
                if (text[i].length === word.length) {
                    text[i] = word;
                }
            }
        }
    }

    let result = '';
    for (let word of text) {
        result += word + ' ';
    }

    console.log(result.trim());
}

solve('great', 'softuni is ***** place for learning new programming languages');

