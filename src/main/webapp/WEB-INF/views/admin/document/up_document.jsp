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
    <title>Homepage</title>

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


        <div id="rightside" class="padding-0 container">

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


            <div class="container  mt-5 mb-5">

                <h2 class="text-center">Đăng tải tài liệu (Quyền admin)</h2>

                <div class="container">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Upload Document</h2>

                        <form action="/submitDocument" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="title" class="form-label">File Title</label>
                                <input type="text" class="form-control" id="title" name="title" required>
                            </div>

                            <div class="mb-3">
                                <label for="documentStore" class="form-label">Select Document Store</label>
                                <select class="form-select" id="documentStore" name="documentStore" required>
                                    <option value="">Select Store</option>
                                    <option value="1">Store 1</option>
                                    <option value="2">Store 2</option>
                                    <option value="3">Store 3</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="genre" class="form-label">Select Genre</label>
                                <select class="form-select" id="genre" name="genre" required>
                                    <option value="">Select Genre</option>
                                    <option value="1">Genre 1</option>
                                    <option value="2">Genre 2</option>
                                    <option value="3">Genre 3</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="file" class="form-label">Upload Document</label>
                                <input type="file" class="form-control file-input" id="file" name="file"
                                       accept=".pdf,.docx,.xlsx" required>
                                <div class="error-message" id="fileError"></div>
                            </div>

                            <div class="mb-3">
                                <label for="author" class="form-label">Author</label>
                                <input type="text" class="form-control" id="author" name="author" value="${user.name}"
                                       readonly>
                            </div>

                            <button type="submit" class="btn btn-primary btn-submit w-100">Upload Document</button>
                        </form>
                    </div>
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


<%--to here--%>

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

<!-- * Bootstrap Modal for update confirmation -->
<%--<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="confirmModalLabel">Xác nhận cập nhật</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                Bạn có muốn cập nhật người dùng?--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>--%>
<%--                <button type="submit" class="btn btn-primary" id="confirmYes">Đồng ý</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

<script>

    document.getElementById("file").addEventListener("change", function() {
        var fileInput = document.getElementById("file");
        var errorMessage = document.getElementById("fileError");
        var filePath = fileInput.value;
        var allowedExtensions = /(\.pdf|\.docx|\.xlsx)$/i;

        if (!allowedExtensions.exec(filePath)) {
            errorMessage.textContent = "Please upload a valid file (PDF, Word, or Excel).";
            fileInput.value = ''; // Reset the input field
        } else {
            errorMessage.textContent = "";
        }
    });

</script>
</body>
</html>
