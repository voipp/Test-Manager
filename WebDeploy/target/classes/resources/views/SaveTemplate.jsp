<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--response.setContentType("text/html; charset=utf-8");--%>
<%--%>--%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<script src="views/media/js/jquery.js" type="text/javascript"></script>
<script src="views/media/js/jquery.dataTables.min.js"
        type="text/javascript"></script>
<script src="views/media/js/jquery.timers.min.js" type="text/javascript"></script>
<script src="views/media/myjs/jquery.dataTables.reloadAjax.min.js"
        type="text/javascript"></script>
<script src="views/media/js/jquery-ui.js" type="text/javascript"></script>
<script src="views/media/js/jquery.bgiframe.min.js"></script>


<link href="views/media/css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
<link href="views/media/mycss/hightlighting.css" rel="stylesheet" type="text/css"/>
<link href="views/media/mycss/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="views/media/css/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<link href="views/media/mycss/mycss.css" rel="stylesheet" type="text/css"/>
<title></title>
<style>
    #parameters_wrapper {
        width: 40%;

    }
    table.dataTable td{
        text-align: center;
    }
</style>
<script>
$(document).ready(function () {


    var $ParamsTable = {};

    var $TestsTable = $("#tests").dataTable({
        "bProcessing": true,
        "bServerSide": true,
        "sAjaxSource": "rest/services/TestsServ",
        "bPaginate": false,
        "sPaginationType": "full_numbers",
        // "sDom": '<"H"Tfr>t<"F"ip>',
        "bSort": true,
        "sServerMethod": "GET",
        "bStateSave": false,
        "bLengthChange": true,
//                "iDisplayLength": 2,
        "bFilter": false,
        "oLanguage": {
            "oPaginate": {
                "sFirst": "Firs",
                "sLast": "Last",
                "sNext": "Next",
                "sPrevious": "Prev",
                "sInfo": "Im Info! :)"
            },

            "sLengthMenu": '<select>' +
                    '<option value="2">2</option>' +
                    '<option value="10">10</option>' +
                    '<option value="20">20</option>' +
                    '<option value="30">30</option>' +
                    '<option value="40">40</option>' +
                    '<option value="-1">All</option>' +
                    '</select>'

        },
        "fnServerData": function (sSource, aoData, fnCallback) {
            $.getJSON(sSource, aoData, function (json) {

                $ParamsTable.paramsDesc = json.params_desc;

                <%--динамически создаем таблицу из params_desc параметра--%>

                $.each($ParamsTable.paramsDesc, function (i, val) {
                    $('<tr>' + '<td>' + val.description.toString() + '</td>' +
                            '<td><input class="input-mini" type="checkbox" name="' +
                            val.paramName.toString() + '"></td>' +
                            '</tr>').appendTo("#parameters tbody");
                });

                $ParamsTable = $('#parameters').dataTable({
                    "bPaginate": false,
                    "bInfo": false,
                    "bSearchable": false,
                    "bSort": false,
                    "bFilter": false,
                    "bScrollCollapse": false,
                    "oLanguage": {
                        "sProcessing": "meow"
                    }

                });


                <%--биндинг пришедших данных к полям апраметров(создание полей on-demand)--%>
//                $(".tooltipped").each(function () {
//
//                    var content;
//
//                    if(json.params_desc[this.getAttribute("name")]==null){
//                        content= "";
//                    }else{
//                        content= json.params_desc[this.getAttribute("name")].toString();
//                    }
//
//
//
//                    $(this).tooltip({
//                        content: content,
//                        position: {
//                            my: "left top+15",
//                            at: "left top-50",
//                            collision: "flipfit"
//                        }
//                    });
//                });

//pass the data to the standard callback and draw the table
                fnCallback(json);
            }, "json");
        },
        "fnInfoCallback": function (oSettings, iStart, iEnd, iMax, iTotal, sPre) {
            return "";
        },

        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            $(nRow).prop("id", aData["id"]);
            $('td:eq(2)', nRow).html('<input type="checkbox" id=\"' + aData["id"] + '' +
                    '" >');
        },
        "fnServerParams": function (aoData) {
            aoData.push({ "name": "bOnlyInstance", value: true });
            aoData.push({ "name": "bOnlyTests", value: true });

        },

        "aoColumns": [
            {
                "mData": "name",
                "sTitle": "Имя теста",
                "bSortable": false

            },
            {
                "mData": "path",
                "mRender": function (url, type, full) {
                    return  '<a href="' + url + '">' + url + '</a>';
                },
                "sTitle": "Путь до теста",
                "bSortable": false
            },
            {
                "mData": "start_test",
                "bSortable": false,
                "sDefaultContent": ""
            }

        ]
    });


//    $("#tests").wrap('<div class="ex_highlight"></div>');

    $("#tests").after('<div style="clear: right; padding: 10px" ><input type="button" id="addTemplateButton" value="Сохранить" /></div>').
            after('<div style="float: right; padding: 10px"><input type="checkbox" id="swapCheckbox" value="Отметить все"> Отменить/Выбрать все тесты</div>');


    $('#swapCheckbox').before("<br>").change(function () {
        $("#tests input[type='checkbox']").each(function (i) {
            this.checked = !this.checked;
        });
    });


    $("#parameters").before(<%@ include file="NavigationBar.jsp" %>);

    $("#parameters").before('<br>');

    //создадим прогресс бар и сделаме его невидимым


    $("#addTemplateButton").click(function () {

        //устанавливаем модульное окно стилями


        $("#overlay_progress_bar").progressbar({
            value: false
        });

        $("#overlay_progress_bar").css({
            "background-color": "black",
            "position": "fixed",
            "top": 0,
            "right": 0,
            "bottom": 0,
            "left": 0,
            "opacity": 0.2, /* also -moz-opacity, etc. */
            "z-index": 10,
            "visibility": "visible",
            "height": "auto"
        });


        var pb = $("#overlay_progress_bar");

        var jsonAData = [];

        // собираем айди тестов, которые надо запустить
        $("#tests tbody tr").each(function (i) {

            if ($("input[type='checkbox']", this).get(0).checked == true) {
                jsonAData.push(this.id);
            }
        });

        //а теперь собираем мета-ифнормацию по запуску тестов

        $.ajaxSetup({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Cache-Control', 'no-cache');
                xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
            }
        });

        var echoNumber = Math.floor(Math.random() * 1000);

        <%--создаем строку данных для отправки и приобразуем ее в JSON-объект--%>


        var jsonOLaunchData = {};


        $("#parameters").find("tbody tr td input").each(function (index, input) {
            if (input.type == "text") {

                jsonOLaunchData[$(input).attr("name")] = input.value;
            }
            else if (input.type == "checkbox") {

                jsonOLaunchData[$(input).attr("name")] = input.checked;

            }
        })


        var dataToSend = {
            aoData: jsonAData,
            oLaunchData: jsonOLaunchData,
            sEcho: echoNumber
        };

        $.ajax({
            url: "rest/services/TemplatesServ",
            type: "POST",
            async: true,
            data: JSON.stringify(dataToSend),
            success: function (data) {
                $('#overlay_progress_bar').progressbar("destroy");
                $('#overlay_progress_bar').css("visibility", "hidden");
                window.open("TemplateInfo?id=" + data.data.toString(), 'ololo');
//            window.location = "LaunchInfo?id=" + data.nLaunchId;
            }
        });


    });

})
;

</script>
</head>
<body style="width: 100%">

<table border="1" id="parameters" style="width: 100%; height: 100%;" class="ex_highlight">
    <thead>
    <tr>
        <th>Параметр</th>
        <th>Значение</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Имя
            Шаблона
        </td>
        <td><input class="input-xxlarge" type="text" name="template_name"></td>
    </tr>
    <tr>
        <td>Текст запроса</td>
        <td><input class="input-xxlarge" type="text" name="description"></td>
    </tr>
    </tbody>
</table>

<table border="1" id="tests" class="ex_highlight">
    <caption>Тесты</caption>
    <thead>
    <tr>
        <th>test_namee</th>
        <th>status</th>
        <th>Сохранить шаблон</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>test_name1</td>
        <td>status1</td>
        <td class="inputs">params1</td>
    </tr>
    </tbody>
</table>

<div id="overlay_progress_bar" style="visibility: hidden;"></div>


</body>
</html>