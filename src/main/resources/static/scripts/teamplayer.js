document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const game = urlParams.get('game');
    if (game) {
        fetchPlayersForGame(game);
    }
});

function fetchPlayersForGame(game) {
    const API_URL = `/api/teamplayer/${game}`;
    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(players => {
            displayPlayers(players);
        })
        .catch(error => console.error("Failed to fetch players:", error));
}