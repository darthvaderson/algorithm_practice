package boj_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_12865 {
	
	static int Number, Capacity;
	static int[] Weight, Value;
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] DP;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		Number = Integer.parseInt(st.nextToken());
		Capacity = Integer.parseInt(st.nextToken());
		Weight = new int[Number+1];
		Value = new int[Number+1];
		DP = new int[Number+1][Capacity+1];
		
		int i = 1;
		while(i <= Number) {
			st = new StringTokenizer(br.readLine());
			Weight[i] = Integer.parseInt(st.nextToken());
			Value[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		calculate();
		for(i = 0 ; i <= Number ; i++) {
			System.out.println(Arrays.toString(DP[i]));
		}
		System.out.println(DP[Number][Capacity]);
		
	}
	
	static void calculate() {
		
		for(int i = 1 ; i <= Number ; i++) {
			for(int j = 1 ; j <= Capacity ; j++) {
				
				int cur_weight = Weight[i];
				int cur_value = Value[i];
				if(cur_weight > j) {
					DP[i][j] = DP[i-1][j];
				}
				else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-cur_weight]+cur_value);
				}
			}
		}
	}
}
