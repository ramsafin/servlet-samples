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

            $('#myButton').click(function(){

                if($('h1').text().length != 0){
                    $('h1').text("");
                    return;
                }
                $.getJSON('/example.json', {}, function(json){

                    $('h1').text(json.message.m1 +"   "+json.message.m2);

                    $('#info').css("text-align","center");
                });
            })
        });

    </script>

    <title>Ajax Test</title>

</head>
<body>
    <div id="info">
        <h1></h1>
    </div>

    <p style="text-align: center; padding-top: 20px;">
        <button id="myButton" class="btn btn-primary btn-lg" type="submit">
            Click me!
        </button>
    </p>

</body>
</html>