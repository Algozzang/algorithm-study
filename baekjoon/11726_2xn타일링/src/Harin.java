package algorithm_study;

import java.util.*;

public class Harin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        int[] dp = new int[num + 1];

        dp[0] = 0;
        if(num >= 1) dp[1] = 1;
        if(num >= 2) dp[2] = 2;


        for (int i = 3; i <= num; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10007;
        }

        System.out.println(dp[num]);
    }
}
