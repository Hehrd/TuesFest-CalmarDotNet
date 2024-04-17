function redirectToPlayerDisplay(game) {
    window.location.href = `/api/teamplayer/playerlist/${game}`;
}

function selectBrawlStars() {
    redirectToPlayerDisplay('bs');
}

function selectLeagueOfLegends() {
    redirectToPlayerDisplay('lol');
}

function selectFortnite() {
    redirectToPlayerDisplay('fort'); // Note: Was 'fort' intended for Rocket League? Should this be 'rl' or similar?
}

function selectValorant() {
    redirectToPlayerDisplay('valo');
}
let username = document.getElementById('search_bar').value
function searchPlayers() {
    fetch('/api/teamplayer/frontpage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(username),
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
}