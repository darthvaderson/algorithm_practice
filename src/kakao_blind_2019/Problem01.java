package kakao_blind_2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem01 {
		
		public static void main(String[] args) {
			
			String[] records = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
			solution(records);
			
		}
		
		static String[] solution(String[] records) {
			Map<String, String> map = new HashMap<String, String>();
			String answer[];
			int idx = 0;
			for(String record : records) {
				String cur[] = record.split(" ");
				if(cur[0].equals("Enter")||cur[0].equals("Change")) {
					map.put(cur[1],cur[2]);
				}
				if(cur[0].equals("Enter")||cur[0].equals("Leave")) idx++;
			}
			answer = new String[idx];
			idx = 0;
			for(String record : records) {
				String cur[] = record.split(" ");
				String key = cur[1];
				if(cur[0].equals("Enter")) {
					answer[idx++] = map.get(cur[1])+"´ÔÀÌ µé¾î¿Ô½À´Ï´Ù."; 
				}else if(cur[0].equals("Leave")) {
					answer[idx++] = map.get(cur[1])+"´ÔÀÌ ³ª°¬½À´Ï´Ù.";
				}
			}
			
			System.out.println(Arrays.toString(answer));
			return answer;
		}
}
