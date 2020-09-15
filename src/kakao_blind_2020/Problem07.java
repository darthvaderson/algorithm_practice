package kakao_blind_2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Robot{
	Pos a;
	Pos b;
	int dis;

	Robot(Pos a, Pos b, int dis){
		this.a = a;
		this.b = b;
		this.dis = dis;
	}
}
class Pos{

	int r;
	int c;
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object o) {
		if(o==null || !(o instanceof Pos)) return false;
		return r==((Pos)o).r && c==((Pos)o).c;
	}

}
public class Problem07 {

	static Queue<Robot> Q = new LinkedList<>();
	static ArrayList<ArrayList<Pos>> visit = new ArrayList<>();
	static int ans;
	static int move[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	static int move2[] = {-1,1};
	
	static int [][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};

	public static void main(String[] args) {


		solution(board);
		System.out.println(ans);


	}

	static void solution(int[][] board) {


		Robot robot = new Robot(new Pos(0,0), new Pos(0,1), 0);

		Q.offer(robot);
		ArrayList<Pos> tmp = new ArrayList<>();
		tmp.add(new Pos(0,0));
		tmp.add(new Pos(0,1));
		visit.add(tmp);

		while(!Q.isEmpty()) {
			Robot cur = Q.poll();

			if((cur.a.r == board.length-1 && cur.a.c == board.length-1)||(cur.b.r == board.length-1 && cur.b.c == board.length-1)) {
				ans = cur.dis;
				break;
			}

			for(int i = 0 ; i < 4 ; i++) {
				int new_a_r = cur.a.r+move[i][0];
				int new_a_c = cur.a.c+move[i][1];
				int new_b_r = cur.b.r+move[i][0];
				int new_b_c = cur.b.c+move[i][1];
				if(new_a_r < 0 || new_a_c < 0 || new_a_r>board.length-1 ||new_a_c > board.length-1 || board[new_a_r][new_a_c] == 1) continue;
				if(new_b_r < 0 || new_b_c < 0 || new_b_r>board.length-1 ||new_b_c > board.length-1 || board[new_b_r][new_b_c] == 1) continue;
				Pos new_a = new Pos(new_a_r, new_a_c);
				Pos new_b = new Pos(new_b_r, new_b_c);

				if(judge(new_a,new_b)) {
					Q.offer(new Robot(new_a, new_b, cur.dis+1));
					tmp = new ArrayList<>();
					tmp.add(new_a); tmp.add(new_b);
					visit.add(tmp);
				}
			}

			if(cur.a.r == cur.b.r) {

				for(int i : move2) {
					int new_a_r = cur.b.r+i;
					int new_a_c = cur.b.c;
					if(new_a_r < 0 || new_a_c < 0 || new_a_r>board.length-1 ||new_a_c > board.length-1 || board[new_a_r][new_a_c] == 1) continue;
					if(board[cur.a.r+i][cur.a.c] == 1) continue;
					Pos new_a = new Pos(new_a_r, new_a_c);
					if(judge(new_a,cur.b)) {
						Q.offer(new Robot(new_a, cur.b, cur.dis+1));
						tmp = new ArrayList<>();
						tmp.add(new_a); tmp.add(cur.b);
						visit.add(tmp);
					}
				}
				for(int i : move2) {
					int new_b_r = cur.a.r+i;
					int new_b_c = cur.a.c;
					if(new_b_r < 0 || new_b_c < 0 || new_b_r>board.length-1 ||new_b_c > board.length-1 || board[new_b_r][new_b_c] == 1) continue;
					if(board[cur.b.r+i][cur.b.c] == 1) continue;
					Pos new_b = new Pos(new_b_r, new_b_c);
					if(judge(new_b,cur.a)) {
						Q.offer(new Robot(new_b, cur.a, cur.dis+1));
						tmp = new ArrayList<>();
						tmp.add(new_b); tmp.add(cur.a);
						visit.add(tmp);
					}
				}
				
			}
			else {
				for(int i : move2) {
					int new_a_r = cur.b.r;
					int new_a_c = cur.b.c+i;
					if(new_a_r < 0 || new_a_c < 0 || new_a_r>board.length-1 ||new_a_c > board.length-1 || board[new_a_r][new_a_c] == 1) continue;
					if(board[cur.a.r][cur.a.c+i] == 1) continue;
					Pos new_a = new Pos(new_a_r, new_a_c);
					if(judge(new_a,cur.b)) {
						Q.offer(new Robot(new_a, cur.b, cur.dis+1));
						tmp = new ArrayList<>();
						tmp.add(new_a); tmp.add(cur.b);
						visit.add(tmp);
					}
				}
				for(int i : move2) {
					int new_b_r = cur.a.r;
					int new_b_c = cur.a.c+i;
					if(new_b_r < 0 || new_b_c < 0 || new_b_r>board.length-1 ||new_b_c > board.length-1 || board[new_b_r][new_b_c] == 1) continue;
					if(board[cur.b.r][cur.b.c+i] == 1) continue;
					Pos new_b = new Pos(new_b_r, new_b_c);
					if(judge(new_b,cur.a)) {
						Q.offer(new Robot(new_b, cur.a, cur.dis+1));
						tmp = new ArrayList<>();
						tmp.add(new_b); tmp.add(cur.a);
						visit.add(tmp);
					}
				}

			}

		}

	}
	static boolean judge(Pos a, Pos b) {

		boolean flag = true;
		for(ArrayList<Pos> al : visit) {
			if(al.contains(a) && al.contains(b)) {
				flag = false; break;
			}
		}
		return flag;
	}

}
