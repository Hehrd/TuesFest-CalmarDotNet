document.addEventListener('DOMContentLoaded', () => {
    // Select all player cards
    const playerCards = document.querySelectorAll('.player-card');

    playerCards.forEach(card => {
        // Find all spans inside the card
        const gameSpans = card.querySelectorAll('div span');

        gameSpans.forEach(span => {
            // Check if the span is not empty
            if(span.textContent.trim() !== '') {
                // Apply a rounded border style to the parent div of the span
                span.parentNode.style.border = '2px solid #007bff';
                span.parentNode.style.borderRadius = '8px';
                span.parentNode.style.padding = '5px';
                span.parentNode.style.marginTop = '5px';
                span.parentNode.style.backgroundColor = 'rgba(240, 240, 240, 0.9)';
                span.parentNode.style.boxShadow = '0 2px 4px rgba(0,0,0,0.1)';
            }
        });
    });
    document.querySelectorAll('.player-card').forEach(card => {
        card.addEventListener('click', function(event) {
            let username = this.querySelector('span').textContent
            console.log(username)
            window.location.href=`/api/teamplayer/profile/${username}`
            // You can also use event.target to refer to the clicked element
        });
    });
});

function goBack() {
    window.location.href="/api/teamplayer/frontpage"
}

