package app.parse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexe on 10.06.2016.
 */
public class JsonToJava {
    private static Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        String path = "files/in/contacts.json";

        List<Contact> contacts = readJsonStream1(new FileInputStream(path));

        List<Address> addresses = readJsonStream2(new FileInputStream(path));
        int lengthContacts = contacts.size();
        int lengthAddresses = addresses.size();

        for (Address address : addresses){
            System.out.println("Address");
            System.out.println("Country = " + address.getCountry());
            System.out.println("City = " + address.getCity());
            System.out.println("Street = " + address.getStreet());
            System.out.println("House Number = " + address.getHouseNumber());
            System.out.println("House Suffix = " + address.getHouseSuffix());
            System.out.println("Apartment = " + address.getApartment());
            System.out.println("Post Code = " + address.getPostCode());
        }

//        for (int i = 0; i < lengthAddresses; i++) {
//            Contact contact = contacts.get(i);
//            Address address = addresses.get(i);
//
//            System.out.println("Uids = " + contact.getUids());
//            System.out.println("First Name = " + contact.getFirstName());
//            System.out.println("Last Name = " + contact.getLastName());
//            System.out.println("Address [" + (i+1) +"];");
//            System.out.println("Country = " + address.getCountry());
//            System.out.println("City = " + address.getCity());
//            System.out.println("Street = " + address.getStreet());
//            System.out.println("House Number = " + address.getHouseNumber());
//            System.out.println("House Suffix = " + address.getHouseSuffix());
//            System.out.println("Apartment = " + address.getApartment());
//            System.out.println("Post Code = " + address.getPostCode());
//            System.out.println("Phones = " + contact.getPhones());
//            System.out.println("Emails = " + contact.getEmails());
//            System.out.println("Photo Path = " + contact.getPhotoPath());
//            System.out.println("Birthday = " + contact.getBirthday());
//            System.out.println("----------------------------------------");
//
//
////            contact.setUids(contact.getUids() + " "
////                    + contact.getFirstName() + " "
////                    + contact.getLastName() + " "
////                    + contact.getAddress() + " "
////                    + contact.getPhones() + " "
////                    + contact.getEmails() + " "
////                    + contact.getPhotoPath() + " "
////                    + contact.getBirthday());
//        }
//
    }

    private static List<Contact> readJsonStream1(FileInputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        List<Contact> contacts = new ArrayList<>();


        reader.beginArray();
        while (reader.hasNext()) {
            Contact contact = gson.fromJson(reader, Contact.class);
            contacts.add(contact);
            System.out.println(contact);
        }
        reader.endArray();
        reader.close();

        return contacts;
    }

    private static List<Address> readJsonStream2(FileInputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        List<Address> addresses = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            Address address = gson.fromJson(reader, Address.class);
            addresses.add(address);
            System.out.println(address);
        }
        reader.endArray();
        reader.close();

        return addresses;
    }

}
