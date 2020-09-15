package kakao_2020_09_12;
import java.util.*;

public class Problem01 {
	
	static String answer;
	
	
	public static void main(String[] args) {
		
		
		solution("abcdefghijklmn.p");
		System.out.println(answer);
		
		
	}
	
	public static String solution(String new_id) {
		
		ArrayList<Character> list = new ArrayList<>();
		String lower_case = new_id.toLowerCase();
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < lower_case.length(); i++) {
			if(97 <= (int)lower_case.charAt(i) && (int)lower_case.charAt(i) <= 122) {
				sb.append(lower_case.charAt(i));
				continue;
			}
			if(48 <= (int)lower_case.charAt(i) && (int)lower_case.charAt(i) <= 57) {
				sb.append(lower_case.charAt(i));
				continue;
			}
			if(lower_case.charAt(i) == '-' || lower_case.charAt(i) == '_' || lower_case.charAt(i) == '.') {
				sb.append(lower_case.charAt(i));
				continue;
			}
		}
		
		StringBuffer sb2 = new StringBuffer();
		int i = 0;
		while(i < sb.length()) {
			if(sb.charAt(i) != '.') {
				sb2.append(sb.charAt(i));
				i++;
			}
			else {
				sb2.append(sb.charAt(i));
				while(i < sb.length() && sb.charAt(i) == '.') {
					i++;
				}
			}
		}
		
		if(sb2.length() != 0 && sb2.charAt(0) == '.') sb2.deleteCharAt(0);
		if(sb2.length() != 0 && sb2.charAt(sb2.length()-1) == '.') sb2.deleteCharAt(sb2.length()-1);
		
		if(sb2.length() == 0) sb2.append('a');
		if(sb2.length() > 15) {
			sb = new StringBuffer(sb2.subSequence(0, 15));
			if(sb.charAt(sb.length()-1) == '.') sb.deleteCharAt(sb.length()-1);
		}
		else sb = sb2;
		
		if(sb.length() <= 2) {
			char k = sb.charAt(sb.length()-1);
			while(sb.length() < 3) {
				sb.append(k);
			}
		}
		answer = sb.toString();
		
		
		return answer;
	}
}
