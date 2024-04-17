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
    var searchText = document.getElementById('search_bar').value;

    if (searchText.trim() === '') {
        document.getElementById('results').style.display = 'none';
        return;
    }

    var apiUrl = '/api/teamplayer/frontpage';
    data={};
    data.username = searchText;
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        const resultsContainer = document.getElementById('results');
        resultsContainer.innerHTML = ''; // Clear previous results
        resultsContainer.style.display = 'block';

        data.usernames.forEach(username => {
            const div = document.createElement('div');
            div.textContent = username;
            div.className = 'result-item';
            div.onclick = () => window.location.href = `/player/${username}`; // Navigate to user page
            resultsContainer.appendChild(div);
        });
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('results').style.display = 'none';
    });
}

// Hide results when clicking outside
document.addEventListener('click', function(event) {
    const resultsContainer = document.getElementById('results');
    if (!resultsContainer.contains(event.target)) {
        resultsContainer.style.display = 'none';
    }
});
