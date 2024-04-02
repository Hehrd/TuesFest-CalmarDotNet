const Username = document.getElementById("myUsername");
        const Password = document.getElementById("myPassword");
        const Password2 = document.getElementById("confirmPassword");
        const UsernameTaken = document.getElementById("UsernameTaken");
        const PasswordsDifferent = document.getElementById("PasswordsDifferent");

        document.querySelector('form').addEventListener('submit', function(event) {
            event.preventDefault();

            const UsernameValue = Username.value.trim();
            const PasswordValue = Password.value.trim();
            const Password2Value = Password2.value.trim();

            // Hide previous error messages
            UsernameTaken.classList.add('hidden');
            PasswordsDifferent.classList.add('hidden');

            if (!PasswordValue || !Password2Value || PasswordValue !== Password2Value) {
                PasswordsDifferent.classList.remove('hidden');
                return;
            }

            const userData = {
                username: UsernameValue,
                password: PasswordValue
            };

            fetch('/api/teamplayer/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            })
            .then(response => {
                if (!response.ok) {
                        throw new Error('Username is already taken');
                } else {
                    window.location.href = '/api/teamplayer/frontpage'
                }
                return response.json();
            })
            .then(data => {
                console.log('Response:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
                if (error.message === 'Username is already taken') {
                    UsernameTaken.classList.remove('hidden');
                }
            });
        });

