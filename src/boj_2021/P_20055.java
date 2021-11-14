package boj_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Robot{
	int pos;
	Robot(int pos){
		this.pos = pos;
	}
	public String toString() {
		return "Pos : "+pos;
	}
}
public class P_20055 {

	static int N, K;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] belt;
	static LinkedList<Robot> robot;
	static boolean[] robot_presence;
	static int count, answer = 1;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2*N];
		robot = new LinkedList<>();
		robot_presence = new boolean[2*N];
		
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		while(st.hasMoreTokens()) {
			belt[idx++] = Integer.parseInt(st.nextToken());
		}

		while(true) {
			System.out.println("Answer : " + answer);
			rotate();
			move();
			putOn();
			if(count >= K) break;
			answer ++;
		}
		
	}
	static void rotate() {
		int temp_1 = belt[2*N-1];
		for(int i = 2*N-1 ; i > 0 ; i--) {
			belt[i] = belt[i-1];
		}
		belt[0] = temp_1;

		for(Iterator<Robot> it = robot.iterator(); it.hasNext();) {
			Robot r = it.next();
			r.pos = r.pos+1;
			robot_presence[r.pos] = true;
			robot_presence[r.pos-1] = false;
			if(r.pos == N-1) {
				robot_presence[r.pos] = false;
				it.remove();
			}
		}
		
	}
	static void move() {
//		for(Iterator<Robot> it = robot.iterator(); it.hasNext();) {
//			Robot r = it.next();
//			int temp = r.pos+1;
//
//			if(robot_presence[temp]) continue;
//			if(belt[temp] < 1) continue;
//			//이동 실시
//
//			robot_presence[r.pos] = false;
//			r.pos = temp;
//			robot_presence[r.pos] = true;
//			if ((--belt[r.pos])==0)  count++ ;
//			if(r.pos == N-1) {
//				it.remove();
//				robot_presence[r.pos] = false;
//			}
//			
//		}
		for(Robot r : new LinkedList<>(robot)) {
			int temp = r.pos+1;
			if(robot_presence[temp]) continue;
			if(belt[temp] < 1) continue;
			//이동 실시

			robot_presence[r.pos] = false;
			r.pos = temp;
			robot_presence[r.pos] = true;
			if ((--belt[r.pos])==0)  count++ ;
			if(r.pos == N-1) {
				robot.remove(r);
				robot_presence[r.pos] = false;
			}
		}
		
	}
	static void putOn() {
		if(belt[0] > 0) {
			robot.add(new Robot(0));
			robot_presence[0] = true;
			if ((--belt[0])==0)  count++ ;
		}
	
	}
}
