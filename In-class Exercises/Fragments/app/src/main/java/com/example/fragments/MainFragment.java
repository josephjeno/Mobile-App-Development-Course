package com.example.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    // Data source to put into my array adapter
    ArrayList<String> character = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Create the contents of this fragment
        ListView listView = view.findViewById(R.id.mainFragmentListView);
        character.add("Batman");
        character.add("Thanos");
        character.add("Ironman");

        // Create an array adapter
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, character);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Create a bundle to store the data
                Bundle bundle = new Bundle();
                bundle.putString("CharacterName", character.get(i));

                // Create an object of second frag -> pass in bundle data
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);

                // FSwitch the fragments
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // enter the data for replacement
                transaction.replace(R.id.fragmentContainer, detailsFragment);

                // Commit the changes
                transaction.commit();
            }
        });

        return view;
    }

}
