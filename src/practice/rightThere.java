package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class rightThere {
	
	static long tree[];
	static int N;
	static long M;
	static long ans = 0;
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		 
		 br = new BufferedReader(new InputStreamReader(System.in));
		 st =  new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Long.parseLong(st.nextToken());
		
		 tree = new long[N];
		 st = new StringTokenizer(br.readLine());
		 int i = 0;
		 while(st.hasMoreTokens()) {
			 tree[i++] = Long.parseLong(st.nextToken());
		 }
		 Arrays.sort(tree);
		 //System.out.println(Arrays.toString(tree));
		 
		 search(0, tree[tree.length-1]);
		 System.out.println(ans);
		 
		 
		 
	 }
	
	static void search(long start, long end) {
		
		if(start > end) return;
		long mid = (start+end)/2;
		//System.out.println("Mid : "+mid);
		long temp = calc(mid);
		//System.out.println("temp : " + temp);
		if(temp > M) {
			if(mid > ans) ans = mid;
			search(mid+1, end);
		}
		else if(temp < M){
			search(start, mid-1);
		}
		else {
			ans = mid;
		}
		
	}
	
	static long calc(long height) {
		long temp = 0;
		for(long k : tree) {
			if((k-height)>0)  temp+=(k-height) ;
		}
		return temp;
	}
	

}
