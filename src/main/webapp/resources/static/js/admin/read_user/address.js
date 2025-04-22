document.addEventListener('DOMContentLoaded', function() {
    // Handle addresses
    document.getElementById('addAddressBtn').addEventListener('click', addAddress);
    document.getElementById('restoreAddressBtn').addEventListener('click', restoreAddresses);

    document.getElementById('addressContainer').addEventListener('click', function(event) {
        if (event.target.closest('.remove-address')) {
            removeAddress(event.target.closest('.remove-address'));
        }
    });
});


// ** Address Functions **

function addAddress() {
    const container = document.getElementById('addressContainer');
    const newIndex = container.children.length;

    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group mb-2';

    inputGroup.innerHTML = `
        <div class="address-group">
            <input type="text" class="form-control mb-1" name="streets" placeholder="Street Name">
            <input type="text" class="form-control mb-1" name="cities" placeholder="City">
            <input type="text" class="form-control" name="provinces" placeholder="Province">
        </div>
        <button type="button" class="btn btn-danger remove-address">
            <i class="bi bi-x"></i>
        </button>
    `;

    container.appendChild(inputGroup);
}

function removeAddress(button) {
    const container = document.getElementById('addressContainer');
    const inputGroup = button.closest('.input-group');

    // Remove the input group
    inputGroup.remove();

    // No need to reindex with string array approach
}

function restoreAddresses() {
    const container = document.getElementById('addressContainer');
    const fragment = document.createDocumentFragment();

    container.innerHTML = '';  // Clear the current container

    addresses.forEach(address => {
        const inputGroup = document.createElement('div');
        inputGroup.className = 'input-group mb-2';

        // Create address fields container
        const addressGroup = document.createElement('div');
        addressGroup.className = 'address-group';

        // Create street name input
        const streetInput = document.createElement('input');
        streetInput.type = 'text';
        streetInput.className = 'form-control mb-1';
        streetInput.name = 'streets';
        streetInput.value = address.streetName.valueOf();
        streetInput.placeholder = 'Street Name';

        // Create city input
        const cityInput = document.createElement('input');
        cityInput.type = 'text';
        cityInput.className = 'form-control mb-1';
        cityInput.name = 'cities';
        cityInput.value = address.city.valueOf();
        cityInput.placeholder = 'City';

        // Create province input
        const provinceInput = document.createElement('input');
        provinceInput.type = 'text';
        provinceInput.className = 'form-control';
        provinceInput.name = 'provinces';
        provinceInput.value = address.province.valueOf();
        provinceInput.placeholder = 'Province';

        // Add inputs to address fields container
        addressGroup.appendChild(streetInput);
        addressGroup.appendChild(cityInput);
        addressGroup.appendChild(provinceInput);

        // Create remove button
        const button = document.createElement('button');
        button.type = 'button';
        button.className = 'btn btn-danger remove-address';
        button.innerHTML = '<i class="bi bi-x"></i>';

        // Add all elements to input group
        inputGroup.appendChild(addressGroup);
        inputGroup.appendChild(button);
        fragment.appendChild(inputGroup);
    });

    container.appendChild(fragment);
}
