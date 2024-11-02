<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hello World!</title>
</head>
<body>

<h1>Login page successfully</h1>

<form:form action="${pageContext.request.contextPath}/ManagerBook/admin/logout" method="post">
    <input type="submit" value="Sign Out"/>
</form:form>

</body>
</html>
