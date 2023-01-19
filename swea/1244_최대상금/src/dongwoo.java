import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class dongwoo {
	public static void main(String[] args) throws IOException {
		// greedy로 풀 수 있을 것 같음
		// 일단 greedy로 하고 최대값이랑 같으면 홀짝인지 판별하기
		// greedy로 못품, 완전탐색 시도 11:00 끝 1:20
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        int T;
        T= Integer.parseInt(br.readLine());

		for (int test_case=1; test_case<=T; test_case++) {
			ArrayList<Integer> results = new ArrayList<>();
        	String[] tmp = br.readLine().split(" ");
        	int originalNum = Integer.parseInt(tmp[0]);
        	int changeNumLimit = Integer.parseInt(tmp[1]);
			
			String orginalNumStr = Integer.toString(originalNum);
			int size = orginalNumStr.length();
			ArrayList<int[]> searchable = new ArrayList<int[]>();
			getSearchableArea(searchable, size);
			
			int[] nums = new int[size];
			for (int i=0; i<size; i++) {
				nums[i] = orginalNumStr.charAt(i)-'0';
			}
//			int[] maxValueArr = nums.clone();
//			reverseSort(maxValueArr);
//			String maxValueString = "";
//			for(int x:maxValueArr) {
//				maxValueString += Integer.toString(x);
//			}
//			int maxValue = Integer.parseInt(maxValueString);
			
			HashSet<String> pool = new HashSet<String>(); 
			dfs(0, changeNumLimit, results, nums.clone(), searchable, pool);
			reverseSort(results);
			System.out.println("#"+test_case+" "+results.get(0));
		}
	}
	public static void dfs(int depth, int changeNumLimit, ArrayList<Integer> results, int[] nowValue, ArrayList<int[]> searchable, HashSet<String> pool) {
		String nowString = "";
		for(int x:nowValue) {
			nowString += Integer.toString(x);
		}
		if (depth == changeNumLimit) {
			results.add(Integer.parseInt(nowString));
			return;
		}
//		if (maxValue==Integer.parseInt(nowString)) {
//			if ((changeNumLimit-depth)%2==0) {
//				results.add(maxValue);
//			}
//			nowString = "";
//			int tmp = nowValue[nowValue.length-1];
//			nowValue[nowValue.length-1] = nowValue[nowValue.length-2];
//			nowValue[nowValue.length-2] = tmp;
//			for(int x:nowValue) {
//				nowString += Integer.toString(x);
//			}
//			results.add(Integer.parseInt(nowString));
//			return;
//		}
		
		for (int[] xy : searchable) {
			int[] nextValue = nowValue.clone();
			int x = xy[0];
			int y = xy[1];
			int tmp = nextValue [x];
			nextValue[x] = nextValue[y];
			nextValue[y] = tmp;
			if (!(pool.contains(Arrays.toString(nextValue)+"_"+depth))) {
				dfs(depth+1, changeNumLimit, results, nextValue, searchable, pool);
				pool.add(Arrays.toString(nextValue)+"_"+depth);
			}
		}
		
	}
	
	public static void getSearchableArea(ArrayList<int[]> searchable, int length) {
		for (int i = 0; i < length-1; i++) {
			for (int j = i+1; j < length; j++) {
				searchable.add(new int[] {i,j});
			}
		}
	}
	
	public static void reverseSort (ArrayList<Integer> arr) {
		Collections.sort(arr, Collections.reverseOrder());
	}
	
	public static void reverseSort (int[] arr) {
		Arrays.sort(arr);
		for (int i=0; i<arr.length/2; i++) {
			int temp = arr[i]; 
			arr[i] = arr[arr.length - i -1];
			arr[arr.length - i - 1] = temp;
		}
	}
}




