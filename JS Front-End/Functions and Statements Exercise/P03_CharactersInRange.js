function solve(char1, char2) {
    let char1code = char1.charCodeAt(0);
    let char2code = char2.charCodeAt(0);
    const start = Math.min(char1code, char2code) + 1;
    const end = Math.max(char1code, char2code) - 1;

    let result = "";
    for (let i = start; i <= end; i++) {
        result += String.fromCharCode(i) + " ";
    }

    console.log(result.trim());
}

solve('a', 'd');
solve('#', ':');
solve('C', '#');