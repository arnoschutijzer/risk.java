/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	private static ResourceBundle RESOURCE_BUNDLE;

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

	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + " does not exist!";
		}
	}
}
