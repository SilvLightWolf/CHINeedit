package com.mitologia.chin.lib;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Validator {

	public static boolean is_url(String url) {
		try {
			URL testURL = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static boolean is_file(String path) {
		File file = new File(path);
		return file.exists();
	}


}
