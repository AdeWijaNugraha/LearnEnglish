package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private  MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<List> color = new ArrayList<>();

        color.add(new List("Black","Hitam", R.drawable.color_black, R.raw.color_black));
        color.add(new List("Brown","Coklat", R.drawable.color_brown, R.raw.color_brown));
        color.add(new List("Dusty Yellow","Kuning Berdebu", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        color.add(new List("Gray","Abu-Abu", R.drawable.color_gray, R.raw.color_gray));
        color.add(new List("Green","Hijau", R.drawable.color_green, R.raw.color_green));
        color.add(new List("Yellow","Kuning", R.drawable.color_mustard_yellow, R.raw.color_yellow));
        color.add(new List("Red","Merah", R.drawable.color_red, R.raw.color_red));
        color.add(new List("White","Putih", R.drawable.color_white, R.raw.color_white));

        ListAdapter itemAdapter = new ListAdapter(this, color, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                List list = color.get(position);

                mediaPlayer = MediaPlayer.create(ColorsActivity.this, list.getmAudioResource());

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
