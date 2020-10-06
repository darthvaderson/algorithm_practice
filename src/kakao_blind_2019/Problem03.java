package kakao_blind_2019;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem03 {
	
	static int N;
	static boolean visit[];
	static ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
	
	public static void main(String[] args) {
		
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		solution(relation);
	}
	
	static int solution(String[][] relation) {
		
		N = relation[0].length;
		visit = new boolean[N];
		
		for(int i = 1 ; i <= N ; i++) {
			Arrays.fill(visit, false);
			search(relation, i, 0, 0);
		}
		System.out.println(comb.size());
		return comb.size();
		
	}
	
	static void search(String[][] relation, int final_depth, int current_depth, int search_from) {
		
		if(final_depth == current_depth) {
			
			ArrayList<Integer> temp = new ArrayList<>();
			
			for(int i = 0 ; i < N ; i++) if(visit[i]) temp.add(i);
			for(int i = 0 ; i < comb.size() ; i++) {
				if(temp.containsAll(comb.get(i))) return;
			}
			System.out.println(comb);
			System.out.println(temp);
			ArrayList<String> check = new ArrayList<>();
			
			for(int i = 0 ; i < relation.length ; i++) {
				String cur ="";
				for(int j = 0 ; j < N ; j++) {
					if(visit[j]) cur+=relation[i][j]+" ";
				}
				if(check.contains(cur)) return;
				check.add(cur);
			}
			comb.add(temp);
			return;
		}
		
		for(int i = search_from ; i < N; i++) {
			visit[i] = true;
			search(relation, final_depth, current_depth+1, i+1);
			visit[i] = false;
			
		}
	}
}
