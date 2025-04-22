document.addEventListener('DOMContentLoaded', function () { // * Handle with gender
    let genderDisplay = document.getElementById('genderDisplay');
    let genderSelect = document.getElementById('genderSelect');

    if (genderDisplay && genderSelect) {
        // Update input fields when a new option is selected
        genderSelect.addEventListener('change', function () {
            let selectedOption = genderSelect.options[genderSelect.selectedIndex];
            genderDisplay.value = selectedOption.text;
            // We don't need to update genderId anymore as we're using the select element directly
        });
    }
});