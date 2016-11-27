package sociopathycheck.selector.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sociopathycheck.selector.Constants;
import sociopathycheck.selector.models.Place;

import static sociopathycheck.selector.ui.WeatherActivity.TAG;

/**
 * Created by JS on 11/20/16.
 */

public class PlaceService {
    public static void findPlace(String latLong, Callback callback) {
        String PLACE_API_KEY = Constants.PLACES_API_KEY;


        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PLACES_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PLACE_API_QUERY_PARAMETER, PLACE_API_KEY);
        urlBuilder.addQueryParameter(Constants.PLACE_LOCATION_QUERY_PARAMETER, latLong);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Place> processResults(Response response) {
        ArrayList<Place> places = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject placeJSON = new JSONObject(jsonData);
                JSONArray placesJSON =placeJSON.getJSONArray("results");
                JSONObject photosJSON = placesJSON.getJSONObject(0);
                JSONArray placerJSON = photosJSON.getJSONArray("photos");
                JSONObject photosTwoJSON = placerJSON.getJSONObject(0);
                String placeString = photosTwoJSON.getString("photo_reference");
//                Log.d(TAG, placeString);
                Place place = new Place(placeString);
                places.add(place);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return places;
    }


}

