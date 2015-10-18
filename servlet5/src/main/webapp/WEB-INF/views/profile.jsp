<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset='UTF-8'><title>registration</title>
    <link rel="stylesheet" type="text/css" href="/profile.css">
    <title>my profile</title>
</head>
<body>

<div id="wrapper">

    <%--header--%>
    <div id="header">
        <h3>my profile</h3>
    </div>

    <div id="information">

        <%--here must be information--%>

    </div>
        
    <%--exit from profile--%>
    <form action='' method="post">
        <input type="submit" value="exit" name="exit">
    </form>

    <div id="info" style="text-align: center; padding-top: 15px">
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
    </div>
</div>

</body>
</html>