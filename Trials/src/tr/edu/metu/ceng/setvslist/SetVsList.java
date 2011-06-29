package tr.edu.metu.ceng.setvslist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class SetVsList {

	public static void main(String[] args) {
		// expand set and list contents.
		expandSet();
		expandList();

	}

	private static void expandList() {
		// create a set...
		List<String> stringList = new Vector<String>();

		for (int i = 0; i < 150; i++) {
			stringList.add(String.valueOf(i));
		}

		System.out.println("list' s size: " + stringList.size());

		for (String string : stringList) {
			System.out.println(string);
		}
	}

	private static void expandSet() {
		// create a set...
		Set<String> stringSet = new HashSet<String>();

		for (int i = 0; i < 150; i++) {
			// add 150 contents into set.
			stringSet.add(String.valueOf(i));
		}

		System.out.println("set's size: " + stringSet.size());

		for (String string : stringSet) {
			System.out.println(string);
		}
		
	}

}
