<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta content="text/html" charset="utf-8">

    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <title>Profile</title>

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

                            <li>


                                <form action="<c:url value=""/>" method="post">
                                    <button id="exit" name="exit" value="exit" type="submit" class="btn btn-default navbar-btn btn-sm"> Exit</button>
                                </form>
                            </li>

                        </ul>

                    </div>

                </div>

            </nav>

        </div>

        <!--profile panel -->
        <div id="profile" style="padding-top:50px;">

            <div class="container">

                <div class="row">

                    <div style="text-align:center;">
                        <p> <h3>User profile</h3>
                    </div>

                    <div class="col-sm-3"></div>

                    <div class="col-sm-6">

                        <div class="panel panel-default">

                            <div id="p" class="panel-heading well" >
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

                                        </c:choose>

                                    </div>

                                    <div class=" col-md-9 col-lg-9 ">

                                        <table class="table table-user-information">

                                            <tr>
                                                <td>name</td>
                                                <td>...</td>
                                            </tr>

                                            <tr>
                                                <td>Gender</td>
                                                <td><c:out value="${user.getSex()}"/></td>
                                            </tr>

                                            <tr>
                                                <td>Email address</td>
                                                <td><c:out value="${user.getEmail()}"/></td>
                                            </tr>

                                            <tr>
                                                <td>Subscription</td>
                                                <td>

                                                    <c:choose>

                                                        <c:when test="${user.getSubscription().equals('on')}">
                                                            <p>you are subscribed!</p>
                                                        </c:when>

                                                        <c:when test="${user.getSubscription().equals('off')}">
                                                            <p>you are not subscribed!</p>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <p>secret</p>
                                                        </c:otherwise>

                                                    </c:choose>

                                                </td>
                                            </tr>

                                            <tr>
                                                <td>About me:</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${not empty user.getAbout()}">
                                                            <p><c:out value="${user.getAbout()}"/></p>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <p>Empty</p>
                                                        </c:otherwise>

                                                    </c:choose>
                                                </td>
                                            </tr>

                                        </table>

                                    </div>

                                </div>

                            </div>

                            <div class="panel-footer">

                                <form accept-charset="UTF-8" method="POST" action="<c:url value=""/>">

                                            <%--user deleting button--%>
                                            <span>
                                                <button
                                                    type="submit" name="delete" value="delete" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i>
                                                    Delete an account
                                                </button>
                                            </span>

                                            <!--profile settings button -->
                                            <span class="pull-right">
                                                 <button
                                                     type="submit" name="settings" value="settings" class="btn btn-sm btn-info"><i class="glyphicon glyphicon-cog"></i>
                                                     Profile settings
                                                 </button>
                                            </span>

                                        </form>

                            </div>

                        </div>

                    </div>

                    <div class="col-sm-3"></div>

                    <!-- row ends -->
                </div>

            </div>

        </div>

    </div>

</body>

</html>