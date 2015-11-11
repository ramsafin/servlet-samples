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
        $(document).ready(function(){

            $('#myButton').click(function(e){
                e.preventDefault();
                $.getJSON('post.json', {}, function(json){
                    $('h1').text(json.data.p1+ "  " + json.data.p2);
                });
                $('h1').css("text-align","center");
            })
        });
    </script>

    <title>Ajax Test</title>

</head>
<body>

    <h1></h1>

    <form style="text-align: center" class="send-message" accept-charset="UTF-8" action="<c:url value=""/>" method="POST">

        <button type="submit" id="myButton" class="btn btn-default well well-lg">Нажми меня пж</button>

    </form>

</body>
</html>