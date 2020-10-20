package coupang_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Problem04 {
	public static void main(String[] args) {
		String roads[][] = {{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","DAEJEON"},{"SEOUL","ULSAN"},{"DAEJEON","DAEGU"},{"GWANGJU","BUSAN"},{"DAEGU","GWANGJU"},{"DAEGU","BUSAN"},{"ULSAN","DAEGU"},{"GWANGJU","YEOSU"},{"BUSAN","YEOSU"}};
		solution("SEOUL","DAEGU","YEOSU",roads);
	}
	
	public static int solution(String depar, String hub, String dest, String[][] roads) {
		
		Map<String, ArrayList<String>> map = new HashMap<>();
		for(String[] road : roads) {
			if(!map.containsKey(road[0])) map.put(road[0], new ArrayList<>());
			map.get(road[0]).add(road[1]);
		}
		Queue<String> q = new LinkedList<>();
		q.offer(depar);
		int depar_hub = 0;
		while(!q.isEmpty()) {
			String cur = q.poll();
			if(cur.equals(hub)) {
				depar_hub++; continue;
			}
			if(map.get(cur)==null) continue;
			for(String s : map.get(cur)) {
				q.offer(s);
				System.out.println(s);
			}
		}
		System.out.println(depar_hub);
		q.offer(hub);
		int hub_dest = 0;
		while(!q.isEmpty()) {
			String cur = q.poll();
			if(cur.equals(hub)) {
				hub_dest++; continue;
			}
			if(map.get(cur)==null) continue;
			for(String s : map.get(cur)) {
				q.offer(s);
			}
		}
		System.out.println(depar_hub*hub_dest);
		return depar_hub*hub_dest;
	}
}
