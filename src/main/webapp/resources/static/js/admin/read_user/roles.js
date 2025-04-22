document.addEventListener('DOMContentLoaded', function () { // * Handle with role user
    let roleDisplay = document.getElementById('roleDisplay');
    let roleSelect = document.getElementById('roleSelect');

    if (roleDisplay && roleSelect) {
        // Update the role fields when a new option is selected
        roleSelect.addEventListener('change', function () {
            let selectedOption = roleSelect.options[roleSelect.selectedIndex];
            roleDisplay.value = selectedOption.text;
            // We don't need to update roleId anymore as we're using the select element directly
        });
    }
});