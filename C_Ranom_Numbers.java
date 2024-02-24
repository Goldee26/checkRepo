import java.util.*;

public class C_Ranom_Numbers {
    public static long solve(int i, long[][][] dp, int mx, int change, int[] val, String s) {
        if (i == -1)
            return 0;
        if (dp[i][mx][change] != -1)
            return dp[i][mx][change];
        int sign = 1;
        if (mx > s.charAt(i) - 'A')
            sign = -1;
        long res = sign * val[s.charAt(i) - 'A'] + solve(i - 1, dp, Math.max(s.charAt(i) - 'A', mx), change, val, s);
        if (change == 0) {
            for (int j = 0; j < 5; j++) {
                if (j != s.charAt(i) - 'A') {

                    sign = 1;
                    if (j < mx)
                        sign = -1;
                    res = Math.max(res, sign * val[j] + solve(i - 1, dp, Math.max(mx, j), 1, val, s));
                }
            }
        }
        return dp[i][mx][change] = res;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int m = 0; m < t; m++) {
            String s = sc.next();
            long[][][] dp = new long[s.length()][7][2];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < 7; j++) {
                    for (int k = 0; k < 2; k++)
                        dp[i][j][k] = -1;
                }
            }
            int[] val = { 1, 10, 100, 1000, 10000 };

            System.out.println(solve(s.length() - 1, dp, 0, 0, val, s));
        }
        sc.close();
    }
}