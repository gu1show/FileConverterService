package reader;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import storage.Artist;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XmlReader {
    private final String PATH;

    public XmlReader(String path) {
        this.PATH = path;
    }

    public ArrayList<Artist> read() throws IOException {
        ArrayList<Artist> artists = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(PATH));
            document.getDocumentElement().normalize();

            NodeList countries = document.getElementsByTagName("country");
            for (int i = 0; i < countries.getLength(); i++) {
                Element country = (Element) countries.item(i);

                String countryName = country.getAttribute("name");

                NodeList listOfArtistFromTheCountry = country.getElementsByTagName("artist");
                for (int j = 0; j < listOfArtistFromTheCountry.getLength(); j++) {
                    Element artistElement = (Element) listOfArtistFromTheCountry.item(j);

                    String artistName = artistElement.getElementsByTagName("name")
                            .item(0)
                            .getTextContent();
                    LinkedHashMap<String, Integer> pictures = getPictures(artistElement);

                    artists.add(new Artist(countryName, artistName, pictures));
                }
            }
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        return artists;
    }

    private LinkedHashMap<String, Integer> getPictures(Element artist) {
        LinkedHashMap<String, Integer> pictures = new LinkedHashMap<>();
        NodeList listOfPictures = artist.getElementsByTagName("picture");
        for (int i = 0; i < listOfPictures.getLength(); i++) {
            Element pictureNode = (Element) listOfPictures.item(i);

            Node nameNode = pictureNode.getElementsByTagName("name").item(0);
            String pictureName = nameNode.getTextContent();

            Node publicationYearNode = pictureNode.getElementsByTagName("publicationYear")
                    .item(0);
            String publicationYearString = publicationYearNode.getTextContent();
            int publicationYear = Integer.parseInt(publicationYearString);

            pictures.put(pictureName, publicationYear);
        }

        return pictures;
    }
}
