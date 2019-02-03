package org.com.tddcamelcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TddCamelCase {
	private final static String VALID_MATCH = 
			"(^(?![0-9]))[A-Za-z]+((\\\\d)|([A-Z0-9][a-z0-9]+))*([A-Z])?";
	
	public TddCamelCase() {
		
	}
	
	/**
	 * Converts a string(when is valid) from a CamelCase to a list of words
	 * @param text The string that will be formated
	 * @return List<String> List with formated items
	 * @throws CamelCaseFormatException
	 */
	public static List<String> converterCamelCase(String text) 
			throws CamelCaseFormatException {
		if(!isValid(text))
			throw new CamelCaseFormatException(text);
		
		List<String> res = new ArrayList<String>();
		getList(res, text);
		
		return res;
	}
	
	private static boolean isCharacter(char c) {
		return Character.toString(c).matches("[A-Z]");
	}
	
	/**
	 * Checks if next position item after the current value(number)
	 * 
	 * @param c The char that will be validated
	 * @param start In case of number we can have a sequence of numbers(123), so in this case
	 * is need to check if the start character from filter is also number, and this is case,
	 * so we waiting from a next letter character 
	 * 
	 * @return
	 */
	private static boolean isNumber(char c, char start) {
		return Character.toString(c).matches("[0-9]") && 
			   !Character.toString(start).matches("[0-9]");
	}
	
	public static int getEndPosition(String check) {
		int position = -1;
		for(int i = 1; i < check.length(); i++)
			if(isCharacter(check.charAt(i)) || 
			   isNumber(check.charAt(i), check.charAt(0))) {
				position = i;
				break;
			}
		return position;
	}
	
	/**
	 * Create a list of words with items that are fixed by start and end points
	 * Ex: CamelCase
	 * 'CamelCase'.substr(0, 5) = 'Camel'
	 * when it does the recursion, it pass to method just the word 'Camel', that is
	 * the last end_position found word, so on:
	 * 'Case'.substr(0, 3);
	 * 
	 * @see This method is recursive!!!
	 * @param itens List where will put the words
	 * @param check The string where is trying found the words
	 * @return Returns a List with the words
	 */
	public static List<String> getList(List<String> itens, String check) {
		String item = Character.toString(check.charAt(0));
		int end_position = 
				getEndPosition(check);
		
		if(end_position != -1) {
			item += check.substring(1, end_position);
		} else {
			item += check.substring(1, check.length());
		}
		
		itens.add(item.toLowerCase());
		
		if(end_position != -1 && check.length() > end_position)
			getList(itens, check.substring(end_position, check.length()));
		
		return itens;
	}

	/**
	 * Check if a string has a valid format to CamelCase
	 * @param check
	 * @return boolean 
	 */
	public static boolean isValid(String check) {
		return check.matches(VALID_MATCH);
	}
}
