package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by adewijanugraha on 8/24/17.
 */

// Merupakan sebuah class custom adapter yang digunakan untuk mempersiapkan data yg ada pada NumbersActivity.java
// yang akan dipetakan pada listItem.xml

public class ListAdapter extends ArrayAdapter<List> {

    private int mColorResourceId;

    public ListAdapter(Activity context, ArrayList<List> list, int colorResourceId) {
        super(context, 0, list);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Periksa apakah tampilan yang ada digunakan kembali, jika tidak, inflate the view

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate( R.layout.list_item, parent, false);
        }

        // Dapatkan objek yang berada pada posisi ini dalam daftar
        List currentList = getItem(position);

        // Temukan TextView di layout list_item.xml dengan ID defaultLanguage
        TextView bahasaLanguage = (TextView) listItemView.findViewById(R.id.bahasa);

        // Dapatkan nama dari objek defaultLanguage saat ini dan
        // set teks ini dengan nama TextView
        bahasaLanguage.setText(currentList.getmBahasa());

        TextView englishLanguage = (TextView) listItemView.findViewById(R.id.english);

        englishLanguage.setText(currentList.getmEnglish());

        ImageView imageResource = (ImageView) listItemView.findViewById(R.id.imageResouce);

        if (currentList.hasImage()){
            // Mengatur image view sesuai dengan gambar resource nya
            imageResource.setImageResource(currentList.getmImageResource());

            // Memastikan image view terlihat
            imageResource.setVisibility(View.VISIBLE);
        } else {
            // membuat image view menjadi hilang/pergi dalam view
            imageResource.setVisibility(View.GONE);
        }

        // Menentukan id textContainer itu yg mana
        View textContainer = listItemView.findViewById(R.id.textContainer);

        // Menyimpan warna yang ingin digunakan pada variable color
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Memasang warna pada view yg memiliki id textContainer
        textContainer.setBackgroundColor(color);

        // Return seluruh daftar item layout (berisi 2 TextViews dan ImageView)
        // sehingga bisa ditampilkan di ListView
        return listItemView;
    }
}
