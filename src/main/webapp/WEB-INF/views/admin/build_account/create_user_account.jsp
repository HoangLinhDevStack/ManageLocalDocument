<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_cre_account/create-account.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Tạo tài khoản người dùng</title>
</head>

<body>


<div class="container-fluid flex-fill padding-0">

    <div class="d-flex height-100percent ">

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


        <div id="right-side" class="padding-0 container">

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


            <div class="container mt-1">
                <h2 class="text-center">Tạo tài khoản cho người dùng</h2>

                <section class="gradient-form" style="background-color: #eee;">


                    <div class="row g-0">
                        <div class="col-lg-8">
                            <div class="card-body p-md-5 mx-md-4" >

                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                        Form create account for Admin "Super" start
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->

                                <sec:authorize access="hasAuthority('Super')">

                                    <form:form action="${pageContext.request.contextPath}/ManagerBook/admin/super/create-account/user-list"
                                               method="post"
                                               modelAttribute="createUserAccountDTO"
                                               id="form-created-acc"
                                               accept-charset="UTF-8">



                                        <%@include file="form/form-create.jsp" %>

                                    </form:form>

                                </sec:authorize>


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Form create account for Admin "Super" end
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Form create account for Admin "Manager" start
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->

                                <sec:authorize access="hasAuthority('Manager')">

                                    <form:form action="${pageContext.request.contextPath}/ManagerBook/admin/manager/create-account/user-list"
                                               method="post"
                                               modelAttribute="createUserAccountDTO"
                                               id="form-created-acc">

                                        <%@include file="form/form-create.jsp" %>

                                    </form:form>

                                </sec:authorize>


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                 Form create account for Admin "Manager" end
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->


                                <div class="d-flex align-items-center justify-content-center pb-4">
                                    <p class="mb-0 me-2">Check tài khoản người dùng</p>
                                    <button type="button" class="btn btn-outline-danger">Kiểm tra</button>
                                </div>

                            </div>
                        </div>
                        <div class="col-lg-4 d-flex gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">


                                <%--                            <img src="${pageContext.request.contextPath}/resources/static/img/combine/beautiful-mountains-landscape_pink.jpg" alt="">--%>
                            </div>


                        </div>
                    </div>


                </section>

            </div>


            <!--
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                Container right end
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            -->


        </div>

    </div>

</div>
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
<script src="${pageContext.request.contextPath}/resources/static/js/admin/created-account/interface-form.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/created-account/validate-created-acc.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/created-account/properties-method-created-acc.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/created-account/form-created-acc.js"></script>

<style>
.warning-icon {
    color: #ff6b6b; /* Màu đỏ vàng */
    margin-right: 5px;
}
</style>

<script>
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM Content Loaded');
    
    const usernameInput = document.getElementById('username');
    console.log('Username input:', usernameInput);
    
    const errorMessageUsername = document.querySelector('.error-message-username');
    console.log('Error message span:', errorMessageUsername);
    
    const warningIcon = document.querySelector('.warning-icon');
    console.log('Warning icon:', warningIcon);
    
    const form = document.getElementById('form-created-acc');
    console.log('Form:', form);
    
    let isUsernameValid = true;
    
    if (!usernameInput || !errorMessageUsername || !warningIcon || !form) {
        console.error('Required elements not found');
        return;
    }
    
    // Lấy danh sách username từ model và chuyển thành mảng JavaScript
    const existingUsernames = [
        <c:forEach items="${existingUsernames}" var="username">
            "${username}",
        </c:forEach>
    ];
    console.log('Existing usernames:', existingUsernames);

    usernameInput.addEventListener('input', function() {
        const username = this.value;
        console.log('Username input value:', username);
        
        if (username) {
            // Check xem username có trùng với username nào trong danh sách không
            const isDuplicate = existingUsernames.some(existingUsername => 
                existingUsername === username
            );
            console.log('Is duplicate:', isDuplicate);

            if (isDuplicate) {
                errorMessageUsername.textContent = 'Tên tài khoản này đã được sử dụng';
                errorMessageUsername.style.color = '#ff6b6b';
                warningIcon.style.display = 'inline-block'; // Hiển thị icon
                usernameInput.setCustomValidity('Tên tài khoản này đã được sử dụng');
                isUsernameValid = false;
            } else {
                errorMessageUsername.textContent = '';
                warningIcon.style.display = 'none'; // Ẩn icon
                usernameInput.setCustomValidity('');
                isUsernameValid = true;
            }
        } else {
            errorMessageUsername.textContent = '';
            warningIcon.style.display = 'none'; // Ẩn icon
            usernameInput.setCustomValidity('');
            isUsernameValid = true;
        }
    });

    // Ngăn submit form nếu username không hợp lệ
    form.addEventListener('submit', function(event) {
        if (!isUsernameValid) {
            event.preventDefault();
            errorMessageUsername.textContent = 'Không thể tạo tài khoản với tên đăng nhập này';
            errorMessageUsername.style.color = '#ff6b6b';
            warningIcon.style.display = 'inline-block'; // Hiển thị icon
        }
    });
});
</script>
</body>

</html>
