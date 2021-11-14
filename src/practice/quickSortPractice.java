package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class quickSortPractice {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int Array[];
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Array = new int[N];
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		while(st.hasMoreTokens()) {
			Array[idx++] = Integer.parseInt(st.nextToken());
		}
		
		QuickSort(Array, 0, Array.length-1);
		System.out.println(Arrays.toString(Array));
		
		
	}
	
	static void QuickSort(int[] Array, int left, int right) {
		
		if(left >= right) return;
		
		int pivot = left;
		int p = left+1;
		int q = right;
		int temp;
		System.out.println(left+" "+right);
		
		while(p <= q) {
			
			while(p <= right && Array[p] <= Array[pivot]) {
				p++;
			}
			while(q > left && Array[q] > Array[pivot]) {
				q--;
			}
			if(p < q) {
				temp = Array[p];
				Array[p] = Array[q];
				Array[q] = temp;
				p++; q--;
			}
		}
		
		temp = Array[pivot];
		Array[pivot] = Array[p-1];
		Array[p-1] = temp;
		System.out.println(Arrays.toString(Array));
		QuickSort(Array, left, p-2);
		QuickSort(Array, p, right);
		
		
	}

}
