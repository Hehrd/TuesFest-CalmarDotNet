function redirectToPlayerDisplay(game) {
    window.location.href = `/playerDisplay.html?game=${game}`;
}

function selectBrawlStars() {
    redirectToPlayerDisplay('bs');
}

function selectLeagueOfLegends() {
    redirectToPlayerDisplay('lol');
}

function selectRocketLeague() {
    redirectToPlayerDisplay('fort'); // Note: Was 'fort' intended for Rocket League? Should this be 'rl' or similar?
}

function selectValorant() {
    redirectToPlayerDisplay('valo');
}