package com.f.firebaserealtimedatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.f.firebaserealtimedatabase.models.Tweet;

import java.util.List;

/**
 * Created by Furkan on 6.12.2017.
 */

public class CustomAdapter extends BaseAdapter {
    List<Tweet> tweetList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Activity activity, List<Tweet> tweetList) {
        this.layoutInflater = ( LayoutInflater ) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tweetList = tweetList;
    }

    @Override
    public int getCount() {
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        return tweetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = layoutInflater.inflate(R.layout.row_layout,null);
        TextView message = ( TextView ) view.findViewById( R.id.textViewMessage );
        TextView userName = ( TextView ) view.findViewById( R.id.textViewUserName );

        Tweet tweet = tweetList.get(position);

        userName.setText(tweet.getName());
        message.setText(tweet.getMessage());

        return view;
    }
}
