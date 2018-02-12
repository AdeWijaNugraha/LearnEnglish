package com.example.android.miwok;

/**
 * Created by adewijanugraha on 8/24/17.
 */
// Merupakan sebuah Class yang mengelola isi dari yang sudah didefinisikan pada list_item.xml

public class List {

    private String mBahasa;

    private String mEnglish;

    private int mAudioResource;

    private int mImageResource = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    public List(String mEnglish, String mBahasa, int mImageResource, int mAudioResource) {
        this.mEnglish = mEnglish;
        this.mBahasa = mBahasa;
        this.mImageResource = mImageResource;
        this.mAudioResource = mAudioResource;
    }

    public List(String mEnglish, String mBahasa, int mAudioResource) {
        this.mEnglish = mEnglish;
        this.mBahasa = mBahasa;
        this.mAudioResource = mAudioResource;
    }

    public String getmBahasa() { return mBahasa; }

    public String getmEnglish() { return mEnglish; }

    public int getmImageResource() { return mImageResource; }

    public int getmAudioResource() { return mAudioResource; }

    public boolean hasImage(){
        boolean image = false;
        if (mImageResource != NO_IMAGE_PROVIDED){
            image = true;
        }
        return image;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mBahasa='" + mBahasa + '\'' +
                ", mEnglish='" + mEnglish + '\'' +
                ", mAudioResourceId=" + mAudioResource +
                ", mImageResourceId=" + mImageResource +
                '}';
    }
}
