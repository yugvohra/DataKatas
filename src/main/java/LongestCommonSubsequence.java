import java.util.Scanner;

/**
 * Created by vohray on 9/14/18.
 */
public class LongestCommonSubsequence {
  private static int lcs2(int[] a, int[] b) {
    int lcs[][] = new int[a.length + 1][b.length + 1];

    for (int aIdx = 0; aIdx <= a.length; aIdx++) {
      for (int bIdx = 0; bIdx <= b.length; bIdx++) {
        if (aIdx == 0 || bIdx == 0)
          lcs[aIdx][bIdx] = 0;
        else if (a[aIdx - 1] == b[bIdx - 1])
          lcs[aIdx][bIdx] = lcs[aIdx - 1][bIdx - 1] + 1;
        else
          lcs[aIdx][bIdx] = Math.max(lcs[aIdx - 1][bIdx], lcs[aIdx][bIdx - 1]);
      }
    }
    return lcs[a.length][b.length];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }

    int m = scanner.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < m; i++) {
      b[i] = scanner.nextInt();
    }

    System.out.println(lcs2(a, b));
  }
}
