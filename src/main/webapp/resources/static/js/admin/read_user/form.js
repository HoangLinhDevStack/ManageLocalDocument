const form = document.getElementById("userForm");
const submitBtn = document.getElementById("submit");
const alertArea = document.getElementById("alertArea");

const multiValueKeys = ["educations", "addresses", "skills"];
const changeSet = {} // * this here for multiple data user

const getFormValues = () => ({
    name: document.getElementById("fullName").value,
    nickName: document.getElementById("nickName").value,
    nation: document.getElementById("nation").value,
    sex: document.getElementById("genderSelect").value,
    username: document.getElementById("Street").value,
    roleId: document.getElementById("roleSelect").value,

    educations: Array.from(document.querySelectorAll(".education-group")).map(group => ({
        id: group.closest(".input-group").getAttribute("id-user-educations"),
        // value: input.value
        value: [
            group.querySelector("[name='educations']")?.value ?? null,
        ]
    })),

    addresses: Array.from(document.querySelectorAll(".address-group")).map(group => ({
        id: group.closest(".input-group").getAttribute("id-user-addresses"),
        value: [
            group.querySelector("[name='streets']")?.value ?? null,
            group.querySelector("[name='cities']")?.value ?? null,
            group.querySelector("[name='provinces']")?.value ?? null
        ],
        // streets: group.querySelector("[name='streets']")?.value ?? null,
        // cities: group.querySelector("[name='cities']")?.value ?? null,
        // provinces: group.querySelector("[name='provinces']")?.value ?? null
    })),

    skills: Array.from(document.querySelectorAll(".skill-group")).map(group => ({
        id: group.closest(".input-group").getAttribute("id-user-skills"),
        // value: input.value
        value: [
            group.querySelector("[name='skills']")?.value ?? null,
        ]
    }))
});

const initialValues = getFormValues()


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


function detectChanges() {
    const currentValues = getFormValues();
    const changedFields = [];

    // Reset changeSet
    Object.keys(changeSet).forEach(key => delete changeSet[key]);

    console.log("📥 Initial Values:", initialValues);
    console.log("📤 Current Values:", currentValues);

    Object.entries(currentValues).forEach(([field, currVal]) => {
        const initVal = initialValues[field];
        let isChanged = false; // * flag to track whether a scalar field has changed

        if (multiValueKeys.includes(field)) { // * check value is array or not
            isChanged = hasArrayFieldChanged(initVal, currVal, field);
            changeSet[field] = currVal; // * adding value type array into change set
        } else {
            isChanged = initVal !== currVal;
            // if (isChanged) { // * scalar field has changed
            //     console.log(`🔁 [SCALAR] Field changed: ${field}`);
            //     console.log(`  ↪ Initial:`, initVal);
            //     console.log(`  ↪ Current:`, currVal);
            // }
        }

        if (isChanged) { // * add value into change set: true to trigger submit
            changedFields.push(field); // * checking value change (Detected)
        }
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

    console.log("✅ Final changeSet:", changeSet);
    return changedFields;
}

function hasArrayFieldChanged(initArr, currArr, fieldName) { // * check for update value
    if (!Array.isArray(initArr) || !Array.isArray(currArr)) { // * check type of array
        // console.warn(`⚠️ ${fieldName} is not an array`);
        return true;
    }

    if (initArr.length !== currArr.length) { // * check length of array
        // console.log(`🔁 [${fieldName}] Length changed: ${initArr.length} → ${currArr.length}`);
        return true;
    }

    for (let i = 0; i < initArr.length; i++) { // * compare each element of array by ID and check value init has equal with current value
        const initArrElement = initArr[i];
        const currArrElement = currArr[i];

        const initArrElementId = initArrElement?.id ?? null;
        const currArrElementId = currArrElement?.id ?? null;
        const initArrElementVal = JSON.stringify(initArrElement?.value ?? null);
        const currArrElementVal = JSON.stringify(currArrElement?.value ?? null);

        if (initArrElementId === currArrElementId && initArrElementVal !== currArrElementVal) {
            return true; // * return true when find different value
        }
    }

    return false; // * return false when all elements have same value
}




// function submitForm(e) {
//
//     e.preventDefault();  // Prevent default submit behavior
//
//     // Trigger form change detection
//     detectChanges();
//
//
//     // If there are no changes, don't submit
//     if (alertArea.textContent === "Không có gì thay đổi") {
//         console.log(detectChanges()) // * print debug curent values
//         return;
//     }
//
//     // If changes are detected, show confirmation modal
//     const updateModal = new bootstrap.Modal(document.getElementById("updateConfirmationModal"));
//     const modalMessage = document.getElementById("modalMessage");
//     modalMessage.textContent = `Are you sure you want to update the changes? ${detectChanges().join(", ")}.`;
//
//     // console.log(detectChanges()) // * print debug curent values
//     // *
//     const hiddenInput = document.createElement("input");
//     hiddenInput.type = "hidden";
//     hiddenInput.name = "changeSetJson";
//     hiddenInput.value = JSON.stringify(changeSet); // convert to JSON string
//
//     form.appendChild(hiddenInput);
//
//     console.log('this is value of multiple information user:' + hiddenInput.value)
//
//     // Handle confirmation in modal
//     document.getElementById("updateConfirmationModal").addEventListener("click", function (e) {
//         if (e.target.id === "confirmYes") {
//             form.submit(); // Submit the form
//         }
//     });
//
//     updateModal.show();
//
// }

function submitForm(e) {
    e.preventDefault(); // Prevent normal form submission

    // Step 1: Detect changes
    const changed = detectChanges();

    // Step 2: If no changes → stop here
    if (changed.length === 0) {
        console.log("❌ No changes detected. Submission canceled.");
        return;
    }

    const hiddenInput = hiddenInputSetValueChangeset()

    // Step 4: Show confirmation modal
    const updateModal = new bootstrap.Modal(document.getElementById("updateConfirmationModal"));
    const modalMessage = document.getElementById("modalMessage");
    modalMessage.textContent = `Are you sure you want to update the changes? ${changed.join(", ")}.`;

    deleteChangeSet(hiddenInput)

    updateModal.show();
}

function hiddenInputSetValueChangeset() {
    // Step 3: Create hidden input with changeSet JSON
    const hiddenInput = document.createElement("input");
    hiddenInput.type = "hidden";
    hiddenInput.name = "changeSetJson";
    hiddenInput.value = JSON.stringify(changeSet);
    form.appendChild(hiddenInput);

    console.log("📦 changeSet to submit:", hiddenInput.value)

    return hiddenInput
}

function deleteChangeSet(hiddenInput) {
    // Step 5: Temporary click handler to control modal actions
    const modalEl = document.getElementById('updateConfirmationModal');



    const handleClick = (event) => {
        console.log(event)
        if (event.target.id === "confirmCancel") {
            hiddenInput.remove(); // Remove hidden input
        }

        // Always remove listener after one use
        modalEl.removeEventListener("click", handleClick);
    };

    modalEl.addEventListener("click", handleClick);

}

