import java.util.Scanner;

/**
 * Created by vohray on 9/6/18.
 */
public class GCDCalculator {

  private static Long getGCD(long firstNumber, long secondNumber) {
    Long divident = firstNumber > secondNumber ? firstNumber : secondNumber;
    Long divisor = firstNumber < secondNumber ? firstNumber : secondNumber;
    Long remainder = divident % divisor;
    while (remainder != 0) {
      divident = divisor;
      divisor = remainder;
      remainder = divident % divisor;
    }
    return divisor;
  }

  public static void main(String args[]) {
    Scanner s = new Scanner(System.in);
    int firstNumber = s.nextInt();
    int secondNumber = s.nextInt();
    System.out.println(getGCD(firstNumber, secondNumber));
  }
}
