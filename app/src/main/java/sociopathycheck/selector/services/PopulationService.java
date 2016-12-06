package sociopathycheck.selector.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sociopathycheck.selector.Constants;
import sociopathycheck.selector.models.Population;
import sociopathycheck.selector.models.Weather;

/**
 * Created by JS on 12/5/16.
 */




public class PopulationService {
        public static void findPopulation(String cityInfoUrl, Callback callback) {


            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(cityInfoUrl).newBuilder();
            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);

        }

        public ArrayList<Population> processResults(Response response) {
            ArrayList<Population> populations = new ArrayList<>();

            try {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {


                    JSONObject populationJSON = new JSONObject(jsonData);
                    JSONObject embeddedJSON = populationJSON.getJSONObject("_embedded");
                    JSONArray nearestJSON = embeddedJSON.getJSONArray("location:nearest-cities");
                    JSONObject object = nearestJSON.getJSONObject(0);
                    JSONObject embeddedTwo = object.getJSONObject("_embedded");
                    JSONObject locationNearestTwo = embeddedTwo.getJSONObject("location:nearest-city");
                    String populationFinal = locationNearestTwo.getString("population");
                    Population population = new Population(populationFinal);
                    populations.add(population);

//                    String city = weatherJSON.getJSONObject("city").getString("name");
//
//                    String country = weatherJSON.getJSONObject("city").getString("country");
//                    int cityId = weatherJSON.getJSONObject("city").getInt("id");
//                    for (int i = 0; i < weathersJSON.length(); i++) {
//                        JSONObject dayJSON = weathersJSON.getJSONObject(i);
//                        int dayTemp = dayJSON.getJSONObject("temp").getInt("day");
//                        String description = dayJSON.getJSONArray("weather").getJSONObject(0).getString("description");
//                        int weatherId = dayJSON.getJSONArray("weather").getJSONObject(0).getInt("id");
//                        int humidity = dayJSON.getInt("humidity");
//                        int windSpeed = dayJSON.getInt("speed");
//
//
//                        Weather weather = new Weather(city, cityId, country, dayTemp, description, humidity, windSpeed);
//                        weathers.add(weather)
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return populations;
        }





    }
