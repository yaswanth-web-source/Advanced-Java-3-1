import java.sql.*;
class Lab5{
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/testdb?";
        String user = "root";
        String password = "Yaswanth@123";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("SELECT * FROM Student");
        rs.last();          
        rs.deleteRow();     
        System.out.println("Last student record deleted successfully.");
        rs.moveToInsertRow();                  
        rs.updateInt("RollNo", 205);           
        rs.updateString("Name", "Yaswanth");   
        rs.updateString("Address", "Paris"); 
        rs.insertRow();                       
        System.out.println("New student record inserted successfully.");
            con.close();
        }catch(Exception e){
        e.printStackTrace();
        }
}
}


OUTPUT:
Last student record deleted successfully.
New student record inserted successfully.


