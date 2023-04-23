import java.io.*;
import java.util.*;

public class JIHANEOL {
	
	static StringTokenizer st = null;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// idx,끝 시간, num;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->a[1] - b[1]);
		
		StringBuilder sb = new StringBuilder();
		int n = Integer.valueOf(br.readLine());
		List<int[]> list = new ArrayList();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.valueOf(st.nextToken());
			int s = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
			list.add(new int[] {no,s,e});
		}
		int num = 1;
		List<int[]> list1 = new ArrayList();
		// 시작 순으로 정렬
		list.sort((a,b) -> a[1]-b[1]);
		pq.add(new int[] {list.get(0)[0],list.get(0)[2],num++});
		
		for(int i =1; i<n; i++) {
			// no, s, e
			int[] now = list.get(i);
			if(pq.peek()[1] > now[1]) {
				// 강의번호, 끝 시간, 회의장소
				pq.add(new int[] {now[0],now[2],num++});
			}else { 
				// 끝이 시작 보다 작다거나 같으면
				int[] temp = pq.poll();
				pq.add(new int[] {now[0],now[2],temp[2]});
				list1.add(new int[] {temp[0],temp[2]});
			}
		}
		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			list1.add(new int[] {temp[0],temp[2]});
		}
		list1.sort((a,b)-> a[0]-b[0] );
		sb.append(num-1+"\n");
		for(int[] a : list1)
			sb.append(a[1]+"\n");
		System.out.println(sb);
	}
	

}
