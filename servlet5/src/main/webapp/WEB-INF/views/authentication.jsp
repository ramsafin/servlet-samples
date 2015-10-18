<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="header" style="text-align: center; width: 100%; height: 30px;padding-top: 10px">
    <h1>authentication</h1>
</div>

<div id="form" style="width: 50%;margin-left: 25%; margin-right: 25%">

    <form action='' method='POST'>
        <p>Email</p>
        <input class="field" type="text" name="email">
        <br>
        <p>Password</p>
        <input class="field" type="password" name="password">
        <br>
        <br>
        <input id="button" type="submit" value="Войти" name="submit">
    </form>
</div>

<div id="message">
    <c:if test="${not empty message}">
        <h3>${message}</h3>
    </c:if>
</div>

</body>
</html>