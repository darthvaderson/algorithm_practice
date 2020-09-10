package kakao_Intern_2020;
import java.util.*;

class Pos{
	
	int r;
	int c;
	int k;
	int dir;
	int dis;
	
	Pos(int r, int c, int k, int dir, int dis){
		this.r = r;
		this.c = c;
		this.k = k;
		this.dir = dir;
		this.dis = dis;
	}
	
	public String toString() {
		
		return ""+r+" "+c+" "+k+" "+dir+" dis : "+dis;
	}
}


public class Problem04 {
	
	static int space[][][][];
	static boolean visit[][][][];
	static int move[][] = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static Queue<Pos> Q = new LinkedList<>();
	static int[][] board = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		solution(board);
	}
	
	
	static void solution(int[][] board) {
		
		visit = new boolean[board.length][board.length][board.length*board.length+1][4];
		
		Q.offer(new Pos(0,0,0,0,0));
		Q.offer(new Pos(0,0,0,1,0));
		visit[0][0][0][0] = true;
		visit[0][0][0][1] = true;
		
		while(!Q.isEmpty()) {
			
			Pos P = Q.poll();
			if(P.r == board.length-1 && P.c == board.length-1) {
				int temp = P.dis*100+P.k*500;
				ans = (temp<ans) ? temp:ans;
				System.out.println(P);
				System.out.println(temp);
				continue;
			}
			if(P.k >= Math.pow(board.length, 2)) continue;
			
			for(int i = -1 ; i < 2 ; i++) {
				int cur_dir = P.dir;
				int new_dir, new_r, new_c;
				if(cur_dir + i < 0) new_dir = 3;
				else new_dir = (cur_dir + i)%4;
				
				if(i==0) {
					new_r = P.r+move[new_dir][0];
					new_c = P.c+move[new_dir][1];
					if(new_r < 0 || new_r > board.length-1 || new_c < 0 || new_c > board.length-1) continue;
					if(board[new_r][new_c] == 1) continue;
					if(visit[new_r][new_c][P.k][new_dir]) continue;
					Q.offer(new Pos(new_r,new_c,P.k,new_dir,P.dis+1));
					visit[new_r][new_c][P.k][new_dir] = true;
				}
				else {
					new_r = P.r+move[new_dir][0];
					new_c = P.c+move[new_dir][1];
					if(new_r < 0 || new_r > board.length-1 || new_c < 0 || new_c > board.length-1) continue;
					if(board[new_r][new_c] == 1) continue;
					if(visit[new_r][new_c][P.k+1][new_dir]) continue;
					Q.offer(new Pos(new_r,new_c,P.k+1,new_dir,P.dis+1));
					visit[new_r][new_c][P.k+1][new_dir] = true;
					
				}
			}
			
		}
		
		
	}

}



