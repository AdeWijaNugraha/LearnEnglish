package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        final ArrayList<List> family = new ArrayList<>();

        family.add(new List("Grand Father","Mbah Kakung", R.drawable.family_grandfather, R.raw.family_grandfather));
        family.add(new List("Grand Mother","Mbah Uti", R.drawable.family_grandmother, R.raw.family_grandmother));
        family.add(new List("Father","Ayah", R.drawable.family_father, R.raw.family_father));
        family.add(new List("Mother","Ibu", R.drawable.family_mother, R.raw.family_mother));
        family.add(new List("Daughter","Anak Perempuan", R.drawable.family_daughter, R.raw.family_daughter));
        family.add(new List("Son","Anak Laki-laki", R.drawable.family_son, R.raw.family_son));
        family.add(new List("Older Brother","Kakak Laki-laki", R.drawable.family_older_brother, R.raw.family_older_brother));
        family.add(new List("Older Sister","Kakak Perempuan", R.drawable.family_older_sister, R.raw.family_older_sister));
        family.add(new List("Young Brother","Adik Laki-laki", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        family.add(new List("Young Sister","Adik Perempuan", R.drawable.family_younger_sister, R.raw.family_younger_sister));

        ListAdapter itemAdapter = new ListAdapter(this, family, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();

                List list = family.get(position);

                mediaPlayer = MediaPlayer.create(FamilyActivity.this, list.getmAudioResource());
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
