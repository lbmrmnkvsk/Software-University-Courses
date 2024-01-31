function solve(entries) {
    let meetings = {};

    for (let entry of entries) {
        let [day, name] = entry.split(' ');
        if (meetings.hasOwnProperty(day)) {
            console.log(`Conflict on ${day}!`);
        } else {
            console.log(`Scheduled for ${day}`);
            meetings[day] = name;
        }
    }

    for (let day in meetings) {
        console.log(`${day} -> ${meetings[day]}`);
    }
}

solve(['Monday Peter',
    'Wednesday Bill',
    'Monday Tim',
    'Friday Tim']
)
console.log('----------');
solve(['Friday Bob',
    'Saturday Ted',
    'Monday Bill',
    'Monday John',
    'Wednesday George']
);