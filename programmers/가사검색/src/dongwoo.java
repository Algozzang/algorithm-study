import java.util.*;

public class dongwoo {
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
        
        Trie[] front = new Trie[100001];
        Trie[] back = new Trie[100001];
        
        for(int i=0; i< queries.length; i++) {
            int length = queries[i].length();
            if (front[length] == null) {
                front[length] = new Trie();
                back[length] = new Trie();
            }
        }

        for(int i=0; i< words.length; i++) {
            int length = words[i].length();
            if (front[length]==null) {
                continue;
            }
            front[length].insertWord(words[i]);
            back[length].insertReversedWord(words[i]);
        }
        for(int i=0; i< queries.length; i++) {
            int length = queries[i].length();
            if (queries[i].charAt(0)=='?') {
                answer[i] = back[length].searchReversedWord(queries[i]);
            } else {
                answer[i] = front[length].searchWord(queries[i]);
            }
        }
        return answer;
    }
}

class Trie {
    Node head = new Node();
    
    public void insertWord(String str) {
        int size = str.length();
        Node now = head;
        now.words++;
        for (int i=0; i<size; i++) {
            int ind = now.children.indexOf(new Node(str.charAt(i)));
            if (ind < 0) {
                now.children.add(new Node(str.charAt(i)));
                ind = now.children.size()-1;
            }
            now = now.children.get(ind);
            now.words++;
        }
    }
    
    public void insertReversedWord(String str) {
        int size = str.length();
        Node now = head;
        now.words++;
        for (int i=size-1; i>=0; i--) {
            int ind = now.children.indexOf(new Node(str.charAt(i)));
            if (ind < 0) {
                now.children.add(new Node(str.charAt(i)));
                ind = now.children.size()-1;
            }
            now = now.children.get(ind);
            now.words++;
        }
    }
    public int searchWord(String str) {
        int size = str.length();
        Node now = head;
        for (int i=0; i<size; i++) {
            if (str.charAt(i)=='?') {
                break;
            }
            int ind = now.children.indexOf(new Node(str.charAt(i)));
            if (ind<0) {
                return 0;
            }
            now = now.children.get(ind);
        }
        return now.words;
    }
    
    public int searchReversedWord(String str) {
        int size = str.length();
        Node now = head;
        for (int i=size-1; i>=0; i--) {
            if (str.charAt(i)=='?') {
                break;
            }
            int ind = now.children.indexOf(new Node(str.charAt(i)));
            if (ind<0) {
                return 0;
            }
            now = now.children.get(ind);
        }
        return now.words;
    }
}

class Node {
    char word;
    int words;
    List<Node> children = new ArrayList<>();
    
    public Node() {
    }
    
    public Node(char word) {
        this.word = word;
    }
    
    public boolean equals(Object obj) {
        Node counter = (Node) obj;
        return this.word==counter.word;
    }
}