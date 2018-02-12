package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private  MediaPlayer mediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<List> number = new ArrayList<List>();

        number.add(new List("One","Satu",R.drawable.number_one,R.raw.number_one));
        number.add(new List("Two","Dua",R.drawable.number_two,R.raw.number_two));
        number.add(new List("Three","Tiga",R.drawable.number_three,R.raw.number_three));
        number.add(new List("Four","Empat",R.drawable.number_four,R.raw.number_four));
        number.add(new List("Five","Lima",R.drawable.number_five,R.raw.number_five));
        number.add(new List("Six","Enam",R.drawable.number_six,R.raw.number_six));
        number.add(new List("Seven","Tujuh",R.drawable.number_seven,R.raw.number_seven));
        number.add(new List("Eight","Delapan",R.drawable.number_eight,R.raw.number_eight));
        number.add(new List("Nine","Sembilan",R.drawable.number_nine,R.raw.number_nine));
        number.add(new List("Ten","Sepuluh",R.drawable.number_ten,R.raw.number_ten));

        // Melihat Isi dari array dengan menggunakan log pada android monitor
//        for (int i = 0 ; i < 10 ; i++){
//            Log.v("NumberActivity", "Liat index " + (i+1) + " isinya " + lists.get(i));
//        }

        // Menampilkan list dengan cara menggunakan linear layout
//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
//        int i = 0;
//        while ( i < lists.size()){
//            TextView wordView = new TextView(this);
//            wordView.setText(lists.get(i));
//            rootView.addView(wordView);
//            i++;
//        }

        // Menampilkan list dengan cara menggunakan listView + ArrayAdapter
        ListAdapter itemsAdapter = new ListAdapter(this, number, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Mendapatkan posisi dari List
                List list = number.get(position);

                // Membuat serta menghubungkan object MediaPlayer dan menentukan lagu yg sesuai
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, list.getmAudioResource());

                //Log.v("NumbersActivity", "Media Player = " + mediaPlayer);
                // Start the audio file
                mediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mediaPlayer.setOnCompletionListener(mCompletionListener);
                //Log.v("NumbersActivity", "Media Player = " + mediaPlayer);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
