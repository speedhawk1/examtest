<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="commons/inc.jsp" %>
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
<a href="${ctx}/user/logout">LOG OUT</a>
</body>
</html>
