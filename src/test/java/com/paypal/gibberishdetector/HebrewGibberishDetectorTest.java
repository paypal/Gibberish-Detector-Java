package com.paypal.gibberishdetector;

import org.junit.Assert;
import org.junit.Test;

import com.paypal.gibberishdetector.GibberishDetector;
import com.paypal.gibberishdetector.GibberishDetectorFactory;

public class HebrewGibberishDetectorTest {
	
	private String[] goodHebrewSentences = {"שלום קוראים לי שיר", "היום שלישי היום, מחר יהיה יום רביעי", "שלומית בונה סוכה מוארת וירוקה"};
	private String[] badHebrewSentences = {"גע גכידגעי דכעדכי רק", "רט כגעעיצכ עשדעש געיח", "יל חי ךחי חיהך חי אבסק סקסקב"};
	private static final String alphabet = "אבגדהוזחטיכךלמםנןסעפףצץקרשת ";

	private static GibberishDetectorFactory factory = new GibberishDetectorFactory(GibberishDetector.class);
	private static GibberishDetector gibberishDetector = factory.createGibberishDetectorFromLocalFile("bigHebrew.txt", "goodHebrew.txt", "badHebrew.txt", alphabet);

	@Test
	public void gibberishDetectorGoodHebrewTest() {
		for (String line : goodHebrewSentences) {
			Assert.assertEquals(false, gibberishDetector.isGibberish(line));
		}		
	}

	@Test
	public void gibberishDetectorBadHebrewTest() {
		for (String line : badHebrewSentences) {
			Assert.assertEquals(true, gibberishDetector.isGibberish(line));
		}		
	}
	
	@Test
	public void randomGibberishTest() {
		GibberishDetectorTestUtils.randomGibberishTest(alphabet, gibberishDetector);
	}
}
