package tr.edu.metu.ceng.strbuilder;

public class StringBuilderCapacityTester {

	public static void main(String[] args) {
		// create a string builder.
		StringBuilder builder = new StringBuilder();

		builder.append("123456789103453443");

		builder.setLength(10);
		System.out.println(builder.toString());
	}

}
