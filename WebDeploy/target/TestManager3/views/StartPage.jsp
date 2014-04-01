<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setContentType("text/html; charset=utf-8");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="views/media/js/jquery.js" type="text/javascript"></script>
    <script src="views/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="views/media/js/jquery.timers.min.js"
            type="text/javascript"></script>
    <script src="views/media/myjs/jquery.dataTables.reloadAjax.min.js"
            type="text/javascript"></script>
    <script src="views/media/js/jquery-ui.js" type="text/javascript"></script>

    <link href="views/media/css/jquery.dataTables.css" rel="stylesheet"/>
    <link href="views/media/mycss/hightlighting.css" rel="stylesheet"/>
    <link href="views/media/mycss/bootstrap.css" rel="stylesheet"/>
    <link href="views/media/css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="views/media/mycss/mycss.css" rel="stylesheet"/>
    <title></title>
    <script>
        $(document).ready(function(){
            $("body").append(<%@ include file="NavigationBar.jsp" %>)
        });
    </script>
</head>
<body style="width: 100%">
</body>
</html>