package parse.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mda on 5/19/16.
 */
public class GsonMain {
    private static Gson gson = new Gson();

    public static void main(String[] args) throws IOException {

        String path = "files/in/messages.json";

        List<Message> messages = readJsonStream(new FileInputStream(path));

        for (Message message: messages) {
            System.out.println("Type = " + message.getType());
            System.out.println("Message = " + message.getMessage());

            message.setMessage(message.getType() + " " +  message.getMessage());
        }

        writeJsonStream(new FileOutputStream(path), messages);
    }

    public static List<Message> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        List<Message> messages = new ArrayList<Message>();
        reader.beginArray();
        while (reader.hasNext()) {
            Message message = gson.fromJson(reader, Message.class);
            messages.add(message);
        }
        reader.endArray();
        reader.close();

        return messages;
    }

    public static void writeJsonStream(OutputStream out, List<Message> messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writer.beginArray();
        for (Message message : messages) {
            gson.toJson(message, Message.class, writer);
        }
        writer.endArray();
        writer.close();
    }

}
