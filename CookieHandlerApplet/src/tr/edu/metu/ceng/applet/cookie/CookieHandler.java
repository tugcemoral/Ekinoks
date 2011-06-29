package tr.edu.metu.ceng.applet.cookie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextField;

import netscape.javascript.JSObject;

public class CookieHandler extends JApplet implements ActionListener,
		Serializable {

	private static final long serialVersionUID = -1030230214076481435L;

	private JTextField tf1, tf2;
	private JButton writer, reader, deletor;
//	, serializer;

	public void init() {
		tf1 = new JTextField(20);
		tf2 = new JTextField(20);

		writer = new JButton("Write Cookie");
		reader = new JButton("Read Cookie");
		deletor = new JButton("Delete Coookie");
//		serializer = new JButton("Serialize Applet");

		setLayout(new FlowLayout());
		add(tf1);
		add(tf2);
		add(writer);
		add(reader);
		add(deletor);
//		add(serializer);

		writer.addActionListener(this);
		reader.addActionListener(this);
		deletor.addActionListener(this);
//		serializer.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == writer) {
			writeCookie(tf1.getText());
		}
		if (ae.getSource() == reader) {
			tf2.setText(readCookie());
		}
		if (ae.getSource() == deletor) {
			deleteCookie(tf1.getText());
		}
//		if (ae.getSource() == serializer) {
			// serializeApplet();
//		}
	}

	private void serializeApplet() {
		// first, serialize the applet...
		FileOutputStream fos;
		try {
			File serializedFile = new File(
					"/home/tugcem/Desktop/cookieHandler.ser");
			fos = new FileOutputStream(serializedFile);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.reset();
			out.writeObject(this);
			out.close();
			System.out.println("Done...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteCookie(String text) {
		/*
		 * * delete a cookie, set the expiration in the past
		 */
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.add(java.util.Calendar.MONTH, -1);
		String expires = "; expires=" + c.getTime().toString();

		String s1 = text + expires;
		JSObject myBrowser = JSObject.getWindow(this);
		JSObject myDocument = (JSObject) myBrowser.getMember("document");
		myDocument.setMember("cookie", s1);
	}

	private void writeCookie(String text) {
		// write a cookie* computes the expiration date, good for 1 month
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.add(java.util.Calendar.MONTH, 1);
		String expires = "; expires=" + c.getTime().toString();

		String s1 = text + expires;
		System.out.println(s1);

		JSObject myBrowser = JSObject.getWindow(this);
		JSObject myDocument = (JSObject) myBrowser.getMember("document");

		myDocument.setMember("cookie", s1);
	}

	public String readCookie() {
		// get all cookies for a document
		try {
			JSObject myBrowser = (JSObject) JSObject.getWindow(this);
			JSObject myDocument = (JSObject) myBrowser.getMember("document");
			String myCookie = (String) myDocument.getMember("cookie");
			if (myCookie.length() > 0)
				return myCookie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "?";
	}

	public String getCookie(String name) {
		/*
		 * * get a specific cookie by its name, parse the cookie.* not used in
		 * this Applet but can be useful
		 */
		String myCookie = readCookie();
		String search = name + "=";
		if (myCookie.length() > 0) {
			int offset = myCookie.indexOf(search);
			if (offset != -1) {
				offset += search.length();
				int end = myCookie.indexOf(";", offset);
				if (end == -1)
					end = myCookie.length();
				return myCookie.substring(offset, end);
			} else
				System.out.println("Did not find cookie: " + name);
		}
		return "";
	}

	@Override
	public void destroy() {
		serializeApplet();
		super.destroy();
	}

}