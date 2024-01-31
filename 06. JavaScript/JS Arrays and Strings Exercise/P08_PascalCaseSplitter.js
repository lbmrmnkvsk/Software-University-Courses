function solve(text) {
    let matches = text.match(/[A-Z][a-z]*/gm);
    console.log(matches.join(', '));
}

solve('SplitMeIfYouCanHaHaYouCantOrYouCan');
solve('HoldTheDoor');
solve('ThisIsSoAnnoyingToDo');