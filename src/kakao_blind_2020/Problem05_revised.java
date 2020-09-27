package kakao_blind_2020;

import java.util.Arrays;
import java.util.Comparator;


public class Problem05_revised {

	static final int PILLAR = 0;
	static final int BEAM = 1;
	static final int DELETE = 0;
	static final int INSERT = 1;

	static int build_frame[][] = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
	static int build_frame2[][] = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
	static boolean pillar[][] = new boolean[103][103];
	static boolean beam[][] = new boolean[103][103];

	public static void main(String[] args) {


		solution(5, build_frame);
	}


	static void solution(int n, int[][] build_frame){

		int cnt = 0;
		for(int[] frame : build_frame) {

			int x = frame[0]+1;
			int y = frame[1]+1;

			if(frame[3] == INSERT) {
				if(frame[2] == PILLAR) {
					if(check(x,y,PILLAR)) {
						pillar[y][x] = true; cnt++;
						System.out.println(x+" "+y+" "+frame[2]);
					}
				}
				else if(frame[2] == BEAM) {
					if(check(x,y,BEAM)) {
						beam[y][x] = true; cnt++;
						System.out.println(x+" "+y+" "+frame[2]);
					}
				}
			}
			else if(frame[3] == DELETE) {
				if(frame[2] == PILLAR) {
					pillar[y][x] = false; cnt--;
					System.out.println("delete "+x+" "+y +" "+ PILLAR);
					if(!delete_check(x,y,PILLAR)) {
						pillar[y][x] = true; cnt++;
						System.out.println("retrive "+x+" "+y+" "+PILLAR);
					}
				}
				else if(frame[2] == BEAM) {
					beam[y][x] = false; cnt--;
					System.out.println("delete "+x+" "+y+" "+BEAM);
					if(!delete_check(x,y,BEAM)) {
						beam[y][x] = true; cnt++;
						System.out.println("retrive "+x+" "+y);
					}
				}
			}

		}
		int[][] answer = new int[cnt][3];
		cnt = 0;
		for(int i = 0 ; i < 103 ; i ++) {
			for(int j = 0 ; j < 103 ; j++) {
				if(pillar[j][i]) {
					answer[cnt++] = new int[]{i-1,j-1,0};
				}
				if(beam[j][i]) {
					answer[cnt++] = new int[]{i-1,j-1,1};
				}
			}
		}
		Arrays.sort(answer, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[0] != b[0]) return a[0]-b[0];
				if(a[1] != b[1]) return a[1]-b[1];
				if(a[2] != b[2]) return a[2]-b[2];
				return 0;
			}
		});
		for(int i = 0 ; i < answer.length ; i++) {
			System.out.println(Arrays.toString(answer[i]));
		}

	}
	static boolean delete_check(int x, int y, int kind) {

		boolean flag = true;
		if(kind == PILLAR) {
			
			for(int j = y ; j <= y+1 ; j++) {
				for(int i = x ; i >= x-1 ; i--) {
					if(pillar[j][i]) {
						if(!check(i,j,0)) flag = false;
					}
					if(beam[j][i]) {
						if(!check(i,j,1)) flag = false;
					}
				}
			}
		}
		else if(kind == BEAM) {
	
			for(int i = x-1 ; i <= x+1 ; i++) {
				if(pillar[y][i]) if(!check(i,y,0)) flag = false;
				if(beam[y][i]) if(!check(i,y,1)) flag = false;
			}
		}
		return flag;
	}

	static boolean check(int x, int y, int kind) {

		if(kind == PILLAR) {
			if(y==1) return true;
			if(pillar[y-1][x]) return true;
			if(beam[y][x] || beam[y][x-1]) return true;
		}
		else if(kind == BEAM){
			if(pillar[y-1][x] || pillar[y-1][x+1]) return true;
			if(beam[y][x-1] && beam[y][x+1]) return true;
		}

		return false;
	}






}
