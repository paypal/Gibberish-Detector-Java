package com.paypal.gibberishdetector;



import java.util.Random;

import com.paypal.gibberishdetector.GibberishDetector;


public class GibberishDetectorTestUtils {

	private static final String WHITE_SPACE = " ";

	public static void randomGibberishTest(String alphabet, GibberishDetector gibberishDetector) {
		Random rand = new Random();
		double correctTagging = 0d;
		double inCorrectTagging = 0d;
		for (int i = 0; i < 1000; i++) {
			int numOfWords = rand.nextInt(10) + 2;
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < numOfWords; j++) {
				for (int c = 0; c < rand.nextInt(10) + 3; c++) {
					line.append(alphabet.charAt(rand.nextInt(alphabet.length()-1)));
				}
				line.append(WHITE_SPACE);
			}
			if (gibberishDetector.isGibberish(line.toString().trim())) {
				correctTagging++;
				System.out.println("correct: " + line);
			}
			else {
				inCorrectTagging++;
				System.out.println("failed to tag correctly: " + line);
			}
		}
		System.out.println("correct tagging percentage: " + correctTagging/1000);
		System.out.println("incorrect tagging percentage: " + inCorrectTagging/1000);
	}

}
