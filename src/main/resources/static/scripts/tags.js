document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.games-list');
    
    // Fetch and update checkboxes and text inputs based on server data
    fetch('/api/teamplayer/gettags')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the response as JSON
    })
    .then(data => {
        // Iterate over the returned data and update checkboxes
        for (const [name, value] of Object.entries(data)) {
            const element = document.querySelector(`[name="${name}"]`);
            if (element && element.type === 'checkbox') {
                element.checked = value;
                // Manually trigger a change event to update label styling
                triggerChangeEvent(element);
            } else if (element) {
                element.value = value; // Set the value for text and textarea inputs
            }
        }
    })
    .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
    });

    // Handle form submission
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission

        const data = {};
        // Collect the current state of checkboxes and text inputs
        form.querySelectorAll('input[type="checkbox"], input[type="text"], textarea').forEach(element => {
            if (element.type === 'checkbox') {
                data[element.name] = element.checked;
            } else {
                data[element.name] = element.value; // Collect data from text and textarea inputs
            }
        });

        const jsonData = JSON.stringify(data); // Convert to JSON
        console.log(jsonData);

        // Send the updated data to the server
        fetch('/api/teamplayer/settings', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: jsonData,
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            window.location.href = '/api/teamplayer/frontpage';
            return response.json(); // Assuming the server sends back a response
        })
        .then(successData => {

            console.log('Success:', successData);
            // Optionally, handle the server's response data
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});

// Function to manually trigger a change event for a given element
function triggerChangeEvent(element) {
    const event = new Event('change', {
        'bubbles': true,
        'cancelable': true
    });
    element.dispatchEvent(event);
}