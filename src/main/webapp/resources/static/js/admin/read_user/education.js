document.addEventListener('DOMContentLoaded', function() { // # Handle with education
    // Add Education button listener
    document.getElementById('addEducationBtn').addEventListener('click', addEducation);

    // Restore Education button listener
    document.getElementById('restoreBtn').addEventListener('click', restoreEducation);

    // Event delegation for removing education
    // document.querySelectorAll(".education-inputs").forEach(input => {
    //     input.addEventListener("input", detectAddEducation(input));
    // });


    document.getElementById('educationContainer').addEventListener('click', function(event) {
        if (event.target.closest('.remove-education')) {
            removeEducation(event.target.closest('.remove-education'));
        }
    });

});

let educationIndex = document.querySelectorAll("[name^='educations']").length;

function addEducation() {
    const container = document.getElementById('educationContainer');
    const newIndex = container.children.length;



    educationIndex++;

    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group mb-2';

    // inputGroup.innerHTML = `
    //     <input type="text" class="form-control" name="educationList" placeholder="Education">
    //     <button type="button" class="btn btn-danger remove-education">
    //         <i class="bi bi-x"></i>
    //     </button>
    // `;

    inputGroup.innerHTML = `
        <div class="education-group" style="display: flex; align-items: center;">
            <input name="educations" class="form-control educations-input" placeholder="School" style="margin-right: 10px;" >
            <button 
                type="button" 
                class="btn btn-danger remove-education">
                <i class="bi bi-x"></i>
            </button>
        </div>
    `;

    container.appendChild(inputGroup);
}

function removeEducation(button) {
    const container = document.getElementById('educationContainer');
    const inputGroup = button.closest('.input-group');

    // Remove the input group
    inputGroup.remove();

    // No need to reindex with string array approach
}


function restoreEducation() {
    const container = document.getElementById('educationContainer');
    const fragment = document.createDocumentFragment();  // Create a document fragment

    // Clear the current container
    container.innerHTML = '';

    // Add each input group to the fragment
    educations.forEach(edu => {
        const inputGroup = document.createElement('div');
        inputGroup.className = 'input-group mb-2';

        const educationGroup = document.createElement('div');
        educationGroup.className = 'education-group';
        educationGroup.style.display = 'flex';
        educationGroup.style.alignItems = 'center';

        const input = document.createElement('input');
        input.type = 'text';
        input.className = 'form-control educations-input';
        input.name = 'educations';
        input.value = edu.school.valueOf();
        input.placeholder = 'School';
        input.style.marginRight = '10px';

        const button = document.createElement('button');
        button.type = 'button';
        button.className = 'btn btn-danger remove-education';
        button.innerHTML = '<i class="bi bi-x"></i>';

        educationGroup.appendChild(input);
        educationGroup.appendChild(button);
        inputGroup.appendChild(educationGroup);
        fragment.appendChild(inputGroup);  // Add to fragment
    });

    // Append the fragment to the container all at once
    container.appendChild(fragment);
}


function detectAddEducation(input) {
    const container = input.closest(".input-group");
    const id = container.getAttribute("id-user-educations");
    const currentValue = input.value;

    const original = initialValues.find(e => e.id === id);
    if (original && original.value !== currentValue) {
        // Mark as update
        const exists = changeSetEducation.update.find(e => e.id === id);
        if (!exists) changeSetEducation.update.push({ id, school: currentValue });
    }
    // else if (!id) {
    //     // It's a new entry
    //     const exists = changeSet.create.find(e => e.tempId === container.dataset.tempId);
    //     if (!exists) {
    //         changeSet.create.push({ school: currentValue });
    //     }
    // }
}
function detectUpdateEducation() {}
function detectDeleteEducation() {}

