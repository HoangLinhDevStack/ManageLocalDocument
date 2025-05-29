<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<p>Điền đầy đủ thông tin giữa các trường</p>

<div class="form-outline mb-4">
    <label class="form-label" for="name">Tên người dùng</label>
    <%-- this name of user --%>
    <form:input type="text" id="name" class="form-control"
                placeholder="Nhập tên người dùng"
                lang="vi"
                htmlEscape="true"
                dynamicAttributes="{lang: vi}"

                path="user.name"/>
    <p class="form-error mt-2">
        <i class="bi bi-emoji-expressionless-fill"></i>
        <span class="error-message"></span> <%--error say --%>
    </p>
</div>

<div class="form-outline mb-4">
    <label class="form-label" for="username">Tài khoản người dùng</label>
    <%-- this username account --%>
    <form:input type="text" id="username" class="form-control"
                placeholder="Tạo tài khoản người dùng" path="username"/>
    <p class="form-error mt-2">
        <i class="bi bi-emoji-expressionless-fill"></i>
        <span class="error-message"></span> <%--error say --%>
    </p>
</div>

<div class="form-outline mb-4">
    <label class="form-label" for="password">Mật khẩu</label>
    <div class="form-show">
        <%--                                                                                            <i class="bi bi-eye"></i>--%>
        <i class="bi bi-eye-slash"></i>
    </div>
    <input type="password" id="password"
           class="form-control" placeholder="Nhập mật khẩu"/>
    <p class="form-error mt-2">
        <i class="bi bi-emoji-expressionless-fill"></i>
        <span class="error-message"></span> <%--error say --%>
    </p>
</div>

<div class="form-outline mb-4">
    <label class="form-label" for="confirm-password">Xác nhận mật khẩu</label>
    <div class="form-show">
        <%--                                            <i class="bi bi-eye"></i>--%>
        <i class="bi bi-eye-slash"></i>
    </div>
    <form:input type="password" id="confirm-password" class="form-control"
                placeholder="Xác nhận mật khẩu" path="password"/>
    <p class="form-error mt-2">
        <i class="bi bi-emoji-expressionless-fill"></i>
        <span class="error-message"></span> <%--error say --%>
    </p>
</div>

<div class="d-flex justify-content-between">

    <div class="form-outline mb-1">
        <label class="form-label" for="dob-id">Ngày tháng năm sinh</label>
        <form:input type="date" id="dob-id" class="form-control"
                    placeholder="Chose DOB" path="user.dateOfBirth"/>
        <p class="form-error mt-2">
            <i class="bi bi-emoji-expressionless-fill"></i>
            <span class="error-message"></span> <%--error say --%>
        </p>
    </div>

    <div class="form-outline mb-4">
        <label class="form-label" for="roles">Vai trò người dùng</label>
        <%-- This get value roles user --%>
        <form:select id="roles" class="form-select"
                     aria-label="Default select example" path="role.id">
            <option value="">Chọn vai trò người dùng</option>
            <c:if test="${roleUser != null}">
                <c:forEach items="${roleUser}" var="entry">
                    <form:option value="${entry.key}">${entry.value}</form:option>
                </c:forEach>
            </c:if>
        </form:select>

        <p class="form-error mt-2">
            <i class="bi bi-emoji-expressionless-fill"></i>
            <span class="error-message"></span> <%--error say --%>
        </p>
    </div>

    <div class="form-outline mb-4">
        <label class="form-label" for="gender">Chọn giới tính</label>
        <%-- This get value gender --%>
        <form:select id="gender" class="form-select"
                     aria-label="Default select example" path="sex.id">
            <option value="">Chọn giới tính</option>
            <c:if test="${sexData != null}">
                <c:forEach items="${sexData}" var="entry">
                    <form:option value="${entry.key}">${entry.value}</form:option>
                </c:forEach>
            </c:if>
        </form:select>

        <p class="form-error mt-2">
            <i class="bi bi-emoji-expressionless-fill"></i>
            <span class="error-message"></span> <%--error say --%>
        </p>
    </div>

</div>

<div class="form-model text-center pt-1 mb-3">

    <!-- Button trigger modal -->
    <button type="button"
            id="button-modal"
            class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3 p-3">
        Tạo tài khoản người dùng
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1"
         aria-labelledby="exampleModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Tạo tài
                        khoản người dùng</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn tạo tài khoản người dùng không?
                </div>
                <div class="modal-footer">
                    <button type="button" id="modalNoBtn"
                            class="model-no btn btn-secondary"
                            data-bs-dismiss="modal">Không (Đóng)
                    </button>
                    <%--                                                        <button type="button" id="modalYesBtn"--%>
                    <%--                                                                class="model-yes btn btn-primary">Có (Đồng ý)--%>
                    <input id="submit"
                           class="btn btn-primary btn-block fa-lg gradient-custom-2"
                           type="submit" value="Có (Đồng ý)"/>
                    <%--                                                        </button>--%>
                </div>
            </div>
        </div>
    </div>

</div>