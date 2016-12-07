package sociopathycheck.selector.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.Photo;

public class PhotoActivity extends AppCompatActivity {
    public static final String TAG = PhotoActivity.class.getSimpleName();
    @Bind(R.id.photoOne)
    ImageView mPhotoOne;
    @Bind(R.id.photoTwo)
    ImageView mPhotoTwo;
    @Bind(R.id.photoThree)
    ImageView mPhotoThree;
    @Bind(R.id.photoFour)
    ImageView mPhotoFour;
    @Bind(R.id.photoFive)
    ImageView mPhotoFive;
    @Bind(R.id.photoSix)
    ImageView mPhotoSix;
    @Bind(R.id.photoSeven)
    ImageView mPhotoSeven;
    @Bind(R.id.photoEight)
    ImageView mPhotoEight;




    @Bind(R.id. emptyPhotosTextView)
    TextView mEmptyPhotosTextView;

    public String photoOne = "";
    public String photoTwo = "";
    public String photoThree = "";
    public String photoFour = "";
    public String photoFive = "";
    public String photoSix = "";
    public String photoSeven = "";
    public String photoEight = "";
    private boolean zoomOut =  false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);



        final String[] PhotoArray = getIntent().getStringArrayExtra("photoArray");



        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

                if (PhotoArray.length == 0) {
                    mEmptyPhotosTextView.setText("No Photos To Display");
                }

                if (PhotoArray.length > 0) {
                        photoOne = PhotoArray[0];
                    }

                if (PhotoArray.length > 1) {
                    photoTwo = PhotoArray[1];
                }

                if (PhotoArray.length > 2) {
                     photoThree = PhotoArray[2];
                }

                if (PhotoArray.length > 3) {
                    photoFour = PhotoArray[3];
                }

                if (PhotoArray.length > 4) {
                    photoFive = PhotoArray[4];
                }
                if (PhotoArray.length > 5) {
                    photoSix = PhotoArray[5];
                }

                if (PhotoArray.length > 6) {
                    photoSeven = PhotoArray[6];
                }

                if (PhotoArray.length > 7) {
                    photoEight = PhotoArray[7];
                 }

                ImageLoader.getInstance().init(config);
                ImageLoader imageLoader = ImageLoader.getInstance();


                if (photoOne.contains("http")) {
                    imageLoader.displayImage(photoOne, mPhotoOne);
                } else {
                    mPhotoOne.setVisibility(View.INVISIBLE);
                }

                if (photoTwo.contains("http")) {
                    imageLoader.displayImage(photoTwo, mPhotoTwo);
                } else {
                    mPhotoTwo.setVisibility(View.INVISIBLE);
                }

                if (photoThree.contains("http")) {
                    imageLoader.displayImage(photoThree, mPhotoThree);
                 } else {
                    mPhotoThree.setVisibility(View.INVISIBLE);
                }

                if (photoFour.contains("http")) {
                    imageLoader.displayImage(photoFour, mPhotoFour);
                } else {
                    mPhotoFour.setVisibility(View.INVISIBLE);
                }

                if (photoFive.contains("http")) {
                    imageLoader.displayImage(photoFive, mPhotoFive);
                } else {
                    mPhotoFive.setVisibility(View.INVISIBLE);
                }
                if (photoSix.contains("http")) {
                    imageLoader.displayImage(photoSix, mPhotoSix);
                } else {
                    mPhotoSix.setVisibility(View.INVISIBLE);

                }
                if (photoSeven.contains("http")) {
                    imageLoader.displayImage(photoSeven, mPhotoSeven);
                } else {
                    mPhotoSeven.setVisibility(View.INVISIBLE);

                }
                if (photoEight.contains("http")) {
                    imageLoader.displayImage(photoEight, mPhotoEight);
                } else {
                    mPhotoEight.setVisibility(View.INVISIBLE);

                }
    }


}








