/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac;

import com.mysql.jdbc.Statement;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1
 */
public class TaskList {

    Connection connection;
    private ArrayList<Task> list = new ArrayList<>();
    StringBuilder tasksId = new StringBuilder();
    private int userId;

    public TaskList() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
        PreparedStatement statement = connection.prepareStatement("select * from Task");
        ResultSet result = statement.executeQuery();
        boolean index = false;
        while (result.next()) {
            Task task = new Task();
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
            list.add(task);
        }
//        connection.close();
    }

//    public void startAlert(TaskList list) {
//        for (;;) {
//            Date d = new Date();
//            TimeZone.setDefault(TimeZone.getTimeZone("Europe/Samara"));
////                    SimpleDateFormat date = new SimpleDateFormat("d.M.yyyy");
//            SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
//            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
//            SimpleDateFormat secF = new SimpleDateFormat("ss");
//            int sec = 60 - Integer.parseInt(secF.format(d));
//
//            for (int i = 0; i < list.getTaskList().size(); i++) {
//                String[] timeLine = list.findTask(i).getTime().split(" ");
//                String[] ymdArr = timeLine[0].split("-");
//                String[] hmArr = timeLine[1].split(":");
//
//                int day = Integer.parseInt(ymdArr[2]);
//                int mont = Integer.parseInt(ymdArr[1]);
//                int year = Integer.parseInt(ymdArr[0]);
//                int hour = Integer.parseInt(hmArr[0]);
//                int min = Integer.parseInt(hmArr[1]);
//
//                Date d2 = new Date();
//                d2.setDate(day);
//                d2.setMonth(mont - 1);
//                d2.setYear(year - 1900);
//                d2.setHours(hour);
//                d2.setMinutes(min);
//
//                if (d.compareTo(d2) > 0 && list.findTask(i).getIndex() == true) {
//                    if (!tasksId.toString().contains(list.findTask(i).getName())) {
//                        tasksId.append(list.findTask(i).getName() + ",");
//                    }
//                }
//
//                if (date.format(d).contains(timeLine[0]) && time.format(d).contains(timeLine[1]) && list.findTask(i).getIndex() == true) {
//
//                }
//            }
//            try {
//                Thread.sleep(sec * 1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
////                th.start();
//    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

    public void setTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    //поиск задачи
    public Task findTask(int number) {
        return (Task) list.get(number);
    }

    //поиск задачи по имени
    public int findTask(String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(name)) {
                return Integer.parseInt(list.get(i).getId());
            }
        }
        return 0;
    }

    //изменение задачи
    public boolean editTask(int id, String col, String value) throws SQLException {
        String updateSQL = "UPDATE Task SET " + col + " = '" + value + "' WHERE t_id = " + id;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
            Statement st = (Statement) connection.createStatement();
            st.executeUpdate(updateSQL);
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //добавление задачи
    public String addTask(String name, String desc, String time, String cont) {
        String insertSQL = "INSERT INTO Task VALUES(NULL, 1, '" + name + "', '" + desc + "', '" + time + "', '" + cont + "', " + userId + ")";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
            Statement st = (Statement) connection.createStatement();
            st.executeUpdate(insertSQL);
            connection.close();
            return "Добавление задачи в БД прошло успешно!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    //удаление задачи
    public String delTask(int id) {
        String deleteSQL = "DELETE FROM Task WHERE t_id=" + id;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
            Statement st = (Statement) connection.createStatement();
            st.executeUpdate(deleteSQL);
            connection.close();
            return "Удаление задачи из БД прошло успешно!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    //сортировать задачи по дате
    public void sortDate() {
        Collections.sort(list, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t1.getTime().compareTo(t2.getTime());
            }
        });
    }

    //сортировать задачи по названию
    public void sortName() {
        Collections.sort(list, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });
    }

    //ввод индекса задачи
    public String setIndex(int number, String index) {
        String updateSQL = "UPDATE Task SET t_index = '" + index;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_manager", "root", "qwerty");
            Statement st = (Statement) connection.createStatement();
            st.executeUpdate(updateSQL);
            connection.close();
            return "Изменение задачи в БД прошло успешно!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return this.userId;
    }
}
