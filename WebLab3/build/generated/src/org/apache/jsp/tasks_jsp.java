package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.Show;
import java.TaskList;
import java.Task;

public final class tasks_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Список задач</title>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("\n");
      out.write("            .message{\n");
      out.write("                color: red;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .active{\n");
      out.write("                color: white;\n");
      out.write("                background: blue;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .cont{\n");
      out.write("                width: 450px;\n");
      out.write("                margin-left: auto;\n");
      out.write("                margin-right: auto;\n");
      out.write("                text-align: center;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"cont\">\n");
      out.write("            <h2>Список задач пользователя:<b id=\"user-name\">");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString(org.apache.jasper.runtime.JspRuntimeLibrary.handleGetProperty(_jspx_page_context.findAttribute("sessionActual"), "name")));
      out.write("</b></h2>\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>\n");
      out.write("                        Поиск задач:\n");
      out.write("                    </td>\n");
      out.write("                    <td>\n");
      out.write("                        <form action=\"tasks.jsp\" method=\"get\">\n");
      out.write("                            <table>\n");
      out.write("                                <tr>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"text\" name=\"findTaskName\" placeholder=\"Введите название задачи\"/>\n");
      out.write("                                    </td>\n");
      out.write("                                    <td>\n");
      out.write("                                        <input type=\"submit\" value=\"Найти\">\n");
      out.write("                                    </td>\n");
      out.write("                                </tr>\n");
      out.write("                            </table>\n");
      out.write("                        </form>\n");
      out.write("                    </td>\n");
      out.write("                    <td>Сортировка задач по:</td>\n");
      out.write("                    <td>\n");
      out.write("                        <form action=\"tasks.jsp\" method=\"get\">\n");
      out.write("                            <select size=\"1\" name=\"sorting\">\n");
      out.write("                                <option value=\"name\" selected>имени</option>\n");
      out.write("                                <option value=\"date\">дате</option>\n");
      out.write("                            </select>\n");
      out.write("                            <input type=\"submit\" value=\"Сортировать\">\n");
      out.write("                        </form>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("\n");
      out.write("            ");
      out.write("\n");
      out.write("            <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\">\n");
      out.write("                <thead>\n");
      out.write("                <th>ID</th><th>Name</th><th>Description</th><th>Date</th><th>Index</th><th>Contacts</th>\n");
      out.write("                </thead>\n");
      out.write("                ");
Show show = new Show(); //создаём объект класса Show
                    try {
                        show.start(); //вызываем метод, который выполняет коннект к бд
                        while (true) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(show.getID());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(show.getName());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(show.getDescription());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(show.getDate());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(show.getIndex());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(show.getContacts());
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                        }

                    } catch (Exception e) {
                        out.println();
                    }
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
