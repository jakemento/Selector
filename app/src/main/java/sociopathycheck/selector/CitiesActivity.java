package sociopathycheck.selector;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CitiesActivity extends AppCompatActivity {

    TextView mLocationTextView;
    ImageView mCityImageView;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        mCityImageView = (ImageView) findViewById(R.id.cityImageView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mLocationTextView.setText(location);
        mCityImageView.setImageAlpha(99);

    }
}
