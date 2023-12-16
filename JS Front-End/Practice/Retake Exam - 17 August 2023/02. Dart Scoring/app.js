window.addEventListener("load", solve);

function solve() {
    let nameInput = document.getElementById("player");
    let scoreInput = document.getElementById("score");
    let roundInput = document.getElementById("round");
    let addBtn = document.getElementById("add-btn");
    addBtn.addEventListener('click', onAdd);

    function onAdd(event) {
        if (nameInput.value == "" || scoreInput.value == "" || roundInput.value == "") {
            return;
        }

        let sureList = document.getElementById("sure-list");
        let li = document.createElement("li");
        li.classList.add("dart-item");
        let article = document.createElement("article");
        let p1 = document.createElement('p');
        let p2 = document.createElement('p');
        let p3 = document.createElement('p');

        p1.textContent = nameInput.value;
        p2.textContent = "Score: " + scoreInput.value;
        p3.textContent = "Round: " + roundInput.value;

        let editBtn = document.createElement('button');
        editBtn.classList.add("btn");
        editBtn.classList.add("edit");
        editBtn.textContent = "edit";
        let okBtn = document.createElement('button');
        okBtn.classList.add("btn");
        okBtn.classList.add("ok");
        okBtn.textContent = "ok";

        article.appendChild(p1);
        article.appendChild(p2);
        article.appendChild(p3);
        li.appendChild(article);
        li.appendChild(editBtn);
        li.appendChild(okBtn);
        sureList.appendChild(li);

        addBtn.disabled = true;
        nameInput.value = "";
        scoreInput.value = "";
        roundInput.value = "";

        editBtn.addEventListener('click', onEdit);
        okBtn.addEventListener('click', onOk);
        let clearBtn = document.querySelector("btn clear");
        clearBtn.addEventListener('click', onClear);

        function onEdit(event) {
            let currentBtn = event.currentTarget;
            li = currentBtn.parentNode;
            sureList.removeChild(li);
            name = li.querySelectorAll("article p")[0].textContent;
            score = li.querySelectorAll("article p")[1].textContent.split(": ")[1];
            round = li.querySelectorAll("article p")[2].textContent.split(": ")[1];

            nameInput.value = name;
            scoreInput.value = score;
            roundInput.value = round;
            addBtn.disabled = false;
        }

        function onOk(event) {
            let currentBtn = event.currentTarget;
            let li = currentBtn.parentNode;
            let btn1 = li.querySelectorAll("button")[0];
            let btn2 = li.querySelectorAll("button")[1];
            li.removeChild(btn1);
            li.removeChild(btn2);
            sureList.removeChild(li);

            let scoreboardList = document.getElementById("scoreboard-list");
            scoreboardList.appendChild(li);
            addBtn.disabled = false;
            let clearBtn = document.querySelector(".clear");
            clearBtn.addEventListener('click', onClear);

            function onClear(event) {
                location.reload();
            }
        }

    }
}
  