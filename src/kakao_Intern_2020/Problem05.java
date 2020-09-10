package kakao_Intern_2020;
import java.util.*;

public class Problem05 {
	
	
	static int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
	static int[][] order = {{8,5},{6,7},{4,1}};
	static int[][] order2 = {{4,1},{8,7},{6,5}};
	
	static ArrayList<Integer> list[];
	static ArrayList<Integer> adj[];
	static boolean[] visit_node;
	static boolean[] visit;
	static boolean answer = true;
	
	public static void main(String[] args) {
		
		
		solution(9, path, order2);
		System.out.println(answer);
		
	}
	
	
	public static boolean solution(int n, int[][] path, int[][] order) {
		
		// 하나는 양방향 (list) 다른 하나는 단방향(adj)
		list = new ArrayList[n];
		adj = new ArrayList[n];
		
		visit = new boolean[n];
		
		for(int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}
		
		//1. 양방향 그래프 만들기 
		for(int i = 0 ; i < path.length ; i++) {
			list[path[i][0]].add(path[i][1]);
			list[path[i][1]].add(path[i][0]);
		}
		
		
		//2. 양방향을 토대로 단방향 그래프 만들기
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(0);
		visit[0] = true;
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			
			for(int next : list[cur]) {
				if(visit[next]) continue;
				adj[cur].add(next);
				visit[next] = true;
				Q.offer(next);
			}
			
		}
		for(ArrayList<Integer> a : adj) {
			System.out.println(a);
		}
		
		for(int i = 0 ; i < order.length ; i++) {
			adj[order[i][0]].add(order[i][1]);
		}
		for(ArrayList<Integer> a : adj) {
			System.out.println(a);
		}
		
		//3. 그래프 사이클 유무 검사
		visit_node = new boolean[n];
		dfs(0);
		
		return answer;
	}
	
	static void dfs(int n) {
		
		for(int i : adj[n]) {
			if(visit_node[i]) {
				answer = false;
				System.out.println(n);
				break;
			}
			visit_node[i] = true;
			dfs(i);
			visit_node[i] = false;
		}
	}
}
