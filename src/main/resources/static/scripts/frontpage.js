let searchText = document.getElementById('search_bar');
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

function searchPlayers() {
    let username = searchText.value;
    window.location.href=`/api/teamplayer/search/${username}`
}

document.querySelectorAll('.user-row').forEach(user => {
        user.addEventListener('click', function(event) {
            let username = this.querySelector('span').textContent
            console.log(username)
            window.location.href=`/api/teamplayer/profile/${username}`
            // You can also use event.target to refer to the clicked element
        });
    });



//// Hide results when clicking outside
//document.addEventListener('click', function(event) {
//    const resultsContainer = document.getElementById('results');
//    if (!resultsContainer.contains(event.target)) {
//        resultsContainer.style.display = 'none';
//    }
//});
