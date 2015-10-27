<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <title>registration</title>

    <style type="text/css">
        a{
            color : #fff;
        }
        .navbar-inverse{
            border-radius: 0;
        }
    </style>

    <script language="JavaScript">

        function validate_form(){

            var reg = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;
            if(!reg.test(document.reg_form.email.value)){
                alert("Некорректный email! Введите еще раз!")
                return false;
            }

            var inp = document.getElementsByName('sex');
            if (!inp[0].checked && !inp[1].checked) {
                alert("Выберите свой пол!!!");
                return false;
            }

            return true;
        }

        function count (str) {
            document.getElementById('counter').innerHTML=str.length;
        }

    </script>

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
                        <%--если вошел гость--%>
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

                    </ul>

                </div>

            </div>

        </nav>

        <%--end id navbar-top--%>
    </div>

    <div id="profile" style="padding-top:50px;">

        <c:if test="${not empty message}">
            <p style="text-align: center">
                <c:out value="${message}"></c:out>
            </p>
        </c:if>

        <div class="container">

            <div class="row">

                <div class="col-md-3"></div>

                <div class="col-md-6">

                    <div class="panel panel-default">

                        <div class="panel-heading" >
                            <h3 class="panel-title">&nbsp;Registration</h3>
                        </div>

                        <div class="panel-body">
                            <div class="row">

                                <form name="reg_form" action='<c:url value=""></c:url>' onsubmit="return validate_form()" method="POST" style="padding :15px;">
                                    <!-- email field -->
                                    <div class="input-group">
                                        <span class="input-group-addon" id="at-addon">@</span>
                                        <input id="email" type="text" name="email" class="form-control" placeholder="email@example.com" aria-describedby="basic-addon1">

                                    </div>
                                    <br>
                                    <!-- Password field -->
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                                    </div>

                                    <br>

                                    <!-- two radios -->
                                    <div id="radios">
                                        <label class="radio-inline" for="radio1">
                                            <input id="radio1" type="radio" name="sex" value="male" checked>male
                                        </label>
                                        <label class="radio-inline" for="radio2">
                                            <input id="radio2" type="radio" name="sex" value="female">female
                                        </label>
                                    </div>

                                    <br>

                                    <!-- subscribe checkbox -->
                                    <div class="checkbox">
                                        <label for="subscription">
                                            <input id="subscription" name="subscription" type="checkbox"> subscribe for news?
                                        </label>
                                    </div>
                                    <br>

                                    <div class="form-group">
                                        <label for="about">About yourself:</label>
                                        <textarea style="resize: none" name="about" class="form-control" rows="5" id="about" onkeyup="count(value)" maxlength="50"></textarea>
                                        <p>symbols <b id="counter">0</b>/<b>50</b></p>
                                    </div>

                                    <br>

                                    <!-- submit button -->
                                    <button type="submit" class="btn btn-primary btn-block">Submit</button>

                                </form>

                            </div>
                        </div>

                    </div>

                </div>

                <div class="col-md-3"></div>

                <!-- row ends -->
            </div>

        </div>

    </div>


    <!-- footer -->
    <footer class="footer">
        <div class="container">
            <div class="text-right">
                <hr>
                <p class="text-muted">&copy; Copyright 2015 Ramil Safin</p>
                <hr>
            </div>
        </div>
    </footer>

    <!-- wrapper ends-->
</div>

</body>
</html>