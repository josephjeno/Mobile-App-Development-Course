package com.example.assignment3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Grab the data from previous fragment
        int bookName = getArguments().getInt("BookName");
        int bookImage = getArguments().getInt("BookImgID");
        int bookDescription = getArguments().getInt("BookDescription");

        // Set views
        ImageView bookImageView = view.findViewById(R.id.bookImageView);
        TextView bookNameTextView = view.findViewById(R.id.bookNameTextView);
        TextView bookDescriptionTextView = view.findViewById(R.id.bookDescriptionTextView);

        bookImageView.setImageResource(bookImage);
        bookNameTextView.setText(bookName);
        bookDescriptionTextView.setText(bookDescription);

        return view;
    }

}
