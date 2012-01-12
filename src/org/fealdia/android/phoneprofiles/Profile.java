package org.fealdia.android.phoneprofiles;

import android.media.AudioManager;

public class Profile {
	private String name;
	private int ringerMode = AudioManager.RINGER_MODE_NORMAL;
	private int vibrateSetting = AudioManager.VIBRATE_SETTING_ON;

	public Profile() {}

	public Profile(String name, int ringerMode, int vibrateSetting) {
		this.name = name;
		this.ringerMode = ringerMode;
		this.vibrateSetting = vibrateSetting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRingerMode() {
		return ringerMode;
	}

	public void setRingerMode(int ringerMode) {
		this.ringerMode = ringerMode;
	}

	public int getVibrateSetting() {
		return vibrateSetting;
	}

	public void setVibrateSetting(int vibrateSetting) {
		this.vibrateSetting = vibrateSetting;
	}
}
