<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta content="text/html" charset="utf-8">

    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <script type="text/javascript" language="JavaScript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/> "></script>
    <script type="text/javascript" language="JavaScript" src="<c:url value="/resources/js/counter.js"/> "></script>
    <script type="text/javascript" language="JavaScript" src="<c:url value="/resources/js/form_validate.js"/> "></script>
    <script type="text/javascript" language="JavaScript" src="<c:url value="/resources/js/password_check.js"/> "></script>

    <title>Registration</title>

    <%--ajax checking an email for available--%>
    <script language="JavaScript">
        $(document).ready(function () {
            $('#email').blur(function(){

                $.post("<c:url value="/registration"/>", {"ch_email": $('#email').val()}, function(response){
                            if(response.param == '0' && $('#email').val().length > 0){
                                //available
                                $('#email_msg').find('p').css("color","green").text('email is available');
                            }else{
                                //not available
                                $('#email_msg').find('p').css("color","red").text('email is not available');
                            }
                        },

                        'json'

                ).fail(function (){
                            alert("Sorry, can't send a post. Try again!");
                        })
            });

        });
    </script>

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

        <%--navigation--%>
        <div id="navbar-top">

            <nav class="navbar navbar-inverse">

                <div class="container-fluid">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="http://study.istamendil.info/">ITIS programming 3</a>
                    </div>

                    <div class="collapse navbar-collapse">

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
                                <a href="<c:url value="/registration"/>">
                                    <span class="glyphicon glyphicon-user"></span> Sign Up
                                </a>
                            </li>

                            <li>
                                <a href="<c:url value="/login"/>">
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


            <div class="container">

                <c:if test="${not empty message}">
                    <p style="text-align: center; font-size: 12pt;">
                        <c:out value="${message}"/>
                    </p>
                </c:if>

                <div class="row">

                    <div class="col-md-3"></div>

                    <div class="col-md-6">

                        <div class="panel panel-default">

                            <div class="panel-heading well" >
                                <h3 class="panel-title">&nbsp;Registration</h3>
                            </div>

                            <div class="panel-body">

                                <div class="row">

                                    <form id="form" accept-charset="UTF-8" onsubmit="return validate_form()" name="reg_form" action='<c:url value=""/>' method="POST" style="padding :20px;">

                                        <!-- email field -->
                                        <div class="input-group">
                                            <span class="input-group-addon" id="at-addon">@</span>
                                            <input id="email" type="text" name="email" class="form-control" placeholder="email@example.com">
                                        </div>
                                        <br>
                                        <div id="email_msg" style="text-align: center; font-size: 12pt;" >
                                            <p></p>
                                        </div>

                                        <br>

                                        <!-- Password field -->
                                        <div class="form-group">
                                            <label for="password">Password</label>
                                            <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                                        </div>

                                        <div id="pass_msg" style="text-align: center; font-size: 12pt;" >
                                            <p></p>
                                        </div>

                                        <br>

                                        <!-- two sex  radios -->
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
                                            <textarea spellcheck="false" style="resize: none" name="about" class="form-control" rows="5" id="about" maxlength="50"></textarea>
                                        </div>

                                        <div class="progress" style="width: 200px;">
                                            <div id="count" class="progress-bar" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">0
                                            </div>
                                        </div>

                                        <br>

                                        <!-- submit button -->
                                        <button id="submit" type="submit" class="btn btn-primary btn-block">Submit</button>

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

    </div>

</body>
</html>