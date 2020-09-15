package line_2020_09_13;

import java.util.ArrayList;

public class Problem02 {
	
	
	public static void main(String[] args) {
		
	int ball[] = {1, 2, 3, 4, 5, 6};
	int order[] = {6,2,5,1,4,3};
	
		solution(ball, order);
		
		
		
	}
	
	static int[] solution(int[] ball, int[] order) {
		int[] answer = new int[ball.length];
		int index = 0;
		ArrayList<Integer> container = new ArrayList<>();
		
		int l = 0;
		int r = ball.length-1;
		
		for(int i : order) {
			container.add(i);
			for(int j = 0 ; j < container.size(); j++) {
				if(container.get(j) == ball[l]) {
					answer[index++] = ball[l];
					container.remove(j);
					l++; j = -1;
				}
				else if(container.get(j) == ball[r]) {
					answer[index++] = ball[r];
					container.remove(j);
					r--; j = -1;
				}
			}
		}		
		
		return answer;
	}

}
