function attachEventsListeners() {
    let daysInput = document.getElementById('days');
    let hoursInput = document.getElementById('hours');
    let minutesInput = document.getElementById('minutes');
    let secondsInput = document.getElementById('seconds');

    let daysBtn = document.getElementById('daysBtn');
    let hoursBtn = document.getElementById('hoursBtn');
    let minutesBtn = document.getElementById('minutesBtn');
    let secondsBtn = document.getElementById('secondsBtn');

    daysBtn.addEventListener('click', convertDays);
    hoursBtn.addEventListener('click', convertHours);
    minutesBtn.addEventListener('click', convertMinutes);
    secondsBtn.addEventListener('click', convertSeconds);

    function convertDays() {
        let days = parseFloat(daysInput.value);
        hoursInput.value = days * 24;
        minutesInput.value = hoursInput.value * 60;
        secondsInput.value = minutesInput.value * 60;
    }

    function convertHours () {
        let hours = parseFloat(hoursInput.value);
        daysInput.value = hours / 24;
        minutesInput.value = hours * 60;
        secondsInput.value = minutesInput.value * 60;
    }

    function convertMinutes() {
        let minutes = parseFloat(minutesInput.value);
        hoursInput.value = minutes / 60;
        daysInput.value = hoursInput.value / 24;
        secondsInput.value = minutes * 60;
    }

    function convertSeconds() {
        let seconds = parseFloat(secondsInput.value);
        minutesInput.value = seconds / 60;
        hoursInput.value = minutesInput.value / 60;
        daysInput.value = hoursInput.value / 24;
    }
}