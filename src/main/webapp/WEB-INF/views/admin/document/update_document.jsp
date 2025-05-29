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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_components/document/up-document.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Update Document</title>
</head>


<style>
    .file-icon {
        width: 40px;
        height: auto;
    }

</style>

<body>
<div class="container-fluid flex-fill padding-0">
    <div class="d-flex height-100percent">
        <%@include file="../combine/navbar-right.jsp" %>

        <div id="rightside" class="padding-0 container">
            <%@include file="../combine/header.jsp" %>

            <div class="container mt-5 mb-5">
                <h2 class="text-center">Cập nhật tài liệu</h2>

                <div class="container">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Update Document</h2>

                        <c:if test="${not empty uploadError}">
                            <div class="alert alert-danger">${uploadError}</div>
                        </c:if>

                        <c:if test="${not empty uploadSuccess}">
                            <div class="alert alert-success">${uploadSuccess}</div>
                        </c:if>

                        <div class="mb-4">
                            <div class="d-flex align-items-center">
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
                                <h4 class="mb-0 ms-3">${document.title}</h4>

                                <div class="d-flex ms-auto">
                                    <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document" class="btn btn-secondary">
                                        <i class="bi bi-arrow-left"></i> Quay lại danh sách
                                    </a>
                                </div>

                            </div>
                            <div class="mt-2">
                                <strong>Thể loại hiện tại:</strong>
                                <div class="d-flex flex-wrap gap-2 mt-2">
                                    <c:forEach var="genre" items="${document.genres}">
                                        <span class="badge bg-primary">${genre.genresName}</span>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <form action="${pageContext.request.contextPath}/ManagerBook/admin/super/update-document/${document.id}"
                              method="POST"
                              onsubmit="return confirm('Bạn có chắc chắn muốn cập nhật tài liệu này?');">
                            <div class="mb-3">
                                <label for="documentStore" class="form-label">Select Document Store</label>
                                <select name="documentStoreId" class="form-select" id="documentStore">
                                    <option value="" disabled>Chọn kho tài liệu</option>
                                    <c:forEach var="documentStore" items="${documentStores}">
                                        <option value="${documentStore.key}"
                                                <c:if test="${idDocumentStoreOfIDDocument != null && idDocumentStoreOfIDDocument == documentStore.key}">
                                                    selected
                                                </c:if>>
                                                ${documentStore.value}
                                        </option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="mb-3">
                                <label class="form-label">Select Genres</label>
                                <div class="d-flex flex-wrap gap-2 p-2 border rounded bg-light shadow-sm" id="selectedGenres" style="min-height: 45px;">
                                    <c:forEach var="genre" items="${document.genres}">
<%--                                        <span class="badge-genre position-relative me-2 mb-2">--%>
                                            <input type="hidden" name="currentGenreIds" value="${genre.id}">
<%--                                        </span>--%>
                                    </c:forEach>
                                    <span class="text-muted" id="genrePlaceholder" style="display: ${empty document.genres ? 'inline' : 'none'}">Chọn thể loại từ danh sách bên dưới</span>
                                </div>
                                <select class="form-select" id="genreSelect">
                                    <option value="" disabled selected>Chọn thể loại</option>
                                    <c:forEach var="genre" items="${genres}">
                                        <c:set var="isSelected" value="false" />
                                        <c:forEach var="docGenre" items="${document.genres}">
                                            <c:if test="${genre.key == docGenre.id}">
                                                <c:set var="isSelected" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <option value="${genre.key}" ${isSelected ? 'disabled' : ''}>${genre.value}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="genreIdsStr" id="genreIdsStr"/>
                            </div>


                            <div class="mb-3">
                                <label for="status" class="form-label">Trạng thái</label>
                                <select name="status" class="form-select" id="status">
                                    <!-- Tùy chọn mặc định từ document -->
                                    <option value="${document.status}" selected>${document.status}</option>

                                    <!-- Các trạng thái khác, trừ 'Rejected' và trạng thái hiện tại -->
                                    <c:forEach var="status" items="${statusList}">
                                        <c:if test="${status != 'Rejected' && status != document.status}">
                                            <option value="${status}">${status}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="mb-3">
                                <label for="author" class="form-label">Author</label>
                                <input type="text" name="author" class="form-control" id="author" value="${document.author}"/>
                            </div>

                            <button type="submit" class="btn btn-primary btn-submit w-100">Update Document</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<%@include file="../combine/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/document/up_document/genres.js"></script>
</body>
</html> 