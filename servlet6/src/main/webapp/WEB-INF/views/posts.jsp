<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link rel="stylesheet" href="<c:url value="/resources/styles/css/scroll.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/scroll.js"/> "></script>

    <title>posts</title>

    <style type="text/css">
        body{
            padding-top: 40px;
        }
        a{
            color : #fff;
        }
        .navbar-inverse{
            border-radius: 0;
        }
    </style>

</head>

<body>

    <div id="wrapper" style="padding-bottom: 80px;">

        <div id="head-wrap">

            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

                <div class="container-fluid">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="http://study.istamendil.info/">ITIS programming 3</a>
                    </div>

                    <div class="collapse navbar-collapse" id="myNavbar">
                        <!--страницы куда можно перейти -->
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="<c:url value="/welcome"/>">Welcome</a>
                            </li>

                            <li>
                                <a href="<c:url value="/profile"/>">Profile</a>
                            </li>

                            <li>
                                <a href="<c:url value="/posts"/>">Posts</a>
                            </li>
                        </ul>

                        <!-- вход и регистрация -->
                        <ul class="nav navbar-nav navbar-right">

                            <c:if test="${not empty user}">
                                <li>
                                    <a href="<c:url value="/profile"/>">
                                        <c:out value="${user.getEmail()}"/>
                                        &nbsp;
                                        <span class="glyphicon glyphicon-user"></span>
                                    </a>
                                </li>

                                <li>
                                    <form action="<c:url value=""/>" method="post" style="padding-right: 17px;">
                                        <button  name="exit" value="exit" type="submit" class="btn btn-default navbar-btn"> Exit</button>
                                    </form>
                                </li>
                            </c:if>

                            <%--если вошел гость--%>
                            <c:if test="${empty user}">
                                <li>
                                    <a href="<c:url value="/registration"/>">
                                        <span class="glyphicon glyphicon-user"></span> Sign Up
                                    </a>
                                </li>

                                <li>
                                    <a href="<c:url value="/login"/>">
                                        <span class="glyphicon glyphicon-log-in"></span> Login
                                    </a>
                                </li>
                            </c:if>

                        </ul>

                    </div>

                </div>

            </nav>

        </div>

        <div id="scrolldown">
            <img alt="Прокрутить вниз" src="<c:url value="/resources/icons/down.png"/>">
        </div>

        <div id="profile" style="padding-top:50px;">

            <div class="container">

                <div class="row">

                    <div class="col-md-2"></div>

                    <div class="col-md-8">

                        <c:forEach items="${posts}" var="p">

                            <div class="panel panel-default">

                                <div class="panel-heading" >
                                    <h1 class="panel-title">
                                        <c:out value="${p.getUserName()}"/>
                                    </h1>
                                </div>

                                    <%--текст поста--%>
                                <div class="panel-body">
                                    <p><c:out value="${p.getText()}"/></p>
                                </div>

                                    <%--доп информация о посте--%>
                                <div class="panel-footer" style="padding-bottom:50px;">

                                    <%-- Like --%>
                                    <button class="submit pull-right btn btn-default well well-sm">
                                        <span class="glyphicon glyphicon-thumbs-up"></span>
                                        <span id="likes">0</span>
                                    </button>

                                    <span class="pull-left">
                                        <p>Published time : <c:out value="${p.getPublishedTime()}"/></p>
                                    </span>
                                </div>

                            </div>

                        </c:forEach>

                        <br>

                        <div id="post">

                            <form accept-charset="UTF-8" action="<c:url value=""/>" method="POST">

                                <div class="form-group">
                                    <label class="well well-sm" for="about">Write a post:</label>
                                    <textarea  style="resize: none" name="post" class="form-control" rows="5" id="about"></textarea>
                                </div>

                                <button  type="submit" class="btn btn-block btn-success">publish</button>

                            </form>

                            <br>
                            <br>

                            <c:if test="${not empty message}">
                                <p style="text-align: center">
                                    <c:out value="${message}"/>
                                </p>
                            </c:if>

                        </div>

                    </div>

                    <div class="col-md-2"></div>

                </div>

            </div>

        </div>

    </div>

    <footer class="navbar">

        <div id="scrollup">
            <img alt="Прокрутить вверх" src="<c:url value="/resources/icons/up.png"/>">
        </div>

    </footer>



</body>
</html>