package reader;

import org.json.JSONArray;
import org.json.JSONObject;
import storage.Artist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Reader of information about artists from JSON.
 */
public class JsonReader {
    /**
     * The path to the file from which the data is read.
     */
    private final String PATH;

    /**
     * Creating a reader with the specified path.
     * @param path Path to JSON file.
     */
    public JsonReader(String path) {
        this.PATH = path;
    }

    /**
     * Read information from JSON file and write it to list of artists.
     * @return List of artists.
     * @throws IOException If file does not exist.
     */
    public ArrayList<Artist> read() throws IOException {
        ArrayList<Artist> artists = new ArrayList<>();

        String json = Files.readString(Paths.get(PATH));
        JSONObject file = new JSONObject(json);

        JSONObject artistsTag = file.getJSONObject("artists");
        JSONArray countryTag = artistsTag.getJSONArray("country");

        for (int i = 0; i < countryTag.length(); i++) {
            JSONObject country = countryTag.getJSONObject(i);

            String countryName = country.getString("name");
            JSONArray informationAboutArtists = country.getJSONArray("artist");
            for (int j = 0; j < informationAboutArtists.length(); j++) {
                JSONObject artist = informationAboutArtists.getJSONObject(j);

                String artistName = artist.getString("name");
                LinkedHashMap<String, Integer> allArtistPictures = allArtistPictures(artist);

                artists.add(new Artist(countryName, artistName, allArtistPictures));
            }
        }

        return artists;
    }

    /**
     * Get information about pictures.
     * @param artist JSON object of artist.
     * @return Linked hashmap of names and publication years of pictures.
     */
    private LinkedHashMap<String, Integer> allArtistPictures(final JSONObject artist) {
        LinkedHashMap<String, Integer> allArtistPictures = new LinkedHashMap<>();
        JSONArray pictures = artist.getJSONArray("picture");
        for (int k = 0; k < pictures.length(); k++) {
            JSONObject picture = pictures.getJSONObject(k);

            String pictureName = picture.getString("name");
            int publicationYear = picture.getInt("publicationYear");

            allArtistPictures.put(pictureName, publicationYear);
        }

        return allArtistPictures;
    }
}
