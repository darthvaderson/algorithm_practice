package kakao_blind_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

class Block {
	
	int idx;
	ArrayList<Pos> element = new ArrayList<>();
	Pos vertex;
	ArrayList<Pos> checkPoint = new ArrayList<>();
	
}
class Pos{
	int r;
	int c;
	int idx;
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}
	Pos(int r, int c, int idx) {
		this.r = r;
		this.c = c;
		this.idx = idx;
	}
	@Override
	public String toString() {
		
		return "r : "+r+", c : "+c;
	}
}
public class Problem07 {
	
	
	static ArrayList<Block> list = new ArrayList<>();
	static int[][] move = {{0,1}, {1,0}, {-1,0}, {0,-1}};

	
	public static void main(String[] args) {
		
		int[][] board = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}};
		System.out.println(solution(board));
		
	}
	static void register(int[][] board) {
		
		boolean visit[][] = new boolean[board.length][board[0].length];
		Queue<Pos> Q = new LinkedList<>();
		
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board[0].length ; j++) {
				if(visit[i][j]) continue;
				if(board[i][j] != 0) {
					Block block = new Block();
					block.element.add(new Pos(i,j,board[i][j]));
					Q.clear();
					Q.offer(new Pos(i,j,board[i][j]));
					visit[i][j] = true;
					while(!Q.isEmpty()) {
						Pos cur = Q.poll();
						
						for(int k = 0 ; k < 4 ; k++) {
							int new_r = cur.r + move[k][0];
							int new_c = cur.c + move[k][1];
							if(new_r < 0 || new_c < 0 || new_r >= board.length || new_c >= board[0].length) continue;
							if(visit[new_r][new_c]) continue;
							if(cur.idx != board[new_r][new_c]) continue;
							Q.offer(new Pos(new_r, new_c, cur.idx));
							visit[new_r][new_c] = true;
							block.element.add(new Pos(new_r,new_c,cur.idx));
						}
						
					}
					list.add(block);
				}
			}	
		}
		
	}
	static void blockTypeCheck(Block block) {
		
		Map<Integer, Integer> row = new HashMap<>();
		Map<Integer, Integer> col = new HashMap<>();
		
		for(Pos pos : block.element) {
			if(row.containsKey(pos.r)) {
				row.put(pos.r, row.get(pos.r)+1);
			}else {
				row.put(pos.r, 1);
			}
			if(col.containsKey(pos.c)) {
				col.put(pos.c, col.get(pos.c)+1);
			}else {
				col.put(pos.c, 1);
			}
		}
		int c = 0, r = 0;
		int row_mx = -1;
		Pos vertex;
		if(col.size() > row.size()) {
			for(Entry<Integer, Integer> e : col.entrySet()) {
				if(e.getValue() == 2) c = e.getKey();
			}
			for(Entry<Integer, Integer> e : row.entrySet()) {
				if(e.getValue() == 1) r = e.getKey();
				if(e.getKey() > row_mx) row_mx = e.getKey();
			}
			vertex = new Pos(r,c);
			if(row_mx > vertex.r) {
				for(Pos pos : block.element) {
					if(pos.r == vertex.r || pos.c == vertex.c) continue;
					block.checkPoint.add(pos);
				}
			}
			

		}else {
			for(Entry<Integer, Integer> e : row.entrySet()) {
				if(e.getValue() == 2) r = e.getKey();
				if(e.getKey() > row_mx) row_mx = e.getKey();
			}
			for(Entry<Integer, Integer> e : col.entrySet()) {
				if(e.getValue() == 1) c = e.getKey();
			}
			vertex = new Pos(r,c);
			if(row_mx == vertex.r) {
				block.checkPoint.add(vertex);
			}

		}
		System.out.println(block);
		for(Pos pos : block.checkPoint) System.out.print(pos+" / ");
		System.out.println();
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		
	}
	
	static int solution(int[][] board) {
		
		//  1. Block Registration
		register(board);
		System.out.println(list.size());
		//  2. Block Type Check
		for(Block block : list) {
			blockTypeCheck(block);
		}
		/** 3. While Loop
		 * 	for every single block in array list
			1) block type check
				true -> go to 2)
				false -> check next block
			2) simulate
				removable -> remove and begin while loop again
				can't be removed -> check next block
				
			if not a single block removed in this iteration, break
		**/
		int cnt = 0;
		while(true) {
			boolean outer_flag = false;
			
			for(Block block : list) {
				
				if(block.checkPoint.size()==0) continue;
				boolean inner_flag = true;
				for(Pos pos : block.checkPoint) {
					int r = pos.r;
					int c = pos.c;
					for( r-=1 ; r >= 0 ; r--) {
						if(board[r][c] != 0 ) {
							inner_flag = false; 
							break;
						}
					}
					if(!inner_flag) break;
				}
				if(inner_flag) {
					System.out.println("yeah");
					for(Pos pos : block.element) {
						board[pos.r][pos.c] = 0;
					}
					list.remove(block);
					cnt++;
					outer_flag = true;
					break;
				}
			}
			if(!outer_flag) break;
		}
		
		return cnt;
		
	}
}
