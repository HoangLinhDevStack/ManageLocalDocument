<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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


<div class="container-fluid padding-0">

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

                <%--                <form:form action="" method="post" modelAttribute="userAccountDTO">--%>
                <%--                    <div class="form-group">--%>
                <%--                        <label for="IDADAccount">IDADAccount (Mã định danh tài khoản) </label>--%>
                <%--                        <form:input type="text" class="form-control" id="IDADAccount" placeholder="Enter your IDAD Account" path="IDUserAccount" />--%>
                <%--                    </div>--%>
                <%--                    <div class="form-group">--%>
                <%--                        <label for="password">Password (Mật khẩu)</label>--%>
                <%--                        <form:input type="password" class="form-control" id="password" placeholder="Enter your password"  path="passwords" />--%>
                <%--                    </div>--%>
                <%--                    <div class="form-group">--%>
                <%--                        <label for="confirmPassword">Confirm Password (Xác nhận mật khẩu)</label>--%>
                <%--                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm your password"--%>
                <%--                               required>--%>
                <%--                    </div>--%>
                <%--                    <div class="form-group">--%>
                <%--                        <label for="roles">Roles (Vai trò)</label>--%>
                <%--                        <select class="form-control" id="roles" required>--%>
                <%--                            <option value="" disabled selected>Select your role</option>--%>
                <%--                            <option value="admin">ADMIN</option>--%>
                <%--                            <option value="user">User</option>--%>
                <%--                            <!-- <option value="guest">Guest</option> -->--%>
                <%--                        </select>--%>
                <%--                    </div>--%>
                <%--                    <button type="submit" class="btn btn-primary">Tạo tài khoản</button>--%>
                <%--                </form:form>--%>


                <section class="gradient-form" style="background-color: #eee;">


                    <div class="row g-0">
                        <div class="col-lg-8">
                            <div class="card-body p-md-5 mx-md-4">


                                <form:form action="" method="post" modelAttribute="userAccount"
                                           id="form-created-acc">
                                    <p>Điền đầy đủ thông tin giữa các trường</p>



                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="final-id">Tên người dùng</label>
                                        <form:input type="text" id="final-id" class="form-control"
                                                    placeholder="Nhập tên người dùng" path="username"/>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="password">Mật khẩu</label>
                                        <div class="form-show">
<%--                                                                                            <i class="bi bi-eye"></i>--%>
                                            <i class="bi bi-eye-slash"></i>
                                        </div>
                                        <input type="password" id="password"
                                               class="form-control"/>
                                        <p class="form-error mt-2">
                                            <i class="bi bi-emoji-expressionless-fill"></i>
                                            <span class="error-message"></span> <%--error say --%>
                                        </p>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="confirm-password">Xác nhận mật khẩu</label>
                                        <div class="form-show">
                                                <%--                                            <i class="bi bi-eye"></i>--%>
                                            <i class="bi bi-eye-slash"></i>
                                        </div>
                                        <form:input type="password" id="confirm-password"
                                                    class="form-control" path="password"/>
                                        <p class="form-error mt-2">
                                            <i class="bi bi-emoji-expressionless-fill"></i>
                                            <span class="error-message"></span> <%--error say --%>
                                        </p>
                                    </div>

                                    <div class="d-flex justify-content-between">

                                        <div class="form-outline mb-1">
                                            <label class="form-label" for="dob-id">Ngày tháng năm sinh</label>
                                            <input type="date" id="dob-id" class="form-control"
                                                   placeholder="Chose DOB"/>
                                            <p class="form-error mt-1">
                                                <i class="bi bi-emoji-expressionless-fill"></i>
                                                <span class="error-message"></span> <%--error say --%>
                                            </p>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Vai trò</label>

                                            <select class="form-select" aria-label="Default select example">
                                                <option selected disabled>Chọn vai trò người dùng</option>
                                                    <%--                                            <c:forEach var="role" items="${roles}">--%>
                                                    <%--                                                <option> ${role} </option>--%>
                                                    <%--                                            </c:forEach>--%>
                                            </select>

                                            <p class="form-error mt-2">
                                                <i class="bi bi-emoji-expressionless-fill"></i>
                                                <span class="error-message"></span> <%--error say --%>
                                            </p>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Vai trò</label>

                                            <select class="form-select" aria-label="Default select example">
                                                <option selected disabled>Chọn khoa người dùng</option>
                                                    <%--                                            <c:forEach var="role" items="${roles}">--%>
                                                    <%--                                                <option> ${role} </option>--%>
                                                    <%--                                            </c:forEach>--%>
                                            </select>

                                            <p class="form-error mt-2">
                                                <i class="bi bi-emoji-expressionless-fill"></i>
                                                <span class="error-message"></span> <%--error say --%>
                                            </p>
                                        </div>

                                    </div>

                                    <div class="form-model text-center pt-1 mb-3">

                                        <!-- Button trigger modal -->
                                        <button type="button"
                                                id="button-modal"
                                                class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 p-3">
                                            Tạo tài khoản người dùng
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade" id="exampleModal" tabindex="-1"
                                             aria-labelledby="exampleModalLabel" >
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Tạo tài
                                                            khoản người dùng</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có muốn tạo tài khoản người dùng không?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" id="modalNoBtn"
                                                                class="model-no btn btn-secondary"
                                                                data-bs-dismiss="modal">Không (Đóng)
                                                        </button>
<%--                                                        <button type="button" id="modalYesBtn"--%>
<%--                                                                class="model-yes btn btn-primary">Có (Đồng ý)--%>
                                                            <input id="submit" class="btn btn-primary btn-block fa-lg gradient-custom-2"
                                                                   type="submit" value="Có (Đồng ý)" />
<%--                                                        </button>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </form:form>

                                <div class="d-flex align-items-center justify-content-center pb-4">
                                    <p class="mb-0 me-2">Check tài khoản người dùng</p>
                                    <button type="button" class="btn btn-outline-danger">Kiểm tra</button>
                                </div>

                            </div>
                        </div>
                        <div class="col-lg-4 d-flex gradient-custom-2">
                            <div class="text-white px-3 py-4 p-md-5 mx-md-4">
<%--                                <h1 class="mb-4"><%=request.getAttribute("accountUserTitle")%>--%>
<%--                                </h1>--%>
<%--                                <h4 class="mt-1">--%>
<%--                                    Quy tắc đặt 2 mã số đầu--%>
<%--                                    <%=request.getAttribute("twoCharacterFirst")%>--%>
<%--                                </h4>--%>

<%--                                <h4 class="mt-1">--%>
<%--                                    Quy tắc đặt đặt mã số giữa--%>
<%--                                    <%=request.getAttribute("middleCharacter")%>--%>
<%--                                </h4>--%>

<%--                                <h4 class="mt-1">--%>
<%--                                    Quy tắc đặt đặt mã số cuối--%>
<%--                                    <%=request.getAttribute("systemCharacter")%>--%>
<%--                                </h4>--%>

<%--                                <h4 class="mt-1">--%>
<%--                                    DOB: Viết tắt của date of birth (Ngày tháng năm sinh)--%>
<%--                                </h4>--%>


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

        </div>

    </div>

</div>


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
</body>

</html>
