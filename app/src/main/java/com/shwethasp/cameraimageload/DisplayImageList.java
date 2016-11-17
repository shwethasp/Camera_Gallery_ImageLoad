package com.shwethasp.cameraimageload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by shwethap on 29-06-2016.
 */
public class DisplayImageList extends AppCompatActivity {

    // private ImageAdapter imageAdapter;
    public static ListView mListview;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image_list);
        activity = this;
       final DBHelper db = new DBHelper(this);
        final ArrayList<String> allimage = db.getAllImages();
        Log.d("", String.valueOf(db.getData()));

        mListview = (ListView) findViewById(R.id.imagelist);
        final ImageAdapter imageAdapter = new ImageAdapter(this, allimage, activity);

        // Attach the adapter to a ListView
        mListview.setAdapter(imageAdapter);
        //  mProgressDialog.dismiss();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  ImageView image = (ImageView) view.findViewById(R.id.itemImage);

                //enlargeProfilePicture(view,position);
                Intent i = new Intent(DisplayImageList.this,ZoomImageActivity.class);
                i.putExtra("position",position);
                startActivity(i);
                   /* Toast.makeText(getApplicationContext(),
                            "Click ListItem Number " + position, Toast.LENGTH_LONG)
                            .show();*/
            }
        });

    }
}
