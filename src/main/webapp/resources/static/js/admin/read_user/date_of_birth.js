// Date of birth validation
let originalDateOfBirth = null;

function validateDateOfBirth(input) {
    const dobError = document.getElementById('dobError');
    const dobErrorFill = document.getElementById('dobErrorFill');
    const today = new Date();
    const selectedDate = new Date(input.value);
    const submitBtn = document.getElementById('submit');
    
    // Reset error state
    input.classList.remove('is-invalid');
    dobError.style.display = 'none';
    dobErrorFill.style.display = 'none';
    
    // Check if date is selected
    if (!input.value) {
        input.classList.add('is-invalid');
        dobErrorFill.style.display = 'block';
        submitBtn.disabled = true;
        return false;
    }
    
    // Check if date is in the future
    if (selectedDate > today) {
        input.classList.add('is-invalid');
        dobError.style.display = 'block';
        submitBtn.disabled = true;
        return false;
    }
    
    submitBtn.disabled = false;
    return true;
}

// Store original date of birth when page loads
document.addEventListener('DOMContentLoaded', function() {
    const dobInput = document.getElementById('DOB');
    originalDateOfBirth = dobInput.value;
    
    // Add restore button if date exists
    if (originalDateOfBirth) {
        const dobContainer = dobInput.closest('.form-group');
        const restoreBtn = document.createElement('button');
        restoreBtn.type = 'button';
        restoreBtn.className = 'btn btn-secondary mt-2';
        restoreBtn.innerHTML = '<i class="bi bi-arrow-counterclockwise"></i> Restore date of birth';
        restoreBtn.onclick = function() {
            dobInput.value = originalDateOfBirth;
            validateDateOfBirth(dobInput);
        };
        dobContainer.appendChild(restoreBtn);
    }
});

// Add validation to form submission
document.getElementById('userForm').addEventListener('submit', function(event) {
    const dobInput = document.getElementById('DOB');
    if (!validateDateOfBirth(dobInput)) {
        event.preventDefault();
        return false;
    }
}); 