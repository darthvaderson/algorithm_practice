package line_2020_09_13;

import java.util.HashMap;
import java.util.Map;

public class Problem01 {

	
	
	public static void main(String[] args) {
		
		int boxes[][] = {{1, 2}, {2, 1}, {3, 3}, {4, 5}, {5, 6}, {7, 8}};
		solution(boxes);
		
	}
	
	
	static int solution(int[][] boxes) {
		
		int answer = -1;
		
		int count = 0;
		Map<Integer,Integer> map = new HashMap<>();
		for(int[] box : boxes) {
			if(map.containsKey(box[0])) {
				map.put(box[0], map.get(box[0])+1);
			}
			else map.put(box[0], 1);
			if(map.containsKey(box[1])) {
				map.put(box[1], map.get(box[1])+1);
			}
			else map.put(box[1], 1);
			
		}
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			count += entry.getValue()/2;
		}
		System.out.println(count);
		
		return answer;
		
	}
}
