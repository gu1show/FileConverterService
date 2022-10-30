import reader.*;
import writer.*;

import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TransformerConfigurationException {
        String firstExtension = getExtension(args[0]);
        String secondExtension = getExtension(args[1]);
        if ((firstExtension.equals("xml")) && (secondExtension.equals("json"))) {
            XmlReader reader = new XmlReader(args[0]);
            JsonWriter writer = new JsonWriter(reader.read());
            writer.write(args[1]);
        } else if ((firstExtension.equals("json")) && (secondExtension.equals("xml"))) {
            JsonReader jsonReader = new JsonReader(args[0]);
            XmlWriter writer = new XmlWriter(jsonReader.read());
            writer.write(args[1]);
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
