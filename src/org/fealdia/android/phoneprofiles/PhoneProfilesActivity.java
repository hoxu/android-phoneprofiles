package org.fealdia.android.phoneprofiles;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

		setListAdapter(new ArrayAdapter<String>(this, R.layout.main, profileManager.getProfileNames()));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text
				CharSequence choice = ((TextView) view).getText();
				Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
				changeProfile(choice.toString());
				showNotification();
			}
		});
	}

	private void changeProfile(String name) {
		profileManager.changeProfile(name);
		Profile profile = profileManager.getCurrentProfile();

		// Change system settings based on profile values
		AudioManager am = getAudioManager();
		am.setRingerMode(profile.getRingerMode());
		am.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER, profile.getVibrateSetting());
		System.out.println("StreamMaxVolume: " + am.getStreamMaxVolume(AudioManager.STREAM_RING));
		am.setStreamVolume(AudioManager.STREAM_RING, (profile.getRingerVolume() * am.getStreamMaxVolume(AudioManager.STREAM_RING)) / 100, 0);
		am.setStreamMute(AudioManager.STREAM_NOTIFICATION, profile.isMuteNotifications());

		finish();
	}

	private AudioManager getAudioManager() {
		return (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	private void showNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		int icon = android.R.drawable.star_on;
		CharSequence tickerText = "Hello"; // ticker-text
		long when = System.currentTimeMillis(); // notification time
		Context context = getApplicationContext(); // application Context
		CharSequence contentTitle = "Phone Profiles"; // message title
		CharSequence contentText = "Active profile: " + profileManager.getCurrentProfileName(); // message text

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