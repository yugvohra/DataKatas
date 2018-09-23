import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by vohray on 9/5/18.
 */
public class MaximumProductFinder {

  public static long getMaximumProduct(long numbers[]) {
    long maxNumber = 0;
    long secondMaxNumber = 0;
    for (long number : numbers) {
      if (number >= maxNumber) {
        secondMaxNumber = maxNumber;
        maxNumber = number;
      }
      else if(number>=secondMaxNumber)
      {
        secondMaxNumber=number;
      }
    }
    return maxNumber * secondMaxNumber;

  }

  public static void main(String args[]) throws IOException {
    FastScanner scanner = new FastScanner(System.in);
    int totalNumbers = (int)scanner.nextLong();
    long[] numbers = new long[totalNumbers];
    for (int count = 0; count < totalNumbers; count++) {
      numbers[count] = scanner.nextLong();
    }
    System.out.println(getMaximumProduct(numbers));

  }

  static class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    FastScanner(InputStream inputStream) {
      try {
        reader = new BufferedReader(new
          InputStreamReader(inputStream));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String next() throws IOException {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }

    long nextLong() throws IOException {
      return Long.parseLong(next());
    }
  }
}