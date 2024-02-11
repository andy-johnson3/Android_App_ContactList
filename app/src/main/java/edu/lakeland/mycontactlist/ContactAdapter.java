package edu.lakeland.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter{
    private ArrayList<Contact> contactData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public static final String TAG = "MyContactList";

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteButton;
        public ImageButton imageButtonPhoto;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textContactName);
            textPhone = itemView.findViewById(R.id.textPhoneNumber);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);
            imageButtonPhoto = itemView.findViewById(R.id.imageContact);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getContactTextView() {
            return textViewContact;
        }
        public TextView getPhoneTextView() {
            return textPhone;
        }
        public ImageButton getImageButtonPhoto() {
            return imageButtonPhoto;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public ContactAdapter(ArrayList<Contact> arrayList, Context context) {
        contactData = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getContactTextView().setText(contactData.get(position).getContactName());
        cvh.getPhoneTextView().setText(contactData.get(position).getPhoneNumber());
        if (isDeleting) {
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else {
            cvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
        if(contactData.get(position).getPicture() != null)
        {
            //Bind the picture.
            cvh.getImageButtonPhoto().setImageBitmap(contactData.get(position).getPicture());
        }
    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }

    private void deleteItem(int position) {
        Contact contact = contactData.get(position);
        int id = contact.getContactID();
        ContactDataSource ds = new ContactDataSource(parentContext);
        try {
//            ds.open();
//            boolean didDelete = ds.deleteContact(contact.getContactID());
//            ds.close();

//            boolean didDelete =false;

            RestClient.executeDeleteRequest(contact,
                    ContactListActivity.CONTACTSAPI + contact.getContactID(),
                    this.parentContext,
                    new VolleyCallback() {
                        @Override
                        public void onSuccess(ArrayList<Contact> result) {
                            Log.d(TAG, "onSuccess: executeDeleteRequest");
                            contactData.remove(position);
                            notifyDataSetChanged();
                        }
                    });

//            if (didDelete) {
//                contactData.remove(position);
//                notifyDataSetChanged();
//            }
//            else {
//                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
//            }

        }
        catch (Exception e) {

        }
    }

    public void setDelete(boolean b) {
        isDeleting = b;
    }
}
