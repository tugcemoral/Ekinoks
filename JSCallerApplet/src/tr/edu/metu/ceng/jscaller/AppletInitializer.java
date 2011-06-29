package tr.edu.metu.ceng.jscaller;

import javax.swing.JApplet;

import netscape.javascript.JSObject;

public class AppletInitializer extends JApplet {

	private static final long serialVersionUID = -3323200774636714719L;

	@Override
	public void init() {
		String eiaID = getParameter("eiaID");
		String eiaProjectName = getParameter("eiaProjectName");
//		System.out.println(eiaID);
//		System.out.println(eiaProjectName);
		JSObject window = JSObject.getWindow(this);
		window.call("doAlertEiaInfo", new Object[] { eiaID, eiaProjectName });
	}

}
