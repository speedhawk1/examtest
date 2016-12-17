<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ include file="commons/inc.jsp" %>
<html>
<head>
    <title>user page</title>
</head>
<body>
<c:if test="${sessionScope.user eq null}">
    <c:redirect url="/"/>
</c:if>
<h1>user page</h1>
user: ${sessionScope.user.username}<br/>
<a href="${ctx}/user/logout">LOG OUT</a>
</body>
</html>
