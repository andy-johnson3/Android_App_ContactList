package edu.lakeland.mycontactlist;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class Navbar {
    public static void initSettingsButton(Activity activity){

        ImageButton ibSettings = activity.findViewById(R.id.imageButtonSettings);
        setupListenerEvent(ibSettings, activity, ContactSettingsActivity.class);

    }
    public static void initMapButton(Activity activity){

        ImageButton ibMap = activity.findViewById(R.id.imageButtonMap);
        setupListenerEvent(ibMap, activity, ContactMapActivity.class);

    }
    public static void initListButton(Activity activity){

        ImageButton ibList = activity.findViewById(R.id.imageButtonList);
        setupListenerEvent(ibList, activity, ContactListActivity.class);

    }
    private static void setupListenerEvent(ImageButton imageButton, Activity activity, Class<?> destinationActivityClass) {

        // Disable the appropriate imagebutton
        imageButton.setEnabled(activity.getClass() != destinationActivityClass);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to the destination activity
                Intent intent = new Intent(activity, destinationActivityClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });
    }
}
