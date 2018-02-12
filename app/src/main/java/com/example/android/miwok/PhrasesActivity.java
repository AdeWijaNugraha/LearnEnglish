package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        final ArrayList<List> phrases = new ArrayList<>();

        phrases.add(new List("Where are you going?","Kemana kamu pergi ?", R.raw.phrase_where_are_you_going));
        phrases.add(new List("What is your name?","Siapa namamu ?", R.raw.phrase_what_is_your_name));
        phrases.add(new List("My name is Shifa","Nama saya Shifa", R.raw.phrase_my_name_is));
        phrases.add(new List("How are you feeling?","Bagaimana perasaanmu ?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new List("I’m feeling good.","Saya merasa baik", R.raw.phrase_im_feeling_good));
        phrases.add(new List("Are you coming?","Apakah kamu datang ?", R.raw.phrase_are_you_coming));
        phrases.add(new List("Yes, I’m coming.","Iya, saya datang", R.raw.phrase_yes_im_coming));
        phrases.add(new List("I’m coming.","Saya datang", R.raw.phrase_im_coming));
        phrases.add(new List("Let’s go.","Ayo pergi", R.raw.phrase_lets_go));
        phrases.add(new List("Come here.","Kemarilah", R.raw.phrase_come_here));
        //phrases.add(new List("Love Kiki", ));

        ListAdapter itemsAdapter = new ListAdapter(this, phrases, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                List list = phrases.get(position);

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, list.getmAudioResource());
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
