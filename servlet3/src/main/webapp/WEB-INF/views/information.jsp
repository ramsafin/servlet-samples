<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.kpfu.itis.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.kpfu.itis.repository.UserRepository" %>
<%@ page import="ru.kpfu.itis.exceptions.DatabaseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset='UTF-8'>
    <title>info</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        table{
            border-collapse: collapse;
            border: 3px solid black;
        }

        td,th{
            border: 2px solid lightgray;
            padding: 20px;
        }

        tr{
            text-align: center;
        }
        #header{
            padding-top: 20px;
            text-align: center;
            width: 100%;
            height: 60px;
        }

        #table{
            margin-left: 10%;
        }

        #info{
            width: 100%;
            height: 30px;
        }

    </style>
</head>
<body>
    <div id="header">
        <h3>registered users info</h3>
    </div>

    <br>
    <div id="table">
        <table cellpadding="10">
            <tr>
                <th>email</th>
                <th>password</th>
                <th>sex</th>
                <th>subscription</th>
            </tr>
            <%
                try {
                    List<User> users = UserRepository.getUsers();
                    for (User user : users){
            %>
            <tr>
                <td><%=user.getEmail()%></td>
                <td><%=user.getPassword()%></td>
                <td><%=user.getSex()%></td>
                <td><%=user.getCheckbox()%></td>
            </tr>

            <%  }
            } catch (DatabaseException e) {
                    request.setAttribute("message",e.getMessage());
            }
            %>
            <br>
        </table>
    </div>

    <div id="info" style="text-align: center; padding-top: 15px">
        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>
    </div>
</body>
</html>
