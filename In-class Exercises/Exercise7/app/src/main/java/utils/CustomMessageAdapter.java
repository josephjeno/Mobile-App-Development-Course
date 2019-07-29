package utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise7.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CustomMessageAdapter extends BaseAdapter {

    private Context context;
    private DatabaseReference dbChildReferenceFromCallingActivity;
    private ArrayList<DataSnapshot> datasnapshotResultsFromDB;

    public CustomMessageAdapter(Context context, DatabaseReference dbChildReferenceFromCallingActivity) {
        this.context = context;
        this.dbChildReferenceFromCallingActivity = dbChildReferenceFromCallingActivity;

        // I have to set childlistener on DB
        this.dbChildReferenceFromCallingActivity.addChildEventListener(dbChildListener);

        // init ArrayList to hold the DB results
        datasnapshotResultsFromDB = new ArrayList<>();
    }

    // set an event listener on the child
    ChildEventListener dbChildListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            datasnapshotResultsFromDB.add(dataSnapshot);
            // update the adapter view
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public int getCount() {
        return datasnapshotResultsFromDB.size();
    }

    @Override
    public MessageContents getItem(int i) {

        //take each item from datasnapshot and convert it to message contents
        DataSnapshot dataSnapshotItemFromList = datasnapshotResultsFromDB.get(i);

        // returns the message contents
        return dataSnapshotItemFromList.getValue(MessageContents.class);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null) {
            view = View.inflate(context, R.layout.message_list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.messageTitle = view.findViewById(R.id.messageTitle);
            viewHolder.messageBody = view.findViewById(R.id.messageBody);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // get the item from DB and set it ot hte views -> title and body
        MessageContents messageDetails = getItem(i);
        // set text for the title and body
        viewHolder.messageTitle.setText(messageDetails.getMessageTitle());
        viewHolder.messageBody.setText(messageDetails.getMessageBody());

        return view;
    }

    private static class ViewHolder{
        TextView messageTitle;
        TextView messageBody;
    }
}
