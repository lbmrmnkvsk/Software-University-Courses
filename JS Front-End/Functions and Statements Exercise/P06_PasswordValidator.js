function solve(password) {
    let result = true;

    if (password.length >= 6 && password.length <= 10) {
        // nothing
    } else {
        result = false;
        console.log("Password must be between 6 and 10 characters");
    }

    if (!/^[a-zA-Z0-9]+$/.test(password)) {
        result = false;
        console.log("Password must consist only of letters and digits");
    }

    if ((password.match(/\d/gm) || []).length < 2) {
        result = false;
        console.log("Password must have at least 2 digits");
    }

    if (result === true) {
        console.log("Password is valid");
    }
}

solve('logIn');
solve('MyPass123');
solve('Pa$s$s');