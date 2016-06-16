package com.example.manuel.birdsounds;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

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
                appBarLayout.setTitle(Ave.avesStr[Integer.valueOf((String)getArguments().get(ARG_ITEM_ID))]);
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bird_detail, container, false);

        CheckBox mChkBx =(CheckBox)rootView.findViewById(R.id.chkBoxLoop);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        if (getActivity().findViewById(R.id.bird_detail_container) != null) {
            fab.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layOutpar = (RelativeLayout.LayoutParams)mChkBx.getLayoutParams();

            //Para pasar lo valores de dp a px:
            float d = getActivity().getResources().getDisplayMetrics().density;

            layOutpar.setMargins(0,(int)(25* d),0,(int)(15* d));
            mChkBx.setLayoutParams(layOutpar);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }else{
            fab.setVisibility(View.GONE);
            RelativeLayout.LayoutParams layOutpar = (RelativeLayout.LayoutParams)mChkBx.getLayoutParams();
            layOutpar.setMargins(0,0,0,10);
            mChkBx.setLayoutParams(layOutpar);
        }




        mChkBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BirdDetailActivity.chkBxPressed();



            }
        });

        return rootView;
    }
}
