package signup;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpModel {
    Connection connection;

    public SignUpModel() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection == null) System.exit(1);
    }

    public boolean isUser(String name) {
        String sql = "SELECT Username FROM loginInfo WHERE Username = ?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, name);
            rs = pr.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
