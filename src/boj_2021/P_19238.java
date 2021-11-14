package boj_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	
	int r;
	int c;
	int dest_r;
	int dest_c;
	
	int dis = 0;
	
	Point(int r, int c){
		this.r = r;
		this.c = c;
	}
	Point(int r, int c, int dis){
		this.r = r;
		this.c = c;
		this.dis = dis;
	}
	Point(int r, int c, int dest_r, int dest_c){
		this.r = r;
		this.c = c;
		this.dest_r = dest_r;
		this.dest_c = dest_c;
	}
}

public class P_19238 {
	
	
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, fuel;
	static int[][] Map, temp;
	static boolean[][] Visit;
	static boolean flag;
	static boolean unreachable;
	static ArrayList<Point> passenger_LIST = new ArrayList<>();
	static Point taxi;
	static Point passenger;
	
	static Queue<Point> q = new LinkedList<>();
	static int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		
		
//		1. ArrayList에 승객 넣기, 승객은 현재위치와 목적지 정보를 가진 구조체
//		2. Taxi Point가 while 문 break되기 전까지 순회
//		3. Taxi 현재위치에서 승객까지의 거리 계산 후 승객 선택
//		4. 승객 위치까지 가는 연료 차감
//		5. 승객 목적지까지 연료 차감
//		6. 연료 충전(승객~목적지 거리 * 2 만큼 연료 충전)
//		7. 3번으로 회귀
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		Map = new int[N+1][N+1];
		temp = new int[N+1][N+1];
		
		int idx = 1;
		while(idx <= N) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) Map[idx][j] = -1;
			}
			idx++;
		}
		st= new StringTokenizer(br.readLine());
		taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			passenger_LIST.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		while(passenger_LIST.size() > 0) {
			
			searchPassenger();
			System.out.println();
			rideToPassenger();
			System.out.println();

			rideToDestination();
			if(flag||unreachable) break;
			System.out.println(passenger_LIST.size());
			System.out.println();
		}
		
		if(flag || unreachable) {
			System.out.println(-1);
		}else{
			System.out.println(fuel);
		}
	}
	// passenger to destination
	static void rideToDestination() {
		
		calc(passenger);
		fuel -= temp[passenger.dest_r][passenger.dest_c];
		taxi.r = passenger.dest_r;
		taxi.c = passenger.dest_c;
		if(fuel <  0) flag = true;
		if(!Visit[passenger.dest_r][passenger.dest_c]) unreachable = true;
		fuel += 2*temp[passenger.dest_r][passenger.dest_c];
		passenger_LIST.remove(passenger);
		
	}
	// taxi to passenger
	static void rideToPassenger() {
		
		if(!Visit[passenger.r][passenger.c]) unreachable = true;
		fuel -= temp[passenger.r][passenger.c];
		if(fuel < 0) flag = true;
		System.out.println("ride to Passenger, Fuel left : "+fuel);
	}
	// taxi 에서 가장 가까운 승객 찾기
	static void searchPassenger() {
		
		calc(taxi);
		temp[0][0] = Integer.MAX_VALUE;
		passenger = new Point(0, 0, Integer.MAX_VALUE);
		for(Point p : passenger_LIST) {
			if(temp[p.r][p.c] < temp[passenger.r][passenger.c] ) {
				passenger = p;
			}else if(temp[p.r][p.c] == temp[passenger.r][passenger.c]) {
				if(p.r < passenger.r ) passenger = p;
				else if(p.r == passenger.r) {
					if(p.c < passenger.c ) passenger = p;
				}
			}
		}
		System.out.println("CHOSE : "+passenger.r+" "+passenger.c);
	}
	// p를 기준으로 거리를 계산
	static void calc(Point p) {
		
		Visit = new boolean[N+1][N+1];
		for(int i = 0 ; i < Map.length ; i++) {
			System.arraycopy(Map[i], 0, temp[i], 0, Map[i].length);
		}
		q.offer(p);
		Visit[p.r][p.c] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			temp[cur.r][cur.c] = cur.dis;
			for(int i = 0 ; i < 4 ; i++) {
				int new_r = cur.r+pos[i][0];
				int new_c = cur.c+pos[i][1];
				if(new_r <= 0 || new_c <= 0 || new_r >= Map.length || new_c >= Map.length ) continue;
				if(temp[new_r][new_c]==-1)continue;
				if(Visit[new_r][new_c]) continue;
				q.offer(new Point(new_r, new_c, cur.dis+1));
				Visit[new_r][new_c] = true;
			}
		}
		for(int i = 0 ; i < temp.length ; i++) {
			System.out.println(Arrays.toString(temp[i]));
		}
	}

}
