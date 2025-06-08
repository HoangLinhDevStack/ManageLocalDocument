// Function to check if any field exists in a container
function hasFields(containerId) {
    const container = document.getElementById(containerId);
    return container && container.children.length > 0;
}

// Function to validate all fields
function validateAllFields() {
    const submitBtn = document.getElementById('submit');
    const hasEducation = hasFields('educationContainer');
    const hasAddress = hasFields('addressContainer');
    const hasSkill = hasFields('skillsContainer');

    // Disable update button if no fields exist
    if (!hasEducation && !hasAddress && !hasSkill) {
        submitBtn.disabled = true;
        return false;
    }

    // Validate education fields
    if (hasEducation) {
        const educationInputs = document.querySelectorAll('.educations-input');
        let hasValidEducation = false;
        educationInputs.forEach(input => {
            if (input.value.trim()) {
                hasValidEducation = true;
            }
        });
        if (!hasValidEducation) {
            submitBtn.disabled = true;
            return false;
        }
    }

    // Validate address fields
    if (hasAddress) {
        const streetInputs = document.querySelectorAll('input[name="streets"]');
        const cityInputs = document.querySelectorAll('input[name="cities"]');
        const provinceInputs = document.querySelectorAll('input[name="provinces"]');
        let hasValidAddress = false;
        
        for (let i = 0; i < streetInputs.length; i++) {
            if (streetInputs[i].value.trim() && cityInputs[i].value.trim() && provinceInputs[i].value.trim()) {
                hasValidAddress = true;
                break;
            }
        }
        if (!hasValidAddress) {
            submitBtn.disabled = true;
            return false;
        }
    }

    // Validate skill fields
    if (hasSkill) {
        const skillInputs = document.querySelectorAll('input[name="skills"]');
        let hasValidSkill = false;
        skillInputs.forEach(input => {
            if (input.value.trim()) {
                hasValidSkill = true;
            }
        });
        if (!hasValidSkill) {
            submitBtn.disabled = true;
            return false;
        }
    }

    submitBtn.disabled = false;
    return true;
}

// Add event listeners when document is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Initial validation
    validateAllFields();

    // Add input event listeners to all fields
    const containers = ['educationContainer', 'addressContainer', 'skillsContainer'];
    containers.forEach(containerId => {
        const container = document.getElementById(containerId);
        if (container) {
            container.addEventListener('input', validateAllFields);
        }
    });

    // Add validation to form submission
    document.getElementById('userForm').addEventListener('submit', function(event) {
        if (!validateAllFields()) {
            event.preventDefault();
            return false;
        }
    });
}); 