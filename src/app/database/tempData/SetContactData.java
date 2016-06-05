package app.database.tempData;


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
    static Integer houseNumber;
    static String houseSuffix;
    static Integer apartment;
    static Integer postCode;

    static List<String> phones = new ArrayList<>();
    static List<String> emails = new ArrayList<>();

    //static String photoPath;
    static Date birthday;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        firstName = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите фамилию контакта");
        lastName = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите дату рождения dd.MM.yyyy");
        String date = in.next("[0-9]{2}.[0-9]{2}.[0-9]{4}");
        try {
            birthday = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        houseNumber = in.nextInt();

        in = new Scanner(System.in);
        System.out.println("Введите суффикс (если есть, если нет пробел)");
        houseSuffix = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Введите номер квартиры");
        apartment = in.nextInt();

        in = new Scanner(System.in);
        System.out.println("Введите почтовый индекс");
        postCode = in.nextInt();

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

        System.out.println("contact name: " + firstName + " " + lastName);
        System.out.println("date of birth: " + date);
        System.out.println("address: " + country + ", city " + city + ", street " + street + ", house " + houseNumber + " " + houseSuffix + ", app " + apartment);
        System.out.println("phones: " + phones);
        System.out.println("e-mails: " + emails);
    }
}
