//package algozzang3week;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.List;
//import java.util.PriorityQueue;
//
//public class 소수의곱 {
//		static List<Integer> list;
//		static List<Integer> arr;
//		static long answer;
//		static PriorityQueue<Integer> myQueue;
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		myQueue = new PriorityQueue<Integer>();
//		list = Arrays.stream(br.readLine().split(" ")).map((i)-> Integer.valueOf(i)).toList();
//		arr = Arrays.stream(br.readLine().split(" ")).map((i)-> Integer.valueOf(i)).toList();// 써보고 싶어서 씀
//		for(int a: arr) {
//			myQueue.add(a);
//		}
//		dfs(0);
//		System.out.println(answer);
//		
//	}
//	public static void dfs(int depth) {
//		if(depth==list.get(1)-1) {
//			answer=myQueue.poll();
//				return;
//		}
//		
//		int n= myQueue.poll(); // 작은거
//		for(int a: arr) {
//			if(!myQueue.contains(a*n))
//			myQueue.add(a*n);
//		}
//		dfs(depth+1);
//		
//			
//	}
//
//}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 지한얼소수의곱 {
		static String[] arr;
		static long answer;
		static PriorityQueue<Integer> myQueue;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		myQueue = new PriorityQueue<Integer>();
		
		String[] n_m = br.readLine().split(" ");
		int n= Integer.valueOf(n_m[0]);
		int m= Integer.valueOf(n_m[1]);
		
		arr = br.readLine().split(" ");
		for(String a: arr) {
			myQueue.add(Integer.valueOf(a));
		}
		dfs(0,m);
		System.out.println(answer);
		
	}
	public static void dfs(int depth,int t) {
		if(depth==t-1) {
			answer=myQueue.poll();
				return;
		}
		
		int n= myQueue.poll(); // 작은거
		
		for(String a: arr) {
			if(Integer.valueOf(a)*n>Math.pow(2, 16)) break;
			if(!myQueue.contains(Integer.valueOf(a)*n))
			myQueue.add(Integer.valueOf(a)*n);
		}
		dfs(depth+1,t);
		
			
	}

}

