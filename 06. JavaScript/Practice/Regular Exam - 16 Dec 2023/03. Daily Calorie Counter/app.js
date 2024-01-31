function attachEvents() {
    let baseUrl = "http://localhost:3030/jsonstore/tasks/"
    let loadBtn = document.getElementById("load-meals");
    let listDiv = document.getElementById("list");
    let addBtn = document.getElementById("add-meal");
    let editBtn = document.getElementById("edit-meal");

    loadBtn.addEventListener('click', onLoad);
    addBtn.addEventListener('click', onAdd);


    let foodInput = document.getElementById("food");
    let timeInput = document.getElementById("time");
    let caloriesInput = document.getElementById("calories");

    function clearAllInputFields() {
        foodInput.value = "";
        timeInput.value = "";
        caloriesInput.value = "";
    }

    async function onLoad(event) {
        event.preventDefault();
        listDiv.innerHTML = "";
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            for (let id of Object.keys(data)) {
                let meal = data[id];
                let food = meal.food;
                let calories = meal.calories;
                let time = meal.time;

                let mealDiv = document.createElement('div');
                mealDiv.classList.add("meal");
                mealDiv.innerHTML = `
                    <h2>${food}</h2>
                    <h3>${time}</h3>
                    <h3>${calories}</h3>
                    <div id="meal-buttons">
                        <button class="change-meal">Change</button>
                        <button class="delete-meal">Delete</button>
                    </div>
                `;

                let changeBtn = mealDiv.querySelector(".change-meal");
                let deleteBtn = mealDiv.querySelector(".delete-meal");
                changeBtn.addEventListener('click', onChange);
                deleteBtn.addEventListener('click', onDelete);

                listDiv.appendChild(mealDiv);
            }
        } catch (error) {
            console.error(error);
        }
    }

    async function onChange(event) {
        event.preventDefault();
        let mealDiv = event.currentTarget.parentNode.parentNode;
        listDiv.removeChild(mealDiv);

        let food = mealDiv.querySelectorAll('h2')[0].textContent;
        let time = mealDiv.querySelectorAll('h3')[0].textContent;
        let calories = mealDiv.querySelectorAll('h3')[1].textContent;
        foodInput.value = food;
        timeInput.value = time;
        caloriesInput.value = calories;

        editBtn.disabled = false;
        addBtn.disabled = true;
        editBtn.addEventListener('click', onEdit);

        let mealId = await getMealId(food);

        async function onEdit(event) {
            event.preventDefault();
            let modifiedMeal = {
                food: foodInput.value,
                calories: caloriesInput.value,
                time: timeInput.value,
                _id: mealId
            };

            try {
                let response = await fetch(baseUrl + mealId, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(modifiedMeal)
                });

                await onLoad(event);
                editBtn.disabled = true;
                addBtn.disabled = false;
            } catch (error) {
                console.error(error);
            }
        }
    }

    async function onDelete(event) {
        event.preventDefault();
        let mealDiv = event.currentTarget.parentNode.parentNode;
        let food = mealDiv.querySelectorAll('h2')[0].textContent;

        try {
            let id = await getMealId(food);
            let response = await fetch(baseUrl + id, {
                method: 'DELETE'
            });
            await onLoad(event);
        } catch (error) {
            console.error(error);
        }
    }

    async function onAdd(event) {
        event.preventDefault();
        if (foodInput.value == "" || caloriesInput.value == "" || timeInput.value == "") {
            return;
        }

        let meal = {
            food: foodInput.value,
            calories: caloriesInput.value,
            time: timeInput.value
        };

        try {
            let response = await fetch(baseUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(meal)
            });

            clearAllInputFields();
            onLoad(event);
        } catch (error) {
            console.error(error);
        }
    }


    async function getMealId(food) {
        try {
            let response = await fetch(baseUrl);
            let data = await response.json();
            let matchingMeal = Object.values(data).find(meal =>
            meal.food == food);

            console.log(matchingMeal._id);
            return matchingMeal._id;
        } catch (error) {
            console.error(error);
        }
    }
}

attachEvents();