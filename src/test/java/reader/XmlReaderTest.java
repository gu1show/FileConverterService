package reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import storage.Artist;
import storage.Picture;

import javax.xml.bind.JAXBException;
import java.util.*;

/**
 * Тестовый класс для класса XmlReader.
 */
public class XmlReaderTest {
    /**
     * Путь к XML-файлу.
     */
    private static final String path = "src\\test\\resources\\artists.xml";

    /**
     * Вручную созданное отношение страны и художников.
     */
    private Map<String, List<Artist>> countryAndArtistsManually;

    /**
     * Считывание информации вручную.
     */
    @Before
    public void readManually() {
        countryAndArtistsManually = new LinkedHashMap<>();

        List<Picture> picturesOfClaude = new ArrayList<>();
        picturesOfClaude.add(new Picture("Impression, Sunrise", 1872));
        picturesOfClaude.add(new Picture("Arrival of the Normandy Train, Gare Saint-Lazare",
                                         1877));
        picturesOfClaude.add(new Picture("Arch to the West from Etretat", 1883));
        Artist claude = new Artist("Claude Monet", picturesOfClaude);

        List<Picture> picturesOfPaul = new ArrayList<>();
        picturesOfPaul.add(new Picture("Self-portrait", 1881));
        picturesOfPaul.add(new Picture("Still Life with a Curtain", 1895));
        picturesOfPaul.add(new Picture("Landscape in Aix. Mont Sainte-Victoire", 1905));
        Artist paul = new Artist("Paul Cézanne", picturesOfPaul);

        List<Artist> frenchArtists = new ArrayList<>();
        frenchArtists.add(claude);
        frenchArtists.add(paul);
        countryAndArtistsManually.put("France", frenchArtists);

        List<Picture> picturesOfApollinary = new ArrayList<>();
        picturesOfApollinary.add(new Picture("Boreal forest in the Ural mountains", 1890));
        picturesOfApollinary.add(new Picture("Mountain Lake", 1892));
        picturesOfApollinary.add(new Picture("Kremlin", 1892));
        Artist apollinary = new Artist("Apollinary Mikhaylovich Vasnetsov", picturesOfApollinary);

        List<Picture> picturesOfViktor = new ArrayList<>();
        picturesOfViktor.add(new Picture("The Flying Carpet", 1880));
        picturesOfViktor.add(new Picture("Alyonushka", 1881));
        picturesOfViktor.add(new Picture("Ivan Tsarevich riding the Gray Wolf", 1889));
        Artist viktor = new Artist("Viktor Mikhaylovich Vasnetsov", picturesOfViktor);

        List<Artist> russianArtists = new ArrayList<>();
        russianArtists.add(apollinary);
        russianArtists.add(viktor);
        countryAndArtistsManually.put("Russia", russianArtists);
    }

    /**
     * Проверка, что созданное вручную и программой отношения равны.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkReadingFromXml() throws JAXBException {
        XmlReader xmlReader = new XmlReader(path);
        Map<String, List<Artist>> countryAndArtistsFromFile = xmlReader.read();

        Set<String> countriesFromFile = countryAndArtistsFromFile.keySet();
        Set<String> countriesManually = countryAndArtistsManually.keySet();

        Assert.assertEquals(countriesFromFile.size(), countriesManually.size());

        for (String country : countriesFromFile) {
            Assert.assertTrue(countriesManually.contains(country));
        }

        for (String country : countriesFromFile) {
            List<Artist> artistsFromCountryFile = countryAndArtistsFromFile.get(country);
            List<Artist> artistsFromCountryManually = countryAndArtistsManually.get(country);

            Assert.assertEquals(artistsFromCountryFile.size(), artistsFromCountryManually.size());

            for (int artistNumber = 0; artistNumber < artistsFromCountryFile.size(); artistNumber++) {
                Artist artistFromFile = artistsFromCountryFile.get(artistNumber);
                Artist artistManually = artistsFromCountryManually.get(artistNumber);

                Assert.assertEquals(artistFromFile.getName(), artistManually.getName());

                List<Picture> picturesFromFile = artistFromFile.getPictures();
                List<Picture> picturesManually = artistManually.getPictures();
                Assert.assertEquals(picturesFromFile.size(), picturesManually.size());

                for (int pictureNumber = 0; pictureNumber < picturesFromFile.size(); pictureNumber++) {
                    Picture pictureFromFile = picturesFromFile.get(pictureNumber);
                    Picture pictureManually = picturesManually.get(pictureNumber);

                    Assert.assertEquals(pictureFromFile.getName(), pictureManually.getName());
                    Assert.assertEquals(pictureFromFile.getPublicationYear(), pictureManually.getPublicationYear());
                }
            }
        }
    }
}