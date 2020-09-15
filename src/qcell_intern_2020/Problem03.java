package qcell_intern_2020;

import java.util.Arrays;
import java.util.Stack;

class Point{
	int r;
	int c;
	int n;
	int dir;
	int dis;

	Point(int r, int c, int n, int dir, int dis){
		this.r = r;
		this.c = c;
		this.n = n;
		this.dir = dir;
		this.dis = dis;
	}
}

public class Problem03 {

	static boolean visit[][];
	static int count = 0;
	static Stack<Point> st = new Stack<>();
	static int move[][] = {{1,0},{0,1},{-1,0},{0,-1}};

	public static void main(String[] args) {




	}

	public static int solution(int[][] board) {
		int answer = 0;
		visit = new boolean[4][4];
		for(int i = 0 ; i < 4 ; i++){
			for(int j = 0 ; j < 4 ; j++){
				dfs(new Point(i,j,board[i][j],0,1), board);
			}
		}
		if(count <=1) answer = -1;
		else answer = count;
		return answer;
	}

	static void dfs(Point P, int[][] board){
		int temp = 1;
		st.push(P);

		for(int i = 0 ; i < board.length ; i++)
		{Arrays.fill(visit[i], false);}
		visit[P.r][P.c] = true;

		while(!st.isEmpty()){
			Point cur = st.pop();
			while(cur.dir < 4){
				if(cur.dis > temp) temp = cur.dis;
				int new_r = cur.r + move[cur.dir][0];
				int new_c = cur.c + move[cur.dir][1];
				if(new_r < 0 || new_r > 3 || new_c < 0 || new_c > 3) {
					cur.dir++;
					continue;
				}
				if(visit[new_r][new_c] || board[new_r][new_c] != cur.n){
					cur.dir++;
					continue;
				}
				cur.dir++;
				st.push(cur);
				visit[new_r][new_c] = true;
				Point tem = new Point(new_r, new_c, cur.n, 0, cur.dis+1);
				cur = tem;
			}
			visit[cur.r][cur.c] = false;
		}
		if(temp > count) count = temp;

	}

}
