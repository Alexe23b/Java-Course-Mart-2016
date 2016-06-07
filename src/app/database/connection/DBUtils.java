package app.database.connection;

import app.database.tempData.SetContactData;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by mda on 5/23/16.
 */
public class DBUtils {
    static String dbDriver;
    static String url;
    static String login;
    static String password;

    public static Connection getDBConnection(int dbType) {
        if (dbType == 2) {
            dbDriver = "org.postgresql.Driver";
            url = "jdbc:postgresql://localhost:5432/tac_addressbook";
            login = "postgres";
            password = "postgres_password";
        } else if (dbType == 1) {
            dbDriver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/MyAddressBook";
            login = "root";
            password = "1608";
        }

        return getDBConnection(dbDriver, url, login, password);


//        return getDBConnection("com.mysql.jdbc.Driver", "jdbc:mysql://hostname:port/dbname","username", "password");
//        return getDBConnection("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:mkyong","username","password");
    }

    private static Connection getDBConnection(String dbDriver,
                                              String url,
                                              String dbUser,
                                              String dbPassword) {
        Connection connection = null;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

//    public static void createPhonesTable(Connection dbConnection) {
//        Statement statement = null;
//
//        String createTableSQL = "CREATE TABLE phones("
//                + "idphone INTEGER AUTO_INCREMENT, "
//                + "phone_number VARCHAR(20) NOT NULL, "
//                + "idcontact INTEGER NOT NULL, "
//                + "PRIMARY KEY (idphone) "
//                + ")";
//
//        try {
//            statement = dbConnection.createStatement();
//            // выполнить SQL запрос
//            statement.execute(createTableSQL);
//            System.out.println("Table \"Phones\" is created!");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public static int getNumberRows(Connection dbConnection, String tableName) {
        Statement statement = null;

        String getNumRowsAddress = "SELECT COUNT(*) FROM " + tableName;
        int quantityRows = -1;
        try {
            statement = dbConnection.createStatement();

            ResultSet resultSet = statement.executeQuery(getNumRowsAddress);
            if (resultSet.next()) {

                quantityRows = resultSet.getInt(1);
            }

            //System.out.println("Количество строк в таблице 'Address' = " + quantityRows+";");

        } catch (
                SQLException e
                )

        {
            System.out.println(e.getMessage());
        } finally

        {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (quantityRows);
    }

    public static void addContact(Connection dbConnection){
        System.out.println("Введите способ ввода данных (1 - с клавиатуры, 2 - из файла)");
        Scanner in = new Scanner(System.in);
        int how = in.nextInt();
        int check = 0;
        while (check != 1) {
            if (how == 1) {
                check = 1;
                SetContactData.manualInputData(dbConnection);
            } else if (how == 2) {
                check = 1;
                System.out.println("Извините ввод из файла пока не работает");
            } else {
                System.out.println("Вы ввели неверное число. Введите 1 или 2 (1 - с клавиатуры, 2 - из файла)");
                in = new Scanner(System.in);
                how = in.nextInt();
            }
        }

    }

}
