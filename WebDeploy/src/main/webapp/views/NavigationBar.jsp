<%@ page contentType="text/html;charset=UTF-8" language="java" %>
"<br><div>\
<form action='AllLaunches'\
      style='float:left;margin:10px'><input type='submit' value='Все запуски'></form>\
<form action='StartPage' style='float:left;margin:10px'><input type='submit' value='Начальная страница'></form>\
<% if (request.isUserInRole("mpgu-admin")) {%>\
<form action='SaveTemplate' style='float:left;margin:10px'><input type='submit' value='Сохранение шаблонов'></form>\
<% } %>\
<form action='AllTemplates' style='float:left;margin:10px'><input type='submit' value='Все шаблоны'></form></div>"