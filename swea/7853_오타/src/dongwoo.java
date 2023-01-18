import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class dongwoo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        
        int T;
        T= Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String str1 = br.readLine();
            int length = str1.length();
            ArrayList<Set<Character>> possible = new ArrayList<>();
            for(int i=0; i<length; i++) {
                for (int j=i-1; j<=i+1; j++) {
                    if (j >= 0 && j < length) {
                        if (possible.size() <= i) {
                            possible.add(new HashSet<>());
                        }
                        Set<Character> temp = possible.get(i);
                        temp.add(str1.charAt(j));
                        possible.set(i, temp);
                    }
                }
            }
            long result = 1;
            for (Set<Character> hubo:possible) {
                result = result*hubo.size();
                if (result > 1_000_000_007) {
                    result %= 1_000_000_007;
                }
            }
            System.out.println("#"+test_case+" "+result%1_000_000_007);
        }
	}

}
