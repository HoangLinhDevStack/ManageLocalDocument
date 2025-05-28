<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_components/admin/css_list_document/list-document.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
    <title>Deleted Documents</title>
    
    <style>
        .file-icon {
            width: 40px;
            height: 40px;
            object-fit: contain;
            margin-right: 10px;
        }
        
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
    </style>
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
                <c:if test="${not empty uploadError}">
                    <div class="alert alert-danger">${uploadError}</div>
                </c:if>

                <c:if test="${not empty uploadSuccess}">
                    <div class="alert alert-success">${uploadSuccess}</div>
                </c:if>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box clearfix">
                            <div class="table-responsive">
                                <table class="table user-list" id="deletedDocumentsTable">
                                    <thead>
                                    <tr>
                                        <th><span>Name Document</span></th>
                                        <th><span>Creator</span></th>
                                        <th class="text-center"><span>Status</span></th>
                                        <th><span>Size</span></th>
                                        <th>&nbsp;</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="document" items="${documents}">
                                        <tr>
                                            <td>
                                                <c:set var="filePath" value="${document.filePath}" />
                                                <c:choose>
                                                    <c:when test="${fn:endsWith(fn:toLowerCase(filePath), '.pdf')}">
                                                        <img src="${pageContext.request.contextPath}/resources/static/images/PDF.png" alt="PDF" class="file-icon">
                                                    </c:when>
                                                    <c:when test="${fn:endsWith(fn:toLowerCase(filePath), '.docx') || fn:endsWith(fn:toLowerCase(filePath), '.doc')}">
                                                        <img src="${pageContext.request.contextPath}/resources/static/images/docx-file_10260348.png" alt="Word" class="file-icon">
                                                    </c:when>
                                                    <c:when test="${fn:endsWith(fn:toLowerCase(filePath), '.xlsx') || fn:endsWith(fn:toLowerCase(filePath), '.xls')}">
                                                        <img src="${pageContext.request.contextPath}/resources/static/images/docx-file_10260348.png" alt="Excel" class="file-icon">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${pageContext.request.contextPath}/resources/static/images/dots_16178698.png" alt="File" class="file-icon">
                                                    </c:otherwise>
                                                </c:choose>
                                                <span class="label label-default">${document.title}</span>
                                            </td>
                                            <td>
                                                ${document.author}
                                            </td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${document.status == 'Approved'}">
                                                        <span class="text-primary">
                                                            <i class="bi bi-check-circle-fill"></i> ${document.status}
                                                        </span>
                                                    </c:when>
                                                    <c:when test="${document.status == 'Pending'}">
                                                        <span class="text-warning">
                                                            <i class="bi bi-hourglass-split"></i> ${document.status}
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="text-danger">
                                                            <i class="bi bi-x-circle-fill"></i> ${document.status}
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <span class="label label-default">${documentService.formatFileSize(document.fileSize)}</span>
                                            </td>
                                            <td style="width: 20%;">
                                                <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document/permanent-delete/${document.id}" 
                                                      method="POST" 
                                                      style="display: inline;"
                                                      onsubmit="return confirm('Bạn có chắc chắn muốn xóa vĩnh viễn tài liệu này? Hành động này không thể hoàn tác!');">
                                                    <button type="submit" class="table-link danger" style="background: none; border: none; padding: 0;">
                                                        <span class="fa-stack">
                                                            <i class="fa fa-square fa-stack-2x"></i>
                                                            <i class="fa fa-trash fa-stack-1x fa-inverse"></i>
                                                        </span>
                                                    </button>
                                                </form>
                                                <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document/restore/${document.id}" 
                                                      method="POST" 
                                                      style="display: inline;"
                                                      onsubmit="return confirm('Bạn có chắc chắn muốn khôi phục tài liệu này?');">
                                                    <button type="submit" class="table-link success" style="background: none; border: none; padding: 0;">
                                                        <span class="fa-stack">
                                                            <i class="fa fa-square fa-stack-2x"></i>
                                                            <i class="fa fa-undo fa-stack-1x fa-inverse"></i>
                                                        </span>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
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

<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.7/js/dataTables.bootstrap5.min.js"></script>

<script>
    $(document).ready(function() {
        $('#deletedDocumentsTable').DataTable({
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
            order: [[0, 'asc']], // Sắp xếp theo cột tên tài liệu
            responsive: true
        });
    });
</script>
</body>
</html> 