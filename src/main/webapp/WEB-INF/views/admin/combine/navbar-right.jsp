<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="bg-body-secondary with-250px">
    <div id="sidebar" class="d-flex flex-column">
        <h4 id="" class="sidebar-title margin-0 text-center">
            <i class="bi bi-book-half"></i>
            Quản lý tài liệu
        </h4>
        <div class="list-group list-group-flush list-sidebar">
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/up-document" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-cloud-arrow-up-fill"></i>
                Đăng tải tài liệu
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-list-columns-reverse"></i>
                Danh sách tài liệu
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document/deleted" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-trash-fill"></i>
                Thùng rác
            </a>
            <a href="#" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-bookmark-star-fill"></i>
                Báo cáo
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-account" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-person-lines-fill"></i>
                Danh sách người dùng
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-account-admin" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-person-fill-gear"></i>
                Danh sách người quản trị
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/setting" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-gear-fill"></i>
                Cài đặt
            </a>
        </div>


        <div class="make-by-sidebar">
            <p href="#" class="author">
                © Created by Nguyễn Hoàng Linh
            </p>
        </div>


    </div>
</div>