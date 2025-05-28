document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("file");
    const titleInput = document.getElementById("title");
    let lastFilledTitle = ""; // Lưu tiêu đề cuối cùng được điền
    let lastManualTitle = ""; // Lưu tiêu đề người dùng nhập thủ công

    fileInput.addEventListener("change", function () {
        const file = fileInput.files[0];
        
        // Xử lý trường hợp người dùng cancel chọn file
        if (!file) {
            // Nếu tiêu đề hiện tại là tiêu đề tự động điền, xóa nó
            if (titleInput.value === lastFilledTitle) {
                titleInput.value = lastManualTitle || "";
                lastFilledTitle = "";
            }
            return;
        }

        // Lấy tên file, loại bỏ phần mở rộng
        const fileName = file.name;
        const baseName = fileName.replace(/\.[^/.]+$/, ""); // Xóa .pdf, .docx, ...

        // Xử lý các trường hợp đặc biệt trong tên file
        let processedName = baseName
            .replace(/_/g, ' ') // Thay dấu gạch dưới bằng khoảng trắng
            .replace(/-/g, ' ') // Thay dấu gạch ngang bằng khoảng trắng
            .replace(/\s+/g, ' ') // Xóa khoảng trắng thừa
            .trim(); // Xóa khoảng trắng đầu và cuối

        // Viết hoa chữ cái đầu của mỗi từ
        processedName = processedName.split(' ')
            .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
            .join(' ');

        // Các trường hợp xử lý:
        // 1. Nếu ô tiêu đề đang trống
        if (titleInput.value.trim() === "") {
            titleInput.value = processedName;
            lastFilledTitle = processedName;
        }
        // 2. Nếu ô tiêu đề đang chứa tiêu đề tự động điền trước đó
        else if (titleInput.value === lastFilledTitle) {
            titleInput.value = processedName;
            lastFilledTitle = processedName;
        }
        // 3. Nếu người dùng đã chỉnh sửa tiêu đề, hỏi xem có muốn cập nhật không
        else {
            if (confirm("Bạn đã có tiêu đề khác. Bạn có muốn cập nhật tiêu đề mới không?")) {
                titleInput.value = processedName;
                lastFilledTitle = processedName;
            }
        }
    });

    // Thêm sự kiện khi người dùng chỉnh sửa tiêu đề thủ công
    titleInput.addEventListener("input", function() {
        if (this.value !== lastFilledTitle) {
            lastManualTitle = this.value;
            lastFilledTitle = "";
        }
    });

    // Thêm sự kiện khi người dùng xóa file
    fileInput.addEventListener("click", function() {
        // Lưu lại tiêu đề hiện tại trước khi người dùng có thể chọn file mới
        if (titleInput.value !== lastFilledTitle) {
            lastManualTitle = titleInput.value;
        }
    });
});
