package coupang_2020;

import java.util.*;

public class Problem03 {

	public static void main(String[] args) {
		int[] score = {24,22,20,10,5,3,2,1};
		int[] score2 = {1300000000,700000000,668239490,618239490,568239490,568239486,518239486,157658638,157658634,100000000,100};
		solution(3, score);
		solution(2, score2);
	}
	
	static int solution(int k, int[] score) {
		int answer = score.length;
		Map<Integer, HashSet<Integer>> map = new HashMap<>();
		Set<Integer> final_set = new HashSet<>();
		
		for(int i = 1; i < score.length ; i++) {
			int a = score[i-1];
			int b = score[i];
			if(!map.containsKey(a-b)) {
				map.put(a-b, new HashSet<>());
			}
			map.get(a-b).add(a);
			map.get(a-b).add(b);
		}
		for(Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
			if(entry.getValue().size() > k) {
				final_set.addAll(entry.getValue());
			}
		}
		
		System.out.println(score.length - final_set.size());
		return answer;
	}
}
