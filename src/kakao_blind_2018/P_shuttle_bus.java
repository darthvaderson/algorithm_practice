package kakao_blind_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Bus{
	
	int hour;
	int minute;
	int capacity;
	
	ArrayList<String> passengers = new ArrayList<>();
	
	
	Bus(int hour, int minute, int capacity){
		this.hour = hour;
		this.minute = minute;
		this.capacity = capacity;
	}
	
}
public class P_shuttle_bus {
	
	static ArrayList<Bus> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		String[] timetable1 = {"08:00", "08:01", "08:02", "08:03"};
		String[] timetable2 = {"09:10", "09:09", "08:00"};
		
//		System.out.println(solution(1,1,5, timetable1));
		System.out.println(solution(2,10,2, timetable2));
		
	}
	
	static String solution(int n, int t, int m, String[] timetable) {
		
		int hour = 9;
		int minute = 0;
		int ans_hour;
		int ans_minute;
		
		list.add(new Bus(9,0,m));
		for(int i = 1 ; i < n ; i++) {
			minute += t;
			if(minute >= 60) {
				hour++;
				minute -= 60;
			}
			list.add(new Bus(hour, minute, m));
		}
		Arrays.sort(timetable, new Comparator<>() {
			public int compare(String a, String b) {
				String temp1[] = a.split(":");
				String temp2[] = b.split(":");
				int a_hour = Integer.parseInt(temp1[0]);
				int a_min = Integer.parseInt(temp1[1]);
				int b_hour = Integer.parseInt(temp2[0]);
				int b_min = Integer.parseInt(temp2[1]);
				if(a_hour != b_hour) {
					return a_hour-b_hour;
				}else if(a_min != b_min) {
					return a_min-b_min;
				}else return 0;
			}
		});
		System.out.println(Arrays.toString(timetable));
		for(String s : timetable) {
			String temp[] = s.split(":");
			int cur_h = Integer.parseInt(temp[0]);
			int cur_m = Integer.parseInt(temp[1]);
			
			for(Bus bus : list) {
				if(bus.capacity == 0) continue;
				if(cur_h < bus.hour) {
					bus.capacity--;
					bus.passengers.add(s);
					break;
				}else if(cur_h ==bus.hour && cur_m <= bus.minute) {
					bus.capacity--;
					bus.passengers.add(s);
					break;
				}
			}
		}
		
		Bus last = list.get(list.size()-1);
		System.out.println(last.capacity);
		if(last.capacity > 0) {
			ans_hour = last.hour;
			ans_minute = last.minute;
		}else {
			String last_passenger = last.passengers.get(last.passengers.size()-1);
			String[] temp = last_passenger.split(":");
			if(Integer.parseInt(temp[1]) == 0) {
				ans_hour = Integer.parseInt(temp[0])-1;
				ans_minute = 59;
			}else {
				ans_hour = Integer.parseInt(temp[0]);
				ans_minute = Integer.parseInt(temp[1])-1;
			}
		}
		
		if(ans_hour <= 9) {
			if(ans_minute <= 9) {
				return "0"+ans_hour+":0"+ans_minute;
			}else {
				return "0"+ans_hour+":"+ans_minute;
			}
		}else {
			if(ans_minute <= 9) {
				return ""+ans_hour+":0"+ans_minute;
			}else {
				return ""+ans_hour+":"+ans_minute;
			}
		}
	}
}
