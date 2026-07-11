import java.sql.*;
import java.util.Scanner;

public class DBConnection {

    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASSWORD = "Yaswanth@123"; // Change this

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connection Established Successfully!");

            int choice;

            do {
                System.out.println("\n===== STUDENT MENU =====");
                System.out.println("1. Insert");
                System.out.println("2. Display");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();

                        String insert = "INSERT INTO student VALUES (?, ?, ?)";

                        PreparedStatement ps1 = con.prepareStatement(insert);
                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setInt(3, age);

                        ps1.executeUpdate();

                        System.out.println("Record Inserted Successfully.");
                        break;

                    case 2:

                        Statement st = con.createStatement();

                        ResultSet rs = st.executeQuery("SELECT * FROM student");

                        System.out.println("\nID\tNAME\tAGE");

                        while (rs.next()) {
                            System.out.println(
                                    rs.getInt("id") + "\t"
                                            + rs.getString("name") + "\t"
                                            + rs.getInt("age"));
                        }
                        break;

                    case 3:

                        System.out.print("Enter Student ID to Update: ");
                        id = sc.nextInt();

                        System.out.print("Enter New Name: ");
                        name = sc.next();

                        System.out.print("Enter New Age: ");
                        age = sc.nextInt();

                        String update = "UPDATE student SET name=?, age=? WHERE id=?";

                        PreparedStatement ps2 = con.prepareStatement(update);

                        ps2.setString(1, name);
                        ps2.setInt(2, age);
                        ps2.setInt(3, id);

                        int u = ps2.executeUpdate();

                        if (u > 0)
                            System.out.println("Record Updated Successfully.");
                        else
                            System.out.println("Record Not Found.");

                        break;

                    case 4:

                        System.out.print("Enter Student ID to Delete: ");
                        id = sc.nextInt();

                        String delete = "DELETE FROM student WHERE id=?";

                        PreparedStatement ps3 = con.prepareStatement(delete);

                        ps3.setInt(1, id);

                        int d = ps3.executeUpdate();

                        if (d > 0)
                            System.out.println("Record Deleted Successfully.");
                        else
                            System.out.println("Record Not Found.");

                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 5);

            con.close();
            sc.close();

            System.out.println("Connection Closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
