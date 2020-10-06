package kakao_blind_2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
class Info{
	int n;
	int idx;
	Info(int n, int idx){
		this.n = n;
		this.idx = idx;
	}
	@Override
	public String toString() {
		return "n : "+n+"idx : "+idx;
	}
	
}
public class Problem04 {
	
	
	public static void main(String[] args) {
		
		int[] food_times = {1,1,2,6,5,4};
		int k = 17;
		solution(food_times, k);
	
	}
	
	static int solution(int[] food_times, long k) {
	
		Queue<Info> Q = new PriorityQueue<Info>((Info a, Info b) -> a.n-b.n);
		long sum = 0;
		int answer;
		for(int i = 0 ; i < food_times.length ; i++) {
			Q.offer(new Info(food_times[i],i));
			sum += food_times[i];
		}
		if(sum <= k) return -1;
		
		long before = 0;
		long current = Q.peek().n;
		while( k >= (current-before)*Q.size()) {
	
			k -= (current-before)*Q.size();
			before = Q.poll().n;
			current = Q.peek().n;
			
		}
		ArrayList<Info> list = new ArrayList<>(Q);
		Collections.sort(list, (Info a, Info b) -> a.idx-b.idx);
		int index = (int)(k % Q.size());
		answer = list.get(index).idx+1;
		System.out.println(answer);
		return answer;

	}
}
