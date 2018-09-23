package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{

    /** Resource ID for the background color for the list of words*/
    private int mColorResourceId;
    private MediaPlayer mMediaPlayer;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        //we can pass in zero because we are inflating the layout ourselves in the get view method.
        //inflating means it will be rendered be creating view object in memory.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view.
     * @param convertView The recycled view to populate
     *                    (search online for "android view recycling" to learn more)
     * @param parent    The parent ViewGroup that is used for inflation.
     * @return  The View for the position in the AdapterView
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise infalte the view.
        // This is just mainually turning the xml file views into actual view objects
        // Get a listItemView that was recycled and provided for us. if one is not there
        //the we create one from the xml file (list_item.xml).



        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@Link Word} object located at this position in the list
        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);

        // Get the text from the current Word object and set this text on the view
        miwokTextView.setText(currentWord.getMiwokTranslation());
        defaultTextView.setText(currentWord.getDefaultTranslation());

        if (currentWord.hasImage()) {
            image.setImageResource(currentWord.getmImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        return listItemView;
    }
}
