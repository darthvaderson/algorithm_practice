package kakao_blind_2019;
import java.util.*;

class Page{
	
	int idx;
	String url;
	double basicScore;
	double linkScore;
	ArrayList<String> outerLink = new ArrayList<>();
	static Map<String, Page> pages = new HashMap<>();
	
	Page(int idx){
		this.idx = idx;
	}
	
	void calcLinkScore() {
		for(String url : outerLink) {
			if(!pages.containsKey(url)) continue;
			pages.get(url).linkScore += basicScore/outerLink.size();
		}
	}
	@Override
	public String toString() {
		return "idx : "+idx+"\nurl : "+url+"\nbasicscore : "+basicScore+"\nlinkScore : "+linkScore+"\nouterLink : "+outerLink.toString();
	}
}
public class Problem06 {
	
	static ArrayList<Page> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		String pages[] = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>123<a href=\"https://hashmode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
		String pages2[] = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
		System.out.println(solution("Muzi", pages));
		System.out.println(list.get(0));
//
//		System.out.println(solution("blind",pages2));
	}
	
	public static int solution(String word, String[] pages) {
		
		for(int i = 0 ; i < pages.length ; i++) {
			///
			Page current = new Page(i);
			String temp[] = pages[i].split("\n");
			///
//			for(String s : temp) System.out.println(s);
			String urlUnprocessed = temp[3].trim().split(" ")[2];
//			System.out.println("url : "+urlUnprocessed);
			StringBuffer sb = new StringBuffer();
			for(int j = 0 ; j < urlUnprocessed.length(); j++) {
				if(urlUnprocessed.substring(j,j+1).equals("\"")){
					j++;
					while(!urlUnprocessed.substring(j,j+1).equals("\"")){
						sb.append(urlUnprocessed.substring(j,j+1));
						j++;
					}
					break;
				}
			}
			current.url = sb.toString();
			Page.pages.put(current.url, current);

			///
//			ArrayList<String> body = new ArrayList<>();
//			for(int j = 6 ; j < temp.length ; j++) {
//				if(temp[j].equals("</body>")) break;
//				body.add(temp[j]);
//			}
			String match = "[^A-z<>\\\\]";
			for(String s : temp) {
				if(s.contains("<a href=")) {
					System.out.println("123123"+s);
					String morph_1[] = s.split(" ");
					System.out.println(Arrays.toString(morph_1));
					
					for(String w : morph_1) {
						if(w.startsWith("href")) {
							sb.delete(0, sb.length());
							for(int k = 6 ; k < w.length(); k++) {
								if(w.substring(k,k+1).equals("\"")) break;
								sb.append(w.substring(k,k+1));
							}
							if(!current.outerLink.contains(sb.toString())) current.outerLink.add(sb.toString());
						}
						
					}
				}
				
				String morph_2[] = s.replaceAll(match," ").split(" ");
//				System.out.println(Arrays.toString(morph_2));
				for(String w : morph_2) {
					if(w.toLowerCase().equals(word.toLowerCase())) current.basicScore++;
				}
			}
			list.add(current);
		}
		
		for(int i = 0 ; i < list.size() ; i++) {
			list.get(i).calcLinkScore();
		}
		Collections.sort(list, new Comparator<Page>(){
			@Override
			public int compare(Page a, Page b) {
				if(a.linkScore+a.basicScore == b.linkScore+b.basicScore) {
					return a.idx-b.idx;
				}else if(a.linkScore+a.basicScore > b.linkScore+b.basicScore){
					return -1;
				}else {
					return 1;
				}
				
			}
		});
		return list.get(0).idx;
	}
}
