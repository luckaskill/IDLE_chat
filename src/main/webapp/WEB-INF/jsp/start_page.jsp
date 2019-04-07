<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 25.01.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Start page</title>
    <style>
        body {
            background-size: cover;
            background-image: url("<c:url value="/images/background.png"/>");
        }
    </style>
</head>
<body>

<div style="color: bisque;" align="center">
    <form action="controller" method="post" style="margin-top: 10%;">
        <input type="hidden" name="command" value="authorization">
        Login:
        <div style="margin-bottom: 10px;margin-top: 10px">
            <input type="text" name="login" value=""/>
        </div>
        Password:
        <div style="margin-bottom: 10px;margin-top: 10px">
            <input type="password" name="password" value=""/>
        </div>
        <input type="submit" name="submit" value="LogIn"/>
    </form>
    <c:out value="${requestScope.error}"/>
</div>
</body>
</html>