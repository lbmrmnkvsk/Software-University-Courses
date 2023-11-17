function solve(array) {
    class Song {
        constructor(typeList, name, time) {
            this.typeList = typeList;
            this.name = name;
            this.time = time;
        }
    }

    let songs = [];
    for (let i = 1; i < array.length - 1; i++) {
        let [typeList, name, time] = array[i].split("_");
        songs.push(new Song(typeList, name, time));
    }
    let playlist = array[array.length - 1];

    if (playlist === "all") {
        for (let song of songs) {
            console.log(song.name);
        }
    } else {
        for (let song of songs) {
            if (song.typeList === playlist) {
                console.log(song.name);
            }
        }
    }
}

solve([3,
    'favourite_DownTown_3:14',
    'favourite_Kiss_4:16',
    'favourite_Smooth Criminal_4:01',
    'favourite']
);
solve([4,
    'favourite_DownTown_3:14',
    'listenLater_Andalouse_3:24',
    'favourite_In To The Night_3:58',
    'favourite_Live It Up_3:48',
    'listenLater']
);
solve([2,
    'like_Replay_3:15',
    'ban_Photoshop_3:48',
    'all']
);