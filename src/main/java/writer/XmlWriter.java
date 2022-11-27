package writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import storage.Artist;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * Writer of information about artists to XML.
 */
public class XmlWriter {
    /**
     * List of artists.
     */
    private final ArrayList<Artist> artists;

    /**
     * Creating a writer of information about artists.
     * @param artists List of information about artists.
     */
    public XmlWriter(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    /**
     * Write information about artists to file.
     * @param path Path where to write information about artists.
     */
    public void write(final String path) {
        Document file = createDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        DOMSource source = new DOMSource(file);

        StreamResult sr = new StreamResult(new File(path));
        try {
            transformer.transform(source, sr);
        } catch (TransformerException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }

    /**
     * Create a document ready for writing.
     * @return Ready for writing document.
     */
    private Document createDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        Document file = builder.newDocument();
        file.setXmlStandalone(true);

        LinkedHashMap<String, ArrayList<Element>> countryAndArtists = coordinateCountryAndArtist(file);
        Element root = file.createElement("artists");
        file.appendChild(root);
        for (String country : countryAndArtists.keySet()) {
            Element countryTag = file.createElement("country");
            countryTag.setAttribute("name", country);

            for (Element artist : countryAndArtists.get(country)) {
                countryTag.appendChild(artist);
            }

            root.appendChild(countryTag);
        }

        return file;
    }

    /**
     * Separate all artists by countries and create tags for them.
     * @param file File to create tags (feature of DOM Parser).
     * @return Linked hashmap of relationships between country and artist.
     */
    private LinkedHashMap<String, ArrayList<Element>> coordinateCountryAndArtist(final Document file) {
        LinkedHashMap<String, ArrayList<Element>> countryAndArtists = new LinkedHashMap<>();
        for (Artist artist : artists) {
            Element allPicturesOfArtist = getAllPicturesOfArtist(artist, file);

            Element artistTag = file.createElement("artist");
            Element artistNameTag = file.createElement("name");
            artistNameTag.setTextContent(artist.getName());

            artistTag.appendChild(artistNameTag);
            artistTag.appendChild(allPicturesOfArtist);

            String country = artist.getCountry();
            if (countryAndArtists.containsKey(country)) {
                ArrayList<Element> listOfArtists = countryAndArtists.get(country);
                listOfArtists.add(artistTag);
                countryAndArtists.put(country, listOfArtists);
            } else {
                ArrayList<Element> newArtist = new ArrayList<>();
                newArtist.add(artistTag);
                countryAndArtists.put(country, newArtist);
            }
        }

        return countryAndArtists;
    }

    /**
     * Get Element with artist's pictures.
     * @param artist Information about artist.
     * @param file Document to create tags (feature of DOM Parser).
     * @return Element with artist's pictures.
     */
    private Element getAllPicturesOfArtist(final Artist artist, final Document file) {
        Element picturesTag = file.createElement("pictures");
        Map<String, Integer> artistPictures = artist.getPictures();
        for (String namePicture : artistPictures.keySet()) {
            Element pictureTag = file.createElement("picture");

            Element pictureNameTag = file.createElement("name");
            pictureNameTag.setTextContent(namePicture);
            Element publicationYearTag = file.createElement("publicationYear");
            publicationYearTag.setTextContent(String.valueOf(artistPictures.get(namePicture)));

            pictureTag.appendChild(pictureNameTag);
            pictureTag.appendChild(publicationYearTag);

            picturesTag.appendChild(pictureTag);
        }

        return picturesTag;
    }
}
