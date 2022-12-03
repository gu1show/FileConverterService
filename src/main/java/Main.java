import lombok.NonNull;
import reader.*;
import writer.*;

import java.io.IOException;

/**
 * Launching class.
 */
public class Main {
    /**
     * Launch the program to convert JSON file to XML file or vice versa.
     * @param args Array of command-line arguments.
     *             args[0] has a path to a source file.
     *             args[1] has a path where to save the file.
     * @throws IOException If file does not exist.
     */
    public static void main(@NonNull String[] args) throws IOException {
        final String errorMessage = """
                
                Incorrect input. Input should contain 2 filenames:
                1) Existing file that you want to convert with extension .xml or .json
                2) New converted file
                """;

        if (args.length != 2) {
            throw new IllegalArgumentException(errorMessage);
        }

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
        } else {
            throw new IllegalArgumentException(errorMessage);
        }

        System.out.println("Converted successfully!");
    }

    /**
     * Get extension of the file.
     * @param path Path to the file.
     * @return Extension of the given file.
     */
    private static String getExtension(String path) {
        int indexOfLastDot = path.lastIndexOf(".");
        if (indexOfLastDot > -1) {
            return path.substring(indexOfLastDot + 1);
        } else {
            return "";
        }
    }
}
