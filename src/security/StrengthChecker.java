package security;

public class StrengthChecker {
	
	/**
	 * Strength Scores:
	 * 		Weak 	 = < 40 pts.
	 * 		Adequate = 
	 * 		Strong 	 = > 60 pts.
	 * 
	 * Add Points:
	 * 		Num Chars		n*4
	 * 		Uppercase		n*2
	 * 		Lowercase		n*2
	 * 
	 * Deduct Points:
	 * 		
	 * 		
	 * 
	 * @param password
	 * @return
	 */
	public String checkStrength(String password) {
		// Easter egg
		if (password.equalsIgnoreCase("spookyscaryskeletonssendshiversdownyourspine")) {
			return "2spooky4me";
		}
		
		int strengthScore = password.length();
		String passwordStrength;
		
		
		return "passwordStrength";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
