const form = document.getElementById('myForm');
const input = document.getElementById('myUsername');
const password = document.getElementById('myPassword');
let loginInvalid = document.getElementById('InvalidLogin');
const submitButton = form.querySelector('input[type="submit"]'); // Reference to the submit button

form.addEventListener('submit', function(event) {
    event.preventDefault();

    const data = {
        username: input.value,
        password: password.value,
    };

    fetch('http://localhost:6969/api/hacktues/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        } 
        return response.json();
    })
    .then(data => {
        console.log('Response:', data);
        if (data === 'OK') {
            console.log(data);
            loginInvalid.classList.add('hidden');
            window.location.href = '/api/hacktues/frontpage'
        } else {
            loginInvalid.classList.remove('hidden');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        loginInvalid.classList.remove('hidden');
    });
});