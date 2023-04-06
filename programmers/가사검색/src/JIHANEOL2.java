import java.util.*;

public class JIHANEOL2 {
	public static void main(String[] args) {
        Solution s1 = new Solution();
        String[] words = new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = new String[] {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] s1Result = s1.solution(words, queries);
        System.out.println(Arrays.toString(s1Result));
        // expected result = [3, 2, 4, 1, 0];
    }
}
// 이진 탐색 활용..
class Solution {
    public int[] solution(String[] words, String[] queries) {
    	int [] answer = new int[queries.length];
    	List<ArrayList<String>> array=new ArrayList();
    	List<ArrayList<String>> reversed_array=new ArrayList();
    	for(int i=0; i<10001; i++) {
    		array.add(new ArrayList());
    		reversed_array.add(new ArrayList());
    	}
    	// 단어 삽입
    	StringBuilder sb;
    	for(String word: words) {
    		array.get(word.length()).add(word);
    		sb = new StringBuilder(word);
    		reversed_array.get(word.length()).add(new String(sb.reverse()));
    	}
    	// 단어 정렬
    	for(int i=1; i<10001;i++) {
    		array.get(i).sort((o1,o2)->{
    			return o1.compareTo(o2);
    		});
    		reversed_array.get(i).sort((o1,o2)->{
    			return o1.compareTo(o2);
    		});
    	}
    	
    	int cnt =0,res=0;
    	for(String q:queries) {
    		
    		if(q.charAt(0) !='?') { // 접미사에 붙은경우
    			res = count_by_range(array.get(q.length()), q.replace('?', 'a'), q.replace('?', 'z'));
    		}else { // 접두사에 붙거나 다 붙은 경우.
    			sb = new StringBuilder(q).reverse();
    			q = new String(sb);
    			res = count_by_range(reversed_array.get(q.length()), q.replace('?', 'a'), q.replace('?', 'z'));
    		}
    		answer[cnt++]=res;
    	}
    	return answer;
    	
    	
    }// frodo, front, frost, frozen, frame,kakao 이고 froaa,frozz 인상황
    int count_by_range(ArrayList<String> a, String arr1,String arr2) {
    	int upper = upperBound(0, a.size(), arr2,a); 
    	int lower = lowerBound(0, a.size(), arr1,a);
//    	System.out.println(a+" "+upper+" "+lower);
    	return upper-lower;
    }
    int upperBound(int s,int e,String target,ArrayList<String> a) {
    	while(s<e) {
    		int mid = (s+e)/2;
    		if(target.compareTo(a.get(mid))<0) {
    			e=mid;
    		}else {
    			s=mid+1;
    		}
    		
    	}
    	return s;
    }
    int lowerBound(int s,int e,String target,ArrayList<String> a) {
    	while(s<e) {
    		int mid = (s+e)/2;
    		if(target.compareTo(a.get(mid))<=0) {
    			e=mid;
    		}else {
    			s=mid+1;
    		}
    		
    	}
    	return s;
    }
}



