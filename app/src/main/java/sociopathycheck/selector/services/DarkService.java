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
import sociopathycheck.selector.models.DarkSky;
import sociopathycheck.selector.models.Restaurant;
import sociopathycheck.selector.models.Weather;

/**
 * Created by JS on 12/8/16.
 */

public class DarkService {
    public static final String TAG = DarkService.class.getSimpleName();


    public static void findDark(String latLong, Callback callback) {
        String DARKSKY_API_KEY = Constants.DARKSKY_API_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DARKSKY_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString()+DARKSKY_API_KEY+"/"+latLong+"?exclude=currently,minutely";

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
    public static ArrayList<DarkSky> processResults(Response response) {
        ArrayList<DarkSky> darkskies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject darkJSON = new JSONObject(jsonData);
                JSONObject dailyJSON = darkJSON.getJSONObject("daily");
                JSONArray dataArray = dailyJSON.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject sevenJSON = dataArray.getJSONObject(i);
                    int preDate = (sevenJSON.getInt("time"));
                    String date = String.valueOf(preDate);
                    String summary = sevenJSON.getString("summary");
                    int tempPreHigh = sevenJSON.getInt("temperatureMax");
                    String tempHigh = String.valueOf(tempPreHigh);
                    int tempPreLow = sevenJSON.getInt("temperatureMin");
                    String tempLow = String.valueOf(tempPreLow);

                    DarkSky darksky = new DarkSky(summary, date, tempHigh, tempLow);
                    darkskies.add(darksky);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return darkskies;
    }

}

