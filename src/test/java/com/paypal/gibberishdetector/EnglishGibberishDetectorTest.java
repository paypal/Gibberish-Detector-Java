package com.paypal.gibberishdetector;

import org.junit.Assert;
import org.junit.Test;

import com.paypal.gibberishdetector.GibberishDetector;
import com.paypal.gibberishdetector.GibberishDetectorExtended;
import com.paypal.gibberishdetector.GibberishDetectorFactory;

public class EnglishGibberishDetectorTest {
	
	private String[] goodEnglishSentences = {"my name is Shir", "hello world", "and you can tell everybody that this is your song"};
	private String[] badEnglishSentences = {"2 chhsdfitoixcv", "fasdf asg ggd fhgkv", "qmdu poebc vuutkl jsupwre"};
	private static final String alphabet = "abcdefghijklmnopqrstuvwxyz ";

	private static GibberishDetectorFactory factory = new GibberishDetectorFactory(GibberishDetectorExtended.class);
	
	private static GibberishDetector gibberishDetector = factory.createGibberishDetectorFromLocalFile("bigEnglish.txt",
										"goodEnglish.txt", "badEnglish.txt", alphabet);
		
	@Test
	public void gibberishDetectorGoodEnglishTest() {
		for (String line : goodEnglishSentences) {
			Assert.assertEquals(false, gibberishDetector.isGibberish(line));
		}		
	}

	@Test
	public void gibberishDetectorBadEnglishTest() {
		for (String line : badEnglishSentences) {
			Assert.assertEquals(true, gibberishDetector.isGibberish(line));
		}		
	}

	@Test
	public void randomGibberishTest() {
		GibberishDetectorTestUtils.randomGibberishTest(alphabet, gibberishDetector);
	}
}
