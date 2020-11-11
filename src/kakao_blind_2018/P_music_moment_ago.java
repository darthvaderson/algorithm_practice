package kakao_blind_2018;

public class P_music_moment_ago {
	
	
	public static void main(String[] args) {
		String m = "ABCDEFG";
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String m2 = "CC#BCC#BCC#BCC#B";
		String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
		String m3 = "ABC";
		String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		
		System.out.println(solution(m3, musicinfos3));
		
	}
	
	static String solution(String m, String[] musicinfos) {
		
		String answer = "";
		int max_play_time = -1;		
		m = m.replaceAll("C#", "c");
		m = m.replaceAll("D#", "d");
		m = m.replaceAll("F#", "f");
		m = m.replaceAll("G#", "g");
		m = m.replaceAll("A#", "a");
		for(String info : musicinfos) {
			String[] temp = info.split(",");
			String melody = temp[3];
			melody = melody.replaceAll("C#", "c");
			melody = melody.replaceAll("D#", "d");
			melody = melody.replaceAll("F#", "f");
			melody = melody.replaceAll("G#", "g");
			melody = melody.replaceAll("A#", "a");
			
			int start = Integer.parseInt(temp[0].split(":")[0])*60 + Integer.parseInt(temp[0].split(":")[1]);
			int end = Integer.parseInt(temp[1].split(":")[0])*60 + Integer.parseInt(temp[1].split(":")[1]);
			int playtime = end-start;
			StringBuffer sb = new StringBuffer();
			int idx = 0;
			while(playtime > 0) {
				idx = idx%melody.length();
				sb.append(melody.substring(idx,idx+1));
				idx++; playtime --;
			}
			String playthrough = sb.toString();
			System.out.println(playthrough);
			if(playthrough.contains(m)) {
				if(max_play_time < end-start) {
					answer = temp[2];
					max_play_time = end-start;
				}
			}
			
		}
		if(answer.equals("")) return "`(None)`";
		else return answer;
		
	}
}
