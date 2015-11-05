<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>

    <meta content="text/html" charset="utf-8">

    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <title>ProfileSettings</title>

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

                        <!-- вход и регистрация -->
                        <ul class="nav navbar-nav navbar-right">

                            <li>
                                <a href="<c:url value="/profile"/>">
                                    <c:out value="${user.getEmail()}"/>
                                    &nbsp;<span class="glyphicon glyphicon-user"></span>
                                </a>
                            </li>

                            <%--exit from profile button--%>
                            <li>
                                <form action="<c:url value=""/>" method="post">
                                    <button  name="exit" value="exit" type="submit" class="btn btn-default navbar-btn btn-sm"> Exit</button>
                                </form>
                            </li>

                        </ul>

                    </div>

                </div>

            </nav>

        </div>

        <div id="profile" style="padding-top:20px;">

            <div class="container">

                <div class="row">

                    <div style="text-align:center;">
                        <h3>
                            <p>User profile settings</p>
                        </h3>
                    </div>

                    <div class=" col-sm-3"></div>

                    <div class=" col-sm-6" >

                        <div class="panel panel-default">

                            <div id="p" class="panel-heading" >
                                <h3 class="panel-title">&nbsp;&nbsp;&nbsp;Information</h3>
                            </div>

                            <div class="panel-body">

                                <div class="row">

                                    <div class="col-md-3 col-lg-3 " align="center">

                                        <c:choose>

                                            <c:when test="${user.getSex().equals('male')}">
                                                <img alt="User Pic" src="<c:url value="/resources/icons/male.png"/> "
                                                     class="img-circle img-responsive">
                                            </c:when>

                                            <c:when test="${user.getSex().equals('female')}">
                                                <img alt="User Pic" src="<c:url value="/resources/icons/female.png"/> "
                                                     class="img-circle img-responsive">
                                            </c:when>

                                            <c:otherwise>
                                                <img alt="User Pic" src="<c:url value="/resources/icons/unknown_user.png"/> "
                                                     class="img-circle img-responsive">
                                            </c:otherwise>

                                        </c:choose>

                                    </div>


                                    <div class=" col-md-9 col-lg-9 ">

                                        <form accept-charset="UTF-8" name="settings_form" action='<c:url value=""/>' method="POST">

                                            <!-- two radios -->
                                            <div id="radios">
                                                <p>Choose your sex</p>
                                                <br>
                                                <c:choose>

                                                    <c:when test="${user.getSex().equals('male')}">
                                                        <label class="radio-inline" for="radio1">
                                                            <input id="radio1" type="radio" name="sex" value="male" checked>male
                                                        </label>

                                                        <label class="radio-inline" for="radio2">
                                                            <input id="radio2" type="radio" name="sex" value="female">female
                                                        </label>
                                                    </c:when>

                                                    <c:when test="${user.getSex().equals('female')}">

                                                        <label class="radio-inline" for="radio1">
                                                            <input id="radio1" type="radio" name="sex" value="male">male
                                                        </label>

                                                        <label class="radio-inline" for="radio2">
                                                            <input id="radio2" type="radio" name="sex" value="female" checked>female
                                                        </label>
                                                    </c:when>

                                                </c:choose>
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

                                            <%--about user--%>
                                            <div class="form-group">
                                                <label for="about">About yourself:</label>
                                                <textarea spellcheck="false" style="resize: none" name="about" class="form-control" rows="5" id="about" onkeyup="count(value)" maxlength="50"></textarea>
                                                <p>symbols <b id="counter">0</b>/<b>50</b></p>
                                            </div>

                                            <br>

                                            <!-- submit button -->
                                            <button type="submit" name="settings" value="settings" class="btn btn-primary btn-block">Save changes</button>

                                        </form>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <div class=" col-sm-3"></div>

                    <!-- row ends -->
                </div>

            </div>

        </div>

    </div>

</body>

</html>