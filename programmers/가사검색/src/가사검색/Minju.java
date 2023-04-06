package 가사검색;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// 트라이 어려워요...........구글링함 이해는함
public class Minju {
	// 트라이만들어서 각 노드마다 개수 저장하고 와일드카드를 가진 쿼리 무조건 와일드카드에 뒤에 있도록 세팅하고 트라이!
	public static void main(String[] args) {
        Solution s1 = new Solution();
        String[] words = new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = new String[] {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] s1Result = s1.solution(words, queries);
        System.out.println(Arrays.toString(s1Result));
        // expected result = [3, 2, 4, 1, 0];
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
    	
        int[] answer = new int[queries.length];
        Trie[] trieArr = new Trie[10001]; // 1<= <= 10000 // 와일드카드 오른쪽에 있는 쿼리
        Trie[] rtrieArr = new Trie[10001]; // 1<= <= 10000// 와일드카드 왼쪽에 있는 쿼리

        // 문자열, 리버스문자열 array에 담기
        for(String word:words) {
        	if(trieArr[word.length()] == null) {
        		trieArr[word.length()] = new Trie(); // 트라이 생성
        	}
        	trieArr[word.length()].insert(word);
        	
        	if(rtrieArr[word.length()] == null) {
        		rtrieArr[word.length()]= new Trie(); // 트라이 생성
        	}
        	String rStr = new StringBuilder(word).reverse().toString();
        	rtrieArr[word.length()].insert(rStr);
        }
        
        // 쿼리 카운트 세기, ?가 앞에온 쿼리면 리버스문자열로 확인
        for(int i =0;i<queries.length; i++) {
        	String cur = queries[i]; //현재 쿼리
        	int cnt = 0;
        	// 왼쪽에 와일드카드 있는 경우
        	try {
        	if(cur.charAt(0) == '?') {
        		cur = new StringBuilder(cur).reverse().toString();
        		cnt = rtrieArr[cur.length()].count(cur);
        	}else { // 왼쪽부터 와일드 카드 있는 경우
        		cnt = trieArr[cur.length()].count(cur);
        	}
        	}catch(NullPointerException e) { 
				cnt = 0;
			}
        	answer[i] = cnt;
        }
        
        return answer;
    }
}

class Trie{
	TrieNode root;
	Trie(){
		root = new TrieNode();
	}
	void insert(String word) {
		TrieNode curNode = this.root; // 루트노드부터 트라이 문자열 노드 
		for(int i =0;i<word.length();i++) {
			curNode.cnt++;
			curNode = curNode.child.computeIfAbsent(word.charAt(i),c-> new TrieNode()); // 없으면 새 노드
		}
	
		curNode.setIsLastChar(true); // 끝났으면 마지막문자로 지정
	}
	boolean contains(String word) {
		TrieNode curNode = this.root;
		for(int i =0;i<word.length();i++) {
			char character = word.charAt(i);
			TrieNode node = curNode.child.get(character);
			if(node == null) return false; // 없다면 저장된 해당 문자열이 없는 것
			curNode = node; // 타고 내려가기
		}
		return curNode.isLastChar; // 다 내려갔으면 마지막문자인지
	}
	int count(String word) {
		TrieNode curNode = this.root;
		int cnt = 0;
		for(int i =0;i<word.length();i++) {
			char character = word.charAt(i);
			if(character == '?') return curNode.cnt; // 와일드카드면 현 부모노드 개수로 반환
			TrieNode node = curNode.child.get(character); // 현 문자 가진애 찾아 타고내려갈거임
			cnt = node.cnt;
			curNode = node;
		}
		return cnt;
	}
}
class TrieNode{
	Map<Character, TrieNode> child = new HashMap<>();
	boolean isLastChar;
	int cnt;
	
	void setIsLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}
}