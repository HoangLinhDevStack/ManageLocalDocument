<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<html>

<head>
    <title>Đăng nhập hệ thống</title>
    <!-- custom-theme -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //custom-theme  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_login/login.css">
    <!-- //font-awesome icons -->
    <link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>
    html body {
        background:url(${pageContext.request.contextPath}/resources/static/img/combine/beautiful-mountains-landscape_ice.jpg) no-repeat 0px 0px;
        font-size: 100%;
        font-family: 'Montserrat', sans-serif;
        background-size:cover;
        background-attachment:fixed;
        -webkit-background-size:cover;
        -moz-background-size:cover;
        -o-background-size:cover;
        -ms-background-size:cover;
    }
</style>

<body>


<div class="login-form w3_form">
    <!--  Title-->
    <div class="login-title w3_title">
        <h1>Quản lý tài liệu</h1>
    </div>
    <div class="login w3_login">
        <h2 class="login-header w3_header">Login Admin</h2>
        <div class="w3l_grid">

            <c:if test="${param.error != null}" >

                <p style="margin-top: 10px; margin-bottom: 10px; color:#cb2027">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    Mật khẩu hoặc tài khoản không chính xác
                </p>

            </c:if>

            <form:form class="login-container" action="${pageContext.request.contextPath}/ManagerBook/admin/process-login" method="POST">

                <p>Nhập mã quản trị</p>
                <label>
                    <input type="text" placeholder="Mã quản trị" name="username" required="">
                </label>

                <p>Nhập mật khẩu</p>
                <label>
                    <input type="password" placeholder="Mật khẩu" name="password" required="">
                </label>

<%--                <p>Nhập mã chính xác</p>--%>
<%--                <label>--%>
<%--                    <input type="number" placeholder="Nhập mã chính xác" Name="number" required="" maxlength="10">--%>
<%--                </label>--%>
                <input type="submit" value="Đăng nhập">

            </form:form>


            <div class="second-section w3_section">
                <div class="bottom-header w3_bottom"></div>
            </div>

            <div class="bottom-text w3_bottom_text">
                <h4>
                    <a href="#">Forgot your password?</a>
                </h4>
            </div>

        </div>
    </div>

</div>


<div class="footer-w3l">
    <p class="agile"> &copy; 2024 Đăng nhập hệ thống quản lý sách. All Rights Reserved | Design by <a
            href="http://w3layouts.com">W3layouts</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>

</html>
