<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index page</title>
</head>
<body>
<c:if test="${sessionScope.user eq null}">
    <c:redirect url="/"/>
</c:if>
<h1>index page</h1>
welcome: ${sessionScope.user.username}<br/>
<a href="/user/logout">LOG OUT</a>
</body>
</html>
