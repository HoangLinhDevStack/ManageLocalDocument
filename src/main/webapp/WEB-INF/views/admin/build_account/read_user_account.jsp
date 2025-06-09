<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_read_user/list-form.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
    <title>Title</title>

    <style>
        /* Style cho DataTables */
        .dataTables_wrapper .dataTables_filter {
            margin-bottom: 15px;
        }

        .dataTables_wrapper .dataTables_filter input {
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-left: 5px;
        }

        .dataTables_wrapper .dataTables_length select {
            padding: 5px 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .dataTables_wrapper .dataTables_info {
            padding-top: 15px;
        }

        .dataTables_wrapper .dataTables_paginate {
            padding-top: 15px;
        }

        /* Style cho bảng */
        .candidate-list-box {
            margin-bottom: 15px;
        }

        .candidate-list-content {
            padding: 15px;
        }
    </style>
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


            <div class="container mt-5 mb-5">
                <!-- Success message alert -->
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                            ${successMessage}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- Error message alert -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${errorMessage}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${not empty uploadSuccess}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                            ${uploadSuccess}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- Error message alert -->
                <c:if test="${not empty uploadError}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${uploadError}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <h1 class=""> Các tài khoản người dùng </h1>

                <link rel="stylesheet"
                      href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css"
                      integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous"/>
                <section class="section">
                    <div class="container">
                        <div class="justify-content-center row">
                            <div class="col-lg-12">
                                <div class="candidate-list-widgets mb-4">
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <h5>Lọc theo:</h5>
                                        </div>
<%--                                        <div class="col-lg-3">--%>
<%--                                            <select class="form-select" id="roleFilter">--%>
<%--                                                <option value="">Tất cả vai trò</option>--%>
<%--                                                <c:if test="${roleUser != null}">--%>
<%--                                                    <c:forEach items="${roleUser}" var="entry">--%>
<%--                                                        <option value="${entry.key}">${entry.value}</option>--%>
<%--                                                    </c:forEach>--%>
<%--                                                </c:if>--%>
<%--                                            </select>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-lg-3">--%>
<%--                                            <select class="form-select" id="departmentFilter">--%>
<%--                                                <option value="">Tất cả khoa</option>--%>
<%--                                                <c:if test="${DepartmentKeyAndValue != null}">--%>
<%--                                                    <c:forEach items="${DepartmentKeyAndValue}" var="entry">--%>
<%--                                                        <option value="${entry.key}">${entry.value}</option>--%>
<%--                                                    </c:forEach>--%>
<%--                                                </c:if>--%>
<%--                                            </select>--%>
<%--                                        </div>--%>
<%--                                        <div class="col-lg-3">--%>
<%--                                            <select class="form-select" id="officeFilter">--%>
<%--                                                <option value="">Tất cả phòng ban</option>--%>
<%--                                                <c:if test="${OfficeKeyAndValue != null}">--%>
<%--                                                    <c:forEach items="${OfficeKeyAndValue}" var="entry">--%>
<%--                                                        <option value="${entry.key}">${entry.value}</option>--%>
<%--                                                    </c:forEach>--%>
<%--                                                </c:if>--%>
<%--                                            </select>--%>
<%--                                        </div>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="align-items-center row">
<%--                                    <div class="col-lg-8">--%>
<%--                                        <div class="mb-3 mb-lg-0">--%>
<%--                                            <h6 class="fs-16 mb-0">Showing 1 – 8 of 11 results</h6>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="col-lg-4">--%>
<%--                                        <div class="candidate-list-widgets">--%>
<%--                                            <div class="row">--%>
<%--                                                <div class="col-lg-6">--%>
<%--                                                    <div class="selection-widget">--%>


<%--                                                        <select class="form-select" data-trigger="true"--%>
<%--                                                                name="choices-single-filter-orderby"--%>
<%--                                                                id="choices-single-filter-orderby"--%>
<%--                                                                aria-label="Default select example">--%>
<%--                                                            <option value="df">Gần đây</option>--%>
<%--                                                            <option value="ne">Newest</option>--%>
<%--                                                            <option value="od">Oldest</option>--%>
<%--                                                            <option value="rd">Random</option>--%>
<%--                                                        </select>--%>


<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                                <div class="col-lg-6">--%>
<%--                                                    <div class="selection-widget mt-2 mt-lg-0">--%>
<%--                                                        <select class="form-select" data-trigger="true"--%>
<%--                                                                name="choices-candidate-page"--%>
<%--                                                                id="choices-candidate-page"--%>
<%--                                                                aria-label="Default select example">--%>
<%--                                                            <option value="df">Toàn bộ tài khoản</option>--%>
<%--                                                            <option value="ne">8 per Page</option>--%>
<%--                                                            <option value="ne">12 per Page</option>--%>
<%--                                                        </select>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                </div>


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                    List user begin
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->


                                <div class="table-responsive">
                                    <table class="table table-hover" id="userAccountsTable">
                                        <thead>
                                        <tr>
                                            <th>Tên</th>
                                            <th>Vị trí khoa</th>
                                            <th>Vị trí phòng ban</th>
                                            <th>Vai trò</th>
                                            <th>Trạng thái</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${allUserInformation}" var="item">
                                            <tr>
                                                <td>
                                                    <div class="d-flex align-items-center">
                                                        <img src="https://bootdey.com/img/Content/avatar/avatar4.png"
                                                             alt=""
                                                             class="avatar-md img-thumbnail rounded-circle me-2"/>
                                                        <div>
                                                            <c:if test="${not empty item.name}">
                                                                <sec:authorize access="hasAuthority('Super')">
                                                                <a class="primary-link"
                                                                   href="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/user/${item.id}">${item.name}</a>
                                                                </sec:authorize>
                                                                <sec:authorize access="hasAuthority('Manager')">
                                                                <a class="primary-link"
                                                                   href="${pageContext.request.contextPath}/ManagerBook/admin/manager/update-account/user/${item.id}">${item.name}</a>
                                                                </sec:authorize>
                                                            </c:if>
                                                            <c:if test="${empty item.name}">
                                                                <a class="primary-link" href="#">(Chưa cập nhật)</a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <c:if test="${not empty item.departmentWork.position}">
                                                        ${item.departmentWork.position}
                                                    </c:if>
                                                    <c:if test="${empty item.departmentWork.position}">
                                                        Chưa cập nhật
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${not empty item.officeWork.position}">
                                                        ${item.officeWork.position}
                                                    </c:if>
                                                    <c:if test="${empty item.officeWork.position}">
                                                        Chưa cập nhật
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${not empty item.userAccount.role.keyRoles}">
                                                        <span class="badge bg-soft-secondary">${item.userAccount.role.keyRoles}</span>
                                                    </c:if>
                                                    <c:if test="${empty item.userAccount.role.keyRoles}">
                                                        <span class="badge bg-soft-secondary">Chưa cập nhật</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${item.userAccount.enable == 1}">
                                                        <span class="badge bg-success">Đã kích hoạt</span>
                                                    </c:if>
                                                    <c:if test="${item.userAccount.enable == 0}">
                                                        <span class="badge bg-danger">Chưa kích hoạt</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <div class="d-flex gap-2">
                                                        <sec:authorize access="hasAuthority('Super')">
                                                        <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/user/${item.id}"
                                                           class="btn btn-sm btn-primary">
                                                            <i class="bi bi-wrench"></i>
                                                        </a>
                                                        </sec:authorize>
                                                        <sec:authorize access="hasAuthority('Manager')">
                                                        <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/update-account/user/${item.id}"
                                                           class="btn btn-sm btn-primary">
                                                            <i class="bi bi-wrench"></i>
                                                        </a>
                                                        </sec:authorize>
                                                        <c:choose>
                                                            <c:when test="${item.userAccount.enable == 0}">
                                                                <sec:authorize access="hasAuthority('Super')">
                                                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/delete-account/user/${item.id}"
                                                                          method="get"
                                                                          style="display: inline;"
                                                                          onsubmit="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">
                                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                                        <button type="submit" class="btn btn-sm btn-danger">
                                                                            <i class="bi bi-trash"></i>
                                                                        </button>
                                                                    </form>
                                                                </sec:authorize>
<%--                                                                <sec:authorize access="hasAuthority('Manager')">--%>
<%--                                                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/manager/delete-account/user/${item.id}"--%>
<%--                                                                          method="get"--%>
<%--                                                                          style="display: inline;"--%>
<%--                                                                          onsubmit="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">--%>
<%--                                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--                                                                        <button type="submit" class="btn btn-sm btn-danger">--%>
<%--                                                                            <i class="bi bi-trash"></i>--%>
<%--                                                                        </button>--%>
<%--                                                                    </form>--%>
<%--                                                                </sec:authorize>--%>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <button type="button" class="btn btn-sm btn-danger" disabled title="Không thể xóa tài khoản đã kích hoạt">
                                                                    <i class="bi bi-trash"></i>
                                                                </button>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div class="dropdown">
                                                            <button class="btn btn-sm btn-secondary dropdown-toggle"
                                                                    type="button"
                                                                    data-bs-toggle="dropdown">
                                                                <i class="bi bi-gear"></i>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <sec:authorize access="hasAuthority('Super')">
                                                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/toggle-account-status/${item.id}"
                                                                          method="post"
                                                                          style="margin: 0;"
                                                                          onsubmit="return confirm('Bạn có chắc chắn muốn ${item.userAccount.enable == 1 ? ' vô hiệu hóa' : ' kích hoạt'} tài khoản này không?');">
                                                                        <button type="submit" class="dropdown-item">
                                                                            <i class="bi ${item.userAccount.enable == 1 ? 'bi-x-circle-fill text-danger' : 'bi-check-circle-fill text-primary'}"></i>
                                                                            <span>${item.userAccount.enable == 1 ? 'Vô hiệu hóa tài khoản' : 'Kích hoạt tài khoản'}</span>
                                                                        </button>
                                                                    </form>
                                                                    </sec:authorize>
                                                                    <sec:authorize access="hasAuthority('Manager')">
                                                                    <form action="${pageContext.request.contextPath}/ManagerBook/admin/manager/update-account/toggle-account-status/${item.id}"
                                                                          method="post"
                                                                          style="margin: 0;"
                                                                          onsubmit="return confirm('Bạn có chắc chắn muốn ${item.userAccount.enable == 1 ? ' vô hiệu hóa' : ' kích hoạt'} tài khoản này không?');">
                                                                        <button type="submit" class="dropdown-item">
                                                                            <i class="bi ${item.userAccount.enable == 1 ? 'bi-x-circle-fill text-danger' : 'bi-check-circle-fill text-primary'}"></i>
                                                                            <span>${item.userAccount.enable == 1 ? 'Vô hiệu hóa tài khoản' : 'Kích hoạt tài khoản'}</span>
                                                                        </button>
                                                                    </form>
                                                                    </sec:authorize>
                                                                </li>
                                                                <li>
                                                                    <sec:authorize access="hasAuthority('Super')">
                                                                    <a class="dropdown-item"
                                                                       href="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/user-account-password/${item.id}">
                                                                        <i class="bi bi-key"></i> Đặt lại mật khẩu
                                                                    </a>
                                                                    </sec:authorize>
                                                                    <sec:authorize access="hasAuthority('Manager')">
                                                                    <a class="dropdown-item"
                                                                       href="${pageContext.request.contextPath}/ManagerBook/admin/manager/update-account/user-account-password/${item.id}">
                                                                        <i class="bi bi-key"></i> Đặt lại mật khẩu
                                                                    </a>
                                                                    </sec:authorize>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                     user end
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->


                            </div>


                            <!--
                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                List user end
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            -->


                        </div>
                    </div>
                </section>
            </div>
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

<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function () {
        // Khởi tạo DataTable
        var table = $('#userAccountsTable').DataTable({
            language: {
                search: "Tìm kiếm:",
                lengthMenu: "Hiển thị _MENU_ bản ghi",
                info: "Hiển thị _START_ đến _END_ của _TOTAL_ bản ghi",
                infoEmpty: "Hiển thị 0 đến 0 của 0 bản ghi",
                infoFiltered: "(được lọc từ _MAX_ bản ghi)",
                paginate: {
                    first: "Đầu",
                    last: "Cuối",
                    next: "Tiếp",
                    previous: "Trước"
                }
            },
            pageLength: 10,
            order: [[0, 'asc']],
            responsive: true
        });

        // Xử lý lọc theo vai trò
        $('#roleFilter').on('change', function () {
            table.column(3).search(this.value).draw();
        });

        // Xử lý lọc theo khoa
        $('#departmentFilter').on('change', function () {
            table.column(4).search(this.value).draw();
        });

        // Xử lý lọc theo phòng ban
        $('#officeFilter').on('change', function () {
            table.column(5).search(this.value).draw();
        });
    });
</script>
</body>
</html>
