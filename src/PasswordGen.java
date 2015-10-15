
import java.util.*;
public class PasswordGen {
	public int length;
	public int specials;
	public int num;
	public int upper;
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
			for(int i = 0; i < length; i++){ 
				if(r.nextInt() <= 1 || r.nextInt() >=1) {
					pass[i] = 'a';
				}
				else { 
					pass[i] = '0';
				}
				
		}
			//TODO Fix this. Currently returns the hex(?) value of the char [] object. 
			//Maybe use secure password API 
			String out = pass.toString();
			out = out.substring(3,out.length());
		return out;
		
			
	}
}


