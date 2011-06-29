package tr.edu.metu.ceng.q1;

public class Answer1 {

	public static void main(String[] args) {
		double n = Double.parseDouble(args[0]);

		double x;
		int intY;
		double y;
		int found = 0;
		x = 0l;
		for (double i = n + 1; i <= n * 2 || i < 10000000; i++) {
			x = i;
			y = x * n / (x - n);

			intY = (int) y;

			if (intY - y == 0 && x != intY) {
				found++;
				System.out.println("x: " + x + " y: " + intY);
			}
		}

	}
}
