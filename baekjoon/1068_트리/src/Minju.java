import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Minju {
    /*
      1. 루트는 0번 노드가 아닐 수도
      2. 자식 노드가 제거 됐을 때 리프노드가 되는 경우

      0203 도움 받아 풀었음
     */
    static int N;
    static int remove, root;
    static int cnt = 0;

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // 0~n-1 노드 생성
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        //root init
        root = 0;
        st = new StringTokenizer(br.readLine());

        // 루트 제외하고 단방향 연결
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a != -1) {
                // 부모로 주어졌으니 뒤집어서 저장
                // 노드 -> 자식으로 탐색할 수 있도록
                // 부모값으로 받은 a 노드에는 현재 인덱스 노드 저장
                graph.get(a).add(i);
            } else {
                // 루트 저장
                root = i;
            }
        }
        //System.out.println(graph);
        remove = Integer.parseInt(br.readLine()); // 지워지는 노드

        bfs(root);

        System.out.println(cnt);
    }

    static void bfs(int root) {
        Queue<Integer> q= new LinkedList<>();
        if(remove == root) return; //루트 노드가 지우는 값이라면 바로 리턴

        q.add(root);

        while(!q.isEmpty()){
            int cur = q.poll(); // 현재 값
            int child = 0; //자식으로 내려간 횟수

            for(Integer i: graph.get(cur)){
                if(i != remove){ // 이동 값이 지우려는 값이 아니면, 지우려는 값이면 탐색하지 않음
                    q.add(i);
                    child++;
                }
            }

            // 이동한 곳이 없으면 리프노드임
            if(child == 0) cnt++;
        }


    }

}



/*
그래프 만들고 지우는 노드 있는 쪽은 돌지않고 리프노드가 되는 노드 구하기

public class tree1068 {
    static int N;
    static int remove;
    static int cnt = 0;
    static boolean[] visited;
    static boolean[] isLeaf;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        isLeaf = new boolean[N];
        // 0~n-1 노드 생성
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        //root init
        int root = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a != -1) { // 루트 제외하고 그래프 연결
                graph.get(i).add(a);
                graph.get(a).add(i);
            } else { // 루트 저장
                root = i;
            }
        }

        visited = new boolean[N];
        remove = Integer.parseInt(br.readLine());


        dfs(root);

        for (boolean x : isLeaf) {
            if (x) cnt++;
        }

        System.out.println(cnt);
    }

    static void dfs(int x) {

        if (x == remove) return;
        visited[x] = true;

        for (int i = 0; i < graph.get(x).size(); i++) {
            int y = graph.get(x).get(i);
            if (graph.get(x).size() == 2) { // 양쪽으로만 이어진 노드에서
                int nam;
                if (y == remove) { // 다음 노드가 지울것이라면
                    if (i == 0) nam = graph.get(x).get(1);
                    else nam = graph.get(x).get(0);
                    if (graph.get(nam).size() == 1) {
                        isLeaf[nam] = true;
                        // System.out.println(nam + " " + graph.get(nam).size());
                    }
                }
            }
            if (graph.get(x).size() == 1) { // 루트에서 아래노드 지울 때
                if (y == remove) {
                    isLeaf[x] = true;
                }
            }
            if (x != remove && y != remove) {
                if (!visited[y]) {
                    dfs(y);
                } else {
                    if (i == (graph.get(x).size() - 1)) {
                        isLeaf[x] = true;
                        //System.out.println(x + " " + graph.get(x).size());
                    }
                }
            }

        }
    }

}
*/
/*
반례

12
-1 0 0 2 2 2 4 3 3 8 8 8
4
출력:6


4
-1 0 1 2
2
출력:1

2
-1 0
1
출력:1

12
1 4 3 -1 3 1 2 0 6 6 6 1
2
출력:3
 */