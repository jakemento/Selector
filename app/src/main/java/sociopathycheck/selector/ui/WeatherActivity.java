package sociopathycheck.selector.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import oauth.signpost.http.HttpResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import sociopathycheck.selector.Constants;
import sociopathycheck.selector.DataAdapter;
import sociopathycheck.selector.R;
import sociopathycheck.selector.adapters.FourSquareListAdapter;
import sociopathycheck.selector.adapters.RestaurantListAdapter;
import sociopathycheck.selector.adapters.SevenListAdapter;
import sociopathycheck.selector.models.DarkSky;
import sociopathycheck.selector.models.FourSquare;
import sociopathycheck.selector.models.Photo;
import sociopathycheck.selector.models.Place;
import sociopathycheck.selector.models.Population;
import sociopathycheck.selector.models.Restaurant;
import sociopathycheck.selector.models.Time;
import sociopathycheck.selector.models.Weather;
import sociopathycheck.selector.services.DarkService;
import sociopathycheck.selector.services.FourSquareService;
import sociopathycheck.selector.services.PlaceService;
import sociopathycheck.selector.services.PopulationService;
import sociopathycheck.selector.services.WeatherService;
import sociopathycheck.selector.services.YelpService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import android.support.v4.app.FragmentActivity;


import android.widget.TextView;



import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    public ArrayList<Population> mPopulations = new ArrayList<>();
    public ArrayList<Weather> mWeathers = new ArrayList<>();

    public ArrayList<DarkSky> mDarkSkies = new ArrayList<>();

    public ArrayList<Time> mTimes = new ArrayList<>();
    public ArrayList<Place> mPlaces = new ArrayList<>();
    private String militaryTime;
    private String latitude;
    private String longitude;
    private String newSearch;
    private String latLong;
    private String photoReference;
    public String weatherIcon;
    private String locationer;
    private String formattedUrl;
    private String myPopulation;
    private int myPopulationInt;
    private String url;
    private String militaryTimeTwo;
    private List<String> militaryArray = new ArrayList<String>();
    private List<String> militaryArrayTwo = new ArrayList<String>();
    public ArrayList<String> recentCities = new ArrayList<String>();
    private String timer;
    private String [] photoArray;
    private boolean isClicked = false;
    private boolean isClickedTwo = false;
    private boolean isClickedThree = false;
    private boolean isClickedFour = false;
    private boolean isClickedFive = false;
    public ArrayList<String> urlStrings = new ArrayList<String>();
    private String cityInfoUrl;
    private String population;
    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    public ArrayList<FourSquare> mFoursquares = new ArrayList<>();


    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;


    @Bind(R.id.locationTextView)
    TextView mLocationTextView;
    @Bind(R.id.searchCities)
    EditText mSearchCities;
    @Bind(R.id.searchCitiesButton)
    Button mSearchCitiesButton;
    @Bind(R.id.listViewTwo)
    ListView mListViewTwo;
    @Bind(R.id.cityTextView)
    TextView mCityTextView;
    @Bind(R.id.temperatureTextView)
    TextView mTemperatureTextView;
    @Bind(R.id.conditionsTextView)
    TextView mConditionsTextView;
    @Bind(R.id.windspeedTextView)
    TextView mWindspeedTextView;
    @Bind(R.id.humidityTextView)
    TextView mHumidityTextView;
    @Bind(R.id.photosButton)
    Button mPhotosButton;
    @Bind(R.id.weatherIconImageView)
    ImageView mWeatherIconImageView;
    @Bind(R.id.populationTextView)
    TextView mPopulationTextView;
    @Bind(R.id.populationTwoTextView)
    TextView mPopulationTwoTextView;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.yelpButton)
    Button mYelpButton;
    @Bind(R.id.venueButton) Button mVenueButton;
    @Bind(R.id.summaryTextView) TextView mSummaryTextView;
    @Bind(R.id.sevenDayButton) Button mSevenDayButton;
    @Bind(R.id.recyclerViewSeven) RecyclerView mRecyclerViewSeven;
    @Bind(R.id.recyclerViewFourSquare) RecyclerView mRecyclerViewFourSquare;




    public ArrayList photo_list = new ArrayList<>();
    private RestaurantListAdapter mAdapter;
    private FourSquareListAdapter mFourAdapter;
    private final OkHttpClient client = new OkHttpClient();
    private SevenListAdapter mSevenAdapter;


    public static final String TAG = WeatherActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        mPopulationTextView.setVisibility(View.INVISIBLE);
        mPopulationTwoTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mYelpButton.setVisibility(View.INVISIBLE);
        mPhotosButton.setVisibility(View.INVISIBLE);
        mSummaryTextView.setVisibility(View.INVISIBLE);
        mSevenDayButton.setVisibility(View.INVISIBLE);
        mRecyclerViewSeven.setVisibility(View.INVISIBLE);
        mVenueButton.setVisibility(View.INVISIBLE);
        mRecyclerViewFourSquare.setVisibility(View.INVISIBLE);
        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);
        mPopulationTextView.setTypeface(quicksand);
        mConditionsTextView.setTypeface(quicksand);
        mHumidityTextView.setTypeface(quicksand);
        mTemperatureTextView.setTypeface(quicksand);
        mWindspeedTextView.setTypeface(quicksand);
        mCityTextView.setTypeface(quicksand);
        mPhotosButton.setTypeface(quicksand);
        mPopulationTwoTextView.setTypeface(quicksand);
        mYelpButton.setTypeface(quicksand);
        mSummaryTextView.setTypeface(quicksand);
        mSevenDayButton.setTypeface(quicksand);
        mVenueButton.setTypeface(quicksand);


        mSearchCities.setText("Portland");

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

        mSevenDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (isClickedThree == true) {
                    mSummaryTextView.setVisibility(View.INVISIBLE);
                    isClickedThree = false;
                } else if (isClickedThree == false) {
                    mSummaryTextView.setVisibility(View.VISIBLE);

                    isClickedThree = true;
                }


                if (isClickedFour == true) {
                    mRecyclerViewSeven
                            .setVisibility(View.INVISIBLE);
                } else if (isClickedFour == false) {

                    mRecyclerViewSeven.setVisibility(View.VISIBLE);

                    isClickedFour = true;
                }
            }
        });
        mYelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClickedTwo == true) {
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    isClickedTwo = false;
                } else if (isClickedTwo == false) {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    isClickedTwo = true;
                }
            }
        });

        mVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClickedFive == true) {
                    mRecyclerViewFourSquare.setVisibility(View.INVISIBLE);
                    isClickedFive = false;
                } else if (isClickedFive == false) {
                    mRecyclerViewFourSquare.setVisibility(View.VISIBLE);

                    isClickedFive = true;
                }
            }
        });


        mSearchCitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhotosButton.setVisibility(View.VISIBLE);
                mYelpButton.setVisibility(View.VISIBLE);
                mSevenDayButton.setVisibility(View.VISIBLE);
                mVenueButton.setVisibility(View.VISIBLE);

                newSearch = mSearchCities.getText().toString();
                mSearchCities.setText("");
                getRestaurants(newSearch);

                getWeathers(newSearch);
                getTimes(newSearch);

                try {
                    geoLocateTwo();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                getPlaces(latLong);


                initViews();
                getDark(latLong);
                getVenues(latLong);

                cityInfoUrl = "https://api.teleport.org/api/locations/" + latLong + "/?embed=location%3Anearest-cities%2Flocation%3Anearest-city";
                getPopulation(cityInfoUrl);
                mLocationTextView.setText(newSearch);

                mPhotosButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        photoArray = urlStrings.toArray(new String[]{});
                        urlStrings.clear();

                        Intent intentPhotos = new Intent(WeatherActivity.this, PhotoActivity.class);
                        intentPhotos.putExtra("photoArray", photoArray);
                        startActivity(intentPhotos);
                    }
                });

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
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {

                        final String weekSummary = getIntent().getStringExtra("weekSummary");
                        mSummaryTextView.setText(weekSummary);

                        for (Weather weather : mWeathers) {
                            weatherIcon = weather.getDescription().toString();
                            mCityTextView.setText(weather.getName());
                            mTemperatureTextView.setText(weather.getTemp() + "˚F");
                            mConditionsTextView.setText(weather.getDescription());
                            mHumidityTextView.setText("humidity: " + weather.getHumidity() + "%");
                            mWindspeedTextView.setText("wind speed: " + weather.getWindSpeed() + "mph");

                            if (weatherIcon.contains("snow")) {
                                mWeatherIconImageView.setImageDrawable(getApplicationContext().getDrawable(R.drawable.snow));
                            }
                            if (weatherIcon.contains("rain")) {
                                mWeatherIconImageView.setImageDrawable(getApplicationContext().getDrawable(R.drawable.rainy));
                            }

                            if (weatherIcon.contains("cloud")) {
                                mWeatherIconImageView.setImageDrawable(getApplicationContext().getDrawable(R.drawable.partialsunny));
                            }
//
                            if (weatherIcon.contains("lightning")) {
                                mWeatherIconImageView.setImageDrawable(getApplicationContext().getDrawable(R.drawable.lightning));
                            }
                            if (weatherIcon.contains("sun") || (weatherIcon.contains("clear sky"))) {
                                mWeatherIconImageView.setImageDrawable(getApplicationContext().getDrawable(R.drawable.sunny));
                            }
                        }

                    }
                });
            }
        });
    }

    private void getTimes(String location) {
        final Weather.TimeService timeService = new Weather.TimeService();

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
                        }
                    }
                });
            }
        });

    }

    private void initViews() {
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
//        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

        ArrayList photoList = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(), photoList);
//        recyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData() {
        String[] photoArray = urlStrings.toArray(new String[]{});

        for (int i = 0; i < photoArray.length; i++) {
            Photo photo = new Photo();
            photo.setPhoto_url(photoArray[i]);
            photo_list.add(photo);

        }
        return photo_list;

    }

    private void getPopulation(String url) {
        final PopulationService populationService = new PopulationService();

        populationService.findPopulation(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mPopulations = populationService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
//
                        for (Population population : mPopulations) {

                            myPopulation = population.getPopulation();
                            myPopulationInt = Integer.parseInt(myPopulation);
                            formattedUrl = NumberFormat.getNumberInstance(Locale.US).format(myPopulationInt);
                            mPopulationTextView.setText(formattedUrl);

                        }
                        mPopulationTwoTextView.setVisibility(View.VISIBLE);
                        mPopulationTextView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    public void geoLocateTwo() throws IOException {
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(newSearch, 1);
        Address add = list.get(0);

        double lat = add.getLatitude();
        double lon = add.getLongitude();

        latitude = String.valueOf(lat);
        longitude = String.valueOf(lon);
        latLong = (latitude + "," + longitude);
    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RestaurantListAdapter(getApplicationContext(), mRestaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void getDark(String latLong) {
        final DarkService darkService = new DarkService();

        darkService.findDark(latLong, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDarkSkies = darkService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mSevenAdapter = new SevenListAdapter(getApplicationContext(), mDarkSkies);
                        mRecyclerViewSeven.setAdapter(mSevenAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerViewSeven.setLayoutManager(layoutManager);
                        mRecyclerViewSeven.setHasFixedSize(true);
                        mSummaryTextView.setText(DarkService.getSummary());
                    }
                });
            }
        });
    }

    private void getVenues(String latLong) {
        final FourSquareService fourSquareService = new FourSquareService();

        fourSquareService.findVenues(latLong, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFoursquares = fourSquareService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mFourAdapter = new FourSquareListAdapter(getApplicationContext(), mFoursquares);
                        mRecyclerViewFourSquare.setAdapter(mFourAdapter);
                        RecyclerView.LayoutManager layoutManagerFour =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerViewFourSquare.setLayoutManager(layoutManagerFour);
                        mRecyclerViewFourSquare.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}















