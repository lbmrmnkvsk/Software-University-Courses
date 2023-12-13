async function getInfo() {
    const stopId = document.getElementById('stopId').value;
    const stopNameDiv = document.getElementById('stopName');
    const busesList = document.getElementById('buses');

    // Clear previous results
    stopNameDiv.textContent = '';
    busesList.innerHTML = '';

    try {
        const response = await fetch(`http://localhost:3030/jsonstore/bus/businfo/${stopId}`);
        const data = await response.json();

        if (response.ok && data) {
            const stopName = data.name;
            const buses = data.buses;

            stopNameDiv.textContent = stopName;

            for (const [busId, time] of Object.entries(buses)) {
                const listItem = document.createElement('li');
                listItem.textContent = `Bus ${busId} arrives in ${time} minutes`;
                busesList.appendChild(listItem);
            }
        } else {
            stopNameDiv.textContent = 'Error';
        }
    } catch (error) {
        stopNameDiv.textContent = 'Error';
        console.error('Error fetching data:', error.message);
    }
}
