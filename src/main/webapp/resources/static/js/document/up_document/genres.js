// Add style for pink background
const style = document.createElement('style');
style.textContent = `
    .bg-pink {
        background-color: #ffb6c1 !important;
    }
`;
document.head.appendChild(style);

document.addEventListener("DOMContentLoaded", function () {
    const genreSelect = document.getElementById("genreSelect");
    const selectedGenresContainer = document.getElementById("selectedGenres");
    const hiddenInput = document.getElementById("genreIdsStr");
    const placeholder = document.getElementById("genrePlaceholder");

    let selectedGenres = [];

    // Khi người dùng chọn thể loại mới
    genreSelect.addEventListener("change", function () {
        const selectedOption = genreSelect.options[genreSelect.selectedIndex];
        const id = selectedOption.value;
        const name = selectedOption.textContent;

        if (!id || selectedGenres.includes(id)) return;

        selectedGenres.push(id);
        updatePlaceholder();

        const tag = document.createElement("span");
        tag.className = "badge-genre position-relative me-2 mb-2 bg-pink";
        tag.innerHTML = `
            ${name}
            <button type="button" class="btn-close ms-2 position-absolute end-0 me-2" aria-label="Remove"></button>
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

    // Cập nhật giá trị vào hidden input mỗi khi thay đổi
    function updateHiddenInput() {
        hiddenInput.value = selectedGenres.join(",");
    }

    // Cập nhật hiển thị của placeholder nếu chưa có thể loại nào
    function updatePlaceholder() {
        placeholder.style.display = selectedGenres.length === 0 ? 'inline' : 'none';
    }

    // Khi trang tải xong, load các thể loại đã có từ backend vào selectedGenres
    const currentGenres = document.querySelectorAll("input[name='currentGenreIds']");
    currentGenres.forEach(input => {
        const genreId = input.value;
        selectedGenres.push(genreId);

        const option = genreSelect.querySelector(`option[value="${genreId}"]`);
        const genreName = option ? option.textContent : genreId;

        const tag = document.createElement("span");
        tag.className = "badge-genre position-relative me-2 mb-2";
        tag.innerHTML = `
        ${genreName}
        <button type="button" class="btn-close ms-2 position-absolute end-0 me-2" aria-label="Remove"></button>
    `;

        const closeBtn = tag.querySelector("button");
        closeBtn.addEventListener("click", function () {
            tag.style.display = 'none';

            const undoTag = document.createElement("span");
            undoTag.className = "badge bg-warning text-dark me-2 mb-2";
            undoTag.innerHTML = `
            Đã xoá "${genreName}". <button type="button" class="btn btn-sm btn-link p-0 m-0" style="font-size: 0.9em;">Hoàn tác</button>
        `;

            const undoBtn = undoTag.querySelector("button");
            undoBtn.addEventListener("click", function () {
                undoTag.remove();
                tag.style.display = 'inline-block';
                if (!selectedGenres.includes(genreId)) selectedGenres.push(genreId);
                updateHiddenInput();
            });

            selectedGenres = selectedGenres.filter(g => g !== genreId);
            updateHiddenInput();
            tag.parentElement.insertBefore(undoTag, tag.nextSibling);
            genreSelect.querySelector(`option[value="${genreId}"]`).disabled = false;
        });

        selectedGenresContainer.appendChild(tag);
        if (option) option.disabled = true;
    });



    updateHiddenInput();
    updatePlaceholder();
});
