package org.fealdia.android.phoneprofiles;

import android.media.AudioManager;

public class Profile {
	private String name;
	private int ringerMode = AudioManager.RINGER_MODE_NORMAL;

	public Profile() {}

	public Profile(String name, int ringerMode) {
		this.name = name;
		this.ringerMode = ringerMode;
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
}
