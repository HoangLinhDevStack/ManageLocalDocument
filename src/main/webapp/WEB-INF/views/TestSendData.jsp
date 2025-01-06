<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <form:form modelAttribute="SexData">--%>
        <c:if test="${SexData != null}">
            <ul>
                <c:forEach items="${SexData}" var="entry">
                    <li>${entry.key}: ${entry.value}</li>
                </c:forEach>
            </ul>
        </c:if>

    <c:if test="${rolesUser != null}">
        <ul>
            <c:forEach items="${rolesUser}" var="entry">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
    </c:if>



<%--    </form:form>--%>

<form:select path="Sex">
    <form:options items="${sexData}" />
</form:select>
</body>
</html>
