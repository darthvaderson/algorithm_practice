package kakao_blind_2020;

import java.util.Arrays;

public class Problem04 {

	
	static String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
	static String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
	public static void main(String[] args) {
		
		solution(words, queries);
	}
	
	static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		int index = 0;
		for(String query : queries) {
			
			char cur[] = query.toCharArray();
			int cnt = 0;
			boolean flag;
			int prefix = 0;
			int suffix = 0;
			if(cur[0]=='?') {
				int k = 0;
				while(cur[k]=='?') k++;
				prefix = k;
			}
			if(cur[cur.length-1]=='?') {
				int k = 0;
				while(cur[cur.length-1-k]=='?') k++;
				suffix = k;
			}
			
			for(String word : words) {
				if(word.length() != cur.length) continue;
				
				flag = true;
				
				for(int i = prefix ; i < word.length()-suffix ; i++) {
					if(cur[i]!=word.charAt(i)) {
						flag = false; 
						break;
					}
				}
				
				if(flag) {
					cnt++;
				}
			}
			answer[index++] = cnt;
		}
		System.out.println(Arrays.toString(answer));
		
		return answer;
	}
}
