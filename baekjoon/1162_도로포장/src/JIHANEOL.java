import java.io.*;
import java.util.*;

public class JIHANEOL {
    static int N, M, K;
    static long[][] dp;
    static class Edge {
        int end,count;
        long cost;

        public Edge(int end, long cost, int count) {
            this.end = end;
            this.cost = cost;
            this.count = count;
        }
    }
    static ArrayList<Edge>[] edges;
    static final long MAX = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N + 1];
        dp = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            Arrays.fill(dp[i], MAX);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[s].add(new Edge(e, c, 0));
            edges[e].add(new Edge(s, c, 0));
        }

        dijkstra();
        long min = MAX;
        for (long a : dp[N]) {
            min = Math.min(a, min);
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        pq.add(new Edge(1, 0, 0));
        dp[1][0] = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.remove();
            if (cur.cost > dp[cur.end][cur.count]) continue;
            for (Edge next : edges[cur.end]) {
                long nextCost = next.cost + cur.cost;
                if(nextCost < dp[next.end][cur.count]) {
                    dp[next.end][cur.count] = nextCost;
                    pq.add(new Edge(next.end, nextCost, cur.count));
                }
                if (cur.count < K && cur.cost < dp[next.end][cur.count + 1]) {
                    dp[next.end][cur.count + 1] = cur.cost;
                    pq.add(new Edge(next.end, cur.cost, cur.count + 1));
                }
            }
        }
    }
}