import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class dongwoo {
	public static Set<Integer> ignore = new HashSet<Integer>();
	public static HashMap<Integer, List<Integer>> link = new HashMap<Integer, List<Integer>>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		// 삭제 해야할 숫자는 nToDel : int , 그로부터 파생되는 자손들은 ignore : Set<Integer>
		int nToDel = Integer.parseInt(br.readLine());

		// hierarchy를 안그려도 될듯? 부모의 set, 자손의 set 따로 그린다. 
		List<Integer> children = new ArrayList<Integer>();
		Set<Integer> parents = new HashSet<Integer>();
		
		for (int child = 0; child < n; child++) {
			int parent = Integer.parseInt(temp[child]);
			if (!link.containsKey(parent)) {
				link.put(parent, new ArrayList<Integer>());
			}
			link.get(parent).add(child);
		}
		
		addIgnore(nToDel);
		
		for (int child = 0; child < n; child++) {
			if(ignore.contains(child)) {
				continue;
			}
			parents.add(Integer.parseInt(temp[child])); 
			children.add(child);
		}
		
		// 자식들 중 부모가 아닌 애들 세기
		int result = 0;
		for (int c:children) {
			if (parents.contains(c)) {
				continue;
			}
			result++;
		}
		System.out.println(result);
	}
	
	public static void addIgnore(int parent) {
		ignore.add(parent);
		for (int child:link.getOrDefault(parent, new ArrayList<Integer>())) {
			addIgnore(child);
		}
	}

}
