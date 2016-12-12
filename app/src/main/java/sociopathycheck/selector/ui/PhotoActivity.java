package sociopathycheck.selector.ui;

import android.app.Activity;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.Photo;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends Activity {
    public static final String TAG = PhotoActivity.class.getSimpleName();

    @Bind(R.id.photoViewOne)
    PhotoView mPhotoViewOne;
    @Bind(R.id.photoViewTwo)
    PhotoView mPhotoViewTwo;
    @Bind(R.id.photoViewThree)
    PhotoView mPhotoViewThree;
    @Bind(R.id.photoViewFour)
    PhotoView mPhotoViewFour;
    @Bind(R.id.photoViewFive)
    PhotoView mPhotoViewFive;
    @Bind(R.id.photoViewSix)
    PhotoView mPhotoViewSix;
    @Bind(R.id.photoViewSeven)
    PhotoView mPhotoViewSeven;
    @Bind(R.id.photoViewEight)
    PhotoView mPhotoViewEight;


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

                    final PhotoViewAttacher attacher = new PhotoViewAttacher(mPhotoViewOne);

                    Picasso.with(this)
                            .load(photoOne)
                            .into(mPhotoViewOne, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacher.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewOne.setVisibility(View.INVISIBLE);
                }

                if (photoTwo.contains("http")) {
                    final PhotoViewAttacher attacherTwo = new PhotoViewAttacher(mPhotoViewTwo);

                    Picasso.with(this)
                            .load(photoTwo)
                            .into(mPhotoViewTwo, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherTwo.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewTwo.setVisibility(View.INVISIBLE);
                }

                if (photoThree.contains("http")) {
                    final PhotoViewAttacher attacherThree = new PhotoViewAttacher(mPhotoViewThree);

                    Picasso.with(this)
                            .load(photoThree)
                            .into(mPhotoViewThree, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherThree.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                 } else {
                    mPhotoViewThree.setVisibility(View.INVISIBLE);
                }

                if (photoFour.contains("http")) {
                    final PhotoViewAttacher attacherFour = new PhotoViewAttacher(mPhotoViewFour);

                    Picasso.with(this)
                            .load(photoFour)
                            .into(mPhotoViewFour, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherFour.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewFour.setVisibility(View.INVISIBLE);
                }

                if (photoFive.contains("http")) {
                    final PhotoViewAttacher attacherFive = new PhotoViewAttacher(mPhotoViewFive);

                    Picasso.with(this)
                            .load(photoFive)
                            .into(mPhotoViewFive, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherFive.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewFive.setVisibility(View.INVISIBLE);
                }
                if (photoSix.contains("http")) {
                    final PhotoViewAttacher attacherSix = new PhotoViewAttacher(mPhotoViewSix);

                    Picasso.with(this)
                            .load(photoSix)
                            .into(mPhotoViewSix, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherSix.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewSix.setVisibility(View.INVISIBLE);

                }
                if (photoSeven.contains("http")) {
                    final PhotoViewAttacher attacherSeven = new PhotoViewAttacher(mPhotoViewSeven);

                    Picasso.with(this)
                            .load(photoSeven)
                            .into(mPhotoViewSeven, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherSeven.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewSeven.setVisibility(View.INVISIBLE);

                }
                if (photoEight.contains("http")) {
                    final PhotoViewAttacher attacherEight = new PhotoViewAttacher(mPhotoViewEight);

                    Picasso.with(this)
                            .load(photoEight)
                            .into(mPhotoViewEight, new Callback() {
                                @Override
                                public void onSuccess() {
                                    attacherEight.update();
                                }

                                @Override
                                public void onError() {
                                }
                            });
                } else {
                    mPhotoViewEight.setVisibility(View.INVISIBLE);

                }
    }


}








