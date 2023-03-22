import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Minju {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		String[] str = new String[n];
		for (int tc = 0; tc < n; tc++) {
			String s = br.readLine();
			str[tc] = s.replace('\\', ' ');
		}
		Arrays.sort(str);
		for (int tc = 0; tc < n; tc++) {
			trie.insert(str[tc].split(" "));

		}

		trie.print(trie.root, 0);
		System.out.println(sb);

	}

	static class Trie {

		Node root;

		Trie() {
			root = new Node("", 0);
		}

		// 삽입
		void insert(String[] token) {// 트라이에 폴더하나씩 넣기

			Node curNode = root; // 루트노드부터 시작할게영

			OUTER: for (int i = 0; i < token.length; i++) {
				// 폴더 하나씩 돌면서 겹치는 이름 있으면 그 밑으로 넣고 없으면 추가해서 넣기

				for (int n = 0; n < curNode.child.size(); n++) {
					if (curNode.child.get(n).str.equals(token[i])) {
						curNode = curNode.child.get(n); // 있으면 현재 노드로 바꾸기
						continue OUTER;
					}
				}

				// 없다면 자식으로 추가함
				curNode.child.add(new Node(token[i], curNode.depth + 1)); // 지금보다 깊이 하나더 내려감
				curNode = curNode.child.get(curNode.child.size() - 1); // 방금 넣은 마지막 애로 바꾸기

			}

		}

		void print(Node curNode, int depth) {

			for(int i = 1;i<depth;i++) {
				sb.append(" "); // depth만큼 띄어쓰기
			}
			if(!curNode.str.equals(""))sb.append(curNode.str + "\n");
			for (int i = 0; i < curNode.child.size(); i++) {
				print(curNode.child.get(i), curNode.child.get(i).depth); // 다음 깊이 탐색
			}
			
		}

	}

}

class Node {
	List<Node> child = new LinkedList<Node>();
	String str;
	int depth;

	public Node(String str, int depth) {
		super();
		this.str = str;
		this.depth = depth;
	}

}
