package coupang_2020;

import java.util.ArrayList;

public class Problem01 {
	
	static int max_value = Integer.MIN_VALUE;
	static int max_system = -1;
	
	public static void main(String[] args) {
		
		
		
	}
	static void calc(int N, int system) {
		
		int residue;
		int share;
		ArrayList<Integer> list = new ArrayList<>();
		
		while(true) {
			share = N/system;
			residue = N%system;
			list.add(residue);
			N = share;
			if(share == 0) break;
		}
		int i = 1;
		for(int k : list) {
			i *= k;
		}
		if(i >= max_value) {
			max_value = i;
			max_system = system;
		}
	}
	
	static int[] solution(int N) {
		
		int[] answer = new int[2];
		
		
		
		
		for(int i = 2 ; i <= 9 ; i ++) {
			
			calc(N, i);
		}
		
		answer[0] = max_system;
		answer[1] = max_value;
		
		return answer;
		
	}
}
