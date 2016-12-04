package sociopathycheck.selector.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import sociopathycheck.selector.Constants;
import sociopathycheck.selector.DataAdapter;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.Photo;
import sociopathycheck.selector.models.Place;
import sociopathycheck.selector.models.Time;
import sociopathycheck.selector.models.Weather;
import sociopathycheck.selector.services.PlaceService;
import sociopathycheck.selector.services.TimeService;
import sociopathycheck.selector.services.WeatherService;

import android.widget.TextView;
import android.widget.Toast;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    public ArrayList<Weather> mWeathers = new ArrayList<>();
    public ArrayList<Time> mTimes = new ArrayList<>();
    public ArrayList<Place> mPlaces = new ArrayList<>();

    private String militaryTime;

    private String latitude;
    private String longitude;
    private String latLong;
    private String photoReference;
    private String url;
    private String militaryTimeTwo;
    private List<String> militaryArray = new ArrayList<String>();
    private List<String> militaryArrayTwo = new ArrayList<String>();
    public ArrayList<String> recentCities = new ArrayList<String>();


    private String timer;
    private boolean isClicked = false;

    public ArrayList<String> urlStrings = new ArrayList<String>();
//    public String photoArray[] = urlStrings.toArray(new String[] {});

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listViewTwo) ListView mListViewTwo;
    @Bind(R.id.backButton) Button mBackButton;
    @Bind(R.id.cityTextView) TextView mCityTextView;
    @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
    @Bind(R.id.conditionsTextView) TextView mConditionsTextView;
    @Bind(R.id.windspeedTextView) TextView mWindspeedTextView;
    @Bind(R.id.humidityTextView) TextView mHumidityTextView;
    @Bind(R.id.photosButton) Button mPhotosButton;
//    public String[] photoArray = urlStrings.toArray(new String[] {});
    public ArrayList photo_list = new ArrayList<>();




//    @Bind(R.id.testImageView) ImageView mTestImageView;

    public static final String TAG = WeatherActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");
        recentCities.add(location);

        getWeathers(location);
        getTimes(location);
        try {
            geoLocate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getPlaces(latLong);

        //IT HAS TO GO HERE!!!!
//        String photoArray[] = urlStrings.toArray(new String[] {});



        initViews();





        //sets up a typeface from the fonts folder & sets textview to it
        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);
        mLocationTextView.setText(location);


        //makes the image transparent
//        mCityImageView.setImageAlpha(99);
        //


        mListViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (isClicked == true) {
                    changeTime();
                    isClicked = false;
                } else if (isClicked == false) {
                    changeTimeBack();
                    isClicked = true;
                }
            }
        });

        //tells the back button to go back and send intent with it
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentTwo = new Intent(WeatherActivity.this, MainActivity.class);

                //added the value of savedLocation string
                intentTwo.putExtra("location", location);
                recentCities.add(location);
                startActivity(intentTwo);
            }
        });

        mPhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] photoArray = urlStrings.toArray(new String[] {});


                Intent intentPhotos = new Intent(WeatherActivity.this, PhotoActivity.class);

                //added the value of savedLocation string
                intentPhotos.putExtra("photoArray", photoArray );
                startActivity(intentPhotos);
            }
        });
    }


    private void getWeathers(String location) {
        final WeatherService weatherService = new WeatherService();

        weatherService.findWeathers(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mWeathers = weatherService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this,
//                                android.R.layout.simple_list_item_1);
//                        mListView.setAdapter(adapter);

                        for (Weather weather : mWeathers) {
//
                            mCityTextView.setText(weather.getName());
                            mTemperatureTextView.setText("temp: " + weather.getTemp() + "˚");
                            mConditionsTextView.setText(weather.getDescription());
                            mHumidityTextView.setText("humidity: " + weather.getHumidity());
                            mWindspeedTextView.setText("wind speed: " + weather.getWindSpeed() + "mph");
                        }
                    }
                });
            }
        });
    }

    private void getTimes(String location) {
        final TimeService timeService = new TimeService();

        timeService.findTimes(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mTimes = timeService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] times = new String[mTimes.size()];
                        for (int i = 0; i < times.length; i++) {
                            times[i] = mTimes.get(i).getTime();
                            militaryTimeTwo = times[i];
                            times[i] = times[i].substring(times[i].lastIndexOf(" "));


                            times[i] = times[i].replaceAll("[:]", "");

                            timer = times[i];
                            militaryTime = timer;

                            DateTimeFormatter inputFormatter = DateTimeFormat.forPattern(" HHmm");
                            DateTimeFormatter outputFormatter = DateTimeFormat.forPattern(" hh:mm a");
                            DateTime dateTime = inputFormatter.parseDateTime(timer);
                            String formattedTimer = outputFormatter.print(dateTime.getMillis());
                            times[i] = formattedTimer;
                        }

                        ArrayAdapter adapterTwo = new ArrayAdapter(WeatherActivity.this,
                                R.layout.list_item, R.id.item_text, times);
                        mListViewTwo.setAdapter(adapterTwo);
                    }
                });
            }
        });
    }

    private void changeTime() {

        militaryArray.add(militaryTime);

        ArrayAdapter adapterThree = new ArrayAdapter(WeatherActivity.this, R.layout.list_item, R.id.item_text, militaryArray);
        mListViewTwo.setAdapter(adapterThree);
        isClicked = true;
    }

    private void changeTimeBack() {

        DateTimeFormatter inputFormatter = DateTimeFormat.forPattern(" HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormat.forPattern(" hh:mm a");
        DateTime dateTime = inputFormatter.parseDateTime(timer);
        militaryTimeTwo = outputFormatter.print(dateTime.getMillis());

        militaryArrayTwo.add(militaryTimeTwo);
        ArrayAdapter adapterFour = new ArrayAdapter(WeatherActivity.this, R.layout.list_item, R.id.item_text, militaryArrayTwo);
        mListViewTwo.setAdapter(adapterFour);
    }


    public void geoLocate() throws IOException {
        Intent intent = getIntent();
        String locationer = intent.getStringExtra("location");

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(locationer, 1);
        Address add = list.get(0);
        String locality = add.getLocality();

        double lat = add.getLatitude();
        double lon = add.getLongitude();

        latitude = String.valueOf(lat);
        longitude = String.valueOf(lon);

        latLong = (latitude + "," + longitude);

//        mTestTextView.setText(latLong);
    }




    private void getPlaces(String latLong) {
        final PlaceService placeService = new PlaceService();

        placeService.findPlace(latLong, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mPlaces = placeService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//
                        for (Place place : mPlaces) {

                            photoReference = place.getPlace();
//                            Log.d(TAG, photoReference);
                            String PLACE_API_KEY = Constants.PLACES_API_KEY;
                            url = Constants.PHOTOS_BASE_URL + "&key=" + PLACE_API_KEY + "&photoreference=" + photoReference;
                            urlStrings.add(url);
//                            Log.d(TAG, urlStrings.toString());


//                                Picasso.with(mTestImageView.getContext()).load(url).into(mTestImageView);

                        }

//                        Log.d(TAG, urlStrings.toString());

                    }
                });
            }
        });

    }



    private void initViews(){
//        Log.d(TAG, photoArray.toString());


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList photoList = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),photoList);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData(){
        String[] photoArray = urlStrings.toArray(new String[] {});

        for(int i=0;i<photoArray.length;i++){
            Photo photo = new Photo();
            photo.setPhoto_url(photoArray[i]);
            photo_list.add(photo);

        }
        return photo_list;

    }

}










