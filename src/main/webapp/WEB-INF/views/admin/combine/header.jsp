<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<nav class="header navbar navbar-expand-lg bg-body-tertiary d-flex">
    <div class="container-fluid flex-fill">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ManagerBook/admin">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ManagerBook/admin/create-account">Tạo tài khoản</a>
                </li>

            </ul>
            <form class="d-flex" role="search">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item dropdown">

                        <a class="nav-link dropdown-toggle" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="" alt="ảnh đại diện của admin"
                                 class="rounded-circle hw-35">
                        </a>

                        <ul class="dropdown-menu">

                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">
                                    <i class="bi bi-box-arrow-right"></i>
                                    Đăng xuất
                                </a>
                            </li>

                        </ul>

                    </li>

                </ul>
            </form>
        </div>
    </div>
</nav>