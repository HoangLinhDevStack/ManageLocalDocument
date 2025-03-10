<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Title</title>
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

                <h1 class=""> Các tài khoản người dùng </h1>

                <link rel="stylesheet"
                      href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css"
                      integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous"/>
                <section class="section">
                    <div class="container">
                        <div class="justify-content-center row">
                            <div class="col-lg-12">
                                <div class="candidate-list-widgets mb-4">

                                    <div class="col-lg-3 ">
                                        <h5>Filter</h5>
                                    </div>

                                    <form action="#" class="">
                                        <div class="g-2 row">


                                            <div class="col-lg-3">
                                                <div class="filler-job-form">
                                                    <i class="uil uil-briefcase-alt"></i><input
                                                        id="exampleFormControlInput1"
                                                        placeholder="name" type="search"
                                                        class="form-control filler-job-input-box form-control"/>
                                                </div>
                                            </div>


                                            <div class="col-lg-3">
                                                <div class="filler-job-form">
                                                    <i class="uil uil-location-point"></i>
                                                    <select class="form-select selectForm__inner" data-trigger="true"
                                                            name="choices-single-location" id="choices-single-location"
                                                            aria-label="Default select example">

                                                        <%-- Render user by Role begin --%>

                                                        <option selected>Vai trò</option>
                                                        <c:if test="${roleUser != null}">
                                                            <c:forEach items="${roleUser}" var="entry">
                                                                <option value="${entry.key}">${entry.value}</option>
                                                            </c:forEach>
                                                        </c:if>

                                                        <%-- Render user by Role end --%>

                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-lg-3">
                                                <div class="filler-job-form">
                                                    <i class="uil uil-clipboard-notes"></i>
                                                    <select class="form-select selectForm__inner" data-trigger="true"
                                                            name="choices-single-categories"
                                                            id="choices-single-categories"
                                                            aria-label="Default select example">

                                                        <%-- Render user by Department begin --%>

                                                        <option value="">Khoa chuyên ngành</option>

                                                        <c:if test="${DepartmentKeyAndValue != null}">
                                                            <c:forEach items="${DepartmentKeyAndValue}" var="entry">
                                                                <option value="${entry.key}">${entry.value}</option>
                                                            </c:forEach>
                                                        </c:if>

                                                        <%-- Render user by Department end --%>

                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-lg-3">
                                                <div class="filler-job-form">
                                                    <i class="uil uil-clipboard-notes"></i>
                                                    <select class="form-select selectForm__inner" data-trigger="true"
                                                            name="choices-single-categories"
                                                            id="choices-double-categories"
                                                            aria-label="Default select example">


                                                            <%-- Render user by Office begin --%>

                                                            <option value="">Phòng ban</option>

                                                            <c:if test="${OfficeKeyAndValue != null}">
                                                                <c:forEach items="${OfficeKeyAndValue}" var="entry">
                                                                    <option value="${entry.key}">${entry.value}</option>
                                                                </c:forEach>
                                                            </c:if>

                                                            <%-- Render user by Office end --%>


                                                    </select>
                                                </div>
                                            </div>


                                        </div>
                                    </form>


                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="align-items-center row">
                                    <div class="col-lg-8">
                                        <div class="mb-3 mb-lg-0">
                                            <h6 class="fs-16 mb-0">Showing 1 – 8 of 11 results</h6>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="candidate-list-widgets">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="selection-widget">


                                                        <select class="form-select" data-trigger="true"
                                                                name="choices-single-filter-orderby"
                                                                id="choices-single-filter-orderby"
                                                                aria-label="Default select example">
                                                            <option value="df">Gần đây</option>
                                                            <option value="ne">Newest</option>
                                                            <option value="od">Oldest</option>
                                                            <option value="rd">Random</option>
                                                        </select>


                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="selection-widget mt-2 mt-lg-0">
                                                        <select class="form-select" data-trigger="true"
                                                                name="choices-candidate-page"
                                                                id="choices-candidate-page"
                                                                aria-label="Default select example">
                                                            <option value="df">Toàn bộ tài khoản</option>
                                                            <option value="ne">8 per Page</option>
                                                            <option value="ne">12 per Page</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!--
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                    List user begin
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                -->


                                <div class="candidate-list">


                                    <!--
                                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                         user begin
                                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    -->

                                <c:forEach items="${userMultipleAccount}" var="item">


                                    <div class="candidate-list-box bookmark-post card mt-4">
                                        <div class="p-4 card-body">
                                            <div class="align-items-center row ">

                                                <div class="col-auto">
                                                    <div class="candidate-list-images">
                                                        <a href="#"><img
                                                                src="https://bootdey.com/img/Content/avatar/avatar4.png"
                                                                alt="" class="avatar-md img-thumbnail rounded-circle"/></a>
                                                    </div>
                                                </div>

                                                <div class="col-auto">
                                                    <div class="candidate-list-content mt-3 mt-lg-0">
                                                        <h5 class="fs-19 mb-0">


                                                            <c:if test="${not empty item.user.name}">
                                                                <a class="primary-link" href="#">${item.user.name}</a> <%-- name --%>
                                                            </c:if>
                                                            <c:if test="${empty item.user.name}">
                                                                <a class="primary-link" href="#">(Chưa cập nhật)</a>
                                                            </c:if>



                                                            <span class="badge bg-success ms-1">
                                                                <i class="mdi mdi-star align-middle"></i>
                                                                4.5 //
                                                            </span>


                                                        </h5>
                                                        <p class="text-muted mb-2">Doctorate</p>
                                                        <%--                                                                            Position  --%>
                                                        <ul class="list-inline mb-0 text-muted">
                                                            <li class="list-inline-item">
                                                                <i class="mdi mdi-map-marker"></i>
                                                                Oakridge Lane Richardson
                                                                <%--                                                                // Active --%>
                                                            </li>
                                                            <li class="list-inline-item"><i class="mdi mdi-wallet"></i>
                                                                $650 / hours
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>

                                                <div class="col-auto">
                                                    <div class="mt-2 mt-lg-0 d-flex flex-wrap align-items-start gap-1">
                                                        <span class="role badge bg-soft-secondary fs-14 mt-1">Manager</span>
                                                        <%--                                                        // ROLE--%>
                                                    </div>
                                                </div>

                                                <div class="col grow favorite-icon">
                                                    <%--                                                    <a href="#"><i class="mdi mdi-heart fs-18"></i></a>--%>
                                                    <a>
                                                        <i class="bi bi-trash-fill"></i>
                                                        <%--                                                        // DELETE ACCOUNT--%>
                                                    </a>

                                                    <a>
                                                        <i class="bi bi-wrench"></i>
                                                        <%--                                                        // UPDATE ACCOUNT--%>
                                                    </a>

                                                </div>

                                                <div class="dropdown col-auto">
                                                    <%--                                                    //* CHANGING ACCOUNT--%>
                                                    <button class="btn btn-secondary dropdown-toggle"
                                                            type="button"
                                                            id="dropdownMenu"
                                                            data-bs-toggle="dropdown"
                                                            aria-expanded="false">
                                                        <i class="bi bi-grip-vertical"></i>
                                                        Cài đặt
                                                    </button>
                                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                                                        <li><a class="dropdown-item" href="#">kích hoạt tài khoản</a>
                                                        </li>
                                                        <li><a class="dropdown-item" href="#">Another action</a></li>
                                                        <li><a class="dropdown-item" href="#">Something else here</a>
                                                        </li>
                                                    </ul>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>



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


                        <div class="row">
                            <div class="mt-4 pt-2 col-lg-12">
                                <nav aria-label="Page navigation example">
                                    <div class="pagination job-pagination mb-0 justify-content-center">

                                        <li class="page-item disabled">
                                            <a class="page-link" tabindex="-1" href="#">
                                                <i class="mdi mdi-chevron-double-left fs-15"></i>
                                            </a>
                                        </li>

                                        <li class="page-item active">
                                            <a class="page-link" href="#">1</a>
                                        </li>

                                        <li class="page-item">
                                            <a class="page-link" href="#">2</a>
                                        </li>

                                        <li class="page-item">
                                            <a class="page-link" href="#">3</a>
                                        </li>

                                        <li class="page-item">
                                            <a class="page-link" href="#">4</a>
                                        </li>

                                        <li class="page-item">
                                            <a class="page-link" href="#">
                                                <i class="mdi mdi-chevron-double-right fs-15"></i>
                                            </a>
                                        </li>

                                    </div>
                                </nav>
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
</body>
</html>
