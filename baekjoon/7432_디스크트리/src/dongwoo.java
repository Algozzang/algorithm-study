import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			trie.put(br.readLine().split("\\\\"));
		}
		trie.print();
	}
}

class Trie {
	Node head = new Node(0, "");

	void put(String[] dirs) {
		Node now = head;
		for (String str : dirs) {
			int ind = now.children.indexOf(new Node(0, str));
			if (ind >= 0) {
				now = now.children.get(ind);
			} else {
				now.children.add(new Node(now.depth + 1, str));
				now = now.children.get(now.children.size() - 1);
			}
		}
	}

	void print() {
		StringBuilder sb = new StringBuilder();
		dfs(head, sb);
		System.out.println(sb);
	}

	void dfs(Node now, StringBuilder sb) {
		for (int i = 0; i < now.depth-1; i++) {
			sb.append(" ");
		}
		if (!now.equals(head)) {
			sb.append(now.word).append("\n");			
		}
		now.children.sort((s1, s2) -> s1.word.compareTo(s2.word));
		for (int i = 0; i < now.children.size(); i++) {
			dfs(now.children.get(i), sb);
		}

	}
}

class Node {
	int depth;
	String word;
	List<Node> children = new ArrayList<>();

	public Node(int depth, String word) {
		this.depth = depth;
		this.word = word;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.word.equals(((Node) obj).word);
	}

}