const form = document.getElementById("userForm");
const submitBtn = document.getElementById("submit");
const alertArea = document.getElementById("alertArea");

const changeSetEducation = {
    updated: [],
    deleted: [],
    created: []
}

const initialValues = {
    name: document.getElementById("fullName").value,
    nickName: document.getElementById("nickName").value,
    nation: document.getElementById("nation").value,
    sex: document.getElementById("genderSelect").value,
    username: document.getElementById("Street").value,
    roleId: document.getElementById("roleSelect").value,

    educations: Array.from(document.querySelectorAll("[name='educations']")).map(input => {
            const container = document.getElementById('educationContainer');

            return {
                id: container.getAttribute("id-user-educations"),
                value: input.value,
            }
        }
    ),

    streets: Array.from(document.querySelectorAll("[name='streets']")).map(input => input.value),
    cities: Array.from(document.querySelectorAll("[name='cities']")).map(input => input.value),
    provinces: Array.from(document.querySelectorAll("[name='provinces']")).map(input => input.value),

    skills: Array.from(document.querySelectorAll("[name='skills']")).map(input => input.value),
};



document.addEventListener("DOMContentLoaded", function () {

    // Function to detect changes in the form
    detectChanges()

    // Event listener for detecting change
    form.addEventListener("input", detectChanges);

    // Simulate button click after page load
    submitBtn.click();

    // Submit handler
    submitBtn.addEventListener("click", e => submitForm(e));

});

function detectChanges() { // * detect changes in the form



    const currentValues = {
        name: document.getElementById("fullName").value,
        nickName: document.getElementById("nickName").value,
        nation: document.getElementById("nation").value,
        sex: document.getElementById("genderSelect").value,
        username: document.getElementById("Street").value,
        roleId: document.getElementById("roleSelect").value,

        educations: Array.from(document.querySelectorAll("[name='educations']")).map(input => {
                const container = document.getElementById('educationContainer');

                return {
                    id: container.getAttribute("id-user-educations"),
                    value: input.value,
                }
            }
        ),

        streets: Array.from(document.querySelectorAll("[name='streets']")).map(input => input.value),
        cities: Array.from(document.querySelectorAll("[name='cities']")).map(input => input.value),
        provinces: Array.from(document.querySelectorAll("[name='provinces']")).map(input => input.value),

        skills: Array.from(document.querySelectorAll("[name='skills']")).map(input => input.value),
    };


    const container = document.getElementById('educationContainer');

    const changedFields = [];

    // Compare initial and current values
    Object.keys(currentValues).forEach(field => {

        let a = JSON.stringify(initialValues[field])
        let b = JSON.stringify(currentValues[field])

        console.log("Giá trị khởi nguyên:" + field + a)
        console.log("Giá trị hiện tại:" + field + b)

        if (a !== b) {
            console.log("Giá trị khởi nguyên:" + a)
            console.log("Giá trị hiện tại:" + b)


            changedFields.push(field);
        } else return

    });

    if (changedFields.length === 0) {
        alertArea.textContent = "Không có gì thay đổi";
        alertArea.classList.remove("d-none", "alert-warning");
        alertArea.classList.add("alert-info");
    } else {
        alertArea.textContent = `Các trường sau đã thay đổi: ${changedFields.join(', ')}`;
        alertArea.classList.remove("d-none", "alert-info");
        alertArea.classList.add("alert-warning");
    }


    return changedFields
}


function submitForm(e) {

    e.preventDefault();  // Prevent default submit behavior

    // Trigger form change detection
    detectChanges();

    // If there are no changes, don't submit
    if (alertArea.textContent === "Không có gì thay đổi") {
        console.log(detectChanges()) // * print debug curent values
        return;
    }

    // If changes are detected, show confirmation modal
    const updateModal = new bootstrap.Modal(document.getElementById("updateConfirmationModal"));
    const modalMessage = document.getElementById("modalMessage");
    modalMessage.textContent = `Are you sure you want to update the changes? ${detectChanges().join(", ")}.`;

    console.log(detectChanges()) // * print debug curent values

    // Handle confirmation in modal
    document.getElementById("updateConfirmationModal").addEventListener("click", function (e) {
        if (e.target.id === "confirmYes") {
            form.submit(); // Submit the form
        }
    });

    updateModal.show();

}