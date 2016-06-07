package app.database;

import app.database.connection.DBUtils;
import app.database.tempData.SetContactData;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Scanner;


/**
 * Created by alexe on 05.06.2016.
 */
public class DBMain {
    static int dbType;

    public static void main(String[] args) throws SQLException {

        System.out.println("Введите номер типа базы данных (1 - mySQL, 2 - PostgreSQL)");
        Scanner in = new Scanner(System.in);
        dbType = in.nextInt();
        int check = 0;
        while (check != 1) {
            if (dbType == 1) {
                check = 1;
            } else if (dbType == 2) {
                check = 1;
            } else {
                System.out.println("Вы ввели неверное число. Введите 1 или 2 (1 - mySQL, 2 - PostgreSQL)");
                in = new Scanner(System.in);
                dbType = in.nextInt();
            }
        }

        Connection dbConnection = DBUtils.getDBConnection(dbType);

        //DBUtils.createPhonesTable(dbConnection);

        String inputData = SetContactData.manualInputData(dbConnection);
        System.out.println("Адрес контакта = " + inputData);
//        int quantityRowsAddress = DBUtils.getNumberRows(dbConnection, "address");
//        System.out.println("Количество строк в таблице 'Address' = " + quantityRowsAddress+";");

//        int quantityRowsContacts = DBUtils.getNumberRows(dbConnection, "contacts");
//        System.out.println("Количество строк в таблице 'Contacts' = " + quantityRowsContacts+";");
//
//        int quantityRowsEmails = DBUtils.getNumberRows(dbConnection, "emails");
//        System.out.println("Количество строк в таблице 'Emails' = " + quantityRowsEmails+";");
//
//        int quantityRowsPhones = DBUtils.getNumberRows(dbConnection, "phones");
//        System.out.println("Количество строк в таблице 'Phones' = " + quantityRowsPhones+";");
//
//        int quantityRowsPhoneToContact = DBUtils.getNumberRows(dbConnection, "phonetocontact");
//        System.out.println("Количество строк в таблице 'PhoneToContact' = " + quantityRowsPhoneToContact+";");


        if (dbConnection != null) {
            dbConnection.close();
        }
    }

}