<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset='UTF-8'><title>registration</title>
    <link rel="stylesheet" type="text/css" href="/styles/profile.css">
    <title>my profile</title>
</head>
<body>

    <div id="wrapper">

        <%--header--%>
        <div id="header">
            <h2>my profile</h2>
        </div>

        <div id="profile">
            <%--some info about user--%>
            <div id="information">

                <p><b>Email:</b> <c:out value="${user.getEmail()}"></c:out></p>
                <br>
                <p><b>Sex:</b> <c:out value="${user.getSex()}"></c:out></p>
                <br>
                <p><b>Subscribe option:</b> <c:out value="${user.getSubscription()}"></c:out></p>
                <br>

                <div id="about">
                    <p><b>About me</b></p>
                    <br>
                    <p>
                        <c:out value="${user.getAbout()}"></c:out>
                    </p>
                </div>
            </div>


            <div id="exit">
                <%--exit from profile--%>
                <form action='<c:url value=''></c:url>' method="post">
                    <input id="exit_but" type="submit" value="exit" name="exit">
                </form>
            </div>

        </div>

        <%--exception message--%>
        <div id="info">
            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>
        </div>

    </div>
</body>
</html>