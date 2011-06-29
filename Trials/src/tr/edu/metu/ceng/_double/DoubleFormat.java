package tr.edu.metu.ceng._double;

import java.text.DecimalFormat;

public class DoubleFormat {

	public static void main(String[] args) {

		int growthRate = 11963;
		int totalPopulation = 1130276;

		// define the two-d format.
		DecimalFormat twoDFormat = new DecimalFormat("#.##");
		// find the result.
		double j = (double)growthRate * 1000 / (double)totalPopulation;
		
		System.out.println(j);
		
		System.out.println(Double.valueOf(twoDFormat.format(j)));

	}

}
