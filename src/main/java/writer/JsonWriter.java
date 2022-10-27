package writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import template.Artist;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter {
    private final ArrayList<Artist> ARTISTS;

    public JsonWriter(ArrayList<Artist> artists) {
        this.ARTISTS = artists;
    }

    public void write(String path) {
        JSONObject jsonInterpretation = getConvertedFile();

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonInterpretation.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getConvertedFile() {
        HashMap<String, ArrayList<JSONObject>> countryAndArtists = new HashMap<>();
        for (int i = 0; i < ARTISTS.size(); i++) {
            Artist artist = artistList.get(i);

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

        JSONObject countryList = new JSONObject();
        for (String country : countryAndArtists.keySet())
            countryList.put(country, countryAndArtists.get(country));

        JSONObject countryTag = new JSONObject();
        countryTag.put("country", countryList);

        JSONObject artistsTag = new JSONObject();
        artistsTag.put("artists", countryTag);

        return artistsTag;
    }

    private JSONArray getAllPicturesOfArtist(Artist artist) {
        JSONArray allPicturesOfArtist = new JSONArray();
        Map<String, Integer> artistPictures = artist.getPictures();
        for (String namePicture : artistPictures.keySet()) {
            JSONObject pictureTag = new JSONObject();
            pictureTag.put("name", namePicture);
            pictureTag.put("publicationYear", artistPictures.get(namePicture));

            allPicturesOfArtist.add(pictureTag);
        }

        return allPicturesOfArtist;
    }
}
