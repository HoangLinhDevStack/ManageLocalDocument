<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8"%>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_update_user/update-user-account-password.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Homepage</title>

</head>
<body>

<%--Coppy this here--%>

<div class="container-fluid flex-fill padding-0">

    <div class="d-flex height-100percent">

        <!--
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                            Sidebar left side begin
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        -->

        <%@include file="../combine/navbar-right.jsp" %>

        <!--
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                            Sidebar left side end
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        -->


        <div id="rightside" class="padding-0 container">

            <!--
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Header begin
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            -->

            <%@include file="../combine/header.jsp" %>

            <!--
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Header end
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            -->


            <!--
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Container right begin
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            -->


            <div class="container d-flex align-items-center justify-content-center" style="min-height: 70vh;">
                <div class="w-100" style="max-width: 500px;">
                    <div class="card shadow rounded-3">
                        <div class="card-body p-4">
                            <h3 class="mb-4 text-center">Change Password</h3>

                            <div class="text-center mb-4">
                                <img src="" alt="Profile Picture" class="rounded-circle mb-2" width="100" height="100">
                                <h5 class="mb-1">${user.name}</h5>
                                <small class="text-muted">@${user.userAccount.username}</small>
                            </div>

                            <form:form action="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/update-password-user" method="post" class="form-signin" modelAttribute="user">
                            <input type="hidden" name="IDUser" value="${user.id}"/>
                                <div class="form-group mb-3">
                                    <label for="newPassword">Mật khẩu mới</label>
                                    <input type="password" name="password" class="form-control form-control-lg" id="newPassword" placeholder="Nhập mật khẩu mới của bạn">
                                </div>

                                <!-- Submit Button with enhanced style -->
                                <div class="text-center">
                                    <input type="button" id="submitButton" value="Thay đổi mật khẩu" class="btn btn-primary btn-lg w-100 p-2 shadow-lg" style="background-color: #007bff; border-color: #007bff; transition: all 0.3s ease;">
                                </div>

                                <!-- * Bootstrap Modal for update password confirmation -->
                                <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="confirmModalLabel">Xác nhận cập nhật</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                            </div>
                                            <div class="modal-body">
                                                Bạn có muốn thay đổi mật khẩu không?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                                <button type="submit" class="btn btn-primary" id="confirmYes">Đồng ý</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </form:form>

                        </div>
                    </div>
                </div>
            </div>


            <!--
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Container right end
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            -->


    </div>

</div>


<%--to here--%>

<!--
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                    footer left side end
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-->

<%@include file="../combine/footer.jsp" %>


<!--
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                    footer left side end
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-->

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/resources/static/js/admin/update_password_user/announce.js"></script>
</body>
</html>
