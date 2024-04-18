addEventListener("DOMContentLoaded", function() {
    const videos = document.querySelectorAll('.vid');

        // Loop through each video element
        videos.forEach(video => {
            // Get the source of the video
            const src = video.getAttribute('src');
            
            // Check if the source ends with a comma
            if (src.endsWith(',')) {
                // Hide the video element
                video.style.display = 'none';
            }
        });
    });

    function goBack() {
        window.location.href="/api/teamplayer/frontpage"
    }