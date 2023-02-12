import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class 지한얼1132 {
	static Set<String> check=new HashSet<>(); //
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //
		long answer=0l;
		Map<String, Long> map = new HashMap<>();
		
		for(int i=0;i<n;i++) { //
			String st = sc.next();
			long num=1;
			for(int j=st.length();j>0;j--,num*=10) {
				if(j==1) { //
					check.add(st.substring(j-1, j));
				}
				if(!map.containsKey(st.substring(j-1, j))) { //
					map.put(st.substring(j-1, j), num);
					continue;
				} //�ִٸ�
				map.put(st.substring(j-1, j),map.get(st.substring(j-1, j))+num);
			}
		}
		
		if(map.size()<10) {
			int num=9;
			List<Map.Entry<String, Long>> entries =  // 
					map.entrySet().stream()
						.sorted((o1,o2)-> o2.getValue().compareTo(o1.getValue()) )//
						.collect(Collectors.toList());
		for(Map.Entry<String, Long> entry: entries) {
			
			answer+=entry.getValue()*num;
			num--;
			
			}
		} else {
			int num=0;
			Stack<Long> s = new Stack<>();
			List<Map.Entry<String, Long>> entries =  // 
					map.entrySet().stream()
						.sorted((o1,o2)-> o1.getValue().compareTo(o2.getValue()) )//
						.collect(Collectors.toList());
			
			for(Map.Entry<String, Long> entry: entries) {
				
				if(check.contains(entry.getKey())&&num==0) {//
					s.add(entry.getValue()); // 
					continue;
				} 
				answer+=entry.getValue()*num;
				if(num==0) num=s.size()+1; //
				else num++; //ㄴ
				}
			int n1=s.size();
			while(!s.isEmpty()) {//
				answer+=s.pop()*n1;
				n1--; //
			}
		}
		System.out.println(answer);
	}
	}

