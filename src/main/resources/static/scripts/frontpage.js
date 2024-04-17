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
console.log(username);
    fetch('/api/teamplayer/frontpage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(username),
        })
        .then(response => {
            if (response.ok) {
            return response.json();
        }})
        .then(data => {
            console.log('Response:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}