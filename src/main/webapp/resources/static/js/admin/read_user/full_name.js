// Full name validation
let originalFullName = null;

function validateFullName(input) {
    const fullNameError = document.getElementById('fullNameError');
    const submitBtn = document.getElementById('submit');
    
    // Reset error state
    input.classList.remove('is-invalid');
    fullNameError.style.display = 'none';
    
    // Check if full name is empty
    if (!input.value.trim()) {
        input.classList.add('is-invalid');
        fullNameError.style.display = 'block';
        submitBtn.disabled = true;
        return false;
    }
    
    submitBtn.disabled = false;
    return true;
}

// Store original full name when page loads
document.addEventListener('DOMContentLoaded', function() {
    const fullNameInput = document.getElementById('fullName');
    originalFullName = fullNameInput.value;
    
    // Add restore button if name exists
    if (originalFullName) {
        const fullNameContainer = fullNameInput.closest('.form-group');
        const restoreBtn = document.createElement('button');
        restoreBtn.type = 'button';
        restoreBtn.className = 'btn btn-secondary mt-2';
        restoreBtn.innerHTML = '<i class="bi bi-arrow-counterclockwise"></i> Restore full name';
        restoreBtn.onclick = function() {
            fullNameInput.value = originalFullName;
            validateFullName(fullNameInput);
        };
        fullNameContainer.appendChild(restoreBtn);
    }
});

// Add validation to form submission
document.getElementById('userForm').addEventListener('submit', function(event) {
    const fullNameInput = document.getElementById('fullName');
    if (!validateFullName(fullNameInput)) {
        event.preventDefault();
        return false;
    }
}); 