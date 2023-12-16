
function attachEvents() {
    let baseUrl = "http://localhost:3030/jsonstore/tasks/";
    let locationInput = document.getElementById("location");
    let temperatureInput = document.getElementById("temperature");
    let dateInput = document.getElementById("date");
    let addBtn = document.getElementById("add-weather");
    let editBtn = document.getElementById("edit-weather");
    let listDiv = document.getElementById("list");
    let loadBtn = document.getElementById("load-history");
    addBtn.addEventListener('click', onAdd);
    editBtn.addEventListener('click', onEdit);
    loadBtn.addEventListener('click', onLoad);

    async function onLoad(event) {
        event.preventDefault();
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            listDiv.innerHTML = "";

            for (let id of Object.keys(data)) {
                let record = data[id];
                let liDiv = createLiDiv(record);
                listDiv.appendChild(liDiv);
            }
        } catch (error) {
            console.error(error);
        }
    }

    async function onChange(event) {
        event.preventDefault();
        let liDiv = event.currentTarget.parentNode.parentNode;
        listDiv.removeChild(liDiv);

        let location = liDiv.querySelector('h2').textContent;
        let date = liDiv.querySelectorAll('h3')[0].textContent;
        let temperature = liDiv.querySelectorAll('h3')[1].textContent;
        locationInput.value = location;
        dateInput.value = date;
        temperatureInput.value = temperature;

        editBtn.disabled = false;
        addBtn.disabled = true;
    }

    async function onEdit(event) {
        event.preventDefault();
        try {
            let id = await getId(locationInput.value, dateInput.value, temperatureInput.value);
            let modifiedRecord = {
                location: locationInput.value,
                temperature: temperatureInput.value,
                date: dateInput.value,
                _id: id
            };

            let response = await fetch(baseUrl + id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(modifiedRecord)
            });

            clearInputFields();
            await onLoad(event);
            editBtn.disabled = true;
            addBtn.disabled = false;
        } catch (error) {
            console.error(error);
        }
    }

    async function getId(location, date, temperature) {
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            let tempString = String(temperature);
            let dateString = String(date);
            let matchingRecord = Object.values(data).find(record =>
                record.location === location);

            console.log(matchingRecord);
            return matchingRecord._id;
        } catch (error) {
            console.error(error);
        }
    }

    async function onDelete(event) {
        event.preventDefault();
        let liDiv = event.currentTarget.parentNode.parentNode;
        let location = liDiv.querySelector('h2').textContent;
        let date = liDiv.querySelectorAll('h3')[0].textContent;
        let temperature = liDiv.querySelectorAll('h3')[1].textContent;

        try {
            let id = await getId(location, date, temperature);
            let response = await fetch(baseUrl + id, {
                method: 'DELETE'
            });
            await onLoad(event);
        } catch (error) {
            console.error(error);
        }
    }

    function createLiDiv(record) {
        let location = record.location;
        let temperature = record.temperature;
        let date = record.date;
        let liDiv = document.createElement('div');
        liDiv.classList.add("container");

        liDiv.innerHTML = `
        <h2>${location}</h2>
        <h3>${date}</h3>
        <h3 id="celsius">${temperature}</h3>
        <div class="buttons-container">
            <button class="change-btn">Change</button>
            <button class="delete-btn">Delete</button>
        </div>
        `;

        let changeBtn = liDiv.querySelector(".change-btn");
        let deleteBtn = liDiv.querySelector(".delete-btn");
        changeBtn.addEventListener('click', onChange);
        deleteBtn.addEventListener('click', onDelete);

        return liDiv;
    }

    async function onAdd(event) {
        event.preventDefault();
        if (locationInput.value == "" || temperatureInput.value == "" || dateInput.value == "") {
            return;
        }
        let newRecord = {
            location: locationInput.value,
            temperature: temperatureInput.value,
            date: dateInput.value
        }

        try {
            let response = await fetch(baseUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newRecord)
            });

            clearInputFields();
            await onLoad(event);
        } catch (error) {
            console.error(error);
        }
    }

    function clearInputFields() {
        locationInput.value = "";
        dateInput.value = "";
        temperatureInput.value = "";
    }
}

attachEvents();