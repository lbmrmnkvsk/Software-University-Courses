function attachEvents() {
    const locationInput = document.getElementById('location');
    const submitButton = document.getElementById('submit');

    submitButton.addEventListener('click', () => {
        const locationName = locationInput.value;
        getWeather(locationName);
    });
}

async function getWeather(locationName) {
    try {
        const locationsResponse = await fetch('http://localhost:3030/jsonstore/forecaster/locations');
        const locationsData = await locationsResponse.json();

        const location = locationsData.find(l => l.name === locationName);

        if (!location) {
            handleError();
            return;
        }

        const todayResponse = await fetch(`http://localhost:3030/jsonstore/forecaster/today/${location.code}`);
        const todayData = await todayResponse.json();

        const upcomingResponse = await fetch(`http://localhost:3030/jsonstore/forecaster/upcoming/${location.code}`);
        const upcomingData = await upcomingResponse.json();

        displayForecast(todayData, upcomingData);
    } catch (error) {
        handleError();
    }
}

function displayForecast(current, upcoming) {
    const forecastDiv = document.getElementById('forecast');
    forecastDiv.style.display = 'block';

    displayCurrentConditions(current);
    displayUpcomingForecast(upcoming);
}

function displayCurrentConditions(current) {
    const currentDiv = document.getElementById('current');
    currentDiv.innerHTML = `
        <div class="label">Current conditions</div>
        <div class="condition">
            <span class="forecast-data">${current.name}</span>
            <span class="symbol">${getWeatherSymbol(current.forecast.condition)}</span>
            <span class="forecast-data">${current.forecast.low}°/${current.forecast.high}°</span>
            <span class="forecast-data">${current.forecast.condition}</span>
        </div>
    `;
}

function displayUpcomingForecast(upcoming) {
    const upcomingDiv = document.getElementById('upcoming');
    upcomingDiv.innerHTML = `
        <div class="label">Three-day forecast</div>
        <div class="forecast-info">
            ${upcoming.forecast.map(day => `
                <span class="upcoming">
                    <span class="symbol">${getWeatherSymbol(day.condition)}</span>
                    <span class="forecast-data">${day.low}°/${day.high}°</span>
                    <span class="forecast-data">${day.condition}</span>
                </span>
            `).join('')}
        </div>
    `;
}

function getWeatherSymbol(condition) {
    const symbols = {
        'Sunny': '&#x2600;',
        'Partly sunny': '&#x26C5;',
        'Overcast': '&#x2601;',
        'Rain': '&#x2614;'
    };

    return symbols[condition] || '';
}

function handleError() {
    const forecastDiv = document.getElementById('forecast');
    forecastDiv.style.display = 'block';

    const currentDiv = document.getElementById('current');
    currentDiv.innerHTML = '<div class="label">Error</div>';

    const upcomingDiv = document.getElementById('upcoming');
    upcomingDiv.innerHTML = '<div class="label">Error</div>';
}

attachEvents();
