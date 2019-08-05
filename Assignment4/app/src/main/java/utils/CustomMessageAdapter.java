package utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CustomMessageAdapter extends BaseAdapter {

    private Context context;
    private DatabaseReference dbChildReferenceFromCallingActivity;
    private FirebaseAuth authenticationRef;
    private ArrayList<DataSnapshot> datasnapshotResultsFromDB;

    public CustomMessageAdapter(Context context, DatabaseReference dbChildReferenceFromCallingActivity) {
        this.context = context;
        this.dbChildReferenceFromCallingActivity = dbChildReferenceFromCallingActivity;

        // I have to set childlistener on DB
        this.dbChildReferenceFromCallingActivity.addChildEventListener(dbChildListener);

        // init ArrayList to hold the DB results
        datasnapshotResultsFromDB = new ArrayList<>();

        // Get the reference of Firebase authentication
        authenticationRef = FirebaseAuth.getInstance();
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
    public Message getItem(int i) {

        //take each item from datasnapshot and convert it to message contents
        DataSnapshot dataSnapshotItemFromList = datasnapshotResultsFromDB.get(i);

        // returns the message contents
        return dataSnapshotItemFromList.getValue(Message.class);
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
            viewHolder.userEmail = view.findViewById(R.id.userEmail);
            viewHolder.messageBody = view.findViewById(R.id.messageBody);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // get the item from DB and set it ot hte views -> title and body
        Message messageDetails = getItem(i);

        // set text for the title and body
        viewHolder.userEmail.setText(messageDetails.getUserEmail());
        viewHolder.messageBody.setText(messageDetails.getMessage());

        // move sender messages to right
        if (messageDetails.getUserEmail().equals(authenticationRef.getCurrentUser().getEmail())) {
            viewHolder.userEmail.setGravity(Gravity.RIGHT);
            viewHolder.messageBody.setGravity(Gravity.RIGHT);
        } else {
            viewHolder.userEmail.setGravity(Gravity.LEFT);
            viewHolder.messageBody.setGravity(Gravity.LEFT);
        }



        return view;
    }

    private static class ViewHolder{
        TextView userEmail;
        TextView messageBody;
    }

}
