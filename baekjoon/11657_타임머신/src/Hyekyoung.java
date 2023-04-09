import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Hyekyoung {
	static int N, M;
	static List<Node> list = new ArrayList<>();
	static long[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new long[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		Arrays.fill(time, Integer.MAX_VALUE);
		time[1] = 0;

		if (bellman()) {
			for (int i = 2; i <= N; i++) {
				System.out.println(time[i] != Integer.MAX_VALUE ? time[i] : "-1");
			}
		} 
		else System.out.println("-1");
	}

	static boolean bellman() {
		Node curNode;
		int s, e, c;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				curNode = list.get(j);
				s = curNode.s;
				e = curNode.e;
				c = curNode.c;
				if (time[s] != Integer.MAX_VALUE && time[s] + c < time[e]) {
					time[e] = time[s] + c;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			curNode = list.get(i);
			s = curNode.s;
			e = curNode.e;
			c = curNode.c;
			if (time[s] != Integer.MAX_VALUE && time[s] + c < time[e]) {
				//갱신이 되면 음수사이클!
				return false;
			}
		}
		return true;
	}

	static class Node {
		int s, e, c;

		public Node(int s, int e, int c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}

	}
}
