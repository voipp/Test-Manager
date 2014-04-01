<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setContentType("text/html; charset=utf-8");
%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script src="views/media/js/jquery.js" type="text/javascript"></script>
<script src="/views" type="text/javascript"></script>
<script src="views/media/js/jquery.dataTables.min.js"
        type="text/javascript"></script>
<script src="views/media/js/jquery.timers.min.js"
        type="text/javascript"></script>
<script src="views/media/myjs/jquery.dataTables.reloadAjax.min.js"
        type="text/javascript"></script>

<link href="views/media/css/jquery.dataTables.css" rel="stylesheet"/>
<link href="views/media/mycss/hightlighting.css" rel="stylesheet"/>
<link href="views/media/mycss/bootstrap.css" rel="stylesheet"/>
<link href="views/media/mycss/mycss.css" rel="stylesheet"/>
<style>
    div#1 {
        width: 70%;
        max-width: 70%;
        align-content: center;
    }

</style>
<script>

    var $table;
    function resizeIframe(obj) {
//            obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
//            obj.style.width = obj.contentWindow.document.body.scrollWidth + 'px';
        return 1;
    }

    var sendStartTemplate = function (templateId) {
        $.ajax({
            url: "rest/services/LaunchServ?templateId=" + templateId,
            type: "POST",
            async: false,
            success: function (arg) {
            }
        });
    }


    /*
     передадим в JSP тэг для вставки на стороне сервера потому, что
     изменять их на стороне клиента невозможно, из-за защиты от кросс-сайт-скриптинга
     параметры запроса : style={ ololo:gigurda };

     style - имя тэга для вставки
     { ololo:gigurda }; - вставляемое значение

     */
    var fnFormatDetails = function (oTable, nTr) {
        var oData = oTable.fnGetData(nTr);
        var templateId = oData.tempId;
        var sOut = '<div class="innerDetails" style="height: 100%;width: 100%;">' +
                '<iframe seamless="true" style="height: 100%;border: none;width: 100%" onload="resizeIframe(this)"  src="TemplateInfo?style= form {display:none;} ' +
                '&id=' + templateId.toString() + '">' +
                'Ваш браузер не поддерживает плавающие фреймы!' +
                '</iframe>' +
                '</div>';

        return sOut;
    }


    $(document).ready(function () {

        <%--массив открытых строк--%>
        var anOpen = [];
        var sImageUrl = "views/media/images/";

        $table = $("#templates").dataTable({
            "bProcessing": false,
            "bServerSide": true,
            "sAjaxSource": "rest/services/TemplatesServ",
            "bPaginate": true,
            "sPaginationType": "full_numbers",
            "bSort": true,
            "sServerMethod": "GET",
            "bStateSave": false,
            "bLengthChange": true,
            "iDisplayLength": -1,
            "bFilter": false,
            <%-- ajax datasource --%>
            "sAjaxDataProp": "data",
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
            <!--тут навешивает событие клик на строку и дополнительный чекбокс для запуска того или иного шаблона-->
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                $(nRow).prop("id", aData["launch_id"]);
                $('td:eq(3)', nRow).html('<input value="Запустить" type="button" id="' +
                        aData["tempId"] + '" ' +
                        <% if (!request.isUserInRole("mpgu-admin")) {%>
                        ' disabled="true" ' +
                        <% } %>
                        'onclick=sendStartTemplate(' + aData["tempId"] + ')' +
                        ' >');

            },

            "aoColumns": [
                {
                    "mDataProp": null,
                    "sClass": "control center",
                    "bSortable": false,
                    "sDefaultContent": '<img src="' + sImageUrl + 'details_open.png' + '">'
                },
                {
                    "mDataProp": "template_name",
                    "sTitle": "Имя шаблона",
                    "bSortable": true
                },
                {
                    "mDataProp": "description",
                    "sTitle": "Описание",
                    "bSortable": true
                },
                {
                    "mDataProp": null,
                    "sTitle": "Запуск",
                    "bSortable": true,
                    "sDefaultContent": "Loading..."
                }


            ],
            "fnInitComplete": function () {

                var $currTable = $("#templates").dataTable();

                $('#templates tbody tr').each(function (index, tr) {
                    $ololo = $currTable.fnGetData(this);
                    var templateId = $currTable.fnGetData(this).tempId;
                    $("td:eq(1)",this).click(function (event) {
                            window.location = "TemplateInfo?id=" + templateId;
                    })
                    $("td:eq(2)",this).click(function (event) {
                        window.location = "TemplateInfo?id=" + templateId;
                    })

                })

                $('#templates td.control').unbind('click');

                $('#templates td.control').live('click', function (event) {

                    event.stopPropagation();

                    var nTr = this.parentNode;
                    var i = $.inArray(nTr, anOpen);

                    if (i === -1) {
                        $('img', this).attr('src', sImageUrl + "details_close.png");
                        var nDetailsRow = $currTable.fnOpen(nTr, fnFormatDetails($currTable, nTr), 'details');
                        $(".details").css('height', '100%');
                        $('div.innerDetails', nDetailsRow).slideDown(1000);

                        anOpen.push(nTr);
                    }
                    else {
                        $('img', this).attr('src', sImageUrl + "details_open.png");
                        $('div.innerDetails', $(nTr).next()[0]).slideUp(function () {
                            $currTable.fnClose(nTr);
                            anOpen.splice(i, 1);
                        });
                    }
                });


            }
        });


        $table.wrap('<div class="ex_highlight"></div>');

        $("div#1 table#example caption").css("font", "200% serif");

        $("#templates_wrapper").after('<br>' + <%@ include file="NavigationBar.jsp" %>);
    });


</script>
<title></title>
</head>
<body>
<div id="1">
    <table border="1" id="templates" class="ex_highlight">
        <caption>Шаблоны</caption>
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