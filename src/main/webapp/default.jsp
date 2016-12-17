<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ include file="commons/inc.jsp"%>
<html>
<head>
    <title>default page</title>
</head>
<body>
<h1>default page</h1>
<form action="${ctx}/user/login" method="post">
    username: <input name="username" value="a"><br/>
    password: <input name="password" type="password" value="a"><br/>
    <input type="submit" value="LOGIN">
</form>
${requestScope.message}
</body>
</html>
