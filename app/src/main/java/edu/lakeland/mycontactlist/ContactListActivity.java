package edu.lakeland.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    public static final String TAG = "ContactListActivity";
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;
    public static final String CONTACTSAPI = "https://fvtcdp.azurewebsites.net/api/Contact/";
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int contactId = contacts.get(position).getContactID();
            Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
            intent.putExtra("contactId", contactId);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        this.setTitle("Contact List");

        Navbar.initListButton(this);
        Navbar.initMapButton(this);
        Navbar.initSettingsButton(this);

        initAddContactButton();
        initDeleteSwitch();

        ContactDataSource ds = new ContactDataSource(this);

//        String sortBy = "contactname";
//        String sortOrder = "ASC";

        try {
//            ds.open();
//            contacts = ds.getContacts(sortBy, sortOrder);
//            ds.close();

            RestClient.executeGetRequest(CONTACTSAPI, this, new VolleyCallback() {
                        @Override
                        public void onSuccess(ArrayList<Contact> result) {
                            Log.d(TAG, "onSuccess: Contacts : " + result.size());
                            contacts = result;
                            if (contacts.size() > 0) {
                                RecyclerView contactList = findViewById(R.id.rvContacts);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ContactListActivity.this);
                                contactList.setLayoutManager(layoutManager);

                                contactAdapter = new ContactAdapter(contacts, ContactListActivity.this);
                                contactAdapter.setOnItemClickListener(onItemClickListener);
                                contactList.setAdapter(contactAdapter);
                                Log.d(TAG, "onSuccess End");
                            }
                            else {
                                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }

        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                double batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                double levelScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
                int batteryPercent = (int) Math.floor(batteryLevel / levelScale * 100);
                TextView textBatteryState = findViewById(R.id.textBatteryLevel);
                textBatteryState.setText(batteryPercent + "%");
            }
        };

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    private void initAddContactButton() {
        Button newContact = findViewById(R.id.buttonAddContact);
        newContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                contactAdapter.setDelete(status);
                contactAdapter.notifyDataSetChanged();
            }
        });
    }
}