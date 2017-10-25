package com.example3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Furkan on 25.10.2017.
 */

public class CustomAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<User>     mKisiListesi;

        public CustomAdapter(Activity activity, List<User> kisiler) {
            //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
            mInflater = (LayoutInflater) activity.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            //gösterilecek listeyi de alalım
            mKisiListesi = kisiler;
        }

        @Override
        public int getCount() {
            return mKisiListesi.size();
        }

        @Override
        public User getItem(int position) {
            //şöyle de olabilir: public Object getItem(int position)
            return mKisiListesi.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View satirView;

            satirView = mInflater.inflate(R.layout.row_layout, null);
            TextView textView =
                    (TextView) satirView.findViewById(R.id.textViewFullName);
            ImageView imageView =
                    (ImageView) satirView.findViewById(R.id.imageView);

            User kisi = mKisiListesi.get(position);

            textView.setText(kisi.getFullName());

            if (kisi.isOgrenciMi()) {
                imageView.setImageResource(R.mipmap.ic_launcher_school);
            }
            else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            return satirView;
        }
    }

