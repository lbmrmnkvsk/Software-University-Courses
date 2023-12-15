window.addEventListener("load", solve);

function solve() {
    let nextBtn = document.getElementById("next-btn");
    nextBtn.addEventListener('click', nextFunction);

    function nextFunction(event) {
        let studentInput = document.getElementById("student");
        let universityInput = document.getElementById("university");
        let scoreInput = document.getElementById("score");

        if (studentInput.value == "" || universityInput.value == "" || scoreInput.value == "") {
            return;
        }

        let li = document.createElement('li');
        li.classList.add("application");
        let article = document.createElement('article');
        let h4 = document.createElement('h4');
        let p1 = document.createElement('p');
        let p2 = document.createElement('p');

        h4.textContent = studentInput.value;
        p1.textContent = `University: ${universityInput.value}`;
        p2.textContent = `Score: ${scoreInput.value}`;

        let editBtn = document.createElement('button');
        editBtn.classList.add("action-btn");
        editBtn.classList.add("edit");
        editBtn.textContent = "edit";
        editBtn.addEventListener('click', editFunction);

        let applyBtn = document.createElement('button');
        applyBtn.classList.add("action-btn");
        applyBtn.classList.add("apply");
        applyBtn.textContent = "apply";
        applyBtn.addEventListener('click', applyFunction);

        article.appendChild(h4);
        article.appendChild(p1);
        article.appendChild(p2);
        let previewList = document.getElementById("preview-list");
        previewList.appendChild(li);
        li.appendChild(article);
        li.appendChild(editBtn);
        li.appendChild(applyBtn);

        nextBtn.disabled = true;
        studentInput.value = '';
        universityInput.value = "";
        scoreInput.value = "";

        function editFunction(event) {
            let currentBtn = event.currentTarget;
            let li = currentBtn.parentNode;
            let h4 = li.querySelector("article h4");
            let p1 = li.querySelectorAll("article p")[0];
            let p2 = li.querySelectorAll("article p")[1];
            let university = p1.textContent.split(": ")[1];
            let score = p2.textContent.split(": ")[1];

            studentInput.value = h4.textContent;
            universityInput.value = university;
            scoreInput.value = score;

            previewList.removeChild(li);
            nextBtn.disabled = false;
        }

        function applyFunction(event) {
            let candidatesList = document.getElementById('candidates-list');
            let btn = event.currentTarget;
            let li = btn.parentNode;
            previewList.removeChild(li);
            let btn1 = li.querySelectorAll('button')[0];
            let btn2 = li.querySelectorAll('button')[1];
            li.removeChild(btn1);
            li.removeChild(btn2);

            candidatesList.appendChild(li);
            nextBtn.disabled = false;
        }
    }
}
  