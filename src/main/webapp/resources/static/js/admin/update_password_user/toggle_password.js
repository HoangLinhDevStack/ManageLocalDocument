document.addEventListener('DOMContentLoaded', function() {
    // Tạo container cho input password
    const passwordInputs = document.querySelectorAll('input[type="password"]');
    
    passwordInputs.forEach(input => {
        // Tạo container div
        const container = document.createElement('div');
        container.className = 'input-group';
        
        // Wrap input trong container
        input.parentNode.insertBefore(container, input);
        container.appendChild(input);
        
        // Tạo button toggle
        const toggleButton = document.createElement('button');
        toggleButton.className = 'btn btn-outline-secondary';
        toggleButton.type = 'button';
        toggleButton.innerHTML = '<i class="bi bi-eye"></i>';
        
        // Thêm button vào container
        container.appendChild(toggleButton);
        
        // Xử lý sự kiện click
        toggleButton.addEventListener('click', function() {
            // Toggle type của input
            const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
            input.setAttribute('type', type);
            
            // Toggle icon
            const icon = this.querySelector('i');
            icon.className = type === 'password' ? 'bi bi-eye' : 'bi bi-eye-slash';
        });
    });
}); 