function solve(string, start, count) {
    let output = string.substr(start, start + count + 1);
    console.log(output);
}

solve('ASentence', 1, 8);
solve('SkipWord', 4, 7);