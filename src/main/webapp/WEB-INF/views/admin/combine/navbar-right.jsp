<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="bg-body-secondary with-250px">
    <div id="sidebar" class="d-flex flex-column">
        <h4 id="" class="sidebar-title margin-0 text-center">
            <i class="bi bi-book-half"></i>
            Quản lý tài liệu
        </h4>
        <div class="list-group list-group-flush list-sidebar">
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/up-document" class="list-sidebar-item list-group-item bg-body-secondary">Đăng tải tài liệu</a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document" class="list-sidebar-item list-group-item bg-body-secondary">Danh sách tài liệu</a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-document/deleted" class="list-sidebar-item list-group-item bg-body-secondary">Tài liệu đã xóa</a>
            <a href="#" class="list-sidebar-item list-group-item bg-body-secondary">Báo cáo</a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/list-account" class="list-sidebar-item list-group-item bg-body-secondary">Danh sách người dùng</a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/super/setting" class="list-sidebar-item list-group-item bg-body-secondary">Cài đặt</a>
            <a href="#" class="list-sidebar-item list-group-item bg-body-secondary">Đăng xuất</a>
        </div>


        <div class="make-by-sidebar">
            <p href="#" class="author">
                © Created by Nguyễn Hoàng Linh
            </p>
        </div>


    </div>
</div>