package kakao_2020_09_12;
import java.util.*;


public class Problem02 {
	
	
	static String[] orders = {"XYZ", "XWY", "WXA"};
	static int[] course = {2,3,4};
	
	static HashMap<String, Integer> map = new HashMap<>();
	
	
	
	public static void main(String[] args) {
		
		solution(orders, course);
		
		
	}
	
	
	public static String[] solution(String[] orders, int[] course) {
		String[] answer ;
		List<String> ans = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		
		for(String order : orders) {
			for(int i = 0 ; i < order.length(); i++) set.add(order.substring(i,i+1));
		}
		System.out.println(set);
		ArrayList<String> list = new ArrayList<String>(set);
		ArrayList<String> menu = new ArrayList<>();
		
		for(int length : course) {
			menu.clear();
			map.clear();
			combination(0, length, menu, list);
			LinkedHashMap<String, Integer> sorted = sortMapByValue(map);
			System.out.println(sorted);
			int max_value = 1;
			for(Map.Entry<String, Integer> entry : sorted.entrySet()) {
				if(entry.getValue() ==1 || entry.getValue() < max_value) break;
				ans.add(entry.getKey().toString());
				max_value = entry.getValue();
			}
			System.out.println(ans);
		}
		
		Collections.sort(ans, (o1,o2) -> o1.compareTo(o2));
		answer = ans.toArray(new String[ans.size()]);
		for(String s : answer) System.out.println(s);
		return answer;
	}
	
	static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map){
		List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
		Collections.sort(entries, (o1,o2) -> o2.getValue() - o1.getValue());
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for(Map.Entry<String, Integer> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	static void combination(int pos, int length, ArrayList<String> menu, ArrayList<String> list) {
		
		if(menu.size() == length) {
			
			Collections.sort(menu, new Comparator<String> () {
				public int compare(String a, String b) {
					return a.compareTo(b);
				}
			});
			
			for(String order : orders) {
				if(length > order.length()) continue;
				ArrayList<String> temp = new ArrayList<>();
				for(int i = 0 ; i < order.length(); i++) temp.add(order.substring(i,i+1));
				if(temp.containsAll(menu)) {
					String key = "";
					for(String s : menu) key += s;
					if(map.containsKey(key)) map.put(key, map.get(key)+1);
					else map.put(key, 1);
				}
			}
			return;
			
		}
		for(int i = pos ; i < list.size() ; i++) {
			
			menu.add(list.get(i));
			combination(i+1, length, menu, list);
			menu.remove(list.get(i));
			
		}
		
	}

}
