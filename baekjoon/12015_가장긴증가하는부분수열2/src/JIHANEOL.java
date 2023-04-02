import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class JIHANEOL {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] list; // 위치 배열 
    public static void main(String[] args) throws IOException {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());
    	int N = Integer.valueOf(st.nextToken());
    	int[] arr = new int[N];
    	list = new int[N];
    	int size =0;
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<arr.length; i++) {
    		arr[i] = Integer.valueOf(st.nextToken());
    	}
    	list[size++]= arr[0];
    	for(int i=1; i<N; i++) {
    		// 현재 차례가 뒤에 붙을 수 없다면.
    		if(list[size-1]>=arr[i]) {
    			// 위치를 잡아준다.
    			int mid = binarySearch(0,size-1,arr[i]);
    			// 같다면 넘겨
    			if(list[mid]==arr[i]) {
    				continue;
    			//다르다면 그자리를 변경해준다.ㅉ
    			}else {
    				list[mid] = arr[i];
    			}
    		}else {
    			list[size++] = arr[i];
    		}
    		
    	}
    	System.out.println(size);
    	
    }
    public static int binarySearch(int s, int e,int target) {
    	int mid=0;
    	while(s<e) {
    		mid = (s+e)/2;
    		
    		// LOWER BOUND 
    		if(target<=list[mid]) {
    			e=mid;
    		}else {
    			s=mid+1;
    		}
    		
    	}
    	return s;
    }

}