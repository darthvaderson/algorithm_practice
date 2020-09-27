package kakao_blind_2020;

import java.util.ArrayList;
import java.util.Collections;

class Fragment implements Comparable<Fragment>{

	int x;
	int y;
	int a;

	Fragment(int x, int y, int a){
		this.x = x;
		this.y = y;
		this.a = a;
	}

	@Override
	public boolean equals(Object O) {
		if(O instanceof Fragment) {
			Fragment cur = (Fragment)O;
			return cur.x==x && cur.y==y && cur.a==a;
		}
		return false;
	}
	@Override
	public int compareTo(Fragment opponent) {
		if(x != opponent.x) {
			return x-opponent.x;
		}
		else if(y != opponent.y){
			return y-opponent.y;
		}
		else {
			return a-opponent.a;
		}
	}
	@Override
	public String toString() {

		return "x : "+x+", y : "+y +", a : "+a;
	}

}
public class Problem05 {

	static ArrayList<Fragment> map[][];
	static ArrayList<Fragment> list = new ArrayList<>();
	static int build_frame[][] = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
	static int build_frame2[][] = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
	static int num;
	
	public static void main(String[] args) {


		solution(5, build_frame2);
	}


	static int[][] solution(int n, int[][] build_frame){
		num = n;
		int[][] answer;
		map = new ArrayList[n+1][n+1];
		for(int i = 0 ; i < n+1 ; i++) {
			for(int j = 0 ; j < n+1 ; j++) {
				map[i][j] = new ArrayList<Fragment>();
			}
		}

		for(int[] frame : build_frame) {
			System.out.println(list);
			
			if(frame[3] == 1) {

				if(check(frame[0],frame[1],frame[2])) {
					list.add(new Fragment(frame[0],frame[1],frame[2]));
					map[frame[1]][frame[0]].add(new Fragment(frame[0],frame[1],frame[2]));
				}
			}
			else {
				list.remove(new Fragment(frame[0],frame[1],frame[2]));
				map[frame[1]][frame[0]].remove(new Fragment(frame[0],frame[1],frame[2]));
				boolean flag = true;
				if(frame[2] == 0) {
					if(frame[0]+1 < n+1 && frame[1]+1 <n+1) {
						for(Fragment cur : map[frame[1]+1][frame[0]+1]) {
							if(!check(cur.x,cur.y,cur.a)) flag = false;
						}
					}
					if(frame[0]-1 >= 0 && frame[1]+1 < n+1) {
						for(Fragment cur : map[frame[1]+1][frame[0]-1]) {
							if(!check(cur.x,cur.y,cur.a)) flag = false;
						}
					}
				}
				else {
					if(frame[0]+1 < n+1)
					{for(Fragment cur : map[frame[1]][frame[0]+1]) {
						if(!check(cur.x,cur.y,cur.a)) flag = false;
					}
					}
					for(Fragment cur : map[frame[1]][frame[0]]) {
						if(!check(cur.x,cur.y,cur.a)) flag = false;
					}
					if(frame[0]-1 >= 0) {
					for(Fragment cur : map[frame[1]][frame[0]-1]) {
						if(!check(cur.x,cur.y,cur.a)) flag = false;
					}
					}
				}
				if(!flag) {
					list.add(new Fragment(frame[0],frame[1],frame[2]));
					map[frame[1]][frame[0]].add(new Fragment(frame[0],frame[1],frame[2]));
				}
			}

		}
		Collections.sort(list);


		answer = new int[list.size()][3];
		for(int i = 0 ; i < list.size(); i++) {
			answer[i][0] = list.get(i).x;
			answer[i][1] = list.get(i).y;
			answer[i][2] = list.get(i).a;
		}

		for(int i = 0 ; i < answer.length ; i++) {
			System.out.println(answer[i][0]+" "+answer[i][1]+" "+answer[i][2]);
		}
		return answer;
	}

	static boolean check(int x, int y, int a) {

		if(a == 0) {
			if(y==0) return true; //¶¥
			if(x-1 >= 0 && map[y][x-1].contains(new Fragment(x-1,y,1))) return true; 
			if(map[x][y].contains(new Fragment(x,y,1))) return true;
			if(y-1 >= 0 && map[y-1][x].contains(new Fragment(x,y-1,0))) return true;
		}
		else {
			if(y-1 >= 0 && map[y-1][x].contains(new Fragment(x,y-1,0))) return true;
			if(x+1 < num+1 && y-1 >= 0 && map[y-1][x+1].contains(new Fragment(x+1,y-1,0))) return true;
			if(x-1 >= 0 && x+1 < num+1 && map[y][x-1].contains(new Fragment(x-1,y,1)) && map[y][x+1].contains(new Fragment(x+1,y,1))) return true;
		}

		return false;
	}


}
