package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mda on 5/23/16.
 */
public class DBMain {
    public static void main(String[] args) throws SQLException {

        Connection dbConnection = DBUtilsSample.getDBConnection();


        Statement statement = dbConnection.createStatement();


        try {
            DBUtilsSample.createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
