package writer;

import org.json.JSONArray;
import org.json.JSONObject;
import storage.Artist;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Writer of information about artists to JSON.
 */
public class JsonWriter {
    /**
     * List of artists.
     */
    private final ArrayList<Artist> ARTISTS;

    /**
     * Creating a writer of information about artists.
     * @param artists List of information about artists.
     */
    public JsonWriter(ArrayList<Artist> artists) {
        this.ARTISTS = artists;
    }

    /**
     * Write information about artists to file.
     * @param path Path where to write information about artists.
     */
    public void write(final String path) {
        JSONObject jsonInterpretation = getJsonInterpretation();

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonInterpretation.toString());
            file.flush();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Converts information about artists to JSONObject.
     * @return JSONObject with information about artists.
     */
    private JSONObject getJsonInterpretation() {
        LinkedHashMap<String, ArrayList<JSONObject>> countryAndArtists = getRelationshipBetweenCountryAndArtist();

        JSONArray listOfContentOfCountryTag = getListOfContentOfCountryTag(countryAndArtists);

        JSONObject countryTag = new JSONObject();
        countryTag.put("country", listOfContentOfCountryTag);

        JSONObject artistsTag = new JSONObject();
        artistsTag.put("artists", countryTag);

        return artistsTag;
    }

    /**
     * Separate all artists by countries.
     * @return Linked hashmap of relationships between country and artist.
     */
    private LinkedHashMap<String, ArrayList<JSONObject>> getRelationshipBetweenCountryAndArtist() {
        LinkedHashMap<String, ArrayList<JSONObject>> countryAndArtists = new LinkedHashMap<>();
        for (Artist artist : ARTISTS) {
            JSONArray allPicturesOfArtist = getAllPicturesOfArtist(artist);

            JSONObject artistTag = new JSONObject();
            artistTag.put("name", artist.getName());
            artistTag.put("picture", allPicturesOfArtist);

            String country = artist.getCountry();
            if (countryAndArtists.containsKey(country)) {
                ArrayList<JSONObject> listOfArtists = countryAndArtists.get(country);
                listOfArtists.add(artistTag);
                countryAndArtists.put(country, listOfArtists);
            } else {
                ArrayList<JSONObject> newArtist = new ArrayList<>();
                newArtist.add(artistTag);
                countryAndArtists.put(country, newArtist);
            }
        }

        return countryAndArtists;
    }

    /**
     * Create JSONArray with content of country tag.
     * @param countryAndArtists Relationship between countries and artists.
     * @return JSONArray with content of country tag.
     */
    private JSONArray getListOfContentOfCountryTag(LinkedHashMap<String, ArrayList<JSONObject>> countryAndArtists) {
        JSONArray listOfContentOfCountryTag = new JSONArray();
        for (String country : countryAndArtists.keySet()) {
            JSONObject insideCountry = new JSONObject();
            insideCountry.put("name", country);

            JSONArray listOfArtists = new JSONArray();
            for (JSONObject artist : countryAndArtists.get(country)) {
                listOfArtists.put(artist);
            }

            insideCountry.put("artist", listOfArtists);
            listOfContentOfCountryTag.put(insideCountry);
        }

        return listOfContentOfCountryTag;
    }

    /**
     * Get JSONArray of artist's pictures.
     * @param artist Information about artist.
     * @return JSONArray of artist's pictures.
     */
    private JSONArray getAllPicturesOfArtist(final Artist artist) {
        JSONArray allPicturesOfArtist = new JSONArray();
        LinkedHashMap<String, Integer> artistPictures = artist.getPictures();
        for (String namePicture : artistPictures.keySet()) {
            JSONObject pictureTag = new JSONObject();
            pictureTag.put("name", namePicture);
            pictureTag.put("publicationYear", artistPictures.get(namePicture));

            allPicturesOfArtist.put(pictureTag);
        }

        return allPicturesOfArtist;
    }
}
