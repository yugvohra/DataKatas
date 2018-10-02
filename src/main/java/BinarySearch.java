import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by vohray on 9/12/18.
 */
public class BinarySearch {

  private static int binarySearch(int[] numbers, long numberToSearch) {
    int lowIdx = 0;
    int highIdx = numbers.length-1;
    while (lowIdx <= highIdx) {
      int midIdx = (highIdx + lowIdx) / 2;
      long midElement = numbers[midIdx];
      if (numberToSearch > midElement) {
        lowIdx = midIdx + 1;
      } else if (numberToSearch < midElement) {
        highIdx = midIdx - 1;
      } else
        return midIdx;
    }
    return -1;

  }

  public static void main(String[] args) throws IOException {

    FastScanner scanner = new FastScanner(System.in);
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
    for (int count = 0; count < m; count++) {
      System.out.print(binarySearch(a, b[count]) + " ");
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
