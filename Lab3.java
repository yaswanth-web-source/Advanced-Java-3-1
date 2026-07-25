import java.sql.*;

public class Lab3 {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "Yaswanth@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected.");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DATABASE()");
            if (rs.next()) {
                System.out.println("Connected Database: " + rs.getString(1));
            }
            CallableStatement insertStmt =
                    conn.prepareCall("{CALL insert_employee(?, ?, ?)}");

            insertStmt.setInt(1, 103);
            insertStmt.setString(2, "John");
            insertStmt.setDouble(3, 55000.00);

            insertStmt.execute();
            System.out.println("Record inserted successfully.");
            CallableStatement salaryStmt =
                    conn.prepareCall("{CALL get_salary_by_id(?, ?)}");

            salaryStmt.setInt(1, 103);
            salaryStmt.registerOutParameter(2, Types.DECIMAL);
            salaryStmt.execute();

            double salary = salaryStmt.getDouble(2);

            System.out.println("Salary = " + salary);
            rs.close();
            stmt.close();
            insertStmt.close();
            salaryStmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


OUTPUT:

Database connected.
Connected Database: testdb
Record inserted successfully.
Salary = 55000.0
