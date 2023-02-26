import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class JIHANEOL {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
 
        boolean[] use = new boolean[101];
        int put = 0;
        int ans = 0;
        for (int i = 0; i < K; i++) {
            int temp = order[i];
 
            if (!use[temp]) { // 콘센트가 꽂혀있지 않은 경우
                if (put < N) { // 콘센트를 꽂을 공간이 있는 경우
                    use[temp] = true;
                    put++;
                } else { // 콘센트를 꽂을 공간이 없는 경우
                    ArrayList<Integer> arrList = new ArrayList<>();
                    for (int j = i; j < K; j++) { // 현재 꽂혀 있는 콘센트가 나중에도 사용되는지 확인.
                        if (use[order[j]] && !arrList.contains(order[j])) {
                            arrList.add(order[j]);
                        }
                    }
 
                    if (arrList.size() != N) { // 나중에도 사용되는 콘센트가 구멍의 개수보다 작을 경우.
                        for (int j = 0; j < use.length; j++) {
                            if (use[j] && !arrList.contains(j)) { // 그 콘센트를 제거.
                                use[j] = false;
                                break;
                            }
                        }
                    } else { // 현재 꽂혀 있는 모든 콘센트가 나중에도 사용될 경우
                        int remove = arrList.get(arrList.size() - 1); // 가장 마지막에 사용될 콘센트를 제거.
                        use[remove] = false;
                    }
 
                    use[temp] = true;
                    ans++;
                }
            }
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Main {
//	static int N, M;
//	static int[] tap;
//	static boolean[] used=new boolean[101];
//	static int answer;
//	
//    public static void main(String[] args) throws IOException {
//	   
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	    String[] s = br.readLine().split(" ");
//	    N = Integer.valueOf(s[0]);
//	    M = Integer.valueOf(s[1]);
//	    tap = new int[M];
//	    StringTokenizer st = new StringTokenizer(br.readLine());
//	    for(int i=0; i<M; i++) {
//	    	tap[i] = Integer.valueOf(st.nextToken());
//	    }
//	   int put=0;
//	    for(int i=0; i<M; i++) {
//	    	int temp = tap[i];
//	    	if(!used[temp]) { // 사용안하는 중
//	    		if(put<N){
//	    			used[temp]=true;// 콘서트가 꽃을 공간이 있는경우
//	    			put++;
//	    			
//	    		}else { // 꽃을 공간이 없다.
//	    			find(i, temp);
//	    		}
//	    	}
//    		used[temp]=true;
//	    }
//	    System.out.println(answer);
//    }
//    public static void find(int idx,int num) {
//    	boolean[] temp = new boolean[101];
//    	int c = 0;
//		for(int i=idx+1; i<idx+N+1; i++) {
//			if(i==M) break;
//			 if (used[tap[i]] && !temp[tap[i]]) {
//				 temp[i]=true;
//				 c++;
//             }
//		}
//		if(c==N) { // 다 사용된다면.
//			used[tap[idx+N]]=false;
//		}else {
//			for(int i=0; i<101; i++) { // 다 사용 안된다.
//				if(used[i] && !temp[i]) {
//					used[i]=false;
//					break;
//				}
//			}			
//		}
//		answer++;
//	}
//}
