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

    <link href="views/media/css/jquery.dataTables.css" rel="stylesheet"/>
    <link href="views/media/mycss/hightlighting.css" rel="stylesheet"/>
    <link href="views/media/mycss/bootstrap.css" rel="stylesheet"/>
    <link href="/views" rel="stylesheet"/>

    <style>
        div#1 {
            width: 70%;
            max-width: 70%;
            align-content: center;
        }
    </style>
    <script>

        var $table;

        $(document).ready(function () {
            $table = $("#example").dataTable({
                "bProcessing": false,
                "bServerSide": true,
                "sAjaxSource": "rest/services/launchesServ",
                "bPaginate": true,
                "sPaginationType": "full_numbers",
                // "sDom": '<"H"Tfr>t<"F"ip>',
                "bSort": true,
//                "bSortClasses": true,
                "bStateSave": false,
                "bLengthChange": true,
                "iDisplayLength": -1,
                "bFilter": false,
                "oLanguage": {
                    "oPaginate": {
                        "sFirst": "Начало",
                        "sLast": "Конец",
                        "sNext": "Следующая страница",
                        "sPrevious": "Предыдущая страница",
                        "sInfo": "Im Info! :)"
                    },

                    "sLengthMenu": '<select>' +
                            '<option value="2">2</option>' +
                            '<option value="10">10</option>' +
                            '<option value="20">20</option>' +
                            '<option value="30">30</option>' +
                            '<option value="40">40</option>' +
                            '<option value="-1">Все</option>' +
                            '</select>'

                },
                "fnInfoCallback": function (oSettings, iStart, iEnd, iMax, iTotal, sPre) {
                    return "";
                },
                <!--тут навешивает событие клик на строку-->
                "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $(nRow).prop("id", aData["launch_id"]);
                    $(nRow).click(function () {
                        window.open("LaunchInfo?id=" + aData["launch_id"]);
                    })
                },

                "sServerMethod": "GET",

                "aoColumns": [
                    {
                        "mData": "launch_name",
                        "sTitle": "Имя запуска",
                        "bSortable": true
                    },
                    {
                        "mData": "start",
                        "sTitle": "Время запуска",
                        "bSortable": true
                    },
                    {
                        "mData": "status",
                        "sTitle": "Статус запуска",
                        "bSortable": true
                    },
                    {
                        "mData": "params",
                        "sTitle": "Параметры запуска",
                        "bSortable": false,
                        "mRender": function (parameters, type, full) {
                            var str = "";
                            $.each(parameters, function (key, value) {
                                if (value) {
                                    str += key + ": " + value + "<br>";
                                }
                            })

                            return  str;
                        }
                    }


                ]
            });


            $("#example_wrapper").after(
                   '<br>' + <%@ include file="NavigationBar.jsp" %>
            );
        });


    </script>
    <title></title>
</head>
<body>
<div id="1" class="ex_highlight">
    <table border="1" id="example">
        <caption>Запуски тестов</caption>
        <thead>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>