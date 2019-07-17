package utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exercise5.R;

import java.util.ArrayList;

public class CustomCharacterAdapter extends BaseAdapter {

    // Receive the context from main activity
    Context context;

    // ArrayList with the data points that are to be populated on my items that I'm creating
    ArrayList<CharacterInfoAdapterItem> characterItems;

    public CustomCharacterAdapter(Context context, ArrayList<CharacterInfoAdapterItem> characterItems) {
        this.context = context;
        this.characterItems = characterItems;
    }

    // Total number of items to be displayed on the listview
    @Override
    public int getCount() {
        return characterItems.size();
    }

    // Extracts the data of the item at specific location in the list
    @Override
    public Object getItem(int i) {
        return characterItems.get(i);
    }

    // Extracts the item id on the adapter; this returns long
    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Returns the view of items in the list view
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Initialize View holder
        ViewHolder viewHolder;

        if(view == null) {
            // Create and return the view
            view = View.inflate(context, R.layout.character_info_adapter_item, null);

            // Create an object of viewHolder -> get hold of my child view references
            viewHolder = new ViewHolder();

            viewHolder.imageView = view.findViewById(R.id.characterImgAdapterItem);
            viewHolder.textView = view.findViewById(R.id.characterNameAdapterItem);

            // Link the viewHolder to my view
            view.setTag(viewHolder);
        } else {
            // If it already exists then restore the viewHolder -> from this I can access my image and text view
            viewHolder = (ViewHolder) view.getTag();
        }

        // Grab the child view from your root view (Linear Layout)
//        ImageView imageView = view.findViewById(R.id.characterImgAdapterItem);
//        TextView textView = view.findViewById(R.id.characterNameAdapterItem);

        // Override the values of the child views -> Based on the input from the MainActivity
        viewHolder.imageView.setImageResource(characterItems.get(i).getImageResID());
        viewHolder.textView.setText(characterItems.get(i).getCharacterName());

        return view;
    }

    // Class to hold my child views
    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
