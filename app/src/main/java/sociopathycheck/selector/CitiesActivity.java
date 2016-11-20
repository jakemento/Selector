package sociopathycheck.selector;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import android.widget.TextView;
import android.widget.Toast;

public class CitiesActivity extends AppCompatActivity {

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.backButton) Button mBackButton;
    public static final String TAG = CitiesActivity.class.getSimpleName();

    private String[] cities = new String[] {"Arcata", "Portland", "Eugene", "St. Petersburg", "Kiev", "Aleppo", "Cairo", "Minsk", "Pinsk", "Toonville", "Rio", "Berlin", "Bejing", "Moscow", "New York", "Denver", "Paris", "Rome", "Bangkok"};
    ImageView mCityImageView;
    private String savedLocation;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cities);

        mListView.setAdapter(adapter);


        mCityImageView = (ImageView) findViewById(R.id.cityImageView);
        mBackButton = (Button) findViewById(R.id.backButton);

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        savedLocation = location;

        mLocationTextView.setText(location);
        Log.d(TAG, "In the onCreate method!");


        mCityImageView.setImageAlpha(99);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city = ((TextView)view).getText().toString();
                Toast.makeText(CitiesActivity.this, city, Toast.LENGTH_SHORT).show();
                Log.v(TAG, "In the onItemClickListener!");
            }
        });





        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentTwo = new Intent(CitiesActivity.this, MainActivity.class);
                intentTwo.putExtra("Savedlocation",savedLocation);
                startActivity(intentTwo);

            }
        });
    }
}