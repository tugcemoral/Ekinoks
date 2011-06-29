package tr.edu.metu.ceng.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleDisplayer {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("lang",
				Locale.getDefault());
		
		System.out.println(bundle);
		
	}

}