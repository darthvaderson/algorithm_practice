package coupang_2020;

import java.util.PriorityQueue;

class Kiosk implements Comparable<Kiosk>{
	
	int idx;
	int month;
	int day;
	int hour;
	int min;
	int second;
	int count;
	
	Kiosk(int idx){
		this.idx = idx;
	}
	int compareDate(int month, int day, int hour, int min, int second) {
		if(this.month != month) {
			return this.month-month;
		}else if(this.day != day) {
			return this.day-day;
		}else if(this.hour != hour) {
			return this.hour = hour;
		}else if(this.min != min) {
			return this.min - min;
		}else if(this.second != second) {
			return this.second - second;
		}
		return 0;
	}
	void update(int time) {
		min += time;
		count++;
		if(min >= 60) {
			min %= 60;
			hour += 1;
			if(hour >= 24) {
				hour %= 24;
				if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					if(day == 31) {
						day = 1;
						month = (month+1)%12;
					}else {
						day++;
					}
				}else if (month == 4 || month == 6 || month == 9 || month == 11) {
					if(day == 30) {
						day = 1;
						month = month+1;
					}else {
						day++;
					}
				}else {
					if(day == 28) {
						day = 1;
						month = month+1;
					}else {
						day++;
					}
				}
			}
		}
	}
	
	@Override
	public int compareTo(Kiosk K) {
		
		if(month != K.month) {
			return month-K.month;
		}else if(day != K.day) {
			return day-K.day;
		}else if(hour != K.hour) {
			return hour-K.hour;
		}else if(min != K.min) {
			return min-K.min;
		}else if(second != K.second) {
			return second-K.second;
		}else {
			return idx- K.idx;
		}
		
	}
	@Override
	public String toString() {
		return ""+idx + " month : "+month+" day : "+day+" hour : "+hour+" min : "+min+" second : "+second;
	}
	
	
}
public class Problem02 {
	
	static PriorityQueue<Kiosk> pq = new PriorityQueue<>();
	int answer;
	
	public static void main(String[] args) {
		String customers[] = {"10/01 23:20:25 30", "10/01 23:25:50 26", "10/01 23:31:00 05", "10/01 23:33:17 24", "10/01 23:50:25 13", "10/01 23:55:45 20", "10/01 23:59:39 03", "10/02 00:10:00 10"};
		String customers2[] = {"02/28 23:59:00 03","03/01 00:00:00 02", "03/01 00:05:00 01"};
		
		solution(2, customers2);
		
	}
	
	static void solution(int n, String[] customers) {
		for(int i = 0 ; i < n ; i++) {
			pq.offer(new Kiosk(i));
		}
		System.out.println(pq);
		for(String customer : customers) {
			String infos[] = customer.split(" ");
			int month = Integer.parseInt(infos[0].split("/")[0]);
			int day = Integer.parseInt(infos[0].split("/")[1]);
			int hour = Integer.parseInt(infos[1].split(":")[0]);
			int minute = Integer.parseInt(infos[1].split(":")[1]);
			int second = Integer.parseInt(infos[1].split(":")[2]);
			int time = Integer.parseInt(infos[2]);
			
			Kiosk cur = pq.poll();
			if(cur.month == 0) {
				cur.month = month; cur.day = day; cur.hour = hour; cur.min = minute; cur.second = second;
			}else if(cur.compareDate(month,  day, hour, minute, second) < 0) {
				cur.month = month; cur.day = day; cur.hour = hour; cur.min = minute; cur.second = second;
			}			
			cur.update(time);
			
			System.out.println(cur);
			pq.offer(cur);
		}
		int max = -1;
		while(!pq.isEmpty()) {
			Kiosk cur = pq.poll();
			if(max < cur.count ) max = cur.count; 
		}
		System.out.println(max);
		int answer;
		
	}
}
