package boj_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class P_1655 {
	
	
	static BufferedReader br;
	static int N;
	static Queue<Integer> pqMin;
	static Queue<Integer> pqMax;
	
	static Integer[] temp;
	static int size;
	static int cur;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pqMax = new PriorityQueue<Integer>((a, b) -> { return b.compareTo(a); });
		pqMin = new PriorityQueue<Integer>((a, b) -> {return a.compareTo(b); });
		
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < N ; i ++) {
			cur = Integer.parseInt(br.readLine());
			calc(cur);
			sb.append(pqMax.peek()+"\n");
		}
		System.out.println(sb);
	
		
	}
	static void calc(int cur) {
		
	
		if(pqMax.size() == pqMin.size()) {
			pqMax.offer(cur);
		}else {
			pqMin.offer(cur);
		}
		
		if(!pqMax.isEmpty() && !pqMin.isEmpty()) {
			if(pqMax.peek() > pqMin.peek()) {
				int temp = pqMin.poll();
				pqMin.offer(pqMax.poll());
				pqMax.offer(temp);
			}
		}
		
	}
	
	

}
