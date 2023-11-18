function solve(entries) {
    class Movie {
        constructor(name) {
            this.name = name;
            this.director = null;
            this.date = null;
        }

        hasAllInfo() {
            return this.name !== null && this.director !== null && this.date !== null;
        }
    }

    let movies = [];

    for (let entry of entries) {
        if (entry.includes("addMovie")) {
            let [command, name] = entry.split("Movie ");
            movies.push(new Movie(name));
        } else if (entry.includes(" directedBy ")) {
            let [name, director] = entry.split(" directedBy ");
            let movie = movies.find(m => m.name === name);
            if (movie !== undefined) {
                movie.director = director;
            }
        } else if (entry.includes(" onDate ")) {
            let [name, date] = entry.split(" onDate ");
            let movie = movies.find(m => m.name === name);
            if (movie !== undefined) {
                movie.date = date;
            }
        }
    }

    for (let movie of movies) {
        if (movie.hasAllInfo()) {
            console.log(JSON.stringify(movie));
        }
    }
}

solve([
        'addMovie Fast and Furious',
        'addMovie Godfather',
        'Inception directedBy Christopher Nolan',
        'Godfather directedBy Francis Ford Coppola',
        'Godfather onDate 29.07.2018',
        'Fast and Furious onDate 30.07.2018',
        'Batman onDate 01.08.2018',
        'Fast and Furious directedBy Rob Cohen'
    ]
);