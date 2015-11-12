package security;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

public class StrengthChecker {
	
	// Multiplier Constants
	private static final int LENGTH_MULTIPLIER = 4;
	private static final int UPPERCASE_MULTIPLIER = 2;
	private static final int LOWERCASE_MULTIPLIER = 2;
	private static final int DIGITS_MULTIPLIER = 3;
	private static final int SYMBOLS_MULTIPLIER = 5;
	private static final int LETTERS_ONLY_MULTIPLIER = 2;
	private static final int DIGITS_ONLY_MULTIPLIER = 2;
	private static final int SYMBOLS_ONLY_MULTIPLIER = 2;
	private static final int SEQUENTIAL_MULTIPLIER = 2;
	private static final int DUPLICATE_MULTIPLIER = 3;
	
	// Minimum Requirement Constants
	private static final int PARTIAL_MIN_REQ_BONUS = 8;
	private static final int FULL_MIN_REQ_BONUS = 10;
	private static final int MIN_PASSWORD_LENGTH = 10;
	
	// Rating Thresholds
	private static final int WEAK_RATING_THRESHOLD = 40;
	private static final int STRONG_RATING_THRESHOLD = 70;
	
	/**
	 * Checks the strength of a given password.
	 * 
	 * @param password	password to check the strength rating of
	 * @return			strength rating: "weak", "adequate", or "strong"
	 */
	public String checkStrength(String password) {
		// Easter egg
		if (password.equalsIgnoreCase("spookyscaryskeletonssendshiversdownyourspine")) {
			return "2spooky4me";
		}
		
		// Password length determines initial strength score
		int strengthScore = LENGTH_MULTIPLIER * password.length();
		System.out.println("Length Points:\t\t" + (LENGTH_MULTIPLIER * password.length())); // ***DEBUG***
		
		// Check minimum requirements
		strengthScore += checkMinRequirements(password);
		
		// Check for point bonuses
		strengthScore += findPointBonuses(password);
		
		// Check for point deductions
		strengthScore -= findPointDeductions(password);
		
		// TODO Implement common word deductions???
		
		// Return password rating based on final strength score
		if (strengthScore < WEAK_RATING_THRESHOLD) {
			System.out.println("Strength Score:\t\t" + strengthScore); // ***DEBUG***
			return "Weak";
		}
		else if (strengthScore > STRONG_RATING_THRESHOLD) {
			System.out.println("Strength Score:\t\t" + strengthScore); // ***DEBUG***
			return "Strong";
		}
		else {
			System.out.println("Strength Score:\t\t" + strengthScore); // ***DEBUG***
			return "Adequate";
		}		
	}
	
	private int checkMinRequirements(String password) {
		// Define rules for minimum requirements
		LengthRule lengthRule = new LengthRule(MIN_PASSWORD_LENGTH, 2048);
		CharacterCharacteristicsRule charCharsRule = new CharacterCharacteristicsRule();
		charCharsRule.getRules().add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		charCharsRule.getRules().add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		charCharsRule.getRules().add(new CharacterRule(EnglishCharacterData.Digit, 1));
		charCharsRule.getRules().add(new CharacterRule(EnglishCharacterData.Special, 1));
		
		// Define validators to check partial(3/4) and full(4/4) minimum requirements
		charCharsRule.setNumberOfCharacteristics(3);
		PasswordValidator partialValidator = new PasswordValidator(Arrays.asList(lengthRule, charCharsRule));
		charCharsRule.setNumberOfCharacteristics(4);
		PasswordValidator fullValidator = new PasswordValidator(Arrays.asList(lengthRule, charCharsRule));
		
		// Get results of validators and assign points appropriately
		RuleResult partialResult = partialValidator.validate(new PasswordData(password));
		if (!partialResult.isValid()) {
			System.out.println("Min Req Bonus:\t\t0"); // ***DEBUG***
			return 0;
		}
		else {
			RuleResult fullResult = fullValidator.validate(new PasswordData(password));
			if (fullResult.isValid()) {
				System.out.println("Min Req Bonus:\t\t" + FULL_MIN_REQ_BONUS); // ***DEBUG***
				return FULL_MIN_REQ_BONUS;
			}
			else {
				System.out.println("Min Req Bonus:\t\t" + PARTIAL_MIN_REQ_BONUS); // ***DEBUG***
				return PARTIAL_MIN_REQ_BONUS;
			}
		}
	}
	
	private int findPointBonuses(String password) {
		int totalPointBonuses = 0;
		
		// Find uppercase instances
		int numUppercases = 0;
		Pattern uppercaseCharPattern = Pattern.compile("[A-Z]");
		Matcher uppercaseMatcher = uppercaseCharPattern.matcher(password);
		while (uppercaseMatcher.find()) {
			numUppercases++;
		}
		totalPointBonuses += (UPPERCASE_MULTIPLIER * numUppercases);
		
		// Find lowercase instances
		int numLowercases = 0;
		Pattern lowercaseCharPattern = Pattern.compile("[a-z]");
		Matcher lowercaseMatcher = lowercaseCharPattern.matcher(password);
		while (lowercaseMatcher.find()) {
			numLowercases++;
		}
		totalPointBonuses += (LOWERCASE_MULTIPLIER * numLowercases);
		
		// Find digit instances
		int numDigits = 0;
		Pattern digitCharPattern = Pattern.compile("[0-9]");
		Matcher digitMatcher = digitCharPattern.matcher(password);
		while (digitMatcher.find()) {
			numDigits++;
		}
		totalPointBonuses += (DIGITS_MULTIPLIER * numDigits);
		
		// Find symbol instances
		int numSymbols = 0;
		Pattern symbolCharPattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher symbolMatcher = symbolCharPattern.matcher(password);
		while (symbolMatcher.find()) {
			numSymbols++;
		}
		totalPointBonuses += (SYMBOLS_MULTIPLIER * numSymbols);
		
		/* DEBUG STATEMENTS */
		System.out.println("Total Bonuses:\t\t" + totalPointBonuses);
		System.out.println("\tUppercases:\t\t" + (UPPERCASE_MULTIPLIER * numUppercases));
		System.out.println("\tLowercases:\t\t" + (LOWERCASE_MULTIPLIER * numLowercases));
		System.out.println("\tDigits:\t\t\t" + (DIGITS_MULTIPLIER * numDigits));
		System.out.println("\tSymbols:\t\t" + (SYMBOLS_MULTIPLIER * numSymbols));
		/* DEBUG STATEMENTS */
		
		return totalPointBonuses;
	}
	
	private int findPointDeductions(String password) {
		int totalPointDeductions = 0;
		
		// ***DEBUG VARIABLES*** //
		boolean letters = false;
		boolean digits = false;
		boolean symbols = false;
		// ***DEBUG VARIABLES*** //
		
		// Deductions for letters-only passwords
		Pattern letterOnlyPattern = Pattern.compile("[^a-zA-Z]");
		Matcher letterOnlyMatcher = letterOnlyPattern.matcher(password);
		if (!letterOnlyMatcher.find()) {
			totalPointDeductions += (LETTERS_ONLY_MULTIPLIER * password.length());
			letters = true; // ***DEBUG VARIABLES*** //
		}
		
		// Deductions for digits-only passwords
		Pattern digitsOnlyPattern = Pattern.compile("[^0-9]");
		Matcher digitsOnlyMatcher = digitsOnlyPattern.matcher(password);
		if (!digitsOnlyMatcher.find()) {
			totalPointDeductions += (DIGITS_ONLY_MULTIPLIER * password.length());
			digits = true; // ***DEBUG VARIABLES*** //
		}
		
		// Deductions for symbols-only passwords
		Pattern symbolsOnlyPattern = Pattern.compile("[a-zA-Z0-9]");
		Matcher symbolsOnlyMatcher = symbolsOnlyPattern.matcher(password);
		if (!symbolsOnlyMatcher.find()) {
			totalPointDeductions += (SYMBOLS_ONLY_MULTIPLIER * password.length()); // TODO Maybe this should deduct less than letters/digits-only
			symbols = true; // ***DEBUG VARIABLES*** //
		}
		
		// Deductions for sequential characters
		Pattern sequentialCharPattern = Pattern.compile("(.)\\1");
		Matcher sequentialCharMatcher = sequentialCharPattern.matcher(password);
		int numSequentials = 0;
		while (sequentialCharMatcher.find()) {
			numSequentials++;
		}
		totalPointDeductions += SEQUENTIAL_MULTIPLIER * numSequentials;
		
		// Deductions for repeated characters
		Pattern duplicateCharPattern = Pattern.compile("(.).+\\1");
		Matcher duplicateCharMatcher = duplicateCharPattern.matcher(password);
		
		int nextIndex = 0;
		int numDuplicates = 0;
		while (duplicateCharMatcher.find(nextIndex)) {
			numDuplicates++;
			nextIndex = duplicateCharMatcher.start() + 1;
		}
		totalPointDeductions += DUPLICATE_MULTIPLIER * numDuplicates;
		
		/* DEBUG STATEMENTS */
		System.out.println("Total Deductions:\t" + totalPointDeductions);
		System.out.println("\tLetters Only:\t\t" + ((letters) ? (LETTERS_ONLY_MULTIPLIER * password.length()) : 0));
		System.out.println("\tDigits Only:\t\t" + ((digits) ? (DIGITS_ONLY_MULTIPLIER * password.length()) : 0));
		System.out.println("\tSymbols Only:\t\t" + ((symbols) ? (SYMBOLS_ONLY_MULTIPLIER * password.length()) : 0));
		System.out.println("\tSequentials:\t\t" + (SEQUENTIAL_MULTIPLIER * numSequentials));
		System.out.println("\tDuplicates:\t\t" + (DUPLICATE_MULTIPLIER * numDuplicates));
		/* DEBUG STATEMENTS */
		
		return totalPointDeductions;
	}

	public static void main(String[] args) {
		String password = "password";
		StrengthChecker checker = new StrengthChecker();
		System.out.println("\nFINAL RATING: " + checker.checkStrength(password));

	}

}
