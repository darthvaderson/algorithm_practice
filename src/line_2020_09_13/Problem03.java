package line_2020_09_13;

public class Problem03 {

	
	static int ans = Integer.MAX_VALUE;
	static int num; 
	
	public static void main(String[] args) {
		
		
		solution(1003004);
		System.out.println();
	}
	
	
	
	static void solution(int n) {
		
		dfs(n, 0);
		
		
	}
	
	
	static void dfs(int n, int count) {
		
		if(n >= 0 && n <= 9) {
			System.out.println("n : "+n + " count : "+count);
			if(count < ans) {
				ans = count;
				num = n;
			}
			return;
		}
		
		String cur = String.valueOf(n);
		
		for(int i = 0 ; i < cur.length()-1 ; i++) {
			
			if(i!=cur.length()-2 && cur.substring(i+1,i+2).equals("0")) continue;
			dfs(calc(cur,i), count+1);
			
		}
		
		
	}
	
	
	static int calc(String n, int index) {
		
		int temp = Integer.parseInt(n.substring(0,index+1))+Integer.parseInt(n.substring(index+1, n.length()));
		return temp;
		
	}
	
	
}
