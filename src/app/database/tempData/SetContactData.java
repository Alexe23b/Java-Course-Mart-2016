package app.database.tempData;


import app.database.connection.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alexe on 05.06.2016.
 */
public class SetContactData {

    static String firstName;
    static String lastName;

    static String country;
    static String city;
    static String street;
    static String houseNumber;
    static String houseSuffix;
    static String apartment;
    static String postCode;

    static List<String> phones = new ArrayList<>();
    static List<String> emails = new ArrayList<>();

    //static String photoPath;
    static Date birthday;

    public static void manualInputData(Connection dbConnection) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        firstName = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите фамилию контакта");
        lastName = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите дату рождения yyyy.MM.dd");
        String birthday = in.next("[0-9]{4}.[0-9]{2}.[0-9]{2}");
//        try {
//            birthday = new SimpleDateFormat("dd.MM.yyyy").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        in = new Scanner(System.in);
        System.out.println("Введите страну проживания");
        country = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите город проживания");
        city = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите улицу");
        street = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите номер дома");
        houseNumber = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите суффикс (если есть, если нет 'Enter')");
        houseSuffix = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите номер квартиры");
        apartment = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите почтовый индекс");
        postCode = in.nextLine();

        in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер телефона (если есть, если нет 'Enter')");
            String phone = in.nextLine();
            int length = phone.length();
            if (length < 1) {
                break;
            } else {
                phones.add(phone);
            }
        }

        in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер E-mail (если есть, если нет 'Enter')");
            String email = in.nextLine();
            int length = email.length();
            if (length < 1) {
                break;
            } else {
                emails.add(email);
            }
        }


        Statement statement = null;


        try {
            statement = dbConnection.createStatement();

            String address_record = "INSERT INTO address (`country`, `city`, `street`, `house`, `suffix`, `appartment`, `postcode`) " +
                    "VALUES ('" + country + "','" + city + "','" + street + "','"
                    + houseNumber + "','" + houseSuffix + "','" + apartment + "','" + postCode + "');";

            statement.execute(address_record);

            long idAddr = ((com.mysql.jdbc.Statement) statement).getLastInsertID();
            String idAddress = String.valueOf(idAddr);

            System.out.println("Пишем в таблицу Address, idAddress =" + idAddress);


            String contact_record = "INSERT INTO contacts (`name`, `surname`, `dateofbirth`, `pathtophoto`, `idaddress`, `uid`)" +
                    "VALUES ('" + firstName + "','" + lastName + "','" + birthday + "','Path to photo','" + idAddress + "','UID');";

            statement.execute(contact_record);

            long idCont = ((com.mysql.jdbc.Statement) statement).getLastInsertID();
            String idContact = String.valueOf(idCont);

            for (String phone : phones) {

                String phone_record = "INSERT INTO phones (`phonenumber`) VALUES ('" + phone + "');";
                statement.execute(phone_record);

                long idPhon = ((com.mysql.jdbc.Statement) statement).getLastInsertID();
                String idPhone = String.valueOf(idPhon);

                String phonetocontact_record = "INSERT INTO phonetocontact VALUES ('" + idPhone + "','" + idContact + "');";
                statement.execute(phonetocontact_record);
            }

            for (String email : emails) {
                String email_record = "INSERT INTO emails (`email`,`idcontact`) VALUES ('" + email + "','" + idContact + "');";
                statement.execute(email_record);
            }

            System.out.println("Все ОК, контакт добавден! " + idContact);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
