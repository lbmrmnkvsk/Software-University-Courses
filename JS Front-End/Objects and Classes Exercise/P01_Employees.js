function solve(entries) {
    class Employee {
        constructor(name) {
            this.name = name;
            this.number = name.length;
        }

        print() {
            console.log(`Name: ${this.name} -- Personal Number: ${this.number}`);
        }
    }

    let employees = [];
    for (let name of entries) {
        let employee = new Employee(name);
        employees.push(employee);
        employee.print();
    }
}

solve([
        'Silas Butler',
        'Adnaan Buckley',
        'Juan Peterson',
        'Brendan Villarreal'
    ]
);
solve([
        'Samuel Jackson',
        'Will Smith',
        'Bruce Willis',
        'Tom Holland'
    ]
);