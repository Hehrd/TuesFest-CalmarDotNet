//function selectBrawlStars() {
//    fetchPlayersByGame('brawl_stars');
//}
//
//function selectLeagueOfLegends() {
//    fetchPlayersByGame('league_of_legends');
//}
//
//function selectRocketLeague() {
//    fetchPlayersByGame('rocket_league');
//}
//
//function selectValorant() {
//    fetchPlayersByGame('valorant');
//}
function fetchPlayersByGame(game) {
    const API_URL = `/api/hacktues/${game}`; // Adjust the API URL as necessary
    fetch("/api/hacktues/list")
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

// Display players in the UI
function displayPlayers(players) {
    const container = document.getElementById('player-cards-container') || document.createElement('div');
    container.id = 'player-cards-container';
    container.innerHTML = ''; // Clear previous player cards

    players.forEach(player => {
        // Create a card for each player
        const card = document.createElement('div');
        card.className = 'player-card';
        
        const aboutMe = document.createElement('p');
        aboutMe.textContent = player.about_me;
        
        const discord = document.createElement('p');
        discord.textContent = `Discord: ${player.discord}`;

        card.appendChild(aboutMe);
        card.appendChild(discord);

        container.appendChild(card);
    });

    if (!document.getElementById('player-cards-container')) {
        document.body.appendChild(container); // Append the container to the body if it's not already there
    }
}