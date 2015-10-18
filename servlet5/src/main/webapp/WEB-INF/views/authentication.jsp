<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>authentication</title>
    <meta charset='UTF-8'><title>registration</title>
    <link rel="stylesheet" type="text/css" href="/authentication.css">
</head>
<body>

<div id="wrapper">
    <%--header--%>
    <div id="header" style="text-align: center; width: 100%; height: 30px;padding-top: 10px">
        <h1>authentication</h1>
    </div>

    <%--form for authenticate--%>
    <div id="formDiv">

        <form id="form" action='' method='POST'>

            <%--email--%>
            <label>
                <p>Your email:</p>
                <input id="email" class="field" type="text" name="email">
            </label>
            <br>

            <%--password--%>
            <label for="password">
                <p>Your password:</p>
                <input id="password" class="field" type="password" name="password"></label>
            <br>

            <%--submit button--%>
            <input id="button" type="submit" value="Войти" name="submit">

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