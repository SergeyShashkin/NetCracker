<%-- 
    Document   : registration
    Created on : 09.03.2017, 19:54:02
    Author     : 1
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <%
            String answer = "";
            String name = "";
            String login = "";
            String password = "";

            if (request.getParameter("username") != null) {
                name = request.getParameter("username");
            }

            if (request.getParameter("login") != null) {
                login = request.getParameter("login");
            }

            if (request.getParameter("password") != null) {
                password = request.getParameter("password");
            }

            if (name != "" && login != "" && password != "") {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
                    PreparedStatement statement = connection.prepareStatement("select * from Client");
                    ResultSet result = statement.executeQuery();
                    ResultSetMetaData metadata = result.getMetaData();
                    int numofcol = metadata.getColumnCount();
                    while (result.next()) {
                        if (result.getObject(3).toString().contains(login) && result.getObject(4).toString().contains(password)) {
                            HttpSession s = request.getSession(true);
                            s.setAttribute("id", new String(result.getObject(1).toString()));
                            s.setAttribute("name", new String(result.getObject(2).toString()));
                            s.setAttribute("login", new String(result.getObject(3).toString()));
                            s.setAttribute("password", new String(result.getObject(4).toString()));
                            answer = "";
                            break;
                        }
                    }
                } catch (Exception e) {
                    answer = "Регистрация не прошла! Попробуйте снова!";
                }
            }
        %>
        
        
    </body>
</html>
