const form = document.getElementById('myForm');
const input = document.getElementById('myUsername');
const password = document.getElementById('myPassword');
let loginInvalid = document.getElementById('InvalidLogin');
const submitButton = form.querySelector('input[type="submit"]'); // Reference to the submit button

form.addEventListener('submit', function(event) {
    event.preventDefault();

    const playerData = {
        username: input.value,
        password: password.value,
    };
    console.log(playerData);
    fetch('/api/teamplayer/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        } else {
            loginInvalid.classList.add('hidden');
            window.location.href = '/api/teamplayer/frontpage'
        }
        return response.json();
    })
    .then(data => {
        console.log('Response:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
        loginInvalid.classList.remove('hidden');
    });
});