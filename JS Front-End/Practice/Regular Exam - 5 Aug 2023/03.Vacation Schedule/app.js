let loadBtn = document.getElementById("load-vacations");
let addBtn = document.getElementById("add-vacation");
let editBtn = document.getElementById("edit-vacation");
let baseUrl = "http://localhost:3030/jsonstore/tasks/"
let nameInput = document.getElementById("name");
let daysInput = document.getElementById("num-days");
let dateInput = document.getElementById("from-date");
let list = document.getElementById("list");


function attachEvents() {
    loadBtn.addEventListener('click', onLoad);
    addBtn.addEventListener('click', onAdd);

    async function onLoad(event) {
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            let list = document.getElementById("list");
            list.innerHTML = "";

            for (let id of Object.keys(data)) {
                let vacationObj = data[id];
                let vacationDiv = createVacation(vacationObj);
                list.appendChild(vacationDiv);
            }
        } catch (error) {
            console.log(error);
        }
    }

    function createVacation(object) {
        let vacationDiv = document.createElement('div');
        vacationDiv.classList.add("container");
        vacationDiv.innerHTML = `
        <h2>${object.name}</h2>
        <h3>${object.date}</h3>
        <h3>${object.days}</h3>
        <button class="change-btn">Change</button>
        <button class="done-btn">Done</button>
        `;

        vacationDiv.querySelector(".change-btn").addEventListener('click', onChange);
        vacationDiv.querySelector(".done-btn").addEventListener('click', onDone);

        return vacationDiv;
    }

    async function onAdd(event) {
        event.preventDefault();
        let nameInput = document.getElementById("name");
        let daysInput = document.getElementById("num-days");
        let dateInput = document.getElementById("from-date");
        if (nameInput.value == "" || daysInput.value == "" || dateInput.value == "") {
            return;
        }

        let vacationData = {
            name: nameInput.value,
            days: daysInput.value,
            date: dateInput.value
        };

        try {
            let response = await fetch(baseUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vacationData)
            });

            nameInput.value = "";
            daysInput.value = "";
            dateInput.value = "";

            await onLoad();
        } catch (error) {
            console.log(error);
        }
    }

    async function onChange(event) {
        let currentBtn = event.currentTarget;
        let vacationDiv = currentBtn.parentNode;
        let list = document.getElementById("list");
        list.removeChild(vacationDiv);
        nameInput.value = vacationDiv.querySelectorAll('h2')[0].textContent;
        dateInput.value = vacationDiv.querySelectorAll('h3')[0].textContent;
        daysInput.value = vacationDiv.querySelectorAll('h3')[1].textContent;

        let vacationId = await getIdByName(nameInput.value, dateInput.value, daysInput.value);
        editBtn.disabled = false;
        addBtn.disabled = true;
        editBtn.addEventListener('click', onEdit);

        async function onEdit(event) {
            event.preventDefault();
            try {
                let updatedVacation = {
                    name: nameInput.value,
                    days: daysInput.value,
                    date: dateInput.value,
                    _id: vacationId
                };

                let response = await fetch(baseUrl + vacationId, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedVacation)
                });

                nameInput.value = "";
                daysInput.value = "";
                dateInput.value = "";
                await onLoad();
                editBtn.disabled = true;
                addBtn.disabled = false;
            } catch (error) {
                console.log(error);
            }

        }
    }

    async function getIdByName(name, date, days) {
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            let matchingVacation = Object.values(data).find(vacation =>
                vacation.name == name && vacation.date == date && vacation.days == days);

            console.log(name);
            console.log(date);
            console.log(days);
            console.log(matchingVacation._id);
            console.log(matchingVacation);

            return matchingVacation._id;
        } catch (error) {
            console.log(error);
        }
    }

    async function onDone(event) {
        let currentBtn = event.currentTarget;
        let vacationDiv = currentBtn.parentNode;
        let vacationName = vacationDiv.querySelectorAll('h2')[0].textContent;
        let vacationDate = vacationDiv.querySelectorAll('h3')[0].textContent;
        let vacationDays = vacationDiv.querySelectorAll('h3')[1].textContent;
        try {
            let vacationId = await getIdByName(vacationName, vacationDate, vacationDays);
            let response = await fetch(baseUrl + vacationId, {
                method: 'DELETE'
            });
            await onLoad();
        } catch (error) {
            console.log(error);
        }
    }
}

attachEvents();
