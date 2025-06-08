document.addEventListener('DOMContentLoaded', function() {
    const filterButtons = document.querySelectorAll('.btn-group button[data-filter]');
    const fileCards = document.querySelectorAll('.file-card');

    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Remove active class from all buttons
            filterButtons.forEach(btn => btn.classList.remove('active'));
            // Add active class to the clicked button
            this.classList.add('active');

            const filterValue = this.getAttribute('data-filter').trim().toLowerCase();

            fileCards.forEach(card => {
                const cardStatus = card.getAttribute('data-status').trim().toLowerCase();
                if (filterValue === 'all' || cardStatus === filterValue) {
                    card.parentElement.style.display = ''; // Show the card's parent
                } else {
                    card.parentElement.style.display = 'none'; // Hide the card's parent
                }
            });
        });
    });
}); 