<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ include file="commons/inc.jsp" %>
<html>
<head>
    <title>sign up page</title>
</head>
<body>
<h1>sign up</h1>
<form action="${ctx}/user/register" method="post">
    <input type="text" name="username" placeholder="USERNAME"><br>
    <input type="password" name="password" placeholder="PASSWORD"><br>
    <input type="submit" value="SIGN UP">
</form>
${requestScope.message}
</body>
</html>
