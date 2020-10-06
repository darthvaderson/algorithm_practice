package kakao_blind_2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Node{
	
	int x;
	int y;
	int idx;
	Node parent;
	Node leftChild;
	Node rightChild;
	
	Node(int x, int y, int idx){
		this.x = x;
		this.y = y;
		this.idx = idx;
	}
	@Override
	public String toString() {
		return "x : "+x+", y : "+y+", idx : "+idx;
	}
	boolean hasParent() {
		if(parent==null) return false;
		else return true;
	}
	boolean isLeftChild() {
		if(parent == null) return false;
		else {
			if(parent.leftChild==this) return true;
			else return false;
		}
	}
	boolean isRightChild() {
		if(parent == null) return false;
		else {
			if(parent.rightChild==this) return true;
			else return false;
		}
	}
	
}


public class Problem05 {
	static int k;
	static int[][] answer;
	
	public static void main(String[] args) {
		
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		solution(nodeinfo);
	}
	
	static int[][] solution(int[][] nodeinfo) {
		answer = new int[2][nodeinfo.length];
		Queue<Node> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(Node a, Node b) {
				if(a.y!=b.y) {
					return b.y-a.y;
				}
				else {
					return a.x-b.x;
				}
			}
		});
		
		for(int i = 0 ; i < nodeinfo.length ; i++) {
			pq.offer(new Node(nodeinfo[i][0],nodeinfo[i][1],i+1));
		}
		Node root = pq.poll();
		ArrayList<Node> current = new ArrayList<>();
		ArrayList<Node> next = new ArrayList<>();
		current.add(root);
		while(!pq.isEmpty()) {
			int level =  pq.peek().y;
			while(!pq.isEmpty() &&pq.peek().y==level) {
				next.add(pq.poll());
			}

			for(int i = 0 ; i < current.size(); i++) {
				
				for(int j = 0 ; j < next.size(); j++) {
					if(next.get(j).hasParent()) continue;
					if(check(current.get(i),next.get(j))) {
						if(current.get(i).x < next.get(j).x ) {
							current.get(i).rightChild = next.get(j);
							next.get(j).parent = current.get(i);
						}else {
							current.get(i).leftChild = next.get(j);
							next.get(j).parent = current.get(i);
						}
					}
				}
				
			}
			current = next;
			next = new ArrayList<Node>();		
		}
		
		inOrder(root);
		k = 0;
		postOrder(root);
		System.out.println(Arrays.toString(answer[0]));
		System.out.println(Arrays.toString(answer[1]));
		return answer;
	}
	static boolean check(Node node, Node candidate) {
		boolean flag = true;
		
		while(node.hasParent()) {
			if(node.isLeftChild()) {
				if(candidate.x > node.parent.x) return false; 
				
			}else {
				if(candidate.x < node.parent.x) return false;
			}
			node = node.parent;
		}
		return flag;
	}
	static void inOrder(Node pointer) {
		answer[0][k++] = pointer.idx;
		if(pointer.leftChild!=null) inOrder(pointer.leftChild);
		if(pointer.rightChild!=null) inOrder(pointer.rightChild);
	}
	static void postOrder(Node pointer) {
		if(pointer.leftChild!=null) postOrder(pointer.leftChild);
		if(pointer.rightChild!=null) postOrder(pointer.rightChild);
		answer[1][k++] = pointer.idx;

	}
}
