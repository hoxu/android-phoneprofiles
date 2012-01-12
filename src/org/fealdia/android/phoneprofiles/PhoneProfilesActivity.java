package org.fealdia.android.phoneprofiles;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneProfilesActivity extends ListActivity {
	private int MAIN_NOTIFICATION_ID = 1;
	private ProfileManager profileManager = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		profileManager = new ProfileManager();
		showNotification();

		setListAdapter(new ArrayAdapter<String>(this, R.layout.main, profileManager.getProfiles()));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text
				CharSequence choice = ((TextView) view).getText();
				Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
				profileManager.changeProfile(choice.toString());
				showNotification();
			}
		});
	}

	private void showNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// int icon = R.drawable.notification_icon; // icon from resources
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Hello"; // ticker-text
		long when = System.currentTimeMillis(); // notification time
		Context context = getApplicationContext(); // application Context
		CharSequence contentTitle = "Phone Profiles"; // message title
		CharSequence contentText = "Active profile: " + profileManager.getCurrentProfile(); // message text

		Intent notificationIntent = new Intent(this, PhoneProfilesActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		// the next two lines initialize the Notification, using the
		// configurations above
		Notification notification = new Notification(icon, tickerText, when);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		mNotificationManager.notify(MAIN_NOTIFICATION_ID, notification);
	}
}