<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
                </div>
                <div class="row g-3">
                    <c:forEach var="doc" items="${documents}">
                        <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6 col-12">
                            <div class="file-card p-2 h-100" data-doc-id="${doc.id}" data-doc-title="${doc.title}" data-title="${doc.title}" data-file-path="${doc.filePath}">
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
    let currentDocId = null;
    let currentDocTitle = null;

    document.addEventListener('DOMContentLoaded', function() {
        // DEBUG: Log tất cả data-doc-id trên trang khi DOM tải xong
        document.querySelectorAll('.file-card').forEach(card => {
            console.log("DOM Loaded - Card data-doc-id:", card.getAttribute('data-doc-id'));
        });

        // Gán sự kiện chuột phải cho từng file-card
        document.querySelectorAll('.file-card').forEach(card => {
            card.addEventListener('contextmenu', function(e) {
                e.preventDefault();
                currentDocId = this.getAttribute('data-doc-id');
                currentDocTitle = this.getAttribute('data-doc-title');
                console.log("Context menu triggered. currentDocId (from clicked card):", currentDocId); // DEBUG
                const menu = document.getElementById('fileContextMenu');
                menu.style.display = 'block';
                menu.style.left = e.pageX + 'px';
                menu.style.top = e.pageY + 'px';
            });
        });

        // Ẩn menu khi click ngoài
        document.addEventListener('click', function() {
            document.getElementById('fileContextMenu').style.display = 'none';
        });

        // Đọc tài liệu
        document.getElementById('viewDocBtn').addEventListener('click', function(e) {
            e.stopPropagation();
            if (currentDocId && currentDocTitle) {
                showDocViewer(currentDocId, currentDocTitle);
            }
            document.getElementById('fileContextMenu').style.display = 'none';
        });

        // Cập nhật tài liệu
        document.getElementById('updateDocBtn').addEventListener('click', function(e) {
            e.stopPropagation();
            if (currentDocId) {
                window.location.href = '${pageContext.request.contextPath}/ManagerBook/admin/super/update-document/' + currentDocId;
            }
            document.getElementById('fileContextMenu').style.display = 'none';
        });
    });

    // Hàm hiển thị modal đọc tài liệu
    function showDocViewer(docId, docTitle) {
        console.log("showDocViewer called with docId:", docId, "docTitle:", docTitle);
        const modal = document.getElementById('docViewerModal');
        const iframe = document.getElementById('docViewerIframe');
        const messageDiv = document.getElementById('docViewerMessage');

        modal.querySelector('.modal-title').textContent = 'Đọc tài liệu: ' + docTitle;
        messageDiv.style.display = 'none';
        iframe.style.display = 'block';
        iframe.src = ''; // Clear previous src

        // Lấy đường dẫn file từ thuộc tính data-file-path của card đang được click
        let clickedCard = null;
        document.querySelectorAll('.file-card').forEach(card => {
            const cardDocId = card.getAttribute('data-doc-id');
            if (cardDocId && parseInt(cardDocId.trim()) === parseInt(docId)) {
                clickedCard = card;
            }
        });

        let filePath = clickedCard ? clickedCard.getAttribute('data-file-path') : '';
        
        if (filePath.toLowerCase().endsWith('.pdf')) {
            iframe.src = '${pageContext.request.contextPath}/ManagerBook/admin/super/document/view/' + docId;
            console.log("Setting iframe src to:", iframe.src);
        } else {
            iframe.style.display = 'none';
            messageDiv.innerHTML = 'Tính năng đọc tài liệu cho loại file này sẽ được phát triển sau. ' +
                '<br><a href="${pageContext.request.contextPath}/ManagerBook/admin/super/document/view/' + docId +
                '" target="_blank" class="btn btn-primary btn-sm mt-2">Tải xuống file hoặc mở trong tab mới</a>';
            messageDiv.style.display = 'block';
        }

        var modalInstance = new bootstrap.Modal(modal);
        modalInstance.show();
    }

    document.getElementById('searchInput').addEventListener('input', function() {
        const keyword = this.value.trim().toLowerCase();
        document.querySelectorAll('.file-card').forEach(card => {
            const title = card.getAttribute('data-title').toLowerCase();
            if (title.includes(keyword)) {
                card.parentElement.style.display = '';
            } else {
                card.parentElement.style.display = 'none';
            }
        });
    });
    </script>
</body>
</html>
