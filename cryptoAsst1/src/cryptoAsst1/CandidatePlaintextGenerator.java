package cryptoAsst1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CandidatePlaintextGenerator {

	String[] input;
	File candidatePlainTextFile;
	
	int[] currentRecursionPossibility;
	int lengthOfWords;
	FileOutputStream fos;

	// length of every possible keyword
	int[] stringLength = new int[100];

	public CandidatePlaintextGenerator(String dictionaryInputpath, String candidatePlainTextFilePath) throws IOException {

		lengthOfWords = 6;
		input = new String[100];
		candidatePlainTextFile = new File(candidatePlainTextFilePath);
		candidatePlainTextFile.createNewFile();
		fos = new FileOutputStream(candidatePlainTextFile,true);
		currentRecursionPossibility = new int[lengthOfWords+1];
		List<String> myList = new ArrayList<String>();
		Scanner fileScanner = new Scanner(new File(dictionaryInputpath));
		
	//	"/home/vaio/Downloads/Dictionary2.txt"

		while (fileScanner.hasNext())
			myList.add(fileScanner.nextLine());

		myList.toArray(input);

		calculateCardinality(input);
		recursiveBruteForceChecker(0);
		fos.close();
		

	}

	public void calculateCardinality(String[] inputWords) {

		int[] cardArray = new int[16];

		for(int i=0;i<inputWords.length;i++){
			stringLength[i]=input[i].length();
			cardArray[input[i].length()]++;
		}
	}

	public void generatePlaintext(String[] inputWords) {

		int ptLength = 100;

	}

	/**
	 * Checks possible combinations of plaintext using recursion.
	 * 
	 * 
	 * 
	 * @param currentRecursionIndex
	 * @throws IOException 
	 */
	public void recursiveBruteForceChecker(int currentRecursionIndex) throws IOException {
		
		if (currentRecursionIndex == lengthOfWords) {
			
//			System.out.print("Fixed " + currentRecursionIndex);
	//		System.out.println();
			
			for (int temp = 0; temp < input.length; temp++) {
				currentRecursionPossibility[currentRecursionIndex] = temp;
				
				checkAndWrite(currentRecursionPossibility);
			}

		} else {

			if (currentRecursionIndex <= (lengthOfWords-3))
			System.out.println("Fixed" + currentRecursionIndex);
			
			for (int temp = 0; temp < input.length; temp++) {
				
				currentRecursionPossibility[currentRecursionIndex] = temp;
				recursiveBruteForceChecker(currentRecursionIndex + 1);
			}
		}

	}

	/**
	 * 
	 * Checks a given plainText combination's length and writes to file if one
	 * found.
	 * 
	 * @param possiblePlaintext
	 * @throws IOException 
	 */
	public void checkAndWrite(int[] possiblePlaintext) throws IOException {
		int totalLength = 0;

//		System.out.print("Checking ");
//		for(int tempcc=0;tempcc<possiblePlaintext.length;tempcc++)
	//	System.out.print(currentRecursionPossibility[tempcc] + " ");
		//System.out.print("which is ");
//		for(int tempcc=0;tempcc<possiblePlaintext.length;tempcc++)
	//	System.out.print(input[currentRecursionPossibility[tempcc]] + " ");
	//	System.out.println("");
		
		for (int i : possiblePlaintext) {
			totalLength = totalLength + stringLength[i] + 1;
		}

		totalLength--;
		
	//	System.out.println("Totallength is " + totalLength);
		
		if (totalLength==100)
		{
			System.out.println("Total length is " + totalLength + ".");
			writeCombinationToFile(possiblePlaintext);
		}

	}

	/**
	 * Write a combination of english words to file if the set can create a
	 * Stirng of Length L.
	 * 
	 * @param possiblePlaintext
	 * @throws IOException 
	 */
	private void writeCombinationToFile(int[] possiblePlaintext) throws IOException {
		// TODO Auto-generated method stub

		String confirmedPossiblePlaintext = new String();
		confirmedPossiblePlaintext = confirmedPossiblePlaintext+ input[possiblePlaintext[0]];

		for (int i = 1; i < possiblePlaintext.length; i++)
			confirmedPossiblePlaintext = confirmedPossiblePlaintext + " "+ input[possiblePlaintext[i]];

		System.out.println("Writing " + confirmedPossiblePlaintext + " to file!");
		
		fos.write((confirmedPossiblePlaintext + "\n").getBytes());
		
		

	}

}