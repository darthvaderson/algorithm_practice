package kakao_blind_2018;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Log{

	long start;
	long end;
	Log(long start, long end){
		this.start = start;
		this.end = end;
	}

}
public class P_traffic {

	static ArrayList<Log> list = new ArrayList<>();
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		String[] lines1 = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
		String[] lines2 = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
		"2016-09-15 21:00:02.066 2.62s"};
		solution(lines2);
	}


	static int solution(String[] lines) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		for(String line : lines) {
			String arr[] = line.split(" ");
			try {
				long end = format.parse(arr[0]+" "+arr[1]).getTime();
				System.out.println(end);
				long duration = (long)(Double.parseDouble(arr[2].replace("s",""))*1000);
				long start = end-duration+1;
				System.out.println(start);
				list.add(new Log(start, end));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		for(Log from : list) {
			long start = from.start;
			long end = from.end;
			int startCnt = 0;
			int endCnt = 0;
			
			for(Log target : list) {
				if(start <= target.end && start+999 >= target.start) {
					startCnt++;
				}
				if(end <= target.end && end+999 >= target.start) {
					endCnt++;
				}
			}
			ans = Math.max(Math.max(startCnt, endCnt),ans);
		}
		System.out.println(ans);
		return ans;
	}
}
