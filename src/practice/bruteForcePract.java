package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bruteForcePract {

	static BufferedReader br;
	static StringTokenizer st;
	static int N,M;
	static char Map[][];
	static int Min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {


		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map = new char[N][M];

		int i = 0;
		String temp;
		while(i < N) {
			temp = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				Map[i][j] = temp.charAt(j);
			}
			i++;
		}

		//		for(i = 0 ; i < N ; i++) {
		//			System.out.println(Arrays.toString(Map[i]));
		//		}

		search(Map);
		System.out.println(Min);
	}

	static void search(char[][] Map) {

		for(int i = 0 ; i < N-7 ; i++) {
			for(int j = 0 ; j < M-7 ; j++) {
				for(int k = 0 ; k < 2 ; k++) {
					int count = 0;
					// k = 0: WBWB..., k = 1: BWBW...
					for(int r = i ; r < i+8 ; r++) {
						for(int c = j ; c < j+8 ; c++) {
							int val = (r+c+k)%2;
							char ch = (val==0) ? 'W':'B';
							if(Map[r][c] != ch) count++;
						}
					}
					Min = Math.min(count, Min);
				}


			}
		}

	}
}
