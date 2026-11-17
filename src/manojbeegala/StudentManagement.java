package manojbeegala;

import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbcexample";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n====== STUDENT MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
            case 1:
                addStudent();
                break;

            case 2:
                displayStudents();
                break;

            case 3:
                updateMarks();
                break;

            case 4:
                deleteStudent();
                break;

            case 5:
                System.out.println("Thank You!");
                System.exit(0);

            default:
                System.out.println("Invalid Choice");
            }
        }
    }

    // Add Student
    public static void addStudent() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            String query = "INSERT INTO student VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, marks);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Added Successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Display Students
    public static void displayStudents() {

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "SELECT * FROM student";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            System.out.println("\nID\tNAME\tMARKS");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("marks"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Marks
    public static void updateMarks() {

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Marks: ");
            double marks = sc.nextDouble();

            String query =
                    "UPDATE student SET marks=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setDouble(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Marks Updated");
            else
                System.out.println("Student Not Found");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Student
    public static void deleteStudent() {

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            String query =
                    "DELETE FROM student WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Deleted");
            else
                System.out.println("Student Not Found");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}