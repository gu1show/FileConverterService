import reader.*;
import writer.*;

import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, TransformerConfigurationException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the first filename:");
        String first = scanner.nextLine();

        System.out.println("Input the second filename:");
        String second = scanner.nextLine();

        String firstExtension = getExtension(first);
        String secondExtension = getExtension(second);
        if ((firstExtension.equals("xml")) && (secondExtension.equals("json"))) {
            XmlReader reader = new XmlReader(first);
            JsonWriter writer = new JsonWriter(reader.read());
            writer.write(second);
        } else if ((firstExtension.equals("json")) && (secondExtension.equals("xml"))) {
            JsonReader jsonReader = new JsonReader(first);
            XmlWriter writer = new XmlWriter(jsonReader.read());
            writer.write(second);
        } else throw new IllegalArgumentException("""
                
                Incorrect input. Input should contain 2 filenames:
                1) Existing file that you want to convert with extension .xml or .json
                2) New converted file
                """
        );

        System.out.println("Converted successfully!");
    }

    private static String getExtension(String path) {
        int indexOfLastDot = path.lastIndexOf(".");
        if (indexOfLastDot > -1) return path.substring(indexOfLastDot + 1);
        else return "";
    }
}
