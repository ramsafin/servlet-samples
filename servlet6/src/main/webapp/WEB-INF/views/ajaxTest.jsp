<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>

    <meta content="text/html" charset="utf-8">

    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
    <link href="<c:url value="/resources/styles/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/styles/css/bootstrap-theme.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <script>
//        $(document).ready(function(){
//            $('#myButton').click(function(){
//
//                $.getJSON('/ajaxServlet', {}, function(json){
//                    $('h1').text(json.message.m1);
//                });
//                $('h1').css("text-align","center");
//            })
//        });


        $(document).ready(function(){

            $('#myButton').click(function(e){
                e.preventDefault();

                $.post("<c:url value="/ajaxTest"/>", {"text":$('textarea').val()}, function(json){
                    $('textarea').empty();
                    $('h1').text(json.post);
                },

                'json'

                ).fail(function (){
                    alert("fail(");
                })

            });
        });

    </script>

    <title>Ajax Test</title>

</head>
<body>

    <h1></h1>

    <form class="send-message" accept-charset="UTF-8" action="<c:url value=""/>" method="POST">

        <div class="form-group">
            <label class="well well-sm" for="text">Write a post:</label>
            <textarea  style="resize: none" name="text" class="form-control" rows="5" id="text"></textarea>
        </div>

        <button  type="submit" id="myButton" class="btn btn-block btn-success">Отправить</button>

    </form>

</body>
</html>