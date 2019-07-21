package utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3.R;

import java.util.ArrayList;

public class CustomBookAdapter extends BaseAdapter {

    // Receive the context from BookList Fragment
    Context context;

    // ArrayList with the data points that are to be populated on my items
    ArrayList<BookInfoAdapterItem> bookItems;

    public CustomBookAdapter(Context context, ArrayList<BookInfoAdapterItem> bookItems) {
        this.context = context;
        this.bookItems = bookItems;
    }

    @Override
    public int getCount() {
        return bookItems.size();
    }

    @Override
    public Object getItem(int i) {
        return bookItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Initialize ViewHolder
        ViewHolder viewHolder;

        if(view == null) {
            // Create and return the view
            view = View.inflate(context, R.layout.book_info_adapter_item, null);

            // Create an object of viewHolder -> get hold of my child view references
            viewHolder = new ViewHolder();

            viewHolder.imageView = view.findViewById(R.id.bookImgAdapterItem);
            viewHolder.textView = view.findViewById(R.id.bookNameAdapterItem);

            // Link the viewHolder to my view
            view.setTag(viewHolder);
        } else {
            // If it already exists then restore the viewHolder
            viewHolder = (ViewHolder) view.getTag();
        }

        // Override the values of the child views -> Based on the input from the MainActivity
        viewHolder.imageView.setImageResource(bookItems.get(i).getImageResID());
        viewHolder.textView.setText(bookItems.get(i).getName());

        return view;

    }

    // Class to hold my child views
    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
