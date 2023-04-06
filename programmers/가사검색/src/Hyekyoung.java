import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


class TrieNode{
	Map<Character, TrieNode> child = new HashMap<>();
	boolean isLast;
	
	public TrieNode() {
	}
	
	public Map<Character, TrieNode> getChild() {
		return child;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
}

class Trie{
	TrieNode rootNode;
	
	public Trie() {
		rootNode = new TrieNode();
	}
	
	void insert(String word) {
		TrieNode thisNode = this.rootNode;
		
		for(int i=0; i<word.length(); i++) {
			//computeIfAbsent():키가 존재하면 value 반환, 없으면 새로 만들어쥼
			thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i), c ->new TrieNode());
		}
		for(int i=word.length()-1; i>=0; i--) {	//거꾸로 넣기
//			System.out.print(word.charAt(i));
			thisNode = thisNode.getChild().computeIfAbsent(word.charAt(i), c ->new TrieNode());
		}
//		System.out.println(	);
	}
	
	int contains(String word) {
		String str;
		TrieNode thisNode = this.rootNode;
		if(word.charAt(0) == '?') {
			StringBuilder sb = new StringBuilder(word);
			str = sb.reverse().toString();
		}
		else str = word;
		
//		System.out.println(str);
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '?') break;
			TrieNode childNode = thisNode.getChild().get(str.charAt(i));
			if(childNode == null) return 0; //자식노드에 일치하는 문자가 없음
			thisNode = childNode;
		}
		return thisNode.getChild().size();
	}
}


public class Hyekyoung {
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
    	Trie trie = new Trie();
    	for(int i=0; i<words.length; i++) {
    		trie.insert(words[i]);
    	}
        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
        	answer[i] = trie.contains(queries[i]);
        }
        
        return answer;
    }
}