import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Hyekyoung {
	static class Node{
		List<Node> child;
		String name;
		boolean isLast;
		public Node() {
			this.isLast = false;
			child = new ArrayList<>();
		}
		
		public Node(String name) {
			super();
			child = new ArrayList<>();
			this.name = name;
			this.isLast = false;
		}

		void insertNode(Node nextNode, int idx) {
			if(path.size() == idx) return;
			
			String folder = path.get(idx);
			for (Node node : nextNode.child) {
				if(node.name.equals(folder)) {
					//자식에 현재 폴더 이름이랑 같은게 있으면 다음으로 넘어가기
					insertNode(node, idx+1);
					return;
				}
			}
			//자식에 폴더이름이 없었으면 폴더 추가하고 last 아님 처리
			this.isLast = true;
			Node newNode = new Node(folder);
			nextNode.child.add(newNode);
			insertNode(newNode, idx+1);
		}
	}
	
	static Node root = new Node();
	static List<String> path;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] str = new String[n];
		
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			str[i]=s.replace('\\', ' ');
			//안바꾸니까 정렬할때 \달린게 더 뒤로 간다...
		}
		Arrays.sort(str);
		for(int i=0; i<n; i++) {
			insert(str[i]);
		}
		
		print(root, 0);
		System.out.print(sb.toString());
	}
	
	static void print(Node parent, int depth) {
		for (Node node : parent.child) {
			for(int i=0; i<depth; i++) sb.append(" ");
			sb.append(node.name+"\n");
			if(node.isLast) return;
			print(node, depth+1);
		}
	}
	
	static void insert(String folder) {
		st = new StringTokenizer(folder);
		path = new ArrayList<>();
		while(st.hasMoreTokens()) {
			path.add(st.nextToken());
		}
		root.insertNode(root, 0);
	}

}
