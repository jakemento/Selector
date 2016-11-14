package sociopathycheck.selector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


private Button mFindWeatherButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     mFindWeatherButton = (Button) findViewById(R.id.findWeatherButton);
     mFindWeatherButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_LONG).show();
         }
     });


    }
}
