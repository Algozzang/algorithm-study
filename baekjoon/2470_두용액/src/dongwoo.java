import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class dongwoo {
	public static int[] yongAekAsc;
	public static int[] yongAekDesc;
	public static int[] result = new int[2];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		yongAekAsc = new int[n];
		yongAekDesc = new int[n];
		for (int i = 0; i < n; i++) {
			yongAekAsc[i] = Integer.parseInt(temp[i]);
		}
		Arrays.sort(yongAekAsc);
		for (int i = 0; i < n; i++) {
			yongAekDesc[i] = yongAekAsc[n-i-1];
		}
		
		int diffMin = Integer.MAX_VALUE;
		int i = 0, j = 0;
		while (i<n && j< n) {
			if (yongAekAsc[i] == yongAekDesc[j]) {
				if (i>j) {
					j++;
				} else {
					i++;
				}
				continue;
			}
			int diff = yongAekAsc[i] + yongAekDesc[j];
			if (Math.abs(diff) < diffMin) {
				diffMin = Math.abs(diff);
				result[0] = yongAekAsc[i];
				result[1] = yongAekDesc[j];
			}
			if (diff>0) {
				j+=1;
			}
			if (diff == 0) {
				break;
			}
			if (diff<0) {
				i+=1;
			}
		}
		
		System.out.println(Math.min(result[0], result[1]) + " " + Math.max(result[0], result[1]));
	}
	

}
