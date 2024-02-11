package edu.lakeland.mycontactlist;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class ContactSettingsActivity extends AppCompatActivity {

    public static final String TAG = "ContactSettingsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        String bgColor = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("bgColor","darkGray");
        ScrollView svObject = findViewById(R.id.svObject);
        if (bgColor == "yellow"){
            svObject.setBackgroundResource(R.color.bgYellow);
        }
        else if (bgColor == "teal"){
            svObject.setBackgroundResource(R.color.bgTeal);
        }
        else {
            svObject.setBackgroundResource(R.color.bgDarkGray);
        }


        this.setTitle("Contact Settings");

        Navbar.initListButton(this);
        Navbar.initMapButton(this);
        Navbar.initSettingsButton(this);
        initSettings();
        initSortByClick();
        initSortOrderClick();
        initBGColorClick();
        Log.d(TAG, "onCreate: ");
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbAscending = findViewById(R.id.radioAscending);
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").apply();
                }
            }
        });
    }

    private void initSortByClick() {
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbName = findViewById(R.id.radioName);
                RadioButton rbCity = findViewById(R.id.radioCity);
                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "contactname").apply();
                }
                else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "city").apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").apply();
                }
            }
        });
    }

    private void initBGColorClick() {
        RadioGroup radioGroupBGColor = findViewById(R.id.radioGroupBGColor);
        radioGroupBGColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbYellow = findViewById(R.id.rbSelectYellow);
                RadioButton rbTeal = findViewById(R.id.rbSelectTeal);
                RadioButton rbDarkGray = findViewById(R.id.rbSelectDarkGray);
                if (rbYellow.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("bgColor", "yellow").apply();
                }
                else if (rbTeal.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("bgColor", "teal").apply();
                }
                else if (rbDarkGray.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("bgColor", "darkGray").apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("bgColor", "darkGray").apply();
                }
            }
        });
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield","contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder","ASC");
        String bgColor = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("bgColor","darkGray");

        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthDay = findViewById(R.id.radioBirthday);
        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        }
        else {
            rbBirthDay.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);
        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }

        RadioButton rbYellow = findViewById(R.id.rbSelectYellow);
        RadioButton rbTeal = findViewById(R.id.rbSelectTeal);
        RadioButton rbDarkGray = findViewById(R.id.rbSelectDarkGray);
        if (bgColor.equalsIgnoreCase("yellow")) {
            rbYellow.setChecked(true);
        }
        else if (bgColor.equalsIgnoreCase("teal")) {
            rbTeal.setChecked(true);
        }
        else {
            rbDarkGray.setChecked(true);
        }

    }
}