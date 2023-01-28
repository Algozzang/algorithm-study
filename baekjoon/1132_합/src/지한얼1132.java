import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class 지한얼1132 {
	static Set<String> check=new HashSet<>(); // 맨앞인지 1나이진 책
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //전체
		long answer=0l;
		Map<String, Long> map = new HashMap<>();
		
		for(int i=0;i<n;i++) { //값 넣기
			String st = sc.next();
			long num=1;
			for(int j=st.length();j>0;j--,num*=10) {
				if(j==1) { //첫값확인 
					check.add(st.substring(j-1, j));
				}
				if(!map.containsKey(st.substring(j-1, j))) { //키확인 없다면
					map.put(st.substring(j-1, j), num);
					continue;
				} //있다면
				map.put(st.substring(j-1, j),map.get(st.substring(j-1, j))+num);
			}
		}
		
		if(map.size()<10) {
			int num=9;
			List<Map.Entry<String, Long>> entries =  // map.entryset을 스트림으로 변경후 키값 정렬하고 리스트로 반환.
					map.entrySet().stream()
						.sorted((o1,o2)-> o2.getValue().compareTo(o1.getValue()) )//내림차순
						.collect(Collectors.toList());
		for(Map.Entry<String, Long> entry: entries) {
			
			answer+=entry.getValue()*num;
			num--;
			
			}
		} else {
			int num=0;
			Stack<Long> s = new Stack<>();
			List<Map.Entry<String, Long>> entries =  // map.entryset을 스트림으로 변경후 키값 정렬하고 리스트로 반환.
					map.entrySet().stream()
						.sorted((o1,o2)-> o1.getValue().compareTo(o2.getValue()) )//오름차순
						.collect(Collectors.toList());
			
			for(Map.Entry<String, Long> entry: entries) {
				
				if(check.contains(entry.getKey())&&num==0) {//첫번째이고 0이면
					s.add(entry.getValue()); // 스택에 값넣기
					continue;
				} 
				answer+=entry.getValue()*num;
				if(num==0) num=s.size()+1; //처음이 아니고 0이라면
				else num++; //아나 증말
				}
			int n1=s.size();
			while(!s.isEmpty()) {//마무리 스택에 있는거
				answer+=s.pop()*n1;
				n1--; //아나 진ㅁ짜 ㄷㄷㄱ ㄴㅇ러넘 라ㅓ 하.... num으로 봤습니다.
			}
		}
		System.out.println(answer);
	}
	}

