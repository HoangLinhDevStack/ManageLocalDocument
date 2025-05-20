<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_list_document/list-document.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <title>Homepage</title>

</head>
<body>

<div class="container-fluid flex-fill padding-0">

    <div class="d-flex height-100percent">

        <!-- Sidebar left side begin -->
        <%@include file="../combine/navbar-right.jsp" %>

        <div id="rightside" class="padding-0 container">

            <!-- Header begin -->
            <%@include file="../combine/header.jsp" %>

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box clearfix">
                            <div class="table-responsive">
                                <table class="table user-list">
                                    <thead>
                                    <tr>
                                        <th><span>Name Document</span></th>
                                        <th><span>Created</span></th>
                                        <th class="text-center"><span>Status</span></th>
                                        <th><span>Who</span></th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
<%--                                            <a href="#" class="user-link">Mila Kunis</a>--%>
                                            <h1 class="user-subhead">Tên tài liệu</h1>
                                        </td>
                                        <td>
                                            2013/08/08
                                        </td>
                                        <td class="text-center">
                                            <span class="label label-default">Inactive</span>
                                        </td>
                                        <td>
                                            <a href="#">mila@kunis.com</a>
                                        </td>
                                        <td style="width: 20%;">
                                            <a href="#" class="table-link">
                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                        </span>
                                            </a>
                                            <a href="#" class="table-link">
                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                        </span>
                                            </a>
                                            <a href="#" class="table-link danger">
                                        <span class="fa-stack">
                                            <i class="fa fa-square fa-stack-2x"></i>
                                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                        </span>
                                            </a>
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="../combine/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

<script>
    // Example functions for the dropdown actions
    function downloadFile(filename) {
        alert('Downloading file: ' + filename);
    }

    function activateFile(filename) {
        alert('Activating file: ' + filename);
    }

    function deleteFile(filename) {
        alert('Deleting file: ' + filename);
    }
</script>

</body>
</html>
