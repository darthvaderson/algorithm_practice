package line_2020_09_13;

class Pos{
	
	int r;
	int c;
	int dir;
	Pos(int r, int c, int dir){
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}

public class Problem04 {
	
	
	static int[][] maze = {{0, 1, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}};
	static int[][] maze2 = {{0, 1, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 1, 0}, {0, 1, 1, 1, 1, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 0}};
	
	static int move[][] = {{1,0},{0,-1},{-1,0},{0,1}};
	
	
	public static void main(String[] args) {
		
		
		solution(maze2);
		
		
	}
	
	
	static int solution(int[][] maze) {
		int count = 0;
		Pos pos = new Pos(0, 0, 0);
		while(true) {
			
			if(pos.r == maze.length-1 && pos.c == maze.length-1) {
				break;
			}
			int left_r = pos.r+move[(4+pos.dir-1)%4][0];
			int left_c = pos.c+move[(4+pos.dir-1)%4][1];
			if(left_r >= 0 && left_r <= maze.length-1 && left_c >= 0 && left_c <= maze.length-1) {
				if(maze[left_r][left_c] == 0) {
					pos.dir = (4+pos.dir-1)%4;
					pos.r = left_r;
					pos.c = left_c;
					count++;
					continue;
				}
			}
			
			
			
			int new_r = pos.r + move[pos.dir][0];
			int new_c = pos.c + move[pos.dir][1];
			if(new_r < 0 || new_r > maze.length-1 || new_c <0 || new_c > maze.length-1) {
				pos.dir = (pos.dir+1)%4;
				continue;
			}
			if(maze[new_r][new_c] == 1) {
				pos.dir = (pos.dir+1)%4;
				continue;
			}
			if(maze[new_r][new_c] == 0) {
				pos.r = new_r;
				pos.c = new_c;
				count++;
				continue;
			}
			
		}
		System.out.println(count);
		return count;
		
	}

}
