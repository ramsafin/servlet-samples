<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>authentication</title>
    <meta charset='UTF-8'><title>registration</title>
    <link rel="stylesheet" type="text/css" href="/styles/authentication.css">
</head>
<body>

<div id="wrapper">
    <%--header--%>
    <div id="header">
        <h2>authentication</h2>
    </div>

    <%--form for authenticate--%>
    <div id="formDiv">

        <form id="form" action='<c:url value=''></c:url>'  method='POST'>

            <div id="email_passwordDiv">
                <%--email--%>
                <label for="email">
                    <p>Email:</p>
                    <input id="email" class="field" type="text" name="email">
                </label>
                <br>
                <br>
                <%--password--%>
                <label for="password">
                    <p>Password:</p>
                    <input id="password" class="field" type="password" name="password">
                </label>
                <br>
                <br>
            </div>

            <div id="remember_me">
                <label for="remember">
                    <input type="checkbox" name="remember" id="remember">&nbsp;запомнить меня
                </label>
            </div>

            <div id="e_button">
                <%--submit button--%>
                <input id="button" type="submit" value="Войти" name="submit">
            </div>

        </form>
    </div>

    <%--exception message--%>
    <div id="message">
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
    </div>
</div>

</body>
</html>