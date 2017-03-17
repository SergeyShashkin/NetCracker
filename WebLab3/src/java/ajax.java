
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pac.TaskList;

@WebServlet("/alert")

public class ajax extends HttpServlet {

    public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        PrintWriter out = rs.getWriter();
        StringBuilder tasksId = new StringBuilder();
        String iserId = rq.getParameter("id");
        

        TaskList taskList = null;
        try {
            taskList = new TaskList();
        } catch (SQLException ex) {
            Logger.getLogger(ajax.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ajax.class.getName()).log(Level.SEVERE, null, ex);
        }
        taskList.setUserId(Integer.parseInt(iserId));

        Date d = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Samara"));
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat secF = new SimpleDateFormat("ss");
        int sec = 60 - Integer.parseInt(secF.format(d));

        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            String[] timeLine = taskList.findTask(i).getTime().split(" ");
            String[] dmyArr = timeLine[0].split("-");
            String[] hmArr = timeLine[1].split(":");

            int day = Integer.parseInt(dmyArr[2]);
            int mont = Integer.parseInt(dmyArr[1]);
            int year = Integer.parseInt(dmyArr[0]);
            int hour = Integer.parseInt(hmArr[0]);
            int min = Integer.parseInt(hmArr[1]);

            Date d2 = new Date();
            d2.setDate(day);
            d2.setMonth(mont - 1);
            d2.setYear(year - 1900);
            d2.setHours(hour);
            d2.setMinutes(min);

            if (d.compareTo(d2) > 0 && taskList.findTask(i).getIndex() == true) {
                if (!tasksId.toString().contains(taskList.findTask(i).getName())) {
                    tasksId.append(taskList.findTask(i).getName() + ",");
                }
            }
            if (date.format(d).contains(timeLine[0]) && time.format(d).contains(timeLine[1]) && taskList.findTask(i).getIndex() == true) {
                out.println(tasksId);
            }
            if (tasksId.length() > 0) {
                out.println("Эти задачи просрочены: "+tasksId);
            }

//            out.println("day: " + day + " month: " + month + " year: " + year);
        }
    }
}