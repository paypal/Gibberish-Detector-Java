package com.paypal.gibberishdetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * gibberish detector factory for creating an instance of GibberishDetector or another detector that extends it.
 * @author sfiszman
 *
 */
public class GibberishDetectorFactory {

	private Class<? extends GibberishDetector> type;

	public GibberishDetectorFactory(Class<? extends GibberishDetector> type) {
		this.type = type;
	}
	/**
	 * creates a gibberish detector trained by the given lines and alphabet.
	 * @param trainingList list of lines for training
	 * @param goodList list of good valid lines
	 * @param badList list of bad gibberish lines
	 * @param alphabet String that contains all the alphabet of the language plus the white space character. for example: "abcdefghijklmnopqrstuvwxyz "
	 * @return gibberish detector 
	 */
	public GibberishDetector createGibberishDetector(List<String> trainingList, List<String> goodList, List<String> badList, String alphabet) {
		try {
			return type.getConstructor(new Class[] {List.class, List.class, List.class, String.class}).newInstance(trainingList, goodList, badList, alphabet);		
		} 
		catch (Exception e) {
			throw new IllegalArgumentException("Exception in GibberishDetectorFactory: " + (e.getCause() != null ? e.getCause().getMessage() : ""));
		}
	}
	
	/**
	 * creates a gibberish detector trained by the given Files and alphabet.
	 * @param trainingFile file object that contains lines for training
	 * @param goodFile file object that contains good valid lines
	 * @param badFile file object that contains bad gibberish lines
	 * @param alphabet String that contains all the alphabet of the language plus the white space character. for example: "abcdefghijklmnopqrstuvwxyz "
	 * @return gibberish detector 
	 */
	public GibberishDetector createGibberishDetector(File trainingFile, File goodFile, File badFile, String alphabet) {
		try {
			return createGibberishDetector(getLinesFromFile(trainingFile), getLinesFromFile(goodFile), getLinesFromFile(badFile), alphabet);
		}		
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		catch (RuntimeException e) {
			throw new RuntimeException("Exception in GibberishDetectorFactory: " + e.getMessage());
		}
	}

	/**
	 * creates a gibberish detector trained by the given file paths and alphabet.
	 * @param trainingFilePath path of a file that contains lines for training
	 * @param goodFilePath path of a file that contains good valid lines
	 * @param badFilePath path of a file that contains bad gibberish lines
	 * @param alphabet String that contains all the alphabet of the language plus the white space character. for example: "abcdefghijklmnopqrstuvwxyz "
	 * @return gibberish detector
	 */
	public GibberishDetector createGibberishDetector(String trainingFilePath, String goodFilePath, String badFilePath, String alphabet) {
		try {
			return createGibberishDetector(new File(trainingFilePath), new File(goodFilePath), new File(badFilePath), alphabet);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * creates a gibberish detector trained by the given files names and alphabet. Assumes UTF-8 encoding.
	 * @param trainingFileName name of a local file that contains lines for training
	 * @param goodFileName name of a local file that contains good valid lines
	 * @param badFileName name of a local file that contains bad gibberish lines
	 * @param alphabet String that contains all the alphabet of the language plus the white space character. for example: "abcdefghijklmnopqrstuvwxyz "
	 * @return gibberish detector
	 */
	public GibberishDetector createGibberishDetectorFromLocalFile(String trainingFileName, String goodFileName, String badFileName, String alphabet) {
		try {		
			return createGibberishDetector(getLinesFromLocalFile(trainingFileName), getLinesFromLocalFile(goodFileName), getLinesFromLocalFile(badFileName), alphabet);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		catch (RuntimeException e) {
			throw new RuntimeException("Exception in GibberishDetectorFactory: " + e.getMessage());
		}
	}
	
	private static List<String> getLinesFromFile(File file) {
		List<String> lines = new ArrayList<String>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line.trim());
			}
		}
		catch (IOException | NullPointerException e) {
			throw new RuntimeException("Can not initiate file: " + file.getAbsolutePath() , e);
		}
		return lines;
	}

	private static List<String> getLinesFromLocalFile(String fileName) {
		List<String> lines = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), Charset.forName("UTF-8")))){
			while(reader.ready()) { 
				lines.add(reader.readLine().trim());
			}			
		} catch (IOException | NullPointerException e) {
			throw new RuntimeException("Can not initiate file: " + fileName , e);
		} 
		return lines;
	}
}