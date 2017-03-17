<%-- 
    Document   : change
    Created on : 16.03.2017, 19:48:22
    Author     : 1
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="pac.Task"%>
<%@page import="pac.TaskList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    TaskList list = new TaskList();
    Task task = new Task();
//    String nM = "";
//    String dM = "";
//    String tM = "";
//    String cM = "";
    String id = "null";
    String url = request.getQueryString();
    if (url != null) {
        if (url.contains("=")) {
            String perArr[] = url.split("=");
            if (perArr[0].contains("id") && perArr[1] != null) {
                session.setAttribute("taskId", perArr[1]);
            }
        }
    }

    id = session.getAttribute("taskId").toString();
    out.println("id = "+id);

    if (id != "null") {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
        PreparedStatement statement = connection.prepareStatement("select * from Task");
        ResultSet result = statement.executeQuery();
        boolean index = false;
        while (result.next()) {
            if (result.getString(1).toString().contains(id)) {
                task.setId(result.getString(1));
                task.setName(result.getString(3));
                task.setDescription(result.getString(4));
                task.setTime(result.getString(5));
                task.setContacts(result.getString(6));
                task.setUserId(result.getString(7));
                if (result.getString(2) == "1") {
                    index = true;
                }
                if (result.getString(2) == "0") {
                    index = false;
                }
                task.setIndex(index);
            }
        }
        connection.close();
        
        String urlPost = request.getQueryString();
        String value = "";

        if (url != null) {
            if (urlPost.contains("&")) {
                String perArr[] = urlPost.split("&");
                for (int i = 0; i < perArr.length; i++) {
                    if (perArr[i].contains("=")) {
                        String arr[] = perArr[i].split("=");
                        if (arr[1] != null) {
                            value = arr[1].replace("+", " ");
                            out.println("value = " + value);
                            switch (arr[0]) {
                                case "name":
                                    list.editTask(Integer.parseInt(id), "t_name", value);
//                                    if (list.editTask(Integer.parseInt(id), "t_name", value)) {
//                                        nM = "Изменение имени прошло успешно!";
//                                    } else {
//                                        nM = "Изменение имени не удалось!";
//                                    }
                                    break;
                                case "desc":
                                        list.editTask(Integer.parseInt(id), "t_description", value);
//                                    if (list.editTask(Integer.parseInt(id), "t_description", value)) {
//                                        dM = "Изменение описания прошло успешно!";
//                                    } else {
//                                        dM = "Изменение описания не удалось!";
//                                    }
                                    break;
                                case "time":
                                    String buf[] = value.split(" ");
                                    out.println(buf[0]);
                                    out.println(buf[1]);
                                    String buf2 = buf[1].replace("%3A", ":");
                                    String time = buf[0] + " " + buf2;
                                    list.editTask(Integer.parseInt(id), "t_date", time);
//                                    if (list.editTask(Integer.parseInt(id), "t_date", time)) {
//                                        tM = "Изменение времени прошло успешно!";
//                                    } else {
//                                        tM = "Изменение времени не удалось!";
//                                    }
                                    break;
                                case "cont":
                                    value = value.replace("%40", "@");
                                    list.editTask(Integer.parseInt(id), "t_contacts", value);
//                                    if (list.editTask(Integer.parseInt(id), "t_contacts", value)) {
//                                        cM = "Изменение контактов прошло успешно!";
//                                    } else {
//                                        cM = "Изменение контактов не удалось!";
//                                    }
                                    break;
                            }
                        }
                    }
                }
                response.sendRedirect("http://localhost:8084/WebLab3/change.jsp?id="+id);
            }
        }
    } else {
        response.sendRedirect("http://localhost:8084/WebLab3/tasks.jsp");
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменение задачи</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="cont">
            <a class="btn" href="tasks.jsp?taskid=<%=session.getAttribute("taskId")%>">Назад</a>
            <h3>Задача: <%=task.getName()%></h3>
            <!--<div class="message">-->
                <%--out.println("<p>" + nM + "</p>");
                    out.println("<p>" + dM + "</p>");
                    out.println("<p>" + tM + "</p>");
                    out.println("<p>" + cM + "</p>");--%><!--</div>-->
            <form action="change.jsp" method="get">
                <table>
                    <tr>
                        <td>Имя:</td>
                        <td><%out.println("<input type=\"text\" name=\"name\" value=\"" + task.getName());%>"/></td>
                    </tr>
                    <tr>
                        <td>Описание:</td>
                        <td><%out.println("<textarea name=\"desc\" >" + task.getDescription() + "</textarea>");%></td>
                    </tr>
                    <tr>
                        <td>Дата/Время:</td>
                        <td><%out.println("<input type=\"text\" name=\"time\" value=\"" + task.getTime());%>"/></td>
                    </tr>
                    <tr>
                        <td>Контакты:</td>
                        <td><%out.println("<textarea name=\"cont\" >" + task.getContacts() + "</textarea>");%></td>
                    </tr>
                </table>
                <input type="submit" value="Изменить" />
            </form>
        </div>
    </body>
</html>
