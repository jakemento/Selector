package sociopathycheck.selector;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CitiesActivity extends AppCompatActivity {

   private TextView mLocationTextView;
    ImageView mCityImageView;
    Button mBackButton;
    private String savedLocation;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        mCityImageView = (ImageView) findViewById(R.id.cityImageView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mBackButton = (Button) findViewById(R.id.backButton);

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        savedLocation = location;



        mLocationTextView.setText(location);
        mCityImageView.setImageAlpha(99);


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