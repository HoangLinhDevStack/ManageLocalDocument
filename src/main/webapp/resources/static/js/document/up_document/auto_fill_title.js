document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("file");
    const titleInput = document.getElementById("title");

    fileInput.addEventListener("change", function () {
        const file = fileInput.files[0];
        if (!file) return;

        // Lấy tên file, loại bỏ phần mở rộng
        const fileName = file.name;
        const baseName = fileName.replace(/\.[^/.]+$/, ""); // Xóa .pdf, .docx, ...

        // Nếu ô tiêu đề đang trống thì tự động điền
        if (titleInput && titleInput.value.trim() === "") {
            titleInput.value = baseName;
        }
    });
});
