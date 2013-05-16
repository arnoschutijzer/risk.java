package gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * This class keeps track of the selected resourcebundle.
 * 
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class Messages {

	private static ResourceBundle RESOURCE_BUNDLE;
	/**
	 * Default constructor for Messages.
	 * Based on the paramater 'bundle' a specific resourcebundle will me used.
	 * @param bundle
	 */
	public void setResourceBundle(String bundle) {
		switch(bundle){
		case "Dutch":
			RESOURCE_BUNDLE = ResourceBundle.getBundle("gui.messages_nl");
			break;
		case "French":
			RESOURCE_BUNDLE = ResourceBundle.getBundle("gui.messages_fr");
			break;
		case "English":
			RESOURCE_BUNDLE = ResourceBundle.getBundle("gui.messages_en");
			break;
		}	
	}
	/**
	 * 
	 * @param key
	 * @return A String based on a keyword in the resourcebundle.
	 */
	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + " does not exist!";
		}
	}
}
