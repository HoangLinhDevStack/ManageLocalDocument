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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Homepage</title>

</head>
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
                <!-- Header section -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div class="d-flex align-items-center">
                        <h4 class="mb-0 me-3">Tài liệu của tôi</h4>
                        <div class="btn-group">
                            <button class="btn btn-outline-secondary btn-sm">
                                <i class="bi bi-grid-3x3-gap"></i>
                            </button>
                            <button class="btn btn-outline-secondary btn-sm">
                                <i class="bi bi-list"></i>
                            </button>
                        </div>
                    </div>
                    <div>
                        <button class="btn btn-primary">
                            <i class="bi bi-plus-lg"></i> Tạo mới
                        </button>
                    </div>
                </div>

                <!-- Search and filter section -->
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-text bg-white">
                                        <i class="bi bi-search"></i>
                                    </span>
                                    <input type="text" class="form-control" placeholder="Tìm kiếm trong tài liệu">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="d-flex gap-2">
                                    <select class="form-select">
                                        <option>Tất cả tài liệu</option>
                                        <option>Tài liệu của tôi</option>
                                        <option>Đã chia sẻ với tôi</option>
                                    </select>
                                    <select class="form-select">
                                        <option>Sắp xếp theo</option>
                                        <option>Tên</option>
                                        <option>Ngày sửa đổi</option>
                                        <option>Ngày tạo</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Quick access section -->
                <div class="mb-4">
                    <h6 class="mb-3">Truy cập nhanh</h6>
                    <div class="row g-3">
                        <div class="col-md-3">
                            <div class="card h-100">
                                <div class="card-body text-center">
                                    <i class="bi bi-star-fill text-warning fs-4 mb-2"></i>
                                    <h6 class="card-title mb-0">Đã đánh dấu</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card h-100">
                                <div class="card-body text-center">
                                    <i class="bi bi-share-fill text-primary fs-4 mb-2"></i>
                                    <h6 class="card-title mb-0">Đã chia sẻ</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card h-100">
                                <div class="card-body text-center">
                                    <i class="bi bi-clock-history text-success fs-4 mb-2"></i>
                                    <h6 class="card-title mb-0">Gần đây</h6>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card h-100">
                                <div class="card-body text-center">
                                    <i class="bi bi-trash text-danger fs-4 mb-2"></i>
                                    <h6 class="card-title mb-0">Thùng rác</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Files and folders section -->
                <div class="card">
                    <div class="card-body">
                        <div class="row g-3">
                            <!-- Folder -->
                            <div class="col-md-3">
                                <div class="card h-100">
                                    <div class="card-body text-center">
                                        <i class="bi bi-folder-fill text-warning fs-1 mb-2"></i>
                                        <h6 class="card-title">Tài liệu quan trọng</h6>
                                        <p class="card-text text-muted small">Cập nhật 2 giờ trước</p>
                                    </div>
                                </div>
                            </div>
                            <!-- PDF File -->
                            <div class="col-md-3">
                                <div class="card h-100">
                                    <div class="card-body text-center">
                                        <i class="bi bi-file-earmark-pdf-fill text-danger fs-1 mb-2"></i>
                                        <h6 class="card-title">Báo cáo tháng 3.pdf</h6>
                                        <p class="card-text text-muted small">2.5 MB</p>
                                    </div>
                                </div>
                            </div>
                            <!-- Word File -->
                            <div class="col-md-3">
                                <div class="card h-100">
                                    <div class="card-body text-center">
                                        <i class="bi bi-file-earmark-word-fill text-primary fs-1 mb-2"></i>
                                        <h6 class="card-title">Kế hoạch dự án.docx</h6>
                                        <p class="card-text text-muted small">1.8 MB</p>
                                    </div>
                                </div>
                            </div>
                            <!-- Excel File -->
                            <div class="col-md-3">
                                <div class="card h-100">
                                    <div class="card-body text-center">
                                        <i class="bi bi-file-earmark-excel-fill text-success fs-1 mb-2"></i>
                                        <h6 class="card-title">Thống kê.xlsx</h6>
                                        <p class="card-text text-muted small">3.2 MB</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <style>
                .card {
                    transition: transform 0.2s;
                    cursor: pointer;
                }
                .card:hover {
                    transform: translateY(-5px);
                    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                }
                .input-group-text {
                    border-right: none;
                }
                .form-control:focus {
                    box-shadow: none;
                    border-color: #ced4da;
                }
                .btn-group .btn {
                    border-radius: 0;
                }
                .btn-group .btn:first-child {
                    border-top-left-radius: 0.25rem;
                    border-bottom-left-radius: 0.25rem;
                }
                .btn-group .btn:last-child {
                    border-top-right-radius: 0.25rem;
                    border-bottom-right-radius: 0.25rem;
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
</body>
</html>
