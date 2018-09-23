import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Created by vohray on 9/8/18.
 */
public class MaximumSalaryCalculator {


  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    int totalNumbers = (int) scanner.nextLong();
    List<NumberHolder> items = new ArrayList<>();
    for (int count = 0; count < totalNumbers; count++) {
      items.add(new NumberHolder(scanner.next()));
    }
    sortNumbers(items);
    System.out.println(items.stream().map(NumberHolder::getNumber).collect(Collectors.joining()));
  }


  private static void sortNumbers(List<NumberHolder> numbers) {
    Collections.sort(numbers);
  }


  private static class NumberHolder implements Comparable<NumberHolder> {
    public String getNumber() {
      return number;
    }

    private final String number;
    private final char[] charArray;

    private NumberHolder(String number) {
      this.number = number;
      this.charArray = number.toCharArray();
    }


    public int compareTo(NumberHolder o) {
      if (this.charArray.length == o.charArray.length)
        return Integer.parseInt(o.number) - Integer.parseInt(this.number);
      int leftConcNumber = Integer.parseInt(this.number + o.number);
      int rightConNumber = Integer.parseInt(o.number + this.number);
      return rightConNumber - leftConcNumber;
    }

  }


  static class Scanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    Scanner(InputStream inputStream) {
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

    double nextDouble() throws IOException {
      return Double.parseDouble(next());
    }
  }

}
