package sociopathycheck.selector.services;

import android.media.Image;
import android.text.Html;
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
import sociopathycheck.selector.models.Photo;
import sociopathycheck.selector.models.Place;

import static sociopathycheck.selector.ui.WeatherActivity.TAG;

/**
 * Created by JS on 11/26/16.
 */

public class PhotoService {
    public static void findPhoto(String photoReference, Callback callback) {
        String PLACE_API_KEY = Constants.PLACES_API_KEY;


        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PHOTOS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.PLACE_API_QUERY_PARAMETER, PLACE_API_KEY);
        urlBuilder.addQueryParameter(Constants.PHOTO_LOCATION_QUERY_PARAMETER, photoReference);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Photo> processResults(Response response) {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject photoJSON = new JSONObject(jsonData);
                JSONArray photoResult = photoJSON.getJSONArray("results");
                for (int i = 0; i < photoResult.length(); i++) {
                    JSONObject result = photoResult.getJSONObject(i);
                    String title = result.getString("KEY_TITLE");
                    Photo photo = new Photo(title);
                    photos.add(photo);
                }



//
//                JSONObject jsonObjectResponseData = mSearchData.getJSONObject("responseData");
//                JSONArray jsonResults = jsonObjectResponseData.getJSONArray("results");
//                ArrayList<HashMap<String, String>> searchResults =
//                        new ArrayList<HashMap<String, String>>();
//                for (int i = 0; i < jsonResults.length(); i++) {
//                    JSONObject result = jsonResults.getJSONObject(i);
//                    String title = result.getString(KEY_TITLE);
//                    title = Html.fromHtml(title).toString();
//                    Log.v(TAG, title);


//                JSONArray placesJSON =placeJSON.getJSONArray("results");
//                JSONObject photosJSON = placesJSON.getJSONObject(0);
//                JSONArray placerJSON = photosJSON.getJSONArray("photos");
//                JSONObject photosTwoJSON = placerJSON.getJSONObject(0);
//                String placeString = photosTwoJSON.getString("photo_reference");
//                Log.d(TAG, placeString);
//                Photo photo = new Photo(placeJSON);
//                photos.add(photo);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photos;
    }


}