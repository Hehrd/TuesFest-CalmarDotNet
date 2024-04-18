let base64StringImg = "";
let base64StringVid = {
    vid1: "",
    vid2: "",
    vid3: "",
    vid4: "",
    vid5: ""
}
function imageUploaded() {
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

function goBack() {
    window.location.href="/api/teamplayer/frontpage"
}


document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.games-list');

        form.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission


        const data = {
            lol: "",
            valo: "",
            fort: "",
            bs: "",
            discord: "",
            aboutme: "",
            pfp: "",
            highlights: {
                vid1: "",
                vid2: "",
                vid3: "",
                vid4: "",
                vid5: ""
            } // No need for a comma after the last item in the object
        };
        // Collect the current state of checkboxes and text inputs
        form.querySelectorAll('input[type="checkbox"], input[type="text"], input[type=file]').forEach(element => {
            if (element.type === 'checkbox') {
                data[element.name] = element.checked;
            }
            else {
                if (element.type === 'text') {
                    data[element.name] = element.value;
                }
                else if (element.type === 'file') {
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

addEventListener("DOMContentLoaded", function() {
    const videos = document.querySelectorAll('.vid');

        // Loop through each video element
        videos.forEach(video => {
            // Get the source of the video
            const src = video.getAttribute('src');

            // Check if the source ends with a comma
            if (src.endsWith('null')) {
                // Hide the video element
                video.style.display = 'none';
            }
        });
    });