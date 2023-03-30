package org.example;

import org.example.models.Attendance;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    // TABLE NAMES
    public static final String TABLE_USER = "user";
    public static final String TABLE_CLASS = "class";
    public static final String TABLE_ATTENDANCE = "attendance";

    // COLUMN NAMES
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_CLASSNAME = "classname";
    public static final String COLUMN_CLASSID = "classId";
    public static final String COLUMN_USERID = "userId";


    public static Connection connect() {
        Connection connection = null;
        String url = "jdbc:sqlite:src/main/resources/database/Attendance_Management_System.db";

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Database Connection Successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static List<User> getUser(Connection connection, String name) {
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " LIKE '%" + name + "%'";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String username = resultSet.getString(COLUMN_USERNAME);
                String password = resultSet.getString(COLUMN_PASSWORD);

                User user = new User(username,password);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static void addUsers (User modelUser, Connection connection){
        String sql = "INSERT INTO " + TABLE_USER + "(" + COLUMN_USERNAME + "," + COLUMN_PASSWORD + ") " + "VALUES(?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modelUser.getUsername());
            pstmt.setString(2, modelUser.getPassword());
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAttendance(Attendance modelAttendance, Connection connection){
        String sql = "INSERT INTO " + TABLE_ATTENDANCE + "(" + COLUMN_CLASSID + "," + COLUMN_USERID +") " + "VALUES(?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,modelAttendance.getClassId());
            pstmt.setInt(2,modelAttendance.getUserId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Attendance> getAttendance(Connection connection, Integer id) {
        String query = "SELECT * FROM " + TABLE_ATTENDANCE + " WHERE " + COLUMN_ID + " = " + id;
        List<Attendance> attendances = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int ids = resultSet.getInt(COLUMN_ID);
                int classID = resultSet.getInt(COLUMN_CLASSID);
                int userId = resultSet.getInt(COLUMN_USERID);

                Attendance attendance = new Attendance(classID,userId);
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return attendances;
    }


    public static void addClass(org.example.models.Classes modelClass, Connection connection) {

        String sql = "INSERT INTO " + TABLE_CLASS + "(" + COLUMN_CLASSNAME + ") " +
                "VALUES(?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modelClass.getClassname());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<org.example.models.Classes> getClasses(Connection connection, String name) {
        String query = "SELECT * FROM " + TABLE_CLASS + " WHERE " + COLUMN_CLASSNAME + " = " + name;
        List<org.example.models.Classes> classesArrayList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String classname = resultSet.getString(COLUMN_CLASSNAME);

                org.example.models.Classes classes = new org.example.models.Classes(classname);
                classesArrayList.add(classes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classesArrayList;
    }

}