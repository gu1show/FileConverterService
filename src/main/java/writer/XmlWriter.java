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

public class XmlWriter {
    private final ArrayList<Artist> ARTISTS;

    public XmlWriter(ArrayList<Artist> artists) {
        this.ARTISTS = artists;
    }

    public void write(String path) throws TransformerConfigurationException {
        Document file = createDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(file);

        StreamResult sr = new StreamResult(new File(path));
        try {
            transformer.transform(source, sr);
        } catch (TransformerException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Document createDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
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

    private Element getAllPicturesOfArtist(Artist artist, Document file) {
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

    private LinkedHashMap<String, ArrayList<Element>> coordinateCountryAndArtist(Document file) {
        LinkedHashMap<String, ArrayList<Element>> countryAndArtists = new LinkedHashMap<>();
        for (Artist artist : ARTISTS) {
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
}
