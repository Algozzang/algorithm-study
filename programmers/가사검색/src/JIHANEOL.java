import java.util.Arrays;

public class JIHANEOL {
	public static void main(String[] args) {
        Solution s1 = new Solution();
        String[] words = new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = new String[] {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] s1Result = s1.solution(words, queries);
        System.out.println(Arrays.toString(s1Result));
        // expected result = [3, 2, 4, 1, 0];
        // 동우가 트라이 해주겠지요..
    }
}

//class Solution {
//    public int[] solution(String[] words, String[] queries) {
//    }
//}


// 그냥 하면 시초. 당연한걸
//int[] answer = new int[queries.length];
//
//for(int i=0; i<queries.length; i++){
//	String keyWord = queries[i];
//	int len = keyWord.length();
//	int s=0,e=len;
//	boolean flag = false;
//	if(keyWord.charAt(s)=='?' && keyWord.charAt(e-1)=='?'){
//		flag = true;
//	}else if(keyWord.charAt(s)=='?'){
//		s=keyWord.lastIndexOf('?');
//		s++;
//		
//	}else{
//		e=keyWord.indexOf('?');
//		
//	}
//	System.out.println(s+" "+e);
//	
//	if(flag){
//		int sum=0;
//		for(String word: words){
//			if(word.length()==len){
//				sum++;
//			}
//		}
//		answer[i]=sum;
//	}else{
//		int sum=0;
//		for(String word: words){
//			String word1 = word.substring(s, e);
//			
//			String k = keyWord.substring(s, e);
//			
//			if(word1.equals(k) && keyWord.length()==word.length()) {
//				sum++;
//			}
//		}
//		answer[i]=sum;
//	}
//	
//	
//}
//
//
//
//return answer;
