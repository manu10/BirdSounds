package com.example.manuel.birdsounds;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.example.manuel.birdsounds.dummy.DummyContent;
import com.example.manuel.multidex.mulbirdsounds.R;

/**
 * A fragment representing a single Bird detail screen.
 * This fragment is either contained in a {@link BirdListActivity}
 * in two-pane mode (on tablets) or a {@link BirdDetailActivity}
 * on handsets.
 */
public class BirdDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = BirdDetailFragment.class.toString();

    private boolean activityPaused=false;

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    private static MediaPlayer mediaPlayer;
    private boolean reproduciendo=false;
    private static boolean isLoop=false;
    private boolean isPaused=false;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BirdDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                //appBarLayout.setTitle(mItem.content);
                appBarLayout.setTitle(Ave.avesStr[Integer.valueOf((String)getArguments().get(ARG_ITEM_ID))]); //TODO:change it!!
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bird_detail, container, false);

        CheckBox mChkBx =(CheckBox)rootView.findViewById(R.id.chkBoxLoop);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        if (getActivity().findViewById(R.id.aves_grid) != null) {
            fab.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layOutpar = (RelativeLayout.LayoutParams)mChkBx.getLayoutParams();

            //Para pasar lo valores de dp a px:
            float d = getActivity().getResources().getDisplayMetrics().density;

            layOutpar.setMargins(0,(int)(25* d),0,(int)(15* d));
            mChkBx.setLayoutParams(layOutpar);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
        }else{
            fab.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layOutpar = (RelativeLayout.LayoutParams)mChkBx.getLayoutParams();
            layOutpar.setMargins(0,0,0,10);
            mChkBx.setLayoutParams(layOutpar);
        }
        int id=Integer.valueOf(getArguments().getString(BirdDetailFragment.ARG_ITEM_ID));
        mediaPlayer = MediaPlayer.create(getActivity(), Ave.avesSounds[id]);
        final FloatingActionButton fab1;
        if (getActivity().findViewById(R.id.aves_grid) == null){
            fab1=(FloatingActionButton) getActivity().findViewById(R.id.fabAct);
//            Log.v(TAG,"fab1 = (FloatingActionButton) rootView.findViewById(R.id.fabAct);");
        }else {
            fab1 =    fab;
//            Log.v(TAG,"fab1=    fab;");
        }
        if (fab1!=null) {
//            Log.v(TAG,"fab1!=null");

            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (reproduciendo) {
                        if (mediaPlayer.isPlaying()) {
                            reproduciendo = false;
                            mediaPlayer.pause();
                            isPaused = true;
                            fab1.setImageResource(android.R.drawable.ic_media_play);
                        }
                    } else {
                        fab1.setImageResource(android.R.drawable.ic_media_pause);

                        reproduciendo = true;
                        Snackbar.make(view, "Comenzo a reproducir el sonido", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        if (!isPaused) {

                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if (!isLoop) {
                                        fab1.setImageResource(android.R.drawable.ic_media_play);
                                        reproduciendo = false;
                                    }
                                }
                            });
                        }
                        mediaPlayer.setLooping(isLoop);

                        mediaPlayer.start();
                    }
                }
            });


        }
//        else
//            Log.v(TAG,"fab1==null");

        mChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BirdDetailFragment.chkBxPressed();



            }
        });
        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.bird_detail)).setText(mItem.details);
//        }

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((mediaPlayer!=null)&&(mediaPlayer.isPlaying())) {
            activityPaused = true;
            mediaPlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        super.onResume();
        if(activityPaused)
            mediaPlayer.start();
    }

    public static void chkBxPressed() {
        isLoop=!isLoop;
        if (mediaPlayer!=null){
            mediaPlayer.setLooping(isLoop);
        }
    }
}
