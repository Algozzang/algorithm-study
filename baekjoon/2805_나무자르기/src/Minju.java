import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Minju {
	// 입력 값의 범위를 조심해야 하는 문제
    public static void main(String[] args) throws NumberFormatException, IOException {

    StringBuilder sb = new StringBuilder();
    /* input */
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String[] token = br.readLine().split(" ");
    int n = Integer.parseInt(token[0]);
    long m = Integer.parseInt(token[1]);
    long[] height = new long[n]; //입력받는 나무 높이 배열 <= 1_000_000_000
    
    String[] token2 = br.readLine().split(" ");
    for (int i = 0; i < n ; i++) {
        height[i] = Long.parseLong(token2[i]);
        
    }
    
    /* sort */
    Arrays.sort(height);
    
    
    /* 이분 탐색 */
    long maxHeight = 0; // 절단기 설정 최대 높이
    //시작 높이 0부터
    long start = 0;
    long end = height[n-1];
    
    while(start<=end) {
        long mid = (start+end)/2;
        long sum = 0;
        for(int j =0; j < n;j++) {
            if(height[j] > mid) sum += Math.abs(height[j]-mid);
        }
        if(sum<m) { // m길이가 아직 못 구해졌다면
             end = mid-1; // 범위 위로
        } else {
            if(maxHeight < mid) maxHeight = mid; //최대값 저장
             start = mid+1;
        }
    }
    
    System.out.println(maxHeight);
    
}
}