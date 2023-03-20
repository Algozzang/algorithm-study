import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Minju {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	
		int n = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		String[] str = new String[n];
		for (int tc = 0; tc < n; tc++) {
			String s = br.readLine();
			str[tc]=s.replace('\\', ' ');
		}
		Arrays.sort(str);
		for (int tc = 0; tc < n; tc++) {
		
			StringTokenizer st = new StringTokenizer(str[tc]);
			while(st.hasMoreTokens()) {
				trie.insert(st.nextToken());
			}
		}
		
		trie.print(trie.root, 0);
		System.out.println(sb);
	
	}


	static class Trie{
		
		Node root;
		Trie(){
			root = new Node();
		}
		
		// 삽입
		void insert(String str) {//트라이에 문자열 쪼개서 트리만들기
			
			Node node = root; //루트노드부터 시작할게영
			
			for(int i = 0;i<str.length();i++) {
				//computeIfAbsent외워두자 : 특정키에 해당하는 값이 없으면 만들어서 넣어줌 : str.charAt(i)에 해당하는 value가 없으면 뉴벨류
				node = node.getChildNode().computeIfAbsent(str.charAt(i), key -> new Node());
			}
			//노드가 이제 끝문자를 가리키니까 트루
			node.setLastNode(true);
		}

		
		
		void print(Node curNode, int depth) {
			Node cur = curNode;

			if(cur.getChildNode() != null) {
				System.out.println(depth);
				
				for(Character str : cur.getChildNode().keySet()) {
					if(cur.isLast()) {
						sb.append("\n");
					}
					
					sb.append(str);
					print(curNode.getChildNode().get(str), depth+1);
					
				}
			}
		}
		
	}
	static class Node{
		Map<Character, Node> child = new HashMap<Character, Node>();
		private boolean isLastChar;

		private Map<Character, Node> getChildNode(){
			return child;
		}


		private void setLastNode(boolean isLast) {
			isLastChar = isLast;
		}
		
		boolean isLast() {
			return isLastChar;
		}
		
	}
}
/*

public class Minju {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	
		int n = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		String[] str = new String[n];
		for (int tc = 0; tc < n; tc++) {
			String s = br.readLine();
			str[tc]=s.replace('\\', ' ');
		}
		Arrays.sort(str);
		for (int tc = 0; tc < n; tc++) {
		
			for(int i =0;i<str[tc].length();i++) {
				trie.insert(str[tc]);
			}
		}
		
		trie.print(trie.root, 0);
		System.out.println(sb);
	
	}


	static class Trie{
		
		Node root;
		Trie(){
			root = new Node();
		}
		
		// 삽입
		void insert(String str) {//트라이에 문자열 쪼개서 트리만들기
			
			Node node = root; //루트노드부터 시작할게영
			
			for(int i = 0;i<str.length();i++) {
				//computeIfAbsent외워두자 : 특정키에 해당하는 값이 없으면 만들어서 넣어줌 : str.charAt(i)에 해당하는 value가 없으면 뉴벨류
				node = node.getChildNode().computeIfAbsent(str.charAt(i), chi -> new Node());
			}
			//노드가 이제 끝문자를 가리키니까 트루
			node.setLastNode(true);
		}

		
		
		void print(Node curNode, int depth) {
			Node cur = curNode;
			if(cur.getChildNode() != null) {
				System.out.println(depth);

				for(Character str : cur.getChildNode().keySet()) {
					if(cur.isLast()) {
						sb.append("\n");
					}
					
					sb.append(str);
					print(curNode.getChildNode().get(str), depth+1);
					
				}
			}
		}
		
	}
	static class Node{
		Map<Character, Node> child = new HashMap<Character, Node>();
		private boolean isLastChar;

		private Map<Character, Node> getChildNode(){
			return child;
		}


		private void setLastNode(boolean isLast) {
			isLastChar = isLast;
		}
		
		boolean isLast() {
			return isLastChar;
		}
		
	}
}

 */