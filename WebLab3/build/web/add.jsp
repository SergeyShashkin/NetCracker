<%-- 
    Document   : add
    Created on : 16.03.2017, 15:48:09
    Author     : 1
--%>

<%@page import="pac.TaskList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String s = "";
    String message = "";
    String url = request.getQueryString();

    out.println(url);
    if (url != null) {
        if (request.getParameter("name") == null) {
            s = "name";
        }

        if (request.getParameter("time") == null) {
            s = "time";
        }

        switch (s) {
            case "name":
                message = "Заполните поле \"Имя\"";
                break;
            case "time":
                message = "Заполните поле \"Дата/Время\"";
                break;
        }
        if (message == "") {
            TaskList list = new TaskList();
            list.setUserId(Integer.parseInt(session.getAttribute("id").toString()));
            message = list.addTask(request.getParameter("name"), request.getParameter("description"), request.getParameter("time"), request.getParameter("contacts"));
        }
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление задачи</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="cont">
            <a class="btn" href="tasks.jsp">Назад</a>
            <h3>Заполните форму и нажмите на кнопку "Добавить"</h3>
            <div class="message"><%=message%></div>
            <form action="add.jsp" method="get">
                <table>
                    <tr>
                        <td>Имя:</td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td>Описание:</td>
                        <td><textarea name="description"></textarea></td>
                    </tr>
                    <tr>
                        <td>Дата/Время:</td>
                        <td><input type="text" name="time" placeholder="гггг-мм-дд чч:мм"/></td>
                    </tr>
                    <tr>
                        <td>Контакты:</td>
                        <td><textarea name="contacts"></textarea></td>
                    </tr>
                </table>
                <input type="submit" value="Добавить" />
            </form>
        </div>
    </body>
</html>
