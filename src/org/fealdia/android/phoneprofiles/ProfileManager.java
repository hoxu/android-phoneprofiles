package org.fealdia.android.phoneprofiles;

public class ProfileManager {
	private String[] PROFILES = { "General", "Meeting", "Night", "Silent" };
	private String currentProfile;

	public void changeProfile(String profile) {
		this.currentProfile = profile;
	}

	public String getCurrentProfile() {
		return currentProfile;
	}

	public String[] getProfiles() {
		return PROFILES;
	}
}
