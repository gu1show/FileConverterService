package reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import storage.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Тестовый класс для класса XmlReader.
 */

public class XmlAndJsonReaderTest {
    /**
     * Путь к XML-файлу.
     */
    private static final String PATH_XML = "src\\test\\resources\\artists.xml";

    /**
     * Путь к JSON-файлу.
     */
    private static final String PATH_JSON = "src\\test\\resources\\artists.json";

    /**
     * Вручную созданное отношение страны и художников.
     */
    private Wrapper correctWrapper;

    /**
     * Создание корректной обёртки информации о художниках.
     */
    @Before
    public void readManually() {
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
        Country france = new Country("France", frenchArtists);

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
        Country russia = new Country("Russia", russianArtists);

        List<Country> countryList = new ArrayList<>();
        countryList.add(france);
        countryList.add(russia);

        correctWrapper = new Wrapper(new Artists(countryList));
    }

    /**
     * Проверка, что созданная считываем из XML-файла обёртка корректная.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkReadingFromXml() throws JAXBException {
        XmlReader xmlReader = new XmlReader(PATH_XML);
        Wrapper wrapperFromFile = xmlReader.read();

        checkCorrectness(wrapperFromFile);
    }

    /**
     * Проверка, что созданная считываем из JSON-файла обёртка корректная.
     * @throws IOException Происходит из-за метода read у JsonReader.
     * Срабатывает, если нет такого файла.
     */
    @Test
    public void checkReadingFromJson() throws IOException {
        JsonReader jsonReader = new JsonReader(PATH_JSON);
        Wrapper wrapperFromFile = jsonReader.read();

        checkCorrectness(wrapperFromFile);
    }

    /**
     * Проверка, что созданные вручную и программой обёртки одинаковые.
     * @param wrapperFromFile Обёртка, созданная программно.
     */
    private void checkCorrectness(Wrapper wrapperFromFile) {
        Artists artistsFromFile = wrapperFromFile.getArtists();
        Artists artistsManually = correctWrapper.getArtists();
        List<Country> countriesFromFile = artistsFromFile.getCountryList();
        List<Country> countriesManually = artistsManually.getCountryList();

        Assert.assertEquals(countriesFromFile.size(), countriesManually.size());

        for (int countryIndex = 0; countryIndex < countriesFromFile.size(); countryIndex++) {
            Country countryFromFile = countriesFromFile.get(countryIndex);
            Country countryManually = countriesManually.get(countryIndex);

            Assert.assertEquals(countryFromFile.getCountryName(), countryManually.getCountryName());

            List<Artist> artistListFromFile = countryFromFile.getArtistList();
            List<Artist> artistListManually = countryManually.getArtistList();

            Assert.assertEquals(artistListFromFile.size(), artistListManually.size());

            for (int artistIndex = 0; artistIndex < artistListFromFile.size(); artistIndex++) {
                Artist artistFromFile = artistListFromFile.get(artistIndex);
                Artist artistManually = artistListManually.get(artistIndex);

                Assert.assertEquals(artistFromFile.getName(), artistManually.getName());

                List<Picture> pictureListFromFile = artistFromFile.getPictures();
                List<Picture> pictureListManually = artistManually.getPictures();

                Assert.assertEquals(pictureListFromFile.size(), pictureListManually.size());

                for (int pictureIndex = 0; pictureIndex < pictureListFromFile.size(); pictureIndex++) {
                    Picture pictureFromFile = pictureListFromFile.get(pictureIndex);
                    Picture pictureManually = pictureListManually.get(pictureIndex);

                    Assert.assertEquals(pictureFromFile.getName(), pictureManually.getName());
                    Assert.assertEquals(pictureFromFile.getPublicationYear(), pictureManually.getPublicationYear());
                }
            }
        }
    }
}
