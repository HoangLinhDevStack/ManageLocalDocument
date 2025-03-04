<%--<?xml version="1.0" encoding="UTF-8"?>--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value = "UTF-8" />



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xml:lang="vi" lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html"/>
    <title>Title</title>
</head>
<body>



<form:form modelAttribute="user" method="POST" acceptCharset="UTF-8">

<%--    Điền form tại đây--%>
<%--    <form:input path="name"/>--%>

    <label>

        Điền form tại đây
        <form:input type="text"  path="name"/>
    </label>


    <input type="submit" name="submit">

</form:form>

<%--<jsp:include flush="false"  page="TestSendData.jsp"/>--%>
</body>
</html>



<%--    <form:form modelAttribute="SexData">--%>
<%--        <c:if test="${SexData != null}">--%>
<%--            <ul>--%>
<%--                <c:forEach items="${SexData}" var="entry">--%>
<%--                    <li>${entry.key}: ${entry.value}</li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </c:if>--%>

<%--    <c:if test="${rolesUser != null}">--%>
<%--        <ul>--%>
<%--            <c:forEach items="${rolesUser}" var="entry">--%>
<%--                <li>${entry.key}: ${entry.value}</li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </c:if>--%>



<%--    </form:form>--%>

<%--<form:select path="Sex">--%>
<%--    <form:options items="${sexData}" />--%>
<%--</form:select>--%>