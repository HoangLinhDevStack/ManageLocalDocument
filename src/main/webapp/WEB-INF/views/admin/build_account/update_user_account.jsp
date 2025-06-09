<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_update_user/update-user.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Update user</title>

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

                <h3 class="text-center mt-3">Cập nhập thông tin người dùng</h3>

                <sec:authorize access="hasAuthority('Super')">
                    <form:form action="${pageContext.request.contextPath}/ManagerBook/admin/super/update-account/update-user" method="post" id="userForm">
                        <!-- User ID is critical for the update operation -->
                        <input type="hidden" name="id" value="${user.id}">
                        <!-- Add a visible debug field for the ID -->
                        <div class="alert alert-info mb-3">
                            User ID: ${user.id} (This ID will be used for the update operation)
                        </div>

                        <!-- Hidden div for collection fields -->
                        <div id="hiddenCollections" style="display: none;"></div>

                        <div class="row gutters mt-3">
                            <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="account-settings">
                                            <div class="user-profile">
                                                <div class="user-avatar">
                                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png"
                                                         alt="picture avatar">
                                                </div>
                                                <h5 class="user-name">${user.name}</h5>
                                                <h6 class="user-email">yuki@Maxwell.com</h6>
                                            </div>
                                            <div class="about">
                                                <h5>About</h5>
                                                <p>I'm Yuki. Full Stack Designer I enjoy creating user-centric, delightful
                                                    and
                                                    human experiences.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Personal Details</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="fullName">Họ và tên <span class="text-danger">*</span></label>
                                                    <input type="text" class="form-control" id="fullName" name="name"
                                                           placeholder="Enter full name"
                                                           value="${user.name}"
                                                           required
                                                           onchange="validateFullName(this)">
                                                    <div class="invalid-feedback" id="fullNameError">
                                                        Trường này cần giá trị đầu vào
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="eMail">Biệt danh</label>
                                                    <label for="nickName"></label>
                                                    <input type="text" class="form-control" id="nickName" name="nickName"
                                                           placeholder="Enter nickname"
                                                           value="${user.nickName}">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="phone">Quốc gia</label>
                                                    <input type="text" class="form-control" id="nation" name="nation"
                                                           placeholder="Enter nation"
                                                           value="${user.nation}">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <div class="form-group">
                                                    <label for="genderDisplay">Giới tính</label>
                                                    <!-- Text input field displaying the gender name -->
                                                    <input type="text" class="form-control" id="genderDisplay"
                                                           placeholder="Chọn giới tính" value="${user.sex != null ? user.sex.sex : ''}" disabled>
                                                    <!-- We're now using the select element for genderId -->
                                                    <!-- Dropdown for selecting gender -->
                                                    <select class="form-select mt-2" id="genderSelect" name="genderId"
                                                            aria-label="Chọn giới tính">
                                                        <c:if test="${user.sex != null}">
                                                            <option value="${user.sex.id}" selected>${user.sex.sex}</option>
                                                        </c:if>
                                                        <c:forEach items="${sexData}" var="entry">
                                                            <c:if test="${user.sex == null || entry.key != user.sex.id}">
                                                                <option value="${entry.key}" ${user.sex == null && entry.key == 1 ? 'selected' : ''}>${entry.value}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mt-3 mb-2 text-primary">Tài khoản</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="Street">Tên tài khoản</label>
                                                    <input type="text" class="form-control" id="Street"
                                                           placeholder="Enter Street"
                                                           value="${user.userAccount.username}"
                                                           readonly
                                                           disabled>
                                                </div>
                                            </div>

                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="DOB">Ngày tháng năm sinh <span class="text-danger">*</span></label>
                                                    <div class="input-group">
                                                        <input type="date" class="form-control" id="DOB" name="dateOfBirth"
                                                               placeholder="Ngày sinh"
                                                               value="<fmt:formatDate value='${user.dateOfBirth}' pattern='yyyy-MM-dd'/>"
                                                               required
                                                               max="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>"
                                                               onchange="validateDateOfBirth(this)">
                                                    </div>
                                                    <div class="invalid-feedback" id="dobError">
                                                        Vui lòng nhập ngày sinh và không được vượt quá ngày hiện tại
                                                    </div>
                                                    <div class="invalid-feedback" id="dobErrorFill">
                                                        Trường ngày sinh không được để trống.
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="roleDisplay">Vai trò</label>
                                                    <!-- Text input field displaying the role name -->
                                                    <input type="text" class="form-control" id="roleDisplay"
                                                           placeholder="Chọn vai trò"
                                                           value="${user.userAccount.role.keyRoles}" disabled>
                                                    <!-- We're now using the select element for roleId -->
                                                    <!-- Dropdown for selecting role -->
                                                    <select class="form-select mt-2" id="roleSelect" name="roleId"
                                                            aria-label="Chọn vai trò">
                                                        <!-- The current role is set as default -->
                                                        <option value="${user.userAccount.role.id}"
                                                                selected>${user.userAccount.role.keyRoles}</option>
                                                        <!-- Loop through the available roles passed from the controller -->
                                                        <c:forEach items="${roleUser}" var="entry">
                                                            <!-- Exclude the current role to avoid duplicate options -->
                                                            <c:if test="${entry.key != user.userAccount.role.id}">
                                                                <option value="${entry.key}">${entry.value}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">

                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value educations begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->

                                                <div class="form-group">
                                                    <label>Education</label>
                                                    <div id="educationContainer">
                                                        <c:forEach items="${user.educations}" var="edu" varStatus="status">
                                                            <div class="input-group mb-2" id-user-educations="${edu.id}">

                                                                <div class="education-group">
                                                                    <input name="educations" class="form-control educations-input" value="${edu.school}" placeholder="School"/>
                                                                </div>

                                                                <button type="button" class="btn btn-danger remove-education">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">

                                                        <button type="button" class="btn btn-primary" id="addEducationBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Education
                                                        </button>

                                                        <button type="button" id="restoreBtn" class="btn btn-secondary"
                                                                style="${empty user.educations ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore educations
                                                        </button>

                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value educations end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                            </div>
                                        </div>

                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Personal Details</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value addresses begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->



                                                <div class="form-group">
                                                    <label>Addresses</label>
                                                    <div id="addressContainer">
                                                        <c:forEach items="${user.addresses}" var="address" varStatus="status">
                                                            <div class="input-group mb-2" id-user-addresses="${address.id}">
                                                                <div class="address-group">
                                                                    <input type="text" class="form-control mb-1"
                                                                           name="streets" value="${address.streetName}" placeholder="Street Name">
                                                                    <input type="text" class="form-control mb-1"
                                                                           name="cities" value="${address.city}" placeholder="City">
                                                                    <input type="text" class="form-control"
                                                                           name="provinces" value="${address.province}" placeholder="Province">
                                                                </div>
                                                                <button type="button" class="btn btn-danger remove-address">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">
                                                        <button type="button" class="btn btn-primary" id="addAddressBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Address
                                                        </button>

                                                        <button type="button" id="restoreAddressBtn" class="btn btn-secondary" style="${empty user.addresses ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore Addresses
                                                        </button>
                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value addresses end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->



                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value skills begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                                <div class="form-group">
                                                    <label>Skills</label>
                                                    <div id="skillsContainer">
                                                        <c:forEach items="${user.skills}" var="skill" varStatus="status">
                                                            <div class="input-group mb-2" id-user-skills="${skill.id}">

    <%--                                                            * group of skill --%>
                                                                <div class="skill-group">
                                                                    <input type="text" class="form-control"
                                                                           name="skills"
                                                                           value="${skill.descriptions}" placeholder="Skill">
                                                                </div>

                                                                <button type="button" class="btn btn-danger remove-skill">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>


                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">
                                                        <button type="button" class="btn btn-primary" id="addSkillBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Skill
                                                        </button>

                                                        <button type="button" id="restoreSkillBtn" class="btn btn-secondary" style="${empty user.skills ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore Skills
                                                        </button>
                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value skills end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                            </div>
                                        </div>

                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">

                                                <div class="text-right mt-3">
                                                    <button type="button" id="submit" name="submit" class="btn btn-primary">
                                                        Update
                                                    </button>
                                                    <button type="button" id="back" name="submit"
                                                            class="btn btn-secondary float-end">
                                                        <sec:authorize access="hasAuthority('Super')">
                                                            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-account"
                                                               style="color: white; text-decoration: none;">
                                                                <i class="bi bi-arrow-left"></i>
                                                                Back to Read
                                                            </a>
                                                        </sec:authorize>
                                                        <sec:authorize access="hasAuthority('Manager')">
                                                            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/list-account"
                                                               style="color: white; text-decoration: none;">
                                                                <i class="bi bi-arrow-left"></i>
                                                                Back to Read
                                                            </a>
                                                        </sec:authorize>
                                                    </button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- * Bootstrap Modal for update confirmation -->
    <%--                    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">--%>
    <%--                        <div class="modal-dialog">--%>
    <%--                            <div class="modal-content">--%>
    <%--                                <div class="modal-header">--%>
    <%--                                    <h5 class="modal-title" id="confirmModalLabel">Xác nhận cập nhật</h5>--%>
    <%--                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>--%>
    <%--                                </div>--%>
    <%--                                <div class="modal-body">--%>
    <%--                                    Bạn có muốn cập nhật người dùng?--%>
    <%--                                </div>--%>
    <%--                                <div class="modal-footer">--%>
    <%--                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>--%>
    <%--                                    <button type="submit" class="btn btn-primary" id="confirmYes">Đồng ý</button>--%>
    <%--                                </div>--%>
    <%--                            </div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>

                        <!-- Bootstrap Modal for update confirmation -->
                        <div class="modal fade" id="updateConfirmationModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Update Confirmation</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="modalMessage">Checking changes...</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" id="confirmCancel" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                    </div>
                                    <div id="debugInfo" class="mt-3" style="display: none;">
                                        <pre id="debugOutput"></pre>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </form:form>
                </sec:authorize>

                <sec:authorize access="hasAuthority('Manager')">
                    <form:form action="${pageContext.request.contextPath}/ManagerBook/admin/manager/update-account/update-user" method="post" id="userForm">
                        <!-- User ID is critical for the update operation -->
                        <input type="hidden" name="id" value="${user.id}">
                        <!-- Add a visible debug field for the ID -->
                        <div class="alert alert-info mb-3">
                            User ID: ${user.id} (This ID will be used for the update operation)
                        </div>

                        <!-- Hidden div for collection fields -->
                        <div id="hiddenCollections" style="display: none;"></div>

                        <div class="row gutters mt-3">
                            <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="account-settings">
                                            <div class="user-profile">
                                                <div class="user-avatar">
                                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png"
                                                         alt="picture avatar">
                                                </div>
                                                <h5 class="user-name">${user.name}</h5>
                                                <h6 class="user-email">yuki@Maxwell.com</h6>
                                            </div>
                                            <div class="about">
                                                <h5>About</h5>
                                                <p>I'm Yuki. Full Stack Designer I enjoy creating user-centric, delightful
                                                    and
                                                    human experiences.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Personal Details</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="fullName">Họ và tên <span class="text-danger">*</span></label>
                                                    <input type="text" class="form-control" id="fullName" name="name"
                                                           placeholder="Enter full name"
                                                           value="${user.name}"
                                                           required
                                                           onchange="validateFullName(this)">
                                                    <div class="invalid-feedback" id="fullNameError">
                                                        Trường này cần giá trị đầu vào
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="eMail">Biệt danh</label>
                                                    <label for="nickName"></label>
                                                    <input type="text" class="form-control" id="nickName" name="nickName"
                                                           placeholder="Enter nickname"
                                                           value="${user.nickName}">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="phone">Quốc gia</label>
                                                    <input type="text" class="form-control" id="nation" name="nation"
                                                           placeholder="Enter nation"
                                                           value="${user.nation}">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <div class="form-group">
                                                    <label for="genderDisplay">Giới tính</label>
                                                    <!-- Text input field displaying the gender name -->
                                                    <input type="text" class="form-control" id="genderDisplay"
                                                           placeholder="Chọn giới tính" value="${user.sex != null ? user.sex.sex : ''}" disabled>
                                                    <!-- We're now using the select element for genderId -->
                                                    <!-- Dropdown for selecting gender -->
                                                    <select class="form-select mt-2" id="genderSelect" name="genderId"
                                                            aria-label="Chọn giới tính">
                                                        <c:if test="${user.sex != null}">
                                                            <option value="${user.sex.id}" selected>${user.sex.sex}</option>
                                                        </c:if>
                                                        <c:forEach items="${sexData}" var="entry">
                                                            <c:if test="${user.sex == null || entry.key != user.sex.id}">
                                                                <option value="${entry.key}" ${user.sex == null && entry.key == 1 ? 'selected' : ''}>${entry.value}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mt-3 mb-2 text-primary">Tài khoản</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="Street">Tên tài khoản</label>
                                                    <input type="text" class="form-control" id="Street"
                                                           placeholder="Enter Street"
                                                           value="${user.userAccount.username}"
                                                           readonly
                                                           disabled>
                                                </div>
                                            </div>

                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="DOB">Ngày tháng năm sinh <span class="text-danger">*</span></label>
                                                    <div class="input-group">
                                                        <input type="date" class="form-control" id="DOB" name="dateOfBirth"
                                                               placeholder="Ngày sinh"
                                                               value="<fmt:formatDate value='${user.dateOfBirth}' pattern='yyyy-MM-dd'/>"
                                                               required
                                                               max="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>"
                                                               onchange="validateDateOfBirth(this)">
                                                    </div>
                                                    <div class="invalid-feedback" id="dobError">
                                                        Vui lòng nhập ngày sinh và không được vượt quá ngày hiện tại
                                                    </div>
                                                    <div class="invalid-feedback" id="dobErrorFill">
                                                        Trường ngày sinh không được để trống.
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="roleDisplay">Vai trò</label>
                                                    <!-- Text input field displaying the role name -->
                                                    <input type="text" class="form-control" id="roleDisplay"
                                                           placeholder="Chọn vai trò"
                                                           value="${user.userAccount.role.keyRoles}" disabled>
                                                    <!-- We're now using the select element for roleId -->
                                                    <!-- Dropdown for selecting role -->
                                                    <select class="form-select mt-2" id="roleSelect" name="roleId"
                                                            aria-label="Chọn vai trò">
                                                        <!-- The current role is set as default -->
                                                        <option value="${user.userAccount.role.id}"
                                                                selected>${user.userAccount.role.keyRoles}</option>
                                                        <!-- Loop through the available roles passed from the controller -->
                                                        <c:forEach items="${roleUser}" var="entry">
                                                            <!-- Exclude the current role to avoid duplicate options -->
                                                            <c:if test="${entry.key != user.userAccount.role.id}">
                                                                <option value="${entry.key}">${entry.value}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">

                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value educations begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->

                                                <div class="form-group">
                                                    <label>Education</label>
                                                    <div id="educationContainer">
                                                        <c:forEach items="${user.educations}" var="edu" varStatus="status">
                                                            <div class="input-group mb-2" id-user-educations="${edu.id}">

                                                                <div class="education-group">
                                                                    <input name="educations" class="form-control educations-input" value="${edu.school}" placeholder="School"/>
                                                                </div>

                                                                <button type="button" class="btn btn-danger remove-education">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">

                                                        <button type="button" class="btn btn-primary" id="addEducationBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Education
                                                        </button>

                                                        <button type="button" id="restoreBtn" class="btn btn-secondary"
                                                                style="${empty user.educations ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore educations
                                                        </button>

                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value educations end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                            </div>
                                        </div>

                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Personal Details</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value addresses begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->



                                                <div class="form-group">
                                                    <label>Addresses</label>
                                                    <div id="addressContainer">
                                                        <c:forEach items="${user.addresses}" var="address" varStatus="status">
                                                            <div class="input-group mb-2" id-user-addresses="${address.id}">
                                                                <div class="address-group">
                                                                    <input type="text" class="form-control mb-1"
                                                                           name="streets" value="${address.streetName}" placeholder="Street Name">
                                                                    <input type="text" class="form-control mb-1"
                                                                           name="cities" value="${address.city}" placeholder="City">
                                                                    <input type="text" class="form-control"
                                                                           name="provinces" value="${address.province}" placeholder="Province">
                                                                </div>
                                                                <button type="button" class="btn btn-danger remove-address">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">
                                                        <button type="button" class="btn btn-primary" id="addAddressBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Address
                                                        </button>

                                                        <button type="button" id="restoreAddressBtn" class="btn btn-secondary" style="${empty user.addresses ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore Addresses
                                                        </button>
                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value addresses end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->



                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value skills begin
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                                <div class="form-group">
                                                    <label>Skills</label>
                                                    <div id="skillsContainer">
                                                        <c:forEach items="${user.skills}" var="skill" varStatus="status">
                                                            <div class="input-group mb-2" id-user-skills="${skill.id}">

    <%--                                                            * group of skill --%>
                                                                <div class="skill-group">
                                                                    <input type="text" class="form-control"
                                                                           name="skills"
                                                                           value="${skill.descriptions}" placeholder="Skill">
                                                                </div>

                                                                <button type="button" class="btn btn-danger remove-skill">
                                                                    <i class="bi bi-x"></i>
                                                                </button>
                                                            </div>


                                                        </c:forEach>
                                                    </div>

                                                    <div class="d-flex gap-2 mt-2">
                                                        <button type="button" class="btn btn-primary" id="addSkillBtn">
                                                            <i class="bi bi-plus"></i>
                                                            Add Skill
                                                        </button>

                                                        <button type="button" id="restoreSkillBtn" class="btn btn-secondary" style="${empty user.skills ? 'display:none;' : ''}">
                                                            <i class="bi bi-arrow-counterclockwise"></i>
                                                            Restore Skills
                                                        </button>
                                                    </div>
                                                </div>


                                                <!--
                                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                    multiple value skills end
                                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                -->


                                            </div>
                                        </div>

                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">

                                                <div class="text-right mt-3">
                                                    <button type="button" id="submit" name="submit" class="btn btn-primary">
                                                        Update
                                                    </button>
                                                    <button type="button" id="back" name="submit"
                                                            class="btn btn-secondary float-end">
                                                        <sec:authorize access="hasAuthority('Super')">
                                                        <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-account"
                                                           style="color: white; text-decoration: none;">
                                                            <i class="bi bi-arrow-left"></i>
                                                            Back to Read
                                                        </a>
                                                        </sec:authorize>
                                                        <sec:authorize access="hasAuthority('Manager')">
                                                        <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/list-account"
                                                           style="color: white; text-decoration: none;">
                                                            <i class="bi bi-arrow-left"></i>
                                                            Back to Read
                                                        </a>
                                                        </sec:authorize>
                                                    </button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- * Bootstrap Modal for update confirmation -->
    <%--                    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">--%>
    <%--                        <div class="modal-dialog">--%>
    <%--                            <div class="modal-content">--%>
    <%--                                <div class="modal-header">--%>
    <%--                                    <h5 class="modal-title" id="confirmModalLabel">Xác nhận cập nhật</h5>--%>
    <%--                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>--%>
    <%--                                </div>--%>
    <%--                                <div class="modal-body">--%>
    <%--                                    Bạn có muốn cập nhật người dùng?--%>
    <%--                                </div>--%>
    <%--                                <div class="modal-footer">--%>
    <%--                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>--%>
    <%--                                    <button type="submit" class="btn btn-primary" id="confirmYes">Đồng ý</button>--%>
    <%--                                </div>--%>
    <%--                            </div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>

                        <!-- Bootstrap Modal for update confirmation -->
                        <div class="modal fade" id="updateConfirmationModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Update Confirmation</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="modalMessage">Checking changes...</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" id="confirmCancel" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                    </div>
                                    <div id="debugInfo" class="mt-3" style="display: none;">
                                        <pre id="debugOutput"></pre>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </form:form>
                </sec:authorize>

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


<%--to here--%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

<script>

    const educations = [ // * constants of educations file js
        <c:forEach items="${user.educations}" var="edu" varStatus="status">
        {index: ${status.index},
            id: ${edu.id},
            school: "${edu.school}"},
        </c:forEach>
    ];

    const addresses = [
        <c:forEach items="${user.addresses}" var="address" varStatus="status">
        {index: ${status.index},
            streetName: "${address.streetName}",
            city: "${address.city}",
            province: "${address.province}"},
        </c:forEach>
    ];

    console.log(addresses)

    const skills = [
        <c:forEach items="${user.skills}" var="skill" varStatus="status">
        {index: ${status.index},
            descriptions: "${skill.descriptions}"},
        </c:forEach>
    ];



</script>

<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/form.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/gender.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/roles.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/education.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/address.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/skill.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/date_of_birth.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/full_name.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/admin/read_user/validate_fields.js"></script>

</body>
</html>
