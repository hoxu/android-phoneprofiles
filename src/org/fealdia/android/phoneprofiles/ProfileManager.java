package org.fealdia.android.phoneprofiles;

import java.util.LinkedList;
import java.util.List;

import android.media.AudioManager;

public class ProfileManager {
	private Profile currentProfile;
	private List<Profile> profiles = new LinkedList<Profile>();

	public ProfileManager() {
		profiles.add(new Profile("General", AudioManager.RINGER_MODE_NORMAL, AudioManager.VIBRATE_SETTING_ON));
		profiles.add(new Profile("Meeting", AudioManager.RINGER_MODE_VIBRATE, AudioManager.VIBRATE_SETTING_ON));
		profiles.add(new Profile("Night", AudioManager.RINGER_MODE_NORMAL, AudioManager.VIBRATE_SETTING_OFF));
		profiles.add(new Profile("Silent", AudioManager.RINGER_MODE_SILENT, AudioManager.VIBRATE_SETTING_OFF));
	}

	public void changeProfile(String profile) {
		this.currentProfile = getProfile(profile);
	}

	public Profile getCurrentProfile() {
		return currentProfile;
	}

	public String getCurrentProfileName() {
		if (currentProfile == null) {
			return null;
		}
		return currentProfile.getName();
	}

	private Profile getProfile(String name) {
		for (Profile profile : profiles) {
			if (profile.getName().equals(name)) {
				return profile;
			}
		}
		return null;
	}

	public String[] getProfileNames() {
		List<String> names = new LinkedList<String>();
		for (Profile profile : profiles) {
			names.add(profile.getName());
		}
		return names.toArray(new String[0]);
	}
}
