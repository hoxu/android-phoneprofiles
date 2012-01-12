package org.fealdia.android.phoneprofiles;

import java.util.LinkedList;
import java.util.List;

public class ProfileManager {
	private String currentProfile;
	private List<Profile> profiles = new LinkedList<Profile>();

	public ProfileManager() {
		profiles.add(new Profile("General"));
		profiles.add(new Profile("Meeting"));
		profiles.add(new Profile("Night"));
		profiles.add(new Profile("Silent"));
	}

	public void changeProfile(String profile) {
		this.currentProfile = profile;
	}

	public String getCurrentProfile() {
		return currentProfile;
	}

	public String[] getProfileNames() {
		List<String> names = new LinkedList<String>();
		for (Profile profile : profiles) {
			names.add(profile.getName());
		}
		return names.toArray(new String[0]);
	}
}
