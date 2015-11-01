<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta content="text/html" charset="utf-8">

    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <title>Profile</title>

    <script type="text/javascript" language="JavaScript" src="<c:url value="/resources/js/counter.js"/> "></script>

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


        <div id="head-wrap">

            <nav class="navbar navbar-inverse" role="navigation">

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

                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="<c:url value="/profile"/>">
                                    <c:out value="${user.getEmail()}"/>
                                    &nbsp;<span class="glyphicon glyphicon-user"></span>
                                </a>
                            </li>

                            <li>
                                <form action="<c:url value=""/>" method="post">
                                    <button  name="exit" value="exit" type="submit" class="btn btn-default navbar-btn"> Exit</button>
                                </form>
                            </li>

                        </ul>

                    </div>

                </div>

            </nav>

        </div>

    </div>


    <div id="profile" style="padding-top:50px;">

        <div class="container">

            <div class="row">

                <div style="text-align:center;">
                    <p><h3>User profile settings</h3></p>
                </div>

                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


                    <div class="panel panel-default">

                        <div id="p" class="panel-heading well well-lg" >
                            <h3 class="panel-title">&nbsp;&nbsp;&nbsp;Information</h3>
                        </div>

                        <div class="panel-body">

                            <div class="row">

                                <div class="col-md-3 col-lg-3 " align="center">

                                    <c:if test="${user.getSex().equals('male')}">
                                        <img alt="User Pic" src="<c:url value="/resources/icons/male.png"/> "
                                             class="img-circle img-responsive">
                                    </c:if>

                                    <c:if test="${user.getSex().equals('female')}">
                                        <img alt="User Pic" src="<c:url value="/resources/icons/female.png"/> "
                                             class="img-circle img-responsive">
                                    </c:if>

                                </div>


                                <div class=" col-md-9 col-lg-9 ">

                                    <form accept-charset="UTF-8" name="settings_form" action='<c:url value=""/>' method="POST" style="padding :15px;">

                                        <!-- two radios -->
                                        <div id="radios">
                                            <p>Choose your sex</p>
                                            <br>
                                            <c:if test="${user.getSex().equals('male')}">
                                                <label class="radio-inline" for="radio1">
                                                    <input id="radio1" type="radio" name="sex" value="male" checked>male
                                                </label>

                                                <label class="radio-inline" for="radio2">
                                                    <input id="radio2" type="radio" name="sex" value="female">female
                                                </label>
                                            </c:if>

                                            <c:if test="${user.getSex().equals('female')}">

                                                <label class="radio-inline" for="radio1">
                                                    <input id="radio1" type="radio" name="sex" value="male" checked>male
                                                </label>

                                                <label class="radio-inline" for="radio2">
                                                    <input id="radio2" type="radio" name="sex" value="female" checked>female
                                                </label>
                                            </c:if>
                                        </div>

                                        <br>

                                        <!-- subscribe checkbox -->
                                        <div class="checkbox">
                                            <label for="subscription">
                                                <c:if test="${user.getSubscription().equals('on')}">
                                                    <input id="subscription" name="subscription" type="checkbox" checked> subscribe for news?
                                                </c:if>
                                                <c:if test="${user.getSubscription().equals('off')}">
                                                    <input id="subscription" name="subscription" type="checkbox"> subscribe for news?
                                                </c:if>
                                            </label>
                                        </div>
                                        <br>

                                        <div class="form-group">
                                            <label for="about">About yourself:</label>
                                            <textarea  style="resize: none" name="about" class="form-control" rows="5" id="about" onkeyup="count(value)" maxlength="50"></textarea>
                                            <p>symbols <b id="counter">0</b>/<b>50</b></p>
                                        </div>

                                        <br>

                                        <!-- submit button -->
                                        <button type="submit" name="settings" value="settings" class="btn btn-primary btn-block">change</button>

                                    </form>

                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <!-- row ends -->
            </div>

        </div>

    </div>

</body>
</html>