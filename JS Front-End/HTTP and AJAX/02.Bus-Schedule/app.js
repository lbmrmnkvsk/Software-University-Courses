function solve() {
    let currentStopId = 'depot';
    let isMoving = false;
    let baseUrl = 'http://localhost:3030/jsonstore/bus/schedule/';
    async function depart() {
        try {
            let response = await fetch(baseUrl + currentStopId);
            let data = await response.json();
            let stopName = data.name;
            currentStopId = data.next;
            isMoving = true;

            updateInfoBox(`Next stop ${stopName}`);
            disableButton('depart');
            enableButton('arrive');
        } catch (error) {
            handleInvalidData();
        }
    }

    function updateInfoBox(text) {
        let infoBox = document.querySelector('.info');
        infoBox.textContent = text;
    }

    function disableButton(buttonId) {
        let button = document.getElementById(buttonId);
        button.disabled = true;
    }

    function enableButton(buttonId) {
        let button = document.getElementById(buttonId);
        button.disabled = false;
    }

    function handleInvalidData() {
        updateInfoBox('Error');
        disableButton('arrive');
        disableButton('depart');
    }

    async function arrive() {
        try {
            let response = await fetch(baseUrl + currentStopId);
            let data = await response.json();
            let stopName = data.name;

            updateInfoBox(`Arriving at ${stopName}`);
            disableButton('arrive');
            enableButton('depart');
        } catch (error) {
            handleInvalidData();
        }
    }

    return {
        depart,
        arrive
    };
}

let result = solve();