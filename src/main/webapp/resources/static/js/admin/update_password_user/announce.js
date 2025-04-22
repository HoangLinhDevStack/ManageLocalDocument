// Get the submit button and the modal
const submitButton = document.getElementById("submitButton");
const confirmModal = new bootstrap.Modal(document.getElementById('confirmModal'));
document.addEventListener("DOMContentLoaded", function() {

    // Event listener for the submit button to show the modal
    submitButton.addEventListener("click", function(event) {
        event.preventDefault(); // Prevent form submission

        // Check if the password input is empty or not
        if (isPasswordEmpty()) {
            // If empty, show the alert message
            alertPasswordNotChanged();
        } else {
            // If password is not empty, show the confirmation modal
            showConfirmModal();
        }
    });

    // Event listener for Enter key in the password field
    document.getElementById("newPassword").addEventListener("keydown", function(event) {
        if (event.key === "Enter") { // Check if Enter key is pressed
            event.preventDefault(); // Prevent form submission when Enter is pressed
            // Check if the password input is empty or not
            if (isPasswordEmpty()) {
                // If empty, show the alert message
                alertPasswordNotChanged();
            } else {
                // If password is not empty, show the confirmation modal
                showConfirmModal();
            }
        }
    });


    // Confirm password change action
    document.getElementById("confirmYes").addEventListener("click", function() {
        document.getElementById("changePasswordForm").submit(); // Submit the form when "Yes" is clicked
        confirmModal.hide(); // Hide the modal
    });


});


// * Function to check if the password field is empty
function isPasswordEmpty() {
    const passwordField = document.getElementById("newPassword");
    return passwordField.value.trim() === ""; // Returns true if the password is empty
}

// * Function to show the "Không có gì thay đổi" alert if password is empty
function alertPasswordNotChanged() {
    alert("Không có gì thay đổi"); // Show the alert message
}

// * Function to show the confirmation modal
function showConfirmModal() {
    confirmModal.show(); // Show the confirmation modal
}