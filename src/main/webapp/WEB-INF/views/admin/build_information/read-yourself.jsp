<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_update_user/update-user.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Update admin</title>

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

            <!-- * Place this alert container at the middle of your form or page -->

            <!-- Left-Top Long Rectangle Alert Container -->
            <div id="alertArea" class="alert alert-warning d-none" role="alert"
                 style="position: fixed; top: 67px; left: 430px; width: 20%; z-index: 1050;">
                <!-- Alert message will appear here -->
            </div>


            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-10">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header bg-primary text-white">
                                <h3 class="text-center mb-0">Thông tin cá nhân</h3>
                            </div>
                            <div class="card-body">
                                <!-- Profile Header -->
                                <div class="text-center mb-4">
                                    <div class="avatar-circle mx-auto mb-3">
                                        <c:if test="${not empty admin.picture}">
                                            <img src="data:image/jpeg;base64,${admin.picture}" class="rounded-circle" style="width: 150px; height: 150px; object-fit: cover;">
                                        </c:if>
                                        <c:if test="${empty admin.picture}">
                                            <i class="bi bi-person-circle" style="font-size: 150px; color: #6c757d;"></i>
                                        </c:if>
                                    </div>
                                    <h4 class="mb-1">${admin.name}</h4>
                                    <p class="text-muted mb-0">${admin.adminAccount.role.keyRoles}</p>
                                </div>

                                <!-- Basic Information -->
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="card mb-4">
                                            <div class="card-header bg-light">
                                                <h5 class="mb-0"><i class="bi bi-person-vcard me-2"></i>Thông tin cơ bản</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Biệt danh</label>
                                                        <p class="mb-0">${admin.nickname}</p>
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Ngày sinh</label>
                                                        <p class="mb-0">${admin.dateOfBirth}</p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Quốc tịch</label>
                                                        <p class="mb-0">${admin.nation}</p>
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Giới tính</label>
                                                        <p class="mb-0">
                                                            <c:choose>
                                                                <c:when test="${admin.sex.id == 1}">
                                                                    <span class="badge bg-info">Nam</span>
                                                                </c:when>
                                                                <c:when test="${admin.sex.id == 2}">
                                                                    <span class="badge bg-info">Nữ</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="badge bg-secondary">Không xác định</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3">
                                                        <label class="text-muted small">Mô tả</label>
                                                        <p class="mb-0">${admin.description}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="card mb-4">
                                            <div class="card-header bg-light">
                                                <h5 class="mb-0"><i class="bi bi-shield-lock me-2"></i>Thông tin tài khoản</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Tên đăng nhập</label>
                                                        <p class="mb-0">${admin.adminAccount.username}</p>
                                                    </div>
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Vai trò</label>
                                                        <p class="mb-0">
                                                            <span class="badge bg-primary">${admin.adminAccount.role.keyRoles}</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6 mb-3">
                                                        <label class="text-muted small">Trạng thái</label>
                                                        <p class="mb-0">
                                                            <c:choose>
                                                                <c:when test="${admin.adminAccount.enable == 1}">
                                                                    <span class="badge bg-success">Đang hoạt động</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="badge bg-danger">Đã khóa</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Additional Information -->
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="card mb-4">
                                            <div class="card-header bg-light">
                                                <h5 class="mb-0"><i class="bi bi-mortarboard me-2"></i>Học vấn</h5>
                                            </div>
                                            <div class="card-body">
                                                <c:forEach items="${admin.educations}" var="education">
                                                    <div class="mb-2">
                                                        <i class="bi bi-check-circle-fill text-success me-2"></i>
                                                        ${education.school}
                                                    </div>
                                                </c:forEach>
                                                <c:if test="${empty admin.educations}">
                                                    <p class="text-muted mb-0">Chưa có thông tin</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="card mb-4">
                                            <div class="card-header bg-light">
                                                <h5 class="mb-0"><i class="bi bi-geo-alt me-2"></i>Địa chỉ</h5>
                                            </div>
                                            <div class="card-body">
                                                <c:forEach items="${admin.addresses}" var="address">
                                                    <div class="mb-2">
                                                        <i class="bi bi-geo-fill text-primary me-2"></i>
                                                        ${address.streetName}, ${address.city}, ${address.province}
                                                    </div>
                                                </c:forEach>
                                                <c:if test="${empty admin.addresses}">
                                                    <p class="text-muted mb-0">Chưa có thông tin</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="card mb-4">
                                            <div class="card-header bg-light">
                                                <h5 class="mb-0"><i class="bi bi-tools me-2"></i>Kỹ năng</h5>
                                            </div>
                                            <div class="card-body">
                                                <c:forEach items="${admin.skills}" var="skill">
                                                    <div class="mb-2">
                                                        <i class="bi bi-star-fill text-warning me-2"></i>
                                                        ${skill.description}
                                                    </div>
                                                </c:forEach>
                                                <c:if test="${empty admin.skills}">
                                                    <p class="text-muted mb-0">Chưa có thông tin</p>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <style>
                .avatar-circle {
                    width: 150px;
                    height: 150px;
                    background-color: #f8f9fa;
                    border-radius: 50%;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    margin: 0 auto;
                    overflow: hidden;
                }
                
                .card {
                    transition: transform 0.2s;
                }
                
                .card:hover {
                    transform: translateY(-5px);
                }
                
                .card-header {
                    border-bottom: 2px solid #f8f9fa;
                }
                
                .badge {
                    font-size: 0.9em;
                    padding: 0.5em 1em;
                }
            </style>

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


<%--to here--%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>


</body>
</html>
