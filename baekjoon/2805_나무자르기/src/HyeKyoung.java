import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyeKyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]), m = Integer.parseInt(str[1]);
		int max = Integer.MIN_VALUE, low, mid = 0, high;
		long sum = 0;
		int[] height = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			if (max < height[i]) max = height[i];
		}

		low = 1;
		high = max;
		while (low <= high) {
			mid = (low + high) / 2;
			sum = 0;

			for (int i : height)
				if (i > mid) sum += i - mid;

			if (sum > m) low = mid + 1;
			else if (sum < m) high = mid - 1;
			else break;
		}
		if(sum < m) mid--;
		System.out.println(mid);
	}

}
