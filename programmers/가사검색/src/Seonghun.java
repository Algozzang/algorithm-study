import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Seonghun {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		String[] words = new String[] { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queries = new String[] { "fro??", "????o", "fr???", "fro???", "pro?" };
		int[] s1Result = s1.solution(words, queries);
		System.out.println(Arrays.toString(s1Result));
		// expected result = [3, 2, 4, 1, 0];
	}

}

class Node {
	// 단어의 마지막 문자?
	boolean isEnd;
	// 현재까지의 노드에 대해 각 길이별로 매칭되는 단어 수
	Map<Integer, Integer> cnt = new HashMap<>();
	// 하위 노드들
	Map<Character, Node> child = new HashMap<>();
}

class Trie {
	// 루트 노드
	Node root = new Node();

	// 단어 추가
	void addWord(String word) {
		// 루트 노드부터 시작
		Node curr = root;
		curr.cnt.put(word.length(), curr.cnt.getOrDefault(word.length(), 0) + 1);
		// 단어의 길이만큼 자식노드가 있는지 확인하고, 없으면 새로운 노드 생성
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!curr.child.containsKey(ch)) {
				curr.child.put(ch, new Node());
			}
			curr = curr.child.get(ch);
			curr.cnt.put(word.length(), curr.cnt.getOrDefault(word.length(), 0) + 1);
		}
		// 가장 끝의 노드에 마지막 문자임을 표시
		curr.isEnd = true;
	}

	// 쿼리와 매치되는 단어들의 수 반환
	int matchCount(String query) {
		// 루트 노드부터 시작
		Node curr = root;
		// 쿼리의 길이만큼 트라이를 탐색하고, ?가 나오면
		for (int i = 0; i < query.length(); i++) {
			char ch = query.charAt(i);
			// 와일드 카드
			if (ch == '?') {
				return curr.cnt.getOrDefault(query.length(), 0);
			}
			// 매치되는 문자가 없는 경우
			if (!curr.child.containsKey(ch)) {
				return 0;
			}
			curr = curr.child.get(ch);
		}
		return curr.isEnd ? 1 : 0;
	}
}

class Solution {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];

		// 단어들을 trie에 추가
		Trie trie1 = new Trie(); // 단어를 정방향으로 트라이 형성
		Trie trie2 = new Trie(); // 단어를 역방향으로 트라이 형성
		for (String word : words) {
			trie1.addWord(word);
			trie2.addWord(new StringBuilder(word).reverse().toString()); // 거꾸로 뒤집자
		}

		// 매치되는 단어들의 수 구하기
		for (int i = 0; i < queries.length; i++) {
			String query = queries[i];
			if (query.charAt(0) != '?') {
				answer[i] += trie1.matchCount(query);
			} else {
				answer[i] += trie2.matchCount(new StringBuilder(query).reverse().toString());
			}
		}

		return answer;
	}
}
