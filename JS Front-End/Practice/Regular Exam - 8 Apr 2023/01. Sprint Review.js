function solve(array) {
    let n = parseInt(array[0]);
    let board = {};

    for (let i = 1; i <= n; i++) {
        let [assignee, id, title, status, points] = array[i].split(":");
        points = Number(points);

        if (!board.hasOwnProperty(assignee)) {
            board[assignee] = [];
        }
        board[assignee].push({
            id, title, status, points
        })
    }

    for (let i = n + 1; i < array.length; i++) {
        let [command, assignee, arg1, arg2, arg3, arg4] = array[i].split(":");

        if (command === "Add New") {
            if (!board.hasOwnProperty(assignee)) {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            } else {
                let id = arg1;
                let title = arg2;
                let status = arg3;
                board[assignee].push({
                    id,
                    title,
                    status,
                    points: Number(arg4)
                });
            }
        } else if (command === "Change Status") {
            if (!board.hasOwnProperty(assignee)) {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            } else {
                let id = arg1;
                let newStatus = arg2;
                let task = board[assignee].find(t => t.id === id);
                if (task === undefined) {
                    console.log(`Task with ID ${id} does not exist for ${assignee}!`);
                } else {
                    task.status = newStatus;
                }
            }
        } else if (command === "Remove Task") {
            if (!board.hasOwnProperty(assignee)) {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            } else {
                let tasks = board[assignee];
                let index = parseInt(arg1);
                if (index >= tasks.length) {
                    console.log(`Index is out of range!`);
                } else {
                    tasks.splice(index, 1);
                }
            }
        }
    }

    let toDo = 0;
    let inProgress = 0;
    let codeReview = 0;
    let done = 0;

    for (let assignee of Object.keys(board)) {
        for (let task of board[assignee]) {
            if (task.status === 'ToDo') {
                toDo += task.points;
            } else if (task.status === 'In Progress') {
                inProgress += task.points;
            } else if (task.status === 'Code Review') {
                codeReview += task.points;
            } else if (task.status === 'Done') {
                done += task.points;
            }
        }
    }

    let notDone = toDo + inProgress + codeReview;

    console.log(`ToDo: ${toDo}pts`);
    console.log(`In Progress: ${inProgress}pts`);
    console.log(`Code Review: ${codeReview}pts`);
    console.log(`Done Points: ${done}pts`);

    if (done >= notDone) {
        console.log("Sprint was successful!");
    } else {
        console.log("Sprint was unsuccessful...");
    }
}

solve(['5', 'Kiril:BOP-1209:Fix Minor Bug:ToDo:3', 'Mariya:BOP-1210:Fix Major Bug:In Progress:3', 'Peter:BOP-1211:POC:Code Review:5', 'Georgi:BOP-1212:Investigation Task:Done:2', 'Mariya:BOP-1213:New Account Page:In Progress:13', 'Add New:Kiril:BOP-1217:Add Info Page:In Progress:5', 'Change Status:Peter:BOP-1290:ToDo', 'Remove Task:Mariya:1', 'Remove Task:Joro:1',]);
console.log("--------");
solve(['4', 'Kiril:BOP-1213:Fix Typo:Done:1', 'Peter:BOP-1214:New Products Page:In Progress:2', 'Mariya:BOP-1215:Setup Routing:ToDo:8', 'Georgi:BOP-1216:Add Business Card:Code Review:3', 'Add New:Sam:BOP-1237:Testing Home Page:Done:3', 'Change Status:Georgi:BOP-1216:Done', 'Change Status:Will:BOP-1212:In Progress', 'Remove Task:Georgi:3', 'Change Status:Mariya:BOP-1215:Done',]);