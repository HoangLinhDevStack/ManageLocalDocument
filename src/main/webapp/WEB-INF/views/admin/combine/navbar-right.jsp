<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
    .role-badge {
        font-size: 1.1rem;
        padding: 8px 15px;
        border-radius: 20px;
        font-weight: 500;
        letter-spacing: 0.5px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        transition: all 0.3s ease;
        background: linear-gradient(45deg, #2196F3, #1976D2);
        border: 2px solid #fff;
        color: white;
        cursor: help;
    }
    .role-badge:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0,0,0,0.3);
    }
    .role-badge i {
        margin-right: 8px;
        font-size: 1.2rem;
    }
    .tooltip-inner {
        max-width: 300px;
        padding: 10px 15px;
        text-align: left;
        font-size: 0.9rem;
        line-height: 1.5;
    }
    .tooltip-inner ul {
        margin: 5px 0;
        padding-left: 20px;
    }
    .tooltip-inner li {
        margin: 3px 0;
    }
</style>

<div class="bg-body-secondary with-250px">
    <div id="sidebar" class="d-flex flex-column">
        <h4 id="" class="sidebar-title margin-0 text-center">
            <i class="bi bi-book-half"></i>
            Quản lý tài liệu
        </h4>

        <div class="list-group list-group-flush list-sidebar">
            <sec:authorize access="hasAuthority('Super')">
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
            </sec:authorize>

            <sec:authorize access="hasAuthority('Manager')">
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/up-document" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-cloud-arrow-up-fill"></i>
                Đăng tải tài liệu
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/list-document" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-list-columns-reverse"></i>
                Danh sách tài liệu
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/list-document/deleted" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-trash-fill"></i>
                Thùng rác
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/list-account" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-person-lines-fill"></i>
                Danh sách người dùng
            </a>
            <a href="${pageContext.request.contextPath}/ManagerBook/admin/manager/setting" class="list-sidebar-item list-group-item bg-body-secondary">
                <i class="bi bi-gear-fill"></i>
                Cài đặt
            </a>
            </sec:authorize>
        </div>

        <div class="text-center mb-3 mt-3">
            <sec:authorize access="hasAuthority('Super')">
                <span class="role-badge" 
                      data-bs-toggle="tooltip" 
                      data-bs-html="true"
                      data-bs-placement="right"
                      title="<sec:authentication property='authorities[0].authority'/> có các quyền sau:
                             <ul>
                               <li>Tạo và quản lý tài khoản người dùng</li>
                               <li>Tạo và quản lý tài khoản quản trị</li>
                               <li>Xem và đọc tất cả tài liệu</li>
                               <li>Xóa tài liệu vào thùng rác</li>
                               <li>Xóa vĩnh viễn tài liệu</li>
                             </ul>">
                    <i class="bi bi-person-badge"></i>
                    <sec:authentication property="authorities[0].authority"/>
                </span>
            </sec:authorize>
            
            <sec:authorize access="hasAuthority('Manager')">
                <span class="role-badge" 
                      data-bs-toggle="tooltip" 
                      data-bs-html="true"
                      data-bs-placement="right"
                      title="<sec:authentication property='authorities[0].authority'/> có các quyền sau:
                             <ul>
                               <li>Tạo và quản lý tài khoản người dùng</li>
                               <li>Xem và đọc tất cả tài liệu</li>
                               <li>Xóa tài liệu vào thùng rác</li>
                             </ul>">
                    <i class="bi bi-person-badge"></i>
                    <sec:authentication property="authorities[0].authority"/>
                </span>
            </sec:authorize>
            
            <sec:authorize access="!hasAuthority('Super') and !hasAuthority('Manager')">
                <span class="role-badge"
                      data-bs-toggle="tooltip"
                      data-bs-html="true"
                      data-bs-placement="right"
                      title="<sec:authentication property='authorities[0].authority'/> - Vai trò cơ bản">
                    <i class="bi bi-person-badge"></i>
                    <sec:authentication property="authorities[0].authority"/>
                </span>
            </sec:authorize>
    </div>

        <div class="make-by-sidebar">
            <p href="#" class="author">
                © Created by Nguyễn Hoàng Linh
            </p>
        </div>

    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl, {
            html: true
        });
    });
});
</script>