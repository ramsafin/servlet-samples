<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <title>Welcome</title>

    <style type="text/css">
        a{
            color : #fff;
        }
        .navbar-inverse{
            border-radius: 0;
        }

    </style>

</head>
<body>

    <div id="wrapper">

        <div id="navbar-top">

            <nav class="navbar navbar-inverse">

                <div class="container-fluid">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">ITIS programming 3</a>
                    </div>

                    <div class="collapse navbar-collapse">

                        <!--страницы куда можно перейти -->
                        <ul class="nav navbar-nav">

                            <li>
                                <a href="<c:url value="/welcome"></c:url>">Welcome</a>
                            </li>

                            <li>
                                <a href="<c:url value="/profile"></c:url>">Profile</a>
                            </li>

                            <li>
                                <a href="<c:url value="/posts"></c:url>">Posts</a>
                            </li>
                        </ul>

                        <!-- вход и регистрация -->
                        <ul class="nav navbar-nav navbar-right">

                            <!-- если пользователь вошел  -->
                            <c:if test="${not empty user}">
                                <li>
                                    <a href="#">
                                        <c:out value="${user.getEmail()}"></c:out>
                                        &nbsp;
                                        <span class="glyphicon glyphicon-user"></span>
                                    </a>
                                </li>

                                <li>
                                    <form action="<c:url value=""></c:url>" method="post">
                                        <button  name="exit" value="exit" type="submit" class="btn btn-default navbar-btn"> Exit</button>
                                    </form>
                                </li>
                            </c:if>

                            <%--если вошел гость--%>
                            <c:if test="${empty user}">
                                <li>
                                    <a href="<c:url value="/registration"></c:url>">
                                        <span class="glyphicon glyphicon-user"></span> Sign Up
                                    </a>
                                </li>

                                <li>
                                    <a href="<c:url value="/login"></c:url>">
                                        <span class="glyphicon glyphicon-log-in"></span> Login
                                    </a>
                                </li>
                            </c:if>

                        </ul>

                    </div>

                </div>

            </nav>

        <%--end id navbar-top--%>
        </div>

        <div class="page-header"  style="text-align:center;">
            <h1>Programming in Java</h1>
        </div>


        <div id="main">

            <div class="container" style="margin-top : 30px;">

                <div class="jumbotron">
                    <h1>Java programming!</h1>
                    <p>Hello my dear friend!<br>You are in java language programming web site</p>
                    <p>
                        <a href="https://www.java.com/ru/" class="btn btn-primary btn-lg" role="button">
                            Learn more
                        </a>
                    </p>
                </div>

                <div class="row">

                    <div class="col-sm-4">
                        <h3>About us</h3>
                        <p>We are ITIS students which want to learn Java.</p>
                        <p>Our aim is to become a real software engineer or someone else.</p>
                    </div>

                    <div class="col-sm-4">
                        <h3>How I can become a cool programmer?</h3>
                        <p>You should read a lot of books,<br>
                            improve yourself every day.</p>
                    </div>

                    <div class="col-sm-4">

                        <h3>What if I don't know how to start?</h3>
                        <p>If you don't know how to start to learn Java<br>
                            you fell into good site.</p>
                        <p>Here we will help to begin.
                            Just <a href='<c:url value="/registration"></c:url>' style="color:blue">sign up</a> and
                            <br> you will see the result!
                    </div>

                    <%--end of row class--%>
                </div>

                <br>

                <div class="text-left">
                    <h3>Quotes of great men</h3>
                    <blockquote>
                        <p>Давайте напишем аналог игры крокодил на Java =)<br>он тормозит и глючит а то \=</p>
                        <footer>Alexander Ferenets</footer>
                    </blockquote>
                </div>

            </div>

        <%--main id end--%>
        </div>

        <br>

        <footer class="footer">
            <div class="container">
                <div class="text-right">
                    <hr>
                    <p class="text-muted">&copy; Copyright 2015 Ramil Safin</p>
                    <hr>
                </div>
            </div>
        </footer>

    </div>

</body>
</html>