package com.example.manuel.birdsounds;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.AndroidCharacter;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.manuel.multidex.mulbirdsounds.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * An activity representing a single Bird detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BirdListActivity}.
 */
public class BirdDetailActivity extends AppCompatActivity {
//    private static MediaPlayer mediaPlayer;
//    private boolean reproduciendo=false;
//    private static boolean isLoop=false;
//    private boolean isPaused=false;
//    private boolean activityPaused=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        AdView adView = (AdView) findViewById(R.id.adViewList);
        AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("D4F0E6D693AC1AF89F9FFBF5F155C836")*/.build();
        if (adView != null) {
            adView.loadAd(adRequest);
        }

        int id=Integer.valueOf(getIntent().getStringExtra(BirdDetailFragment.ARG_ITEM_ID));
//TODO: delete this!
//        mediaPlayer = MediaPlayer.create(getBaseContext(), Ave.avesSounds[id]);//mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.condor_short);
        setAppBarBckground(id);
        setSupportActionBar(toolbar);
//TODO: delete this!
//        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (reproduciendo){
//                    if (mediaPlayer.isPlaying()){
//                        reproduciendo=false;
//                        mediaPlayer.pause();
//                        isPaused=true;
//                        fab.setImageResource(android.R.drawable.ic_media_play);
//                    }
//                }else {
//                    fab.setImageResource(android.R.drawable.ic_media_pause);
//
//                    reproduciendo = true;
//                    Snackbar.make(view, "Comenzo a reproducir el sonido", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    if (!isPaused){
//
//                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                            @Override
//                            public void onCompletion(MediaPlayer mp) {
//                                if (!isLoop){
//                                    fab.setImageResource(android.R.drawable.ic_media_play);
//                                    reproduciendo=false;
//                                }
//                            }
//                        });
//                    }
//                    mediaPlayer.setLooping(isLoop);
//
//                    mediaPlayer.start();
//                }
//            }
//        });


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        // CheckBox mChkBx = chkBoxLoop
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(BirdDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(BirdDetailFragment.ARG_ITEM_ID));
            BirdDetailFragment fragment = new BirdDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bird_detail_container, fragment)
                    .commit();
        }

    }
    @TargetApi(16)
    private void setAppBarBckground(int id) {
        AppBarLayout appBarLayout= (AppBarLayout) findViewById(R.id.app_bar);
        if (appBarLayout != null) {
            appBarLayout.setBackground(getResources().getDrawable(Ave.avesImg[id]));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if ((mediaPlayer!=null)&&(mediaPlayer.isPlaying())) {
//            activityPaused = true;
//            mediaPlayer.pause();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(activityPaused)
//            mediaPlayer.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, BirdListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public static void chkBxPressed() {
//        isLoop=!isLoop;
//        if (mediaPlayer!=null){
//            mediaPlayer.setLooping(isLoop);
//        }
//    }
}
