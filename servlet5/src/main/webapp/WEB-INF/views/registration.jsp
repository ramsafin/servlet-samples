<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'><title>registration</title>
    <link rel="stylesheet" type="text/css" href="/registration.css">
    <script type="text/javascript" language="javascript">

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
            document.getElementById('symbols').innerHTML=str.length;
        }

    </script>
</head>
<body>
    <%--wrapper--%>
    <div id="wrapper">

        <%--header of registration form--%>
        <div id="header">
            <h1>Registration form</h1>
        </div>

        <%--my form--%>
        <div id="formDiv">

            <form id="form" name="reg_form" class="form_class" onsubmit="return validate_form()" action='' method='POST'>

                <%--eamil and password div--%>
                <div id="email_passwordDiv">
                    <%--email--%>
                    <label for="email"><p>Email:</p><input id="email" class="email_class" type='text' name='email'></label>
                    <br>
                    <br>
                    <%--password--%>
                    <label for="password"><p>Password</p><input id="password" class="email_class" type='password' name='password'></label>
                </div>

                <%--radios div--%>
                <div id="radiosDiv">
                    <%--radios sex (male and female)--%>
                    <label for="sex1"><input class="sex_class" id="sex1" type='radio' name='sex' value="male">&nbsp;мужской</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label for="sex2"><input class="sex_class" id="sex2" type='radio' name='sex' value="female">&nbsp;женский</label>
                </div>

                <%--about people div--%>
                <div id="aboutDiv">
                    <p>
                        <label><p>Напишите про себя:</p>
                            <textarea id="about" onkeyup="count(value)" maxlength="50" name="about"></textarea>
                        </label>
                    </p>
                    <%--counter of symbils in about--%>
                    <p>символов <b id="symbols">0</b>/<b>50</b></p>
                </div>


                <%--checkbox div--%>
                <div id="checkboxDiv">
                    <%--subcribtion--%>
                    <label for="box"><input id="box" class="box_class" type='checkbox' name='subscription'>&nbsp;подписаться</label>
                </div>

                <%--submit button--%>
                <div>
                    <input class="submit-class" id="submit" type='submit' value='отправить'>
                </div>

            </form>
        </div>

        <%--exceptions div--%>
        <div id="message">
            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>
        </div>

    </div>
</body>
</html>