let base64StringImg = "";
let base64StringVid = {
    vid1: "",
    vid2: "",
    vid3: "",
    vid4: "",
    vid5: ""
}
function imageUploaded() {
    alert("test!");
    console.log("test");

    // Retrieve the file from the input element
    let file = document.getElementById('profile_picture').files[0];

    // Check if file is selected
    if (file) {
        let reader = new FileReader();
        console.log("next");

        reader.onloadend = function () {
            base64StringImg = reader.result.replace("data:", "").replace(/^.+,/, "");
            var imageBase64Stringsep = base64StringImg;
            // alert(imageBase64Stringsep);
            console.log(base64StringImg);
        };
        reader.readAsDataURL(file);
    } else {
        console.log("No file selected.");
    }
}

function videoUploaded() {
    alert("test");
    const files = document.getElementById('highlights').files;
    if (files.length > 0) {
        var i = 1;
        Array.from(files).forEach(file => {
            const reader = new FileReader();
            reader.onloadend = function(event) {
                 base64StringVid["vid" + i] = event.target.result.replace("data:", "").replace(/^.+,/, "");
                console.log(base64StringVid)
                 i = i + 1;
                // Optionally, you can handle the Base64 string further here
            };
            reader.readAsDataURL(file);
        });
    }
}


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
        form.querySelectorAll('input[type="checkbox"], input[type="text"], input[type=file], textarea').forEach(element => {
            if (element.type === 'checkbox') {
                data[element.name] = element.checked;
            }
            else {
                if (element.type === 'file') {
                    Array.from(element.files).forEach(file => {
                        if (file.type.match('image/jpeg')) {
                            data[element.name] = base64StringImg; // Store JPEG image Base64 string
                        } else if (file.type.match('video/mp4')) {
                            data[element.name] = base64StringVid; // Store MP4 video Base64 string
                        }
                    });
                }
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