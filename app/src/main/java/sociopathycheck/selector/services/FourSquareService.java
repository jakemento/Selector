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
import sociopathycheck.selector.models.FourSquare;


public class FourSquareService {
    public static void findVenues(String latLong, Callback callback) {

        String FOURSQUARE_CLIENT_ID = Constants.FOURSQUARE_CLIENT_ID;
        String FOURSQUARE_CLIENT_SECRET = Constants.FOURSQUARE_CLIENT_SECRET;
        String FOURSQUARE_EXTRAS = Constants.FOURSQUARE_EXTRAS;



        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FOURSQUARE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FOURSQUARE_LAT_LONG, latLong);
        urlBuilder.addQueryParameter(Constants.FOURSQUARE_CID, FOURSQUARE_CLIENT_ID);
        urlBuilder.addQueryParameter(Constants.FOURSQUARE_CS, FOURSQUARE_CLIENT_SECRET);

        String url = urlBuilder.build().toString();
        url = url + (FOURSQUARE_EXTRAS);
        Log.d("THIS", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<FourSquare> processResults(Response response) {
        ArrayList<FourSquare> foursquares = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.d("THIS", jsonData);
            if (response.isSuccessful()) {

                JSONObject fourJSON = new JSONObject(jsonData);
                JSONObject responseJSON = fourJSON.getJSONObject("response");
                JSONArray groups = responseJSON.getJSONArray("groups");
                JSONObject inGroups = groups.getJSONObject(0);
                JSONArray items = inGroups.getJSONArray("items");

                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemJSON = items.getJSONObject(i);
                    String venueName = itemJSON.getJSONObject("venue").getString("name");
                    String venueId = itemJSON.getJSONObject("venue").getString("id");

                    String venueAddress = itemJSON.getJSONObject("venue").getJSONObject("location").getJSONArray("formattedAddress").toString();
                    FourSquare foursquare = new FourSquare(venueName, venueId, venueAddress);
                    foursquares.add(foursquare);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foursquares;
    }
}