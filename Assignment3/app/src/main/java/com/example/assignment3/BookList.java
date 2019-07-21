package com.example.assignment3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import utils.BookInfoAdapterItem;
import utils.CustomBookAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookList extends Fragment {

    public BookList() {
        // Required empty public constructor
    }

    ArrayList<BookInfoAdapterItem> books = new ArrayList<BookInfoAdapterItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        GridView gridView = view.findViewById(R.id.bookListFragmentGridView);

        // Populate Book Array
        populateBookList();

        // Create array adapter
        CustomBookAdapter myAdapter = new CustomBookAdapter(getContext(), books);

        // Connect the adapter to the gridView
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Create a bundle to store the data
                Bundle bundle = new Bundle();
                bundle.putInt("BookName", books.get(i).getName());
                bundle.putInt("BookImgID", books.get(i).getImageResID());
                bundle.putInt("BookDescription", books.get(i).getDescription());

                // Create an object of second fragment and pass in bundle data
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);

                // Switch the fragments
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Enter the data for replacement
                transaction.replace(R.id.fragmentContainer, detailsFragment);

                // Commit the changes
                transaction.commit();

            }
        });

        return view;
    }

    private void populateBookList() {
        books.add(new BookInfoAdapterItem(R.string.book1_name, R.drawable.img1, R.string.book1_description));
        books.add(new BookInfoAdapterItem(R.string.book2_name, R.drawable.img2, R.string.book2_description));
        books.add(new BookInfoAdapterItem(R.string.book3_name, R.drawable.img3, R.string.book3_description));
        books.add(new BookInfoAdapterItem(R.string.book4_name, R.drawable.img4, R.string.book4_description));
        books.add(new BookInfoAdapterItem(R.string.book5_name, R.drawable.img5, R.string.book5_description));
        books.add(new BookInfoAdapterItem(R.string.book6_name, R.drawable.img6, R.string.book6_description));
        books.add(new BookInfoAdapterItem(R.string.book7_name, R.drawable.img7, R.string.book7_description));
        books.add(new BookInfoAdapterItem(R.string.book8_name, R.drawable.img8, R.string.book8_description));
        books.add(new BookInfoAdapterItem(R.string.book9_name, R.drawable.img9, R.string.book9_description));
        books.add(new BookInfoAdapterItem(R.string.book10_name, R.drawable.img10, R.string.book10_description));
        books.add(new BookInfoAdapterItem(R.string.book11_name, R.drawable.img11, R.string.book11_description));
        books.add(new BookInfoAdapterItem(R.string.book12_name, R.drawable.img12, R.string.book12_description));
        books.add(new BookInfoAdapterItem(R.string.book13_name, R.drawable.img13, R.string.book13_description));
        books.add(new BookInfoAdapterItem(R.string.book14_name, R.drawable.img14, R.string.book14_description));
        books.add(new BookInfoAdapterItem(R.string.book15_name, R.drawable.img15, R.string.book15_description));
    }

}
