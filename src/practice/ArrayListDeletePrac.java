package practice;

import java.util.ArrayList;

class Point{
	int pos;
	Point(int pos){
		this.pos = pos;
	}
}
public class ArrayListDeletePrac {

	public static void main(String[] args) {
		
		
		ArrayList<Point> list = new ArrayList<>();
		
		for(int i = 1 ; i <= 100 ; i ++) {
			list.add(new Point(i));
		}
		for(Point k : list) {
			System.out.println(k.hashCode());
		}
		System.out.println();
		for(Point k : new ArrayList<>(list)) {
			System.out.println(k.hashCode());
			if(k.pos % 2 == 0) {
				list.remove(k);
			}
		}
		System.out.println();
		
	}
}
