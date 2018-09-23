import java.util.Scanner;

/**
 * Created by vohray on 9/6/18.
 */
public class FibonacciLastDigitCalculator {

  private static long getLastDigitOfNthFibonaaciNumber(int n) {
    int[] fibNumbersLastDifgits = new int[n];
    int firstNumberDigit = 1;
    int secondNumberDigit = 1;
    fibNumbersLastDifgits[0] = firstNumberDigit;
    fibNumbersLastDifgits[1] = secondNumberDigit;
    for (int count = 2; count < n; count++) {
      int currentFibLastDigit = (fibNumbersLastDifgits[count - 1] + fibNumbersLastDifgits[count - 2]) % 10;
      fibNumbersLastDifgits[count] = currentFibLastDigit;
    }
    return fibNumbersLastDifgits[n - 1];
  }

  public static void main(String[] args) {
    Scanner inputScanner = new Scanner(System.in);
    int n = inputScanner.nextInt();
    System.out.println(getLastDigitOfNthFibonaaciNumber(n));
  }

}
