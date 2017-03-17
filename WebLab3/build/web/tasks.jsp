<%@page import="org.apache.tomcat.dbcp.dbcp2.BasicDataSource"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.*"%>

<%@page import="pac.TaskList"%>
<%@page import="pac.Task"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Список задач</title>

        <link rel="stylesheet" href="style.css">
        <script>

//            var sleepSeconds = 60;
//            window.onload = "alert();";
//            var num = setInterval("show()", (sleepSeconds * 1000));
//            function show() {
//
//                var d = new Date();
//                var day = d.getDate();
//    //                var month = d.getMonth();
//    //                var year = d.getfFullYear();
//    //                var hour = d.getHours();
//                sendGet(day, 3, 1, 1, 1);
//            }
//
//            function sendGet(dy, mo, yr, hr, mt) {
//                var x = new XMLHttpRequest();
//                var line = "/WebLab3/alert?id=2";
//                x.open("GET", line, true);
//                x.onload = function(){
//                    alert(x.responseText);
//                }
//                x.send(null);
//            }

        </script>

    </head>
    <body onload="show();">
        <div class="cont">
            <a class="btn" href="index.jsp?exit=true">Выход</a>
            <h2>Список задач пользователя: <b id="user-name"><%out.println(session.getAttribute("name"));%></b></h2>
            <table>
                <tr>
                    <td onclick="show();">
                        Поиск задач:
                    </td>
                    <td>
                        <form action="tasks.jsp" method="get">
                            <table>
                                <tr>
                                    <td>
                                        <input type="text" name="findTaskName" placeholder="Введите название задачи"/>
                                    </td>
                                    <td>
                                        <input type="submit" value="Найти" style="cursor: pointer;">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>

            <%
                if (session.getAttribute("id") == "null") {
                    response.sendRedirect("http://localhost:8084/WebLab3/");
                } else {
                    TaskList taskList = new TaskList();
                    taskList.setUserId(Integer.parseInt(session.getAttribute("id").toString()));

                    String message = "";
                    int actTaskNum = -1;
                    String buf = "";
                    String url = request.getQueryString();
                    if (url != null) {
                        if (url.contains("=")) {
                            String perArr[] = url.split("=");
                            if (perArr.length == 2) {
                                if (perArr[0].contains("delete")) {
                                    message = taskList.delTask(Integer.parseInt(perArr[1]));
                                    response.sendRedirect("http://localhost:8084/WebLab3/tasks.jsp");
                                }

                                if (perArr[0].contains("taskid")) {
                                    session.setAttribute("taskId", "null");
                                }

                                if (perArr[0].contains("findTaskName")) {
                                    actTaskNum = taskList.findTask(new String(perArr[1].replace("+", " ")));
                                }
                            }
                        }
                    }

                    out.println("<div class=\"message\">" + message + "</div>");
                    out.println("<table class=\"taskTable\">");
                    out.println("<th>ID</th>");
                    out.println("<th>Задача</th>");
                    out.println("<th>Описание</th>");
                    out.println("<th>Дата/Время</th>");
                    out.println("<th>Контакты</th>");
                    out.println("<th>Изменить</th>");
                    out.println("<th>Удалить</th>");
                    for (int i = 0; i < taskList.getTaskList().size(); i++) {
                        if (session.getAttribute("id").toString().contains(taskList.getTaskList().get(i).getUserId())) {
                            if (actTaskNum == Integer.parseInt(taskList.getTaskList().get(i).getId())) {
                                buf = "active";
                            }
                            out.println("<tr class=\"" + buf + "\">");
                            out.println("<td>");
                            out.println(taskList.getTaskList().get(i).getId());
                            out.println("</td>");
                            out.println("<td>");
                            out.println(taskList.getTaskList().get(i).getName());
                            out.println("</td>");
                            out.println("<td>");
                            out.println(taskList.getTaskList().get(i).getDescription());
                            out.println("</td>");
                            out.println("<td>");
                            out.println(taskList.getTaskList().get(i).getTime());
                            out.println("</td>");
                            out.println("<td>");
                            out.println(taskList.getTaskList().get(i).getContacts());
                            out.println("</td>");

                            out.println("<td>");
                            out.println("<a href=\"change.jsp?id=" + taskList.getTaskList().get(i).getId() + "\">Изменить</a>");
                            out.println("</td>");

                            out.println("<td>");
                            out.println("<a href=\"?delete=" + taskList.getTaskList().get(i).getId() + "\">Удалить</a>");
                            out.println("</td>");

                            out.println("</tr>");
                            buf = "";
                        }
                    }
                    out.println("</table>");
                    out.println("<a class=\"btn\" href=\"add.jsp\">Добавить задачу</a>");
                }
            %>

        </div>
    </body>
</html>
