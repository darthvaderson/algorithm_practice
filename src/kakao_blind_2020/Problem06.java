package kakao_blind_2020;

import java.util.ArrayList;
import java.util.Stack;

public class Problem06 {

	
	static int ans = Integer.MAX_VALUE;
	static Stack<Integer> st = new Stack<>();
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> weak_point = new ArrayList<>();
	
	static int[] weak = {1, 3, 4, 9, 10};
	static int[] dist = {3, 5, 7};
	
	public static void main(String[] args) {
		
		solution(12, weak, dist);
		
	}
	
	
	static int solution(int n, int[] weak, int[] dist) {
		
		
		for(int i : weak) weak_point.add(i);
		
		search(n, dist, dist.length-1);
		
		
		return ans;
	}
	
	static void search(int n, int[] dist, int depth) {
		System.out.println(list);
		if(list.containsAll(weak_point)) {
			int tmp = dist.length-(depth+1);
			System.out.println(tmp);
			ans = Math.min(ans, tmp);
			return;
		}
		if(depth < 0) return;
		
		ArrayList<Integer> tmp = new ArrayList<>();
		
		for(int i = 0 ; i < weak_point.size() ; i++) {
			if(list.contains(weak_point.get(i))) continue;
			int base = weak_point.get(i);
			tmp.clear();
			for(int j = 0 ; j <= dist[depth] ; j++) {
				if(list.contains(((base+j)%n))) continue;
				if(!weak_point.contains(((base+j)%n))) continue;
				tmp.add((base+j)%n);
			}
			list.addAll(tmp);
			search(n, dist, depth-1);
			list.removeAll(tmp);
		}
		
		for(int i = 0 ; i < weak_point.size() ; i++) {
			if(list.contains(weak_point.get(i))) continue;
			int base = weak_point.get(i);
			tmp.clear();
			for(int j = 0; j <= dist[depth] ; j++) {
				if(list.contains(((base-j)+n)%n)) continue;
				if(!weak_point.contains(((base-j)+n)%n)) continue;
				tmp.add( ((base-j)+n)%n );
			}
			list.addAll(tmp);
			search(n, dist, depth-1);
			list.removeAll(tmp);
		}
	}
	
	
}
