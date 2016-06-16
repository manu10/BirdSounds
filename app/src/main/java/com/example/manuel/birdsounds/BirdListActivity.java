package com.example.manuel.birdsounds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;

import static com.google.android.gms.ads.MobileAds.initialize;

/**
 * An activity representing a list of Birds. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BirdDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BirdListActivity extends AppCompatActivity {

    private AveAdapter mAveAdapter;

    Ave[] avesArr = {
            new Ave("Bigua",R.drawable.bigua_main),
            new Ave("Cauquen",R.drawable.cauquen_main),
            new Ave("Choique patagonico",R.drawable.choique_patagonico_main),
            new Ave("Condor",R.drawable.condor_main),
            new Ave("Flamenco",R.drawable.flamengo_main),
            new Ave("garcita blanca",R.drawable.garcita_blanca_main),
            new Ave("maca comun",R.drawable.maca_comun_main),
            new Ave("Martineta",R.drawable.martineta_main),
            new Ave("Ostrero austral",R.drawable.ostrero_austral_main)

    };
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_list);
        initialize(getApplicationContext(), "ca-app-pub-3654891208582210~5460911886");


        AdView adView = (AdView) findViewById(R.id.adViewList);
        AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("D4F0E6D693AC1AF89F9FFBF5F155C836")*/.build();
        adView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
  /* 
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        if (findViewById(R.id.bird_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }


        mAveAdapter = new AveAdapter(this, Arrays.asList(avesArr));



        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) findViewById(R.id.aves_grid);
        assert gridView != null;
        gridView.setAdapter(mAveAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(BirdDetailFragment.ARG_ITEM_ID, Long.toString(id));
                    BirdDetailFragment fragment = new BirdDetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bird_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, BirdDetailActivity.class);
                    intent.putExtra(BirdDetailFragment.ARG_ITEM_ID, Long.toString(id));

                    context.startActivity(intent);
                }

            }
        });

    }

}
