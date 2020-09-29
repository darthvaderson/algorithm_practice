package kakao_blind_2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class State implements Comparable<State>{

	int n;
	int count;
	int fail;
	double failure_rate;

	State(int n){
		this.n = n;
	}
	State(int n, int count, int fail){
		this.count = count;
		this.fail = fail;
	}
	void calc() {
		failure_rate = fail/(double)count;
	}
	@Override
	public int compareTo(State S) {
		if(S.failure_rate == failure_rate) return n-S.n;
		return S.failure_rate-failure_rate > 0 ? 1 : -1;
	}
	@Override
	public boolean equals(Object O) {
		if(O instanceof State) {
			if(n==((State)O).n) return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "("+n+", "+count+", "+fail+", "+failure_rate+")";
	}

}
public class Problem02 {

	public static void main(String[] args) {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		solution(5, stages);
	}
	static int[] solution(int N, int[] stages) {
		
		int[] answer = new int[N];
		ArrayList<State> list = new ArrayList<>();
		// 1. 스테이지 Register
		for(int i = 1 ; i <= N ; i++) list.add(new State(i));
		
		// 2. 각 스테이지 정보 Counting
		for(int stage : stages) {
	
			for(int i = 1 ; i <= stage ; i++) {
				if(i == N+1) continue;
				list.get(i-1).count++;
				if(i==stage) list.get(i-1).fail++;
				
			}
			
		}
		// 3. failure rate 계산
		for(int i = 0 ; i < list.size() ; i++) {
			if(list.get(i).count == 0) {
				list.get(i).failure_rate = 0;
			}else {
				list.get(i).calc();
			}
		}
		// 4. 정렬
		for(State S : list) System.out.println(S);
		Collections.sort(list);
		// 5. 정답 배열 기록
		int idx = 0;
		for(int i = 0 ; i < list.size(); i ++) {
			answer[idx++] = list.get(i).n;
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}
}
