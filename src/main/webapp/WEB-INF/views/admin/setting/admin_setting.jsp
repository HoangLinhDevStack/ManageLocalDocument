<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Homepage</title>

    <style>
        body {
            transition: background-color 0.3s, color 0.3s;
        }

        .container-fluid {
            background-color: var(--bg-color);
            color: var(--text-color);
        }

        .btn-light {
            background-color: #f8f9fa;
            color: #495057;
        }

        .btn-dark {
            background-color: #343a40;
            color: white;
        }

        .form-container {
            margin-top: 30px;
        }

        .form-container h2 {
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }

        .settings-list {
            list-style-type: none;
            padding: 0;
        }

        .settings-list li {
            padding: 10px;
            cursor: pointer;
            font-size: 18px;
            border-radius: 8px;
            transition: background-color 0.3s;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            background-color: #f8f9fa;
        }

        .settings-list li:hover {
            background-color: #e2e6ea;
        }

        .setting-content {
            display: none;
            padding: 20px;
            border: 1px solid #ddd;
            margin-top: 20px;
            border-radius: 8px;
            background-color: #f8f9fa;
        }

        .active-content {
            display: block;
        }

        .settings-list li.active {
            background-color: #d1ecf1;
        }

        .btn-toggle {
            display: inline-block;
            width: 100%;
            padding: 10px 15px;
            text-align: center;
            font-size: 16px;
            background-color: #28a745;
            color: white;
            border-radius: 5px;
            border: none;
            transition: background-color 0.3s ease;
        }

        .btn-toggle:hover {
            background-color: #218838;
        }

        /* Modal Styling */
        .modal-content {
            padding: 30px;
            border-radius: 8px;
        }

        .modal-header .close {
            color: #000;
            font-size: 30px;
            font-weight: bold;
            opacity: 1;
        }
    </style>
</head>

<body>

<div class="container-fluid flex-fill padding-0">

    <div class="d-flex height-100percent">

        <!-- Sidebar left side begin -->
        <%@include file="../combine/navbar-right.jsp" %>
        <!-- Sidebar left side end -->

        <div id="rightside" class="padding-0 container">

            <!-- Header begin -->
            <%@include file="../combine/header.jsp" %>
            <!-- Header end -->

            <div class="container mt-5 mb-5 form-container">
                <h2>Cài đặt tài khoản (Người quản trị)</h2>

                <!-- Danh sách cài đặt -->
                <ul class="settings-list">
                    <li onclick="showSetting('password-settings')">Đổi mật khẩu</li>
<%--                    <li onclick="showSetting('theme-settings')">Chế độ sáng tối</li>--%>
                </ul>

            </div>

            <!-- Modal for settings -->
            <div class="modal fade" id="settingsModal" tabindex="-1" aria-labelledby="settingsModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="settingsModalLabel">Cài đặt tài khoản</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Phần cài đặt Đổi mật khẩu -->
                            <div id="password-settings" class="setting-content">
                                <h3>Đổi Mật Khẩu</h3>
                                
                                <c:if test="${not empty passwordError}">
                                    <div class="alert alert-danger">${passwordError}</div>
                                </c:if>
                                
                                <c:if test="${not empty passwordSuccess}">
                                    <div class="alert alert-success">${passwordSuccess}</div>
                                </c:if>

                                <sec:authorize access="hasAuthority('Super')">

                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/password/change" method="post">
                                        <div class="form-group mb-3">
                                            <label for="currentPassword">Mật khẩu hiện tại</label>
                                            <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="newPassword">Mật khẩu mới</label>
                                            <input type="password" class="form-control" id="newPassword" name="newPassword" required
                                                   minlength="6" oninput="checkPasswordMatch()">
                                            <small class="form-text text-muted">Mật khẩu phải có ít nhất 6 ký tự</small>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="confirmPassword">Xác nhận mật khẩu mới</label>
                                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required
                                                   oninput="checkPasswordMatch()">
                                            <div id="passwordMatchMessage" class="form-text"></div>
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100" id="changePasswordBtn">Đổi mật khẩu</button>
                                    </form>
                                </sec:authorize>

                                <sec:authorize access="hasAuthority('Manager')">

                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/manager/password/change" method="post">
                                        <div class="form-group mb-3">
                                            <label for="currentPassword">Mật khẩu hiện tại</label>
                                            <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="newPassword">Mật khẩu mới</label>
                                            <input type="password" class="form-control" id="newPassword" name="newPassword" required
                                                   minlength="6" oninput="checkPasswordMatch()">
                                            <small class="form-text text-muted">Mật khẩu phải có ít nhất 6 ký tự</small>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label for="confirmPassword">Xác nhận mật khẩu mới</label>
                                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required
                                                   oninput="checkPasswordMatch()">
                                            <div id="passwordMatchMessage" class="form-text"></div>
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100" id="changePasswordBtn">Đổi mật khẩu</button>
                                    </form>
                                </sec:authorize>

                            </div>

                            <!-- Phần cài đặt Chế độ sáng tối -->
                            <div id="theme-settings" class="setting-content">
                                <h3>Chế độ sáng tối</h3>
                                <button id="toggleThemeBtn" class="btn btn-toggle">
                                    <i class="bi bi-moon"></i> Toggle Dark Mode
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Footer include -->
<%@include file="../combine/footer.jsp" %>

<!-- Script to toggle light and dark themes -->
<script>
    // Function to show selected setting content
    function showSetting(settingId) {
        // Show the modal
        var modal = new bootstrap.Modal(document.getElementById('settingsModal'), {
            keyboard: false
        });
        modal.show();

        // Hide all settings
        var settings = document.querySelectorAll('.setting-content');
        settings.forEach(function (setting) {
            setting.classList.remove('active-content');
        });

        // Show the selected setting
        var activeSetting = document.getElementById(settingId);
        activeSetting.classList.add('active-content');
    }

    // Function to check if passwords match
    function checkPasswordMatch() {
        var newPassword = document.getElementById('newPassword').value;
        var confirmPassword = document.getElementById('confirmPassword').value;
        var message = document.getElementById('passwordMatchMessage');
        var changeBtn = document.getElementById('changePasswordBtn');
        
        if (newPassword === '' || confirmPassword === '') {
            message.innerHTML = '';
            message.className = 'form-text';
            changeBtn.disabled = false;
            return;
        }
        
        if (newPassword === confirmPassword) {
            message.innerHTML = 'Mật khẩu khớp';
            message.className = 'form-text text-success';
            changeBtn.disabled = false;
        } else {
            message.innerHTML = 'Mật khẩu không khớp';
            message.className = 'form-text text-danger';
            changeBtn.disabled = true;
        }
    }

    // Function to toggle light/dark mode
    document.getElementById("toggleThemeBtn").addEventListener("click", function () {
        var currentTheme = document.body.classList.contains("dark-theme") ? "dark" : "light";
        // if (currentTheme === "light") {
        //     document.body.classList.add("dark-theme");
        //     document.body.classList.remove("light-theme");
        //     document.documentElement.style.setProperty('--bg-color', '#343a40');
        //     document.documentElement.style.setProperty('--text-color', 'white');
        //     document.getElementById("toggleThemeBtn").classList.remove("btn-light");
        //     document.getElementById("toggleThemeBtn").classList.add("btn-dark");
        //     document.getElementById("toggleThemeBtn").innerHTML = '<i class="bi bi-sun"></i> Toggle Light Mode';
        // } else {
        //     document.body.classList.add("light-theme");
        //     document.body.classList.remove("dark-theme");
        //     document.documentElement.style.setProperty('--bg-color', 'white');
        //     document.documentElement.style.setProperty('--text-color', '#495057');
        //     document.getElementById("toggleThemeBtn").classList.remove("btn-dark");
        //     document.getElementById("toggleThemeBtn").classList.add("btn-light");
        //     document.getElementById("toggleThemeBtn").innerHTML = '<i class="bi bi-moon"></i> Toggle Dark Mode';
        // }
    });
    
    // Hiển thị thông báo lỗi hoặc thành công nếu có
    document.addEventListener('DOMContentLoaded', function() {
        var passwordError = "${passwordError}";
        var passwordSuccess = "${passwordSuccess}";
        
        if (passwordError || passwordSuccess) {
            showSetting('password-settings');
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</body>
</html>
