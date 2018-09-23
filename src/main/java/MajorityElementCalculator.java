import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by vohray on 9/12/18.
 */
public class MajorityElementCalculator {

  private static int getMajorityElement(int[] numbers, int left, int right) {

    if (left == right)
      return numbers[left];
    int pivotPoint = (left + right) / 2;
    int leftResult = getMajorityElement(numbers, left, pivotPoint);
    int rightResult = getMajorityElement(numbers, pivotPoint + 1, right);
    if (isMajorityNumber(numbers, leftResult, left, right))
      return leftResult;
    else if (isMajorityNumber(numbers, rightResult, left, right))
      return rightResult;
    else
      return -1;
  }

  private static boolean isMajorityNumber(int[] numbers, int numberToBeTested, int left, int right) {
    int occurrenceCount = 0;
    for (int count = left; count <= right; count++) {
      if (numbers[count] == numberToBeTested)
        occurrenceCount++;
    }
    if (occurrenceCount > (right - left+1) / 2)
      return true;
    return false;
  }
  public static void main(String[] args) {
    FastScanner scanner = new FastScanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    if (getMajorityElement(a, 0, a.length-1) != -1) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }
  }
  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    FastScanner(InputStream stream) {
      try {
        br = new BufferedReader(new InputStreamReader(stream));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}