import java.sql.*;

public class Con {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "Yaswanth@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String createTable = "CREATE TABLE IF NOT EXISTS Student ("
                    + "ROLLNO INT PRIMARY KEY, "
                    + "Name VARCHAR(50), "
                    + "Address VARCHAR(100))";

            Statement st = con.createStatement();
            st.executeUpdate(createTable);
            System.out.println("Table created successfully.");
            // Delete all old records
            st.executeUpdate("DELETE FROM Student");
            st.executeUpdate("INSERT INTO Student VALUES(1,'Ravi','Hyderabad')");
            st.executeUpdate("INSERT INTO Student VALUES(2,'Kiran','Chennai')");
            st.executeUpdate("INSERT INTO Student VALUES(3,'Sita','Bangalore')");
            System.out.println("\nInitial records inserted.");
            System.out.println("\nInitial Records:");
            displayRecords(con);
            String insertSQL = "INSERT INTO Student (ROLLNO, Name, Address) VALUES (?, ?, ?)";
            PreparedStatement insertSt = con.prepareStatement(insertSQL);
            insertSt.setInt(1, 4);
            insertSt.setString(2, "Meera");
            insertSt.setString(3, "Pune");
            insertSt.executeUpdate();
            insertSt.setInt(1, 5);
            insertSt.setString(2, "Ramesh");
            insertSt.setString(3, "Mumbai");
            insertSt.executeUpdate();
            System.out.println("\nTwo records inserted.");
            String updateSQL = "UPDATE Student SET Address=? WHERE ROLLNO=?";
            PreparedStatement updateSt = con.prepareStatement(updateSQL);
            updateSt.setString(1, "Delhi");
            updateSt.setInt(2, 2);
            int updated = updateSt.executeUpdate();
            System.out.println(updated + " record updated.");
            String deleteSQL = "DELETE FROM Student WHERE ROLLNO=?";
            PreparedStatement deleteSt = con.prepareStatement(deleteSQL);
            deleteSt.setInt(1, 3);
            int deleted = deleteSt.executeUpdate();
            System.out.println(deleted + " record deleted.");
            System.out.println("\nFinal Records:");
            displayRecords(con);
            insertSt.close();
            updateSt.close();
            deleteSt.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayRecords(Connection con) throws SQLException {

        String selectSQL = "SELECT * FROM Student ORDER BY ROLLNO";

        PreparedStatement selectSt = con.prepareStatement(selectSQL);
        ResultSet rs = selectSt.executeQuery();

        System.out.println("ROLLNO\tNAME\tADDRESS");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("ROLLNO") + "\t"
                    + rs.getString("Name") + "\t"
                    + rs.getString("Address"));
        }

        rs.close();
        selectSt.close();
    }
}
