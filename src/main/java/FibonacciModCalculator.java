import java.util.Scanner;

/**
 * Created by vohray on 9/6/18.
 */
public class FibonacciModCalculator {

  private static long findPisanoPeriod(long modulus) {
    long periodFirst = 0;
    long periodSecond = 1;
    long pisanoPeriodLength = 2;
    for (int count = 0; count < modulus * modulus; count++) {
      long remainder = (periodFirst + periodSecond) % modulus;
      periodFirst = periodSecond;
      periodSecond = remainder;
      if (periodFirst == 0 && periodSecond == 1) {
        pisanoPeriodLength = count + 1;
        break;
      }
    }
    return pisanoPeriodLength;
  }

  private static long getFibonacciMod(long n, long modulus) {
    long periodRemainder = n % findPisanoPeriod(modulus);

    long firstRemainder = 0;
    long secondRemainder = 1;
    long remainder = periodRemainder;
    for (int count = 1; count <periodRemainder; count++) {
      remainder = (firstRemainder + secondRemainder) % modulus;
      firstRemainder = secondRemainder;
      secondRemainder = remainder;
    }

    return remainder % modulus;
  }

  public static void main(String args[]) {
    Scanner s = new Scanner(System.in);
    long n = s.nextLong();
    long modulus = s.nextLong();
    System.out.println(getFibonacciMod(n, modulus));
  }

}
