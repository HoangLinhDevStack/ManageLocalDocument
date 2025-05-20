document.addEventListener("DOMContentLoaded", function () {
    const genreSelect = document.getElementById("genreSelect");
    const selectedGenresContainer = document.getElementById("selectedGenres");
    const hiddenInput = document.getElementById("genreIdsStr");
    const placeholder = document.getElementById("genrePlaceholder");

    let selectedGenres = [];

    genreSelect.addEventListener("change", function () {
        const selectedOption = genreSelect.options[genreSelect.selectedIndex];
        const id = selectedOption.value;
        const name = selectedOption.textContent;

        if (!id || selectedGenres.includes(id)) return;

        selectedGenres.push(id);
        updatePlaceholder();

        const tag = document.createElement("span");
        tag.className = "badge-genre";
        tag.innerHTML = `
            ${name}
            <button type="button" class="btn-close ms-2 position-absolute end-0 me-2"
                     aria-label="Remove"></button>
        `;

        const closeBtn = tag.querySelector("button");
        closeBtn.addEventListener("click", function () {
            tag.classList.add("fade");
            setTimeout(() => {
                selectedGenres = selectedGenres.filter(g => g !== id);
                selectedGenresContainer.removeChild(tag);
                genreSelect.querySelector(`option[value="${id}"]`).disabled = false;
                updateHiddenInput();
                updatePlaceholder();
            }, 200);
        });

        selectedGenresContainer.appendChild(tag);
        updateHiddenInput();
        genreSelect.querySelector(`option[value="${id}"]`).disabled = true;
        genreSelect.selectedIndex = 0;
    });

    function updateHiddenInput() {
        hiddenInput.value = selectedGenres.join(",");
    }

    function updatePlaceholder() {
        placeholder.style.display = selectedGenres.length === 0 ? 'inline' : 'none';
    }
});
