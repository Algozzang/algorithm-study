import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Minju {
	// 누적합으로 그냥 푼다면 O(NM) : 구간합구할때N,데이터변경M번
	// 세그먼트 트리
	// 값 변경 : O(logN)
	// 구간합 연산 : O(logN)
	// 세그먼트 트리로 풀면 O(MlogN)
	
	static int n,m,k;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		long[] num = new long[n+1];

		for(int i =1;i<=n;i++) {
			num[i] = Long.parseLong(br.readLine());
		}

		// 세그먼트 트리 생성
		SegmentTree segTree = new SegmentTree(n);
		segTree.init(num, 1, 1, n);
		
		for(int i=0;i<m+k;i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			switch(a) {
				case "1":
					segTree.update(1, 1, n, b, c-num[b]); // 변경값에서 num의b인덱스 빼서 차이값 전달, 트리 업데이트 되도록
					num[b] = c; 
					break;
				case "2":
					sb.append(segTree.sum(1, 1, n, b, (int)c)+ "\n"); //b~c 구간합 출력
					break;
			}
		}
		System.out.println(sb);
		
	}
	
}

class SegmentTree{
	long tree[]; // 각원소가 담길 트리
	int treesize;
	
	public SegmentTree(int n) {
		//트리 높이 
		//2^k >= N인 최소의 k
		// k >= logN / log2
		int k = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
		this.treesize = (int) Math.pow(2, k);
		//배열 생성
		tree = new long[treesize];
	}
	public long init(long[] arr, int node, int start, int end) {
		//리프노드면 원소배열 그대로 세그먼트트리에 넣기
		if(start == end) return tree[node] = arr[start];
		// 리프노드 아님녀 왼쪽오른쪽 자식 노드 합 구해서 넣기
		return tree[node] = init(arr, node*2, start, (start+end)/2)+ init(arr,node*2+1,(start+end)/2+1, end);
	}
	
	public void update(int node, int s, int e,int idx, long diff) { //값 변경함수
		// 입력되는 값이 범위 바깥이면 리턴
		if(idx<s || e<idx) return;
		tree[node]+=diff;//차이추가해서 업데이트
		//리프노드 아니면 아래 자식들도 바꾸기
		if(s!=e) {
			update(node*2,s,(s+e)/2,idx,diff); //왼쪽자식
			update(node*2+1,(s+e)/2+1,e,idx,diff); //오른쪽자식
			
		}
	}
	public long sum(int node, int s, int e, int left, int right) { // 구간합 구하기
		//범위 벗어나면 리턴
		if(left>e || right<s) return 0;
		// 찾는 범위가 완전하게 포함되어 있으면 재귀 안돌고 바로 리턴
		if(left<= s && e<=right) {
			return tree[node];
		}
		//아니면 왼쪽 오른쪽 계속 탐색
		return sum(node*2, s, (s+e)/2, left, right)+sum(node*2+1, (s+e)/2+1, e, left, right);
	}
	
}
