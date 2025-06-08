document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('searchInput').addEventListener('input', function() {
        const keyword = this.value.trim().toLowerCase();
        document.querySelectorAll('.file-card').forEach(card => {
            const title = card.getAttribute('data-title').toLowerCase();
            if (title.includes(keyword)) {
                card.parentElement.style.display = '';
            } else {
                card.parentElement.style.display = 'none';
            }
        });
    });
}); 