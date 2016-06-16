package com.example.manuel.birdsounds;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manuel on 30/04/16.
 */
public class AveAdapter extends ArrayAdapter<Ave> {
    public AveAdapter(FragmentActivity activity, List<Ave> aves) {//Ojo que aca lo dejo como por defecto (Activity context)
        super(activity,0,aves);
    }
    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Ave ave = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.ave_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.ave_image);
        iconView.setImageResource(ave.image);

        TextView versionNameView = (TextView) convertView.findViewById(R.id.ave_text);
        versionNameView.setText(ave.aveName);

        return convertView;
    }
}
