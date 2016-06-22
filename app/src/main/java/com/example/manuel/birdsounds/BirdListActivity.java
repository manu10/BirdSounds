package com.example.manuel.birdsounds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.manuel.birdsounds.dummy.DummyContent;
import com.example.manuel.multidex.mulbirdsounds.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.List;

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_list);
        initialize(getApplicationContext(), "ca-app-pub-3654891208582210~5460911886");


        AdView adView = (AdView) findViewById(R.id.adViewList);
//        adView.setAdSize(AdSize.BANNER);
//
//        adView.setAdSize(AdSize.LARGE_BANNER);
//
//        adView.setAdSize(AdSize.SMART_BANNER);
//
//        adView.setAdSize(AdSize.FULL_BANNER);
//
//        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
//
//        adView.setAdSize(AdSize.LEADERBOARD);
        AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("D4F0E6D693AC1AF89F9FFBF5F155C836")*/.build();
        if (adView != null) {
            adView.loadAd(adRequest);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
  /* TODO: delete this!
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
//
//        View recyclerView = findViewById(R.id.bird_list);
//        assert recyclerView != null;
//        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.bird_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        /* AGREGADO DEL OTRO PROYECTO

         *//* AGREGADO DEL OTRO PROYECTO

         *//* AGREGADO DEL OTRO PROYECTO

         */
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mAveAdapter = new AveAdapter(this, Arrays.asList(avesArr));



        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) findViewById(R.id.aves_grid);
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

//                TODO: delete!
//                Intent intent = new Intent(this, DetailActivity.class);
//                startActivity(intent);
            }
        });
        /* AGREGADO DEL OTRO PROYECTO

         *//* AGREGADO DEL OTRO PROYECTO
        "layout/bird_list.xml" tenia lo siguiente:
        <?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/bird_list"
    android:name="com.example.manuel.pruebamasterdetail.BirdListFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_marginLeft="16dp"
    android:layout_marginRight="16dp"
    app:layoutManager="LinearLayoutManager"
    tools:context="com.example.manuel.pruebamasterdetail.BirdListActivity"
    tools:listitem="@layout/bird_list_content" />

         *//* AGREGADO DEL OTRO PROYECTO

         */

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bird_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(BirdDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        BirdDetailFragment fragment = new BirdDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bird_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BirdDetailActivity.class);
                        intent.putExtra(BirdDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
