package org.example;

import org.example.models.Attendance;
import org.example.models.Classes;
import org.example.models.User;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection connection = DbHandler.connect();

        String username;
        String password;
        String classname;
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter username: ");
        username = scanner.nextLine();
        System.out.println("Enter password:");
        password = scanner.nextLine();

        User user1 = new User(username, password);
        DbHandler.addUsers(user1, connection);

        List<User> userList = DbHandler.getUser(connection, "Sayara");
        for (User user : userList) {
            System.out.println(user.getUsername() + " " + user.getPassword());
        }

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter classname:");
        classname = scanner2.nextLine();

        Classes classes00 = new Classes(classname);
        DbHandler.addClass(classes00, connection);

        List<Classes> classesList = DbHandler.getClasses(connection, "Khumbila");
        for (Classes classes : classesList) {
            System.out.println(classes.getClassname());
        }


        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter classId: ");
        int classId = scanner1.nextInt();
        System.out.println("Enter userId:");
        int userId = scanner1.nextInt();
        scanner1.close();


        try {
            Attendance attendance = new Attendance(classId, userId);
            DbHandler.addAttendance(attendance, connection);
            System.out.println("Attendance added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add attendance: " + e.getMessage());
        }
    }
}