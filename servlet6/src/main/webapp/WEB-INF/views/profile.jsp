<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
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
                        <li>
                            <a href="<c:url value="/profile"></c:url>">
                                <c:out value="${user.getEmail()}"></c:out>
                                &nbsp;<span class="glyphicon glyphicon-user"></span>
                            </a>
                        </li>

                        <li>


                            <form action="<c:url value=""></c:url>" method="post">
                                <button  name="exit" value="exit" type="submit" class="btn btn-default navbar-btn"> Exit</button>
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
                    <p><h3>User profile</h3></p>
                </div>

                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


                    <div class="panel panel-default">

                        <div id="p" class="panel-heading" >
                            <h3 class="panel-title">&nbsp;&nbsp;&nbsp;Information</h3>
                        </div>

                        <div class="panel-body">

                            <div class="row">

                                <div class="col-md-3 col-lg-3 " align="center">

                                    <c:if test="${user.getSex().equals('male')}">
                                        <%--src="https://cdn4.iconfinder.com/data/icons/rcons-user/32/child_boy-512.png"--%>
                                        <img alt="User Pic" src="https://cdn4.iconfinder.com/data/icons/rcons-user/32/child_boy-512.png"
                                             class="img-circle img-responsive">
                                    </c:if>

                                    <c:if test="${user.getSex().equals('female')}">
                                        <img alt="User Pic" src="http://simpleicon.com/dev/wp-content/uploads/business-woman-2-256x256.png"
                                             class="img-circle img-responsive">
                                    </c:if>

                                </div>


                                <div class=" col-md-9 col-lg-9 ">

                                    <table class="table table-user-information">

                                        <tr>
                                            <td>name</td>
                                            <td>...</td>
                                        </tr>

                                        <tr>
                                            <td>Gender</td>
                                            <td><c:out value="${user.getSex()}"></c:out></td>
                                        </tr>

                                        <tr>
                                            <td>Email address</td>
                                            <td><c:out value="${user.getEmail()}"></c:out></td>
                                        </tr>

                                        <tr>
                                            <td>Subscription</td>
                                            <td>

                                                <c:if test="${user.getSubscription().equals('on')}">
                                                    you are subscribed!
                                                </c:if>

                                                <c:if test="${user.getSubscription().equals('off')}">
                                                    <p>you are not subscribed!</p>
                                                </c:if>

                                            </td>
                                        </tr>

                                        <tr>
                                            <td>About me:</td>
                                            <td>
                                                <c:out value="${user.getAbout()}"></c:out>
                                            </td>
                                        </tr>

                                    </table>
                                    </table>

                                </div>
                            </div>
                        </div>

                        <div class="panel-footer">

                                    <%--В разработке ......--%>

                                    <form method="POST" action="<c:url value=""></c:url>">

                                        <%--<span>--%>
                                            <%--<button--%>
                                                <%--type="submit" name="delete_user" value="delete" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i>--%>
                                                <%--Delete an account--%>
                                            <%--</button>--%>
                                        <%--</span>--%>

                                        <%--<!--сслыка на сервлет по изменению профиля -->--%>
                                        <%--<span class="pull-right">--%>
                                            <%--<a type="submit" class="btn btn-sm btn-info"><i class="glyphicon glyphicon-cog"></i> Profile settings--%>
                                            <%--</a>--%>
                                        <%--</span>--%>

                                    </form>
                        </div>

                    </div>
                </div>

                <!-- row ends -->
            </div>

        </div>

    </div>


    <!-- wrapper ends -->
</div>

</body>
</html>