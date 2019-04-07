<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 01.02.2019
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>hello page</title>
    <style>
        .chatWindow {
            position: fixed;
            width: 40%;
            height: 85%;
            right: 8%;
            top: 5%;
            color: #1abadf;
            font-family: cursive;
            word-wrap: break-word;
        }

        .textWindow {
            height: 100%;
            width: 80%;
            font-family: monospace;
            font-size: larger;
            border-radius: 4px;
        }

        .sendWindow {
            position: absolute;
            bottom: 2%;
            height: 10%;
            width: 50%;
            right: 25%;
            margin-bottom: 3%;
        }

        body {
            background-size: cover;
            background-image: url("<c:url value="/images/background2.png"/>");
            color: aliceblue;
        }

        #msgsWindow {
            height: 80%;
            overflow: auto;
            margin-top: 2%;
        }

        #sendMsg {
            position: absolute;
            background-image: url("https://lh3.googleusercontent.com/S0wT3WlK6_Y8nKy71NEhfC57nPbLxTZkjox02PArpgZReRZ0RHPZ7ms2f8pIgTrcSAWH");
            width: 40px;
            background-color: #0000ff00;
            border: 0;
            top: 23%;
        }

        .sentMsg {
            color: #4d5aba;
        }

        .receivedMsg {
            color: #588649;
        }

        .userForm {
            margin: 0;
        }

        .viewCorButton {
            width: 100%;
            height: 100%;
            background-color: #3c277e30;
            border: 0;
            color: #cde8ffc7;
        }

        table {
            position: absolute;
            top: 15%;
            left: 10%;
            width: 20%
        }

        #messageText {
            color: #4d5aba;
            background-color: #68a3c53b;
        }
    </style>
</head>
<body>
<input id="userName" type="hidden" value="${sessionScope.user.login}">
<div align="center" class="chatWindow">
    <div id="msgsWindow">
    </div>
    <form class="sendWindow">
        <textarea id="messageText" class="textWindow"></textarea>
        <button type="submit" name="sendMsgButton" id="sendMsg">
            <img src="https://lh3.googleusercontent.com/S0wT3WlK6_Y8nKy71NEhfC57nPbLxTZkjox02PArpgZReRZ0RHPZ7ms2f8pIgTrcSAWH" alt="Send" style="width: 100%">
        </button>
    </form>
</div>
<table border="1">
    <tr>
        <td align="center">
            Users
        </td>
    </tr>
    <c:forEach items="${sessionScope.allUsers}" var="user">
        <tr>
            <td>
                <form class="userForm">
                    <input name="userToTalk" type="hidden" value="${user.id}">
                    <input class="viewCorButton" type="submit" value="${user.login}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<div style="right: 3%; top: 3%; width: 50px; height: 27px; position: fixed">
    <form>
        <input type="hidden" name="command" value="logOut">
        <button style="background-color: #0000ff00; border: 0;"><img width="100%" height="100%" src="https://cdn0.iconfinder.com/data/icons/arrows-volume-5/48/251-512.png" alt="Log out"> </button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    <%@include file="../scripts/loadMsg.js" %>
</script>
</body>
</html>
