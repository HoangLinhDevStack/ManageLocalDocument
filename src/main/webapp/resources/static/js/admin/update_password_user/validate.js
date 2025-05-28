document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('newPassword');
    const submitButton = document.getElementById('submitButton');
    const confirmModal = document.getElementById('confirmModal');
    let isValid = false;

    // Tạo container cho thông báo lỗi
    const errorContainer = document.createElement('div');
    errorContainer.className = 'password-error mt-2';
    passwordInput.parentNode.appendChild(errorContainer);

    // Thêm icon cho input
    const iconContainer = document.createElement('div');
    // iconContainer.className = 'password-icon position-absolute end-0 top-50 translate-middle-y me-3';
    passwordInput.parentNode.style.position = 'relative';
    passwordInput.parentNode.appendChild(iconContainer);

    // Hàm kiểm tra mật khẩu
    function validatePassword(password) {
        const errors = [];
        
        // Kiểm tra trống
        if (!password) {
            errors.push('Trường này cần chứa giá trị đầu vào');
        }
        // Kiểm tra độ dài
        else if (password.length < 6) {
            errors.push('Mật khẩu cần ít nhất 6 kí tự');
        }
        // Kiểm tra ký tự đặc biệt
        const regexSpecifiedCharacter = /[\-\_\*\/\\\#\&\@\^\~\+\-\*\/\%\=]/g;
        if (!regexSpecifiedCharacter.test(password)) {
            errors.push('Mật khẩu cần chứa ít nhất 1 kí tự đặc biệt: - _ * / \\ # & @ ^ ~ + % =');
        }

        return errors;
    }

    // Hàm cập nhật giao diện
    function updateUI(errors) {
        // Xóa nội dung cũ
        errorContainer.innerHTML = '';
        iconContainer.innerHTML = '';

        if (errors.length > 0) {
            // Hiển thị lỗi
            passwordInput.classList.add('is-invalid');
            passwordInput.classList.remove('is-valid');
            
            // Thêm icon lỗi
            // iconContainer.innerHTML = '<i class="bi bi-x-circle-fill text-danger"></i>';
            
            // Hiển thị thông báo lỗi
            errors.forEach(error => {
                const errorDiv = document.createElement('div');
                errorDiv.className = 'text-danger small';
                errorDiv.innerHTML = `<i class="bi bi-exclamation-circle"></i> ${error}`;
                errorContainer.appendChild(errorDiv);
            });
            
            isValid = false;
        } else {
            // Hiển thị thành công
            passwordInput.classList.add('is-valid');
            passwordInput.classList.remove('is-invalid');
            
            // Thêm icon thành công
            // iconContainer.innerHTML = '<i class="bi bi-check-circle-fill text-success"></i>';
            //
            isValid = true;
        }

        // Cập nhật trạng thái nút submit
        submitButton.disabled = !isValid;
        submitButton.classList.toggle('btn-primary', isValid);
        submitButton.classList.toggle('btn-secondary', !isValid);
    }

    // Xử lý sự kiện input
    passwordInput.addEventListener('input', function() {
        const errors = validatePassword(this.value);
        updateUI(errors);
    });

    // Xử lý sự kiện blur
    passwordInput.addEventListener('blur', function() {
        const errors = validatePassword(this.value);
        updateUI(errors);
    });

    // Xử lý sự kiện submit
    submitButton.addEventListener('click', function(e) {
        const errors = validatePassword(passwordInput.value);
        if (errors.length > 0) {
            e.preventDefault();
            updateUI(errors);
        } else {
            // Hiển thị modal xác nhận
            const modal = new bootstrap.Modal(confirmModal);
            modal.show();
        }
    });

    // Xử lý sự kiện đóng modal
    confirmModal.addEventListener('hidden.bs.modal', function () {
        // Xóa backdrop khi modal đóng
        const backdrop = document.querySelector('.modal-backdrop');
        if (backdrop) {
            backdrop.remove();
        }
        // Xóa class modal-open từ body
        document.body.classList.remove('modal-open');
        document.body.style.overflow = '';
        document.body.style.paddingRight = '';
    });

    // Xử lý nút đóng modal
    const closeButtons = confirmModal.querySelectorAll('[data-bs-dismiss="modal"]');
    closeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const modal = bootstrap.Modal.getInstance(confirmModal);
            if (modal) {
                modal.hide();
            }
        });
    });
});