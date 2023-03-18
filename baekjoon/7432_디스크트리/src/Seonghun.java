import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Seonghun {

	static int N;
	static Set<String> roots = new TreeSet<>(); // 루트 디렉토리 저장
	static Map<String, Set<String>> tree = new TreeMap<>(); // 디렉토리 트리
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// initialize
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String path = br.readLine();
			String[] dirs = path.split("\\\\");
			String key = dirs[0];
			
			// 루트 디렉토리 저장
			roots.add(key);
			
			// 디렉토리 트리 형성
			for (int j = 0; j < dirs.length - 1; j++) {
				if (!tree.containsKey(key)) {
					tree.put(key, new TreeSet<>());
				}
				String value = key + "\\" + dirs[j + 1];
				tree.get(key).add(value);
				key = value;
			}
		}
		
		// solution
		for (String root : roots) {
			printDir(0, root);
		}
		
		// result
		System.out.println(answer);
	}
	
	static void printDir(int depth, String dir) {
		for (int d = 0; d < depth; d++) {
			answer.append(" ");
		}
		int idx = dir.lastIndexOf("\\");
		answer.append(dir.substring(idx == -1 ? 0 : idx + 1)).append("\n");
		if (tree.containsKey(dir)) {
			for (String subdir : tree.get(dir)) {
				printDir(depth + 1, subdir);
			}
		}
	}
	
}
