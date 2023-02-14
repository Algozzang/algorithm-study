//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.Set;
//
//public class ���Ѿ�_1107 {
//	static int answer,N,M;
//	static String N_str;
//	static Set<Integer> broken;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt(); // ã�� ��ȣ
//		N_str =N+""; // ���� �����
//		M = sc.nextInt(); //���峭 ��
//		broken = new HashSet<Integer>(); //���� ��ȣ
//		for(int i=0; i<M; i++) {
//			broken.add(sc.nextInt());
//		}
//		
//		answer= Math.abs(N-100); // ���� ��ġ���� ã�� �� ����
//		dfs(0, 0, 0);
//		
//		System.out.println(answer);
//		
//	}
//	public static void dfs(int now,int depth, int cnt) {
//		
//		if(now==N) { //������ ����
//			if(answer>cnt) answer=cnt;
//			return;
//		} 
//		if(N_str.length()==depth) { //�ڸ��� ��ä��� ����
//			System.out.println(now);
//			if(answer> Math.abs(now-N)+depth) {
//				answer=Math.abs(now-N)+depth;
//			}
//			return;
//		}
//		
//		int tmp = Integer.valueOf(N_str.substring(depth, depth+1)); // �� �ڸ� ��ư
//		
//		if(broken.contains(tmp)) {
//			while(broken.contains(tmp)) {
//				if(tmp==9) break;
//				tmp++;
//			}
//		}
//		if(!(tmp==9)) {
//			for(;tmp<10;tmp++)
//			dfs(now*10+tmp, depth+1, cnt+1);
//		}
//		tmp = Integer.valueOf(N_str.substring(depth, depth+1));
//		
//		if(broken.contains(tmp)) {
//			while(broken.contains(tmp)) {
//				if(tmp==0) return;
//				tmp--;
//			}
//		}
//		for(;tmp>=0;tmp--) {
//			if(depth==0&&tmp==0) return; 
//			dfs(now*10+tmp, depth+1, cnt+1);
//		}
//		
//	}
//
//}

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 지한얼_1107 {
	static int answer,N,M;
	static String N_str;
	static Set<Integer> broken;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //
		N_str =N+""; // 
		M = sc.nextInt(); 
		broken = new HashSet<Integer>(); //
		for(int i=0; i<M; i++) {
			broken.add(sc.nextInt());
		}
		
		answer= Math.abs(N-100); // 
		dfs(100, 0, 0);
		
		System.out.println(answer);
		
	}
	public static void dfs(int now,int depth, int cnt) {
		if(now==N) { //������ ����
			if(answer>cnt) answer=cnt;
			return;
		} 
		if(N_str.length()==depth) { 
			if(answer> Math.abs(now-N)+depth) {
				answer=Math.abs(now-N)+depth;
			}
			return;
		}
		
		if(N_str.length()==1) {
			for(int i=0;i<10;i++) {
				if(!broken.contains(i)) {
					dfs(i, depth+1, cnt+1);
				}
			}
		}
		if(depth==0) {
			for(int i=1;i<10;i++) {
				if(!broken.contains(i)) {
					dfs(i, depth+1, cnt+1);
				}
			}
		} else {
			for(int i=0;i<10;i++) {
				if(!broken.contains(i)) {
					dfs(now*10+i, depth+1, cnt+1);
				}
			}
		
		
		}

	}
}
