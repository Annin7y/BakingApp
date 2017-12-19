package annin.my.android.bakingapp.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import annin.my.android.bakingapp.custom.Ingredients;

import static android.content.ContentValues.TAG;

/**
 * Created by Maino96-10022 on 11/27/2017.
 */

public class NetworkUtils {

    //https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    public NetworkUtils() {
    }

    private static ArrayList<Recipe> fetchRecipesData(String requestUrl) {

        // Create a URL object
        URL url = buildUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Movie}s
        ArrayList<Movie> moviesList = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Movie}s
        return moviesList;
    }

    /**
     * @param sortMode
     * @return either most popular or top rated movies
     */
    public static URL buildUrl(String sortMode) {
        URL url = null;
        try {
            if (sortMode.equals(SORT_BY_POPULAR)) {
                Uri builtUri = Uri.parse(BASE_URL_POPULAR).buildUpon()
                        .appendQueryParameter(API_KEY, BuildConfig.OPEN_MOVIES_API_KEY)
                        .build();
                url = new URL(builtUri.toString());
            } else if (sortMode.equals(SORT_BY_RATING)) {
                Uri builtUri = Uri.parse(BASE_URL_TOP_RATED).buildUpon()
                        .appendQueryParameter(API_KEY, BuildConfig.OPEN_MOVIES_API_KEY)
                        .build();
                url = new URL(builtUri.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        Log.i("URL: ", url.toString());
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving movie JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Ingredients> extractFeatureFromJson(String recipeJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(recipeJSON)) {
            return null;
        }
        ArrayList<Recipes> recipes = new ArrayList<>();
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(recipeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray movieArray = baseJsonResponse.getJSONArray("results");

// For each earthquake in the earthquakeArray, create an {@link Movie} object
            for (int i = 0; i < movieArray.length(); i++) {

                // Get a single movie description at position i within the list of movies
                JSONObject currentMovie = movieArray.getJSONObject(i);

                // Extract the value for the key called "poster_title"
                String posterName = currentMovie.getString(KEY_POSTER_PATH);

                String movieName = currentMovie.getString(KEY_ORIGINAL_TITLE);

                String overviewName = currentMovie.getString(KEY_OVERVIEW);

                String voteName = currentMovie.getString(KEY_VOTE_AVERAGE);

                String releaseDate = currentMovie.getString(KEY_RELEASE_DATE);

                String movieId = currentMovie.getString(MOVIE_ID);

                Movie movie = new Movie(posterName, movieName, overviewName, voteName, releaseDate, movieId);
                movies.add(movie);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing movies JSON results", e);
        }

        // Return the list of movies
        return movies;
    }

}
