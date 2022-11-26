package writer;

import org.json.JSONArray;
import org.json.JSONObject;
import storage.Artist;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonWriter {
    private final ArrayList<Artist> ARTISTS;

    public JsonWriter(ArrayList<Artist> artists) {
        this.ARTISTS = artists;
    }

    public void write(final String path) {
        JSONObject jsonInterpretation = getConvertedFile();

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonInterpretation.toString());
            file.flush();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private JSONObject getConvertedFile() {
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

        JSONArray countryList = new JSONArray();
        for (String country : countryAndArtists.keySet()) {
            JSONObject insideCountry = new JSONObject();
            insideCountry.put("name", country);

            JSONArray arr = new JSONArray();
            for (JSONObject artist : countryAndArtists.get(country)) {
                arr.put(artist);
            }

            insideCountry.put("artist", arr);
            countryList.put(insideCountry);
        }

        JSONObject countryTag = new JSONObject();
        countryTag.put("country", countryList);

        JSONObject artistsTag = new JSONObject();
        artistsTag.put("artists", countryTag);

        return artistsTag;
    }

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
