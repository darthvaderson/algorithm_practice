package boj_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_1717 {
	
	static int N, M;
	static int[] root;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		Arrays.fill(root, -1);
		int code, a, b;
		while(M > 0) {
			
			st = new StringTokenizer(br.readLine());
			code = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(code == 0) {
				if(find(a) != find(b)) {
					union(a,b);
				}
			}else {
				if(find(a) == find(b)) System.out.println("YES");
				else System.out.println(("NO"));
			}
			M--;
		}
		
	}
	static void union(int a, int b) {
		root[find(b)] = find(a);
	}
	static int find(int a) {
		if(root[a] == -1) return a;
		return root[a] = find(root[a]);
	}
}
