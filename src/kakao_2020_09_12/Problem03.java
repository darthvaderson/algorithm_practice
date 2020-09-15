package kakao_2020_09_12;

import java.util.ArrayList;
import java.util.Arrays;



public class Problem03 {
	
	
	static String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
	static String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

	public static void main(String[] args) {
		
		System.out.println("java and 1 and 2 and 3 and 4".split(" and ")[0]);
		solution(info, query);
		
		
	}
	
	public static int[] solution(String[] info, String[] query) {
		
		int[] answer = new int[query.length];
		ArrayList<String[]> infos = new ArrayList<>();
		
		for(String s : info) {
			infos.add(s.split(" "));
		}
		
		int index = 0;
		for(String s : query) {
			String[] q = s.replace(" and "," ").split(" ");
			System.out.println(Arrays.asList(q));
			int count = 0;
			for(String[] cur : infos) {
				boolean flag = true;
				for(int i = 0 ; i < q.length-1 ; i++) {
					if(q[i].equals("-")) continue;
					if(!q[i].equals(cur[i])) {
						flag = false;
						break;
					}
				}
				if(!flag) continue;
				if(Integer.parseInt(q[4]) > Integer.parseInt(cur[4])) flag = false;
				if(flag) count++;
			}
			answer[index++] = count;
		}
		return answer;
	}

}
