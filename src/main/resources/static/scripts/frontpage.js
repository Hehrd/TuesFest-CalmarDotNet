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


    //    if (searchText.trim() === '') {
    //        document.getElementById('results').style.display = 'none';
    //        return;
    //    }

        var apiUrl = '/api/teamplayer/frontpage';
        data={};
        data.username = searchText.value;
        console.log(JSON.stringify(data));
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
        .then(response => response.text())
        .then(html => {
        console.log(html)
            document.open();  // Open a document for writing
            document.write(html);  // Write the new HTML
            document.close();  // Close the document stream
          })
        .catch(error => {
            console.error('Error:', error);
        });
    }



//// Hide results when clicking outside
//document.addEventListener('click', function(event) {
//    const resultsContainer = document.getElementById('results');
//    if (!resultsContainer.contains(event.target)) {
//        resultsContainer.style.display = 'none';
//    }
//});
