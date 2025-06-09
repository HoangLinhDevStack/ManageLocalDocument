<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="user-role" content="${pageContext.request.userPrincipal.authorities[0].authority}"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/side-bar.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/static/dist/css_web_config/right-side.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Homepage</title>

</head>


<style>
    .file-card {
        border-radius: 12px;
        transition: box-shadow 0.2s, transform 0.2s;
        cursor: pointer;
        border: 1px solid #f0f0f0;
        box-shadow: 0 1px 2px rgba(0,0,0,0.03);
        background: #fff;
    }
    .file-card:hover {
        box-shadow: 0 6px 24px rgba(0,0,0,0.10);
        transform: translateY(-4px) scale(1.03);
    }
    .file-icon {
        font-size: 2.8rem;
        border-radius: 8px 8px 0 0;
        width: 100%;
        height: 110px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .file-img {
        width: 100%;
        height: 110px;
        object-fit: cover;
        border-radius: 8px 8px 0 0;
        background: #f8f9fa;
    }
    .file-title {
        font-weight: 500;
        font-size: 1rem;
        margin-bottom: 0.25rem;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .file-date {
        font-size: 0.92rem;
        color: #888;
        display: flex;
        align-items: center;
        gap: 4px;
    }
</style>


<body>

<div class="container-fluid flex-fill padding-0">

    <div class="d-flex height-100percent">

        <!--
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                            Sidebar left side begin
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        -->

        <%@include file="./combine/navbar-right.jsp" %>

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

            <%@include file="./combine/header.jsp" %>

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


            <div class="container">
                <h3 class="mt-4 mb-4 fw-bold text-primary">Tài liệu nội bộ trường đại học sao đỏ</h3>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm tài liệu theo tên...">
                    </div>

                    <div class="btn-group col-md-6" role="group" aria-label="Filter by status">
                        <button type="button" class="btn btn-outline-primary active" data-filter="All">Tất cả</button>
                        <button type="button" class="btn btn-outline-primary" data-filter="Approved">Đã duyệt</button>
                        <button type="button" class="btn btn-outline-primary" data-filter="Pending">Đang chờ</button>
                        <button type="button" class="btn btn-outline-primary" data-filter="Rejected">Từ chối</button>
                    </div>

                </div>

<%--                <div class="col-md-6 text-end">--%>
<%--                    <div class="btn-group" role="group" aria-label="Filter by status">--%>
<%--                        <button type="button" class="btn btn-outline-primary active" data-filter="All">Tất cả</button>--%>
<%--                        <button type="button" class="btn btn-outline-primary" data-filter="Approved">Đã duyệt</button>--%>
<%--                        <button type="button" class="btn btn-outline-primary" data-filter="Pending">Đang chờ</button>--%>
<%--                        <button type="button" class="btn btn-outline-primary" data-filter="Rejected">Từ chối</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="row g-3">
                    <c:forEach var="doc" items="${documents}">
                        <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                            <div class="file-card p-2 h-100" 
                                data-doc-id="${doc.id}" 
                                data-doc-title="${doc.title}" 
                                data-title="${doc.title}" 
                                data-file-path="${doc.filePath}" 
                                data-status="${doc.status}">
                                
                                <c:choose>
                                    <c:when test="${fn:endsWith(fn:toLowerCase(doc.filePath), '.pdf')}">
                                        <img src="${pageContext.request.contextPath}/resources/static/images/PDF.png"
                                             alt="PDF" class="file-img">
                                    </c:when>
                                    <c:when test="${fn:endsWith(fn:toLowerCase(doc.filePath), '.docx') || fn:endsWith(fn:toLowerCase(doc.title), '.doc')}">
                                        <img src="${pageContext.request.contextPath}/resources/static/images/docx-file_10260348.png"
                                             alt="Word" class="file-img">
                                    </c:when>
                                    <c:when test="${fn:endsWith(fn:toLowerCase(doc.filePath), '.xlsx') || fn:endsWith(fn:toLowerCase(doc.title), '.xls')}">
                                        <img src="${pageContext.request.contextPath}/resources/static/images/excel_732220.png"
                                             alt="Excel" class="file-img">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/resources/static/images/dots_16178698.png"
                                             alt="File" class="file-img">
                                    </c:otherwise>
                                </c:choose>
                                <div class="pt-2 px-1">
                                    <div class="file-title" title="${doc.title}">${doc.title}</div>
                                    <div class="file-date">
                                        <c:choose>
                                            <c:when test="${doc.status == 'Approved'}">
                                                        <span class="text-primary">
                                                            <i class="bi bi-check-circle-fill"></i> ${doc.status}
                                                        </span>
                                            </c:when>
                                            <c:when test="${doc.status == 'Pending'}">
                                                        <span class="text-warning">
                                                            <i class="bi bi-hourglass-split"></i> ${doc.status}
                                                        </span>
                                            </c:when>
                                            <c:otherwise>
                                                        <span class="text-danger">
                                                            <i class="bi bi-x-circle-fill"></i> ${doc.status}
                                                        </span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>



                <!--
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                    Container right end
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                -->


            </div>


        </div>

    </div>

</div>

    <!--
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                        footer left side end
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    -->

    <%@include file="./combine/footer.jsp" %>


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

    <!-- Context Menu -->
    <div id="fileContextMenu" class="dropdown-menu" style="position: absolute; display: none; z-index: 9999;">
        <button class="dropdown-item" id="viewDocBtn"><i class="bi bi-eye"></i> Đọc tài liệu</button>
        <button class="dropdown-item" id="updateDocBtn"><i class="bi bi-pencil"></i> Cập nhật tài liệu</button>
    </div>

    <!-- Modal đọc tài liệu -->
    <div class="modal fade" id="docViewerModal" tabindex="-1" aria-labelledby="docViewerModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-xl" style="max-width:90vw;">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="docViewerModalLabel">Đọc tài liệu</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
          </div>
          <div class="modal-body" style="height:80vh;">
            <iframe id="docViewerIframe" src="" style="width: 100%; height: 100%; border: none;"></iframe>
            <div id="docViewerMessage" class="alert alert-info text-center mt-3" style="display:none;"></div>
          </div>
        </div>
      </div>
    </div>


<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/resources/static/js/document/homepage/document_viewer.js" ></script>
<script src="${pageContext.request.contextPath}/resources/static/js/document/homepage/document_search.js" ></script>
<script src="${pageContext.request.contextPath}/resources/static/js/document/homepage/document_filter.js" ></script>
</body>
</html>
