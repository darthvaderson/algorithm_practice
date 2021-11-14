package boj_2021;

import java.util.Arrays;
import java.util.Scanner;

public class P_1929 {
	
	static int M, N;
	static Scanner scin;
	static boolean[] arr;
	
	public static void main(String[] args) {
		
		scin = new Scanner(System.in);
		String temp[] = scin.nextLine().split(" ");
		System.out.println(Arrays.toString(temp));
		M = Integer.parseInt(temp[0]);
		N = Integer.parseInt(temp[1]);
		arr = new boolean[1000001];
		calc();
		print(M, N);
		
		
	}
	static void calc() {
		int cur;
		arr[1] = true;
		for(int i = 2 ; i <= 1000001/2 ; i++) {
			cur = i;
			if(arr[cur]) continue;
			for(int j = 2 ; cur*j < 1000001 ; j++) {
				arr[cur *j] = true;
			}
		}
	}
	static void print(int a, int b) {
		for(int i = a ; i <= b ; i++) {
			if(!arr[i]) System.out.println(i);
		}
	}
}
