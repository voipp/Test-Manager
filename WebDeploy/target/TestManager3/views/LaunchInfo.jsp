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
        table {
            max-width: 80%;
        }
    </style>

    <script>
        // ?launchId= $.url().param('id')

        var docHasFocus = true;

        document.onfocus = function(){
            docHasFocus = true;
        }

        document.onblur = function(){
            docHasFocus = false;
        }

        $(document).ready(function () {
            $infoTable = $("#tests").dataTable({
                "bProcessing": false,
                "bServerSide": true,
                "sAjaxSource": "rest/services/LaunchInfoServ?launchId=" + $.url().param('id'),
                "bPaginate": false,
                "bSort": true,
                "bStateSave": false,
                "bLengthChange": true,
                "bFilter": false,
                "bInfo": false,
                "sServerMethod": "GET",
                "aoColumns": [
                    {
                        "mData": "test_name",
                        "sTitle": "Имя теста",
                        "bSortable": false

                    },
                    {
                        "mData": "result",
                        "sTitle": "Результат",
                        "bSortable": true
                    }
                ],
                "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $(nRow).click(function () {
                        window.location.href = aData["log_path"];
                    })
                }
            });
            $infoTable.everyTime(5000, 'table_update', function () {
                if(docHasFocus==true){
                    $infoTable.fnReloadAjax(null, null, true);
                }
            });


            $infoTable.after(<%@ include file="NavigationBar.jsp" %>);
        });


    </script>
</head>
<body style="width: 100%">
<table border="1" id="tests" class="ex_highlight">
    <caption>Запущенные тесты</caption>
    <thead>
    <tr>
        <th>test_name</th>
        <th>result</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>test_name</td>
        <td>result</td>
    </tr>
    </tbody>
</table>
<br>


</body>
</html>