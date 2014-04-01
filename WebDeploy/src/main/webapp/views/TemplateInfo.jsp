<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setContentType("text/html; charset=utf-8");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="views/media/js/jquery.js" type="text/javascript"></script>
    <script src="views/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="views/media/js/jquery.timers.min.js"
            type="text/javascript"></script>
    <script src="views/media/myjs/jquery.dataTables.reloadAjax.min.js"
            type="text/javascript"></script>
    <script src="views/media/js/jquery-ui.js" type="text/javascript"></script>
    <script src="views/media/js/purl.min.js"></script>

    <link href="views/media/css/jquery.dataTables.css" rel="stylesheet"/>
    <link href="views/media/mycss/hightlighting.css" rel="stylesheet"/>
    <link href="views/media/mycss/bootstrap.css" rel="stylesheet"/>
    <link href="views/media/css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="views/media/mycss/mycss.css" rel="stylesheet"/>
    <title></title>

    <style>
        .dataTables_wrapper {
            width: 30%;
            float: left;
            clear: none;
            margin-right: 10px;
        }


    </style>

    <script>

        // проверяем, передан ли параметр для стилизации страницы
        if($.url().param('style') !== undefined){
            $("head style").append( $.url().param('style').toString() );
        }

        var docHasFocus = true;

        document.onfocus = function () {
            docHasFocus = true;
        }

        document.onblur = function () {
            docHasFocus = false;
        }

        $(document).ready(function () {


            $paramsTable = $("#params").dataTable({
                "bProcessing": false,
                "bServerSide": false,
                "bPaginate": false,
                "bSort": true,
                "bStateSave": false,
                "bLengthChange": true,
                "bFilter": false,
                "bInfo": false
            });

            $testsTable = $("#tests").dataTable({
                "bProcessing": false,
                "bServerSide": false,
                "bPaginate": false,
                "bSort": true,
                "bStateSave": false,
                "bLengthChange": true,
                "bFilter": false,
                "bInfo": false
            });

            $templateInfoTable = $("#templateInfo").dataTable({
                "bProcessing": false,
                "bServerSide": false,
                "bPaginate": false,
                "bSort": true,
                "bStateSave": false,
                "bLengthChange": true,
                "bFilter": false,
                "bInfo": false
            });


            $.ajax({
                url: "rest/services/TestsServ?templateId=" + $.url().param('id'),
                type: "GET",
                async: true,
                success: function (data) {
                    $.each(data.aaData, function (index, value) {
                        $testsTable.fnAddData([
                            value.name.toString(),
                            value.path.toString()
                        ])
                    });
                }
            });

            $.ajax({
                url: "rest/services/ParamsServ?templateId=" + $.url().param('id'),
                type: "GET",
                async: true,
                success: function (arg) {
                    for (var key in arg.data) {
                        if (arg.data.hasOwnProperty(key)) {
                            $paramsTable.fnAddData([
                                key.toString(),
                                arg.data[key].toString()
                            ])
                        }
                    }

                }
            });

            $.ajax({
                url: "rest/services/TemplatesServ?templateId=" + $.url().param('id'),
                type: "GET",
                async: true,
                success: function (data) {
                    $templateInfoTable.fnAddData([
                        data.data.template_name.toString(),
                        data.data.description.toString()
                    ]);

                }
            });


            $("#tables").after('<div style="clear: both">' + <%@ include file="NavigationBar.jsp"%>
                    +
                    '<div>');
        });


    </script>
</head>
<body style="width: 100%; height: 100%" class="ex_highlight">
<div id="tables">
<table border="1" id="templateInfo">
    <caption>Шаблон</caption>
    <thead>
    <tr>
        <td>Имя</td>
        <td>Описание</td>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<table border="1" id="tests" class="ex_highlight" >
    <caption>Тесты</caption>
    <thead>
    <tr>
        <th>Тест</th>
        <th>Путь</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
    <table border="1" id="params" class="ex_highlight">
        <caption>Параметры</caption>
        <thead>
        <tr>
            <th>Параметр</th>
            <th>Значение</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>