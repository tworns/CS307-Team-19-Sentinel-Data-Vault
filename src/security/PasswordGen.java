package security;

import java.util.*;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class PasswordGen {
	
	/*
	 * APIs
	 * PasswordGenerator API Class
	 * 		http://javadoc.iaik.tugraz.at/iaik_jce/current/iaik/utils/PasswordGenerator.html
	 * */
	
	public int length;
	public int specials;
	public int num;
	public int upper;
	
	public PasswordGen() {
		
	}
	
	public PasswordGen(int length, int specials, int num, int upper) { 
		this.length = length;
		this.specials = specials;
		this.num = num;
		this.upper = upper;

		generator(length, specials, num, upper);

	}

	public String generator(int length, int specials, int num, int upper){
		Random r = new Random();	
		r.nextInt();
		char [] pass = new char[length];
		//TODO Fix this. Currently broken, returns string of length+1 for length >= 10. 
		//Maybe use secure password API 
		String out = pass.toString();
		char[] alpha = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] numbers = {'1','2', '3','4', '5', '6', '7', '8','9', '0'};
		char[] punct = {'!','@','#','$','%'};
		out = out.substring(3,out.length()); //takes the string representation of the char[]
		StringBuilder s = new StringBuilder(out);
		for(int i = out.length()+1; i < length; i++) {  //adds to the string representation of the char[] to meet length. Probably super unsecure.

			if( i >= length) { 
				return s.toString();
			}
			if(i%3 == 1) {
				s.append(alpha[(i%26)]);
			}
			if (i%3 == 2) { 
				s.append(punct[i%5]);
			}
			else { 
				s.append(numbers[(i%10)]);
			}
		}

		return s.toString();
	}
	
	public String generatePassword(Boolean containsUpper, Boolean containsLower, Boolean containsDigit, Boolean containsSpecial, int length) {
//		CharacterRule upperRule;
//		CharacterRule lowerRule;
//		CharacterRule digitRule;
//		CharacterRule specialRule;
		
		List<CharacterRule> passRules = new ArrayList<CharacterRule>();
		
		// TODO Implement method of adding rules to list if they are true
		
		if (containsUpper) {
			CharacterRule upperRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
			//passRules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
			passRules.add(upperRule);
		}
		if (containsLower) {
			CharacterRule lowerRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
			//passRules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
			passRules.add(lowerRule);
		}
		if (containsDigit) {
			CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
			//passRules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
			passRules.add(digitRule);
		}
		if (containsSpecial) {
			CharacterRule specialRule = new CharacterRule(EnglishCharacterData.Special, 1);
			//passRules.add(new CharacterRule(EnglishCharacterData.Special, 1));
			passRules.add(specialRule);
			CharacterRule specials = new CharacterRule(EnglishCharacterData.Special);
			System.out.println(specials.getValidCharacters());
		}
		
		//List<CharacterRule> passRules = Arrays.asList(upperRule, lowerRule, digitRule, specialRule);
		PasswordGenerator generator = new PasswordGenerator();
		
		return generator.generatePassword(length, passRules);
	}
	
	public static void main(String args[]) {
		PasswordGen pg = new PasswordGen();
		String password = pg.generatePassword(true, true, true, false, 20);
		System.out.println(password);
	}
}


