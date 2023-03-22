import java.io.*;
import java.util.*;

public class JIHANEOL {
	static BufferedReader br;
	
	static class Node{
		Map<String, Node> map = new TreeMap<>();
	}
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		String[][] contents = new String[n][];
		for(int i=0; i<n; i++) {
			contents[i] = br.readLine().split("\\\\");
		}
		Node rootNood = new Node(); // 루트
		
		for(int i=0; i<n; i++) {
			String[] now = contents[i];
			Node root = rootNood;
			
			for(String current : now) {
				// 있으면
				if(root.map.containsKey(current)) {
					root = root.map.get(current);
				}
				// 엾으면
				else {
					root.map.put(current, new Node());
					root = root.map.get(current);
				}
			}
		}
		dfs(rootNood, "");
		System.out.println(sb.toString());
	}
	public static void dfs(Node root, String tap) {
		
		for(String now : root.map.keySet()) {
			sb.append(tap+now+"\n");
			dfs(root.map.get(now), tap+" ");
		}
		
	}
}












