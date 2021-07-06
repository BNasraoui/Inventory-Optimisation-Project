package gui;

import java.io.File;

/**
 * This class includes various helper methods used throughout the project
 * 
 * @author Alexander Parker
 *
 */
public class Utils {

	// Modified sample code for using the getExtension method to match only CSV
	// files, retrieved from
	// https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html#filters
	public final static String csv = "csv";

	/*
	 * Get the extension of a file.
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}