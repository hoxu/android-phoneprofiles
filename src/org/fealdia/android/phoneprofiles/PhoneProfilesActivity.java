package org.fealdia.android.phoneprofiles;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PhoneProfilesActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showNotification();
		setContentView(R.layout.main);
	}

	private void showNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// int icon = R.drawable.notification_icon; // icon from resources
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "Hello"; // ticker-text
		long when = System.currentTimeMillis(); // notification time
		Context context = getApplicationContext(); // application Context
		CharSequence contentTitle = "My notification"; // message title
		CharSequence contentText = "Hello World!"; // message text

		Intent notificationIntent = new Intent(this, PhoneProfilesActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		// the next two lines initialize the Notification, using the
		// configurations above
		Notification notification = new Notification(icon, tickerText, when);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		notification.flags |= Notification.FLAG_ONGOING_EVENT;

		mNotificationManager.notify(1, notification);
	}
}