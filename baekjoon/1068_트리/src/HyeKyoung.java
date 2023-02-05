import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HyeKyoung {
	static Map<Integer, List<Integer>> map = new HashMap<>();
	static int cnt = 0, rNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), root = 0;
		int[] parent = new int[n];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			parent[i] = Integer.parseInt(str[i]);
			if (parent[i] == -1)
				root = i;
			else {
				if (map.containsKey(parent[i]))
					map.get(parent[i]).add(i);
				else {
					List<Integer> list = new ArrayList<>();
					list.add(i);
					map.put(parent[i], list);
				}
			}
		}
		rNode = Integer.parseInt(br.readLine());
		leafNode(rNode, root);
		System.out.println(cnt);
	}

	static void leafNode(int p, int c) {
		if (c == rNode) {
			if(map.get(p).size()==1) cnt++;
			return;
		}
		if (!map.containsKey(c)) {
			cnt++;
			return;
		}

		for (int x : map.get(c)) {
			leafNode(c, x);
		}
	}
}


