function generateReport() {
    // Get all checkboxes in the header
    const checkboxes = document.querySelectorAll('thead input[type="checkbox"]');

    // Get all rows in the table body
    const rows = document.querySelectorAll('tbody tr');

    // Create an array to store the report data
    const reportData = [];

    // Loop through each row
    rows.forEach(row => {
        // Create an object to store the data for each row
        const rowData = {};

        // Loop through each cell in the row
        row.querySelectorAll('td').forEach((cell, index) => {
            // Check if the corresponding checkbox is checked
            if (checkboxes[index].checked) {
                // Get the name attribute of the checkbox
                const columnName = checkboxes[index].name;

                // Add the cell data to the rowData object
                rowData[columnName] = cell.textContent;
            }
        });

        // Add the rowData object to the reportData array
        reportData.push(rowData);
    });

    // Convert the reportData array to a JSON string
    const reportJson = JSON.stringify(reportData, null, 2);

    // Display the JSON string in the textarea with id "output"
    document.getElementById('output').value = reportJson;
}
