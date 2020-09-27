package kakao_blind_2020;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem06_revised {
	
	
	static int ans = Integer.MAX_VALUE;
	
	static int weak[] = {1, 5, 6, 10};
	static int weak2[] = {1,3,4,9,10};
	
	static int dist[] = {1,2,3,4};
	static int dist2[] = {3,5,7};
	
	static boolean visit[];
	
	static ArrayList<Integer> distance = new ArrayList<>();
	
	public static void main(String[] args) {
		
		
		solution(12, weak, dist);
		System.out.println(ans);
		System.out.println();
		solution(12, weak2, dist2);
		System.out.println(ans);
		
		
	}
	
	
	static int solution(int n, int[] weak, int[] dist) {
     
		visit = new boolean[dist.length];
		
		
		for(int i = 1 ; i <= dist.length ; i++) {
			
			Arrays.fill(visit, false);
			permutation(n, weak, dist, 0, i);
		}
		
		return (ans==Integer.MAX_VALUE) ? -1 : ans;
    }
	
	
	
	static boolean simulate(int n, int[] weak, int[] dist) {
		
		ArrayList<Integer> list = new ArrayList<>();
		boolean weak_visit[] = new boolean[weak.length];
		
		for(int i = 0 ; i < weak.length ; i++) {
			list.clear();
			Arrays.fill(weak_visit, false);
			int start = i;
			for(int j = start ; j < weak.length ; j++ ) {
				list.add(weak[j]);
			}
			for(int j = 0 ; j < start ; j++) {
				list.add(weak[j]+n);
			}
			System.out.println("weak sequence : "+ list);
			for(int j = 0 ; j < distance.size() ; j++) {
				int cur = distance.get(j);
				System.out.println("cur : "+cur);
				for(int k = 0 ; k < list.size(); k++) {
					if(weak_visit[k]) continue;
					int from = k;
					while(from < list.size() && (list.get(from) <= list.get(k)+cur)) {
						weak_visit[from] = true;
						from++;
					}
					break;
				}
			}
			for(int j = 0 ; j < weak_visit.length ; j++) System.out.print(weak_visit[j]);
			System.out.println();
			boolean flag = true;
			for(boolean b : weak_visit) if(!b) flag = false;
			if(flag) return true;
		}
		return false;
	}
	
	static void permutation(int n, int[] weak, int[] dist, int cur_depth, int max_depth) {
		
		if(cur_depth == max_depth) {
			System.out.println("distance permutation : "+distance);
			if(simulate(n, weak, dist)) {
				ans = Math.min(cur_depth, ans);
			}
			return;
		}
		
		for(int i = 0 ; i < visit.length ; i++) {
			if(visit[i]) continue;
			distance.add(dist[i]);
			visit[i] = true;
			permutation(n, weak, dist, cur_depth+1, max_depth);
			visit[i] = false;
			distance.remove(distance.size()-1);
		}
	}
	
}
