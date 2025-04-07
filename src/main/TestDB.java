package main;
import java.sql.*;
import util.DBConnection;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM patient";  // Replace with your table
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Patient Name: " + rs.getString("firstname"));  // Replace column name
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println(PropertyUtil.getPropertyString());
    }
}
