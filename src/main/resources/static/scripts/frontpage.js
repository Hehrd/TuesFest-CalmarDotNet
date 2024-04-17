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
function searchPlayers(event) {

    event.preventDefault();
    // Get the value from the input field
    var searchText = document.getElementById('search_bar').value;

    // Define the API endpoint you're sending data to
    var apiUrl = '/api/teamplayer/frontpage';

    // Make a POST request with the input data
    fetch(apiUrl, {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ sb: searchText })
    })
    .then(response => response.json())  // Assuming the server responds with JSON
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
