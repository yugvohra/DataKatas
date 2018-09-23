import java.util.Scanner;

/**
 * Created by vohray on 9/6/18.
 */
public class FibonacciSumLastDigitCalculator {

  private static int[] getLastDigitsOfFibonacciSequence(int n)
  {
    if(n==0){
      int[] fibNumbersLastDifgits={0};
      return fibNumbersLastDifgits;
    }
    int[] fibNumbersLastDifgits = new int[n+1];
    int firstNumberDigit = 0;
    int secondNumberDigit = 1;
    fibNumbersLastDifgits[0] = firstNumberDigit;
    fibNumbersLastDifgits[1] = secondNumberDigit;
    for (int count = 2; count <= n; count++) {
      int currentFibLastDigit = (fibNumbersLastDifgits[count - 1] + fibNumbersLastDifgits[count - 2]) % 10;
      fibNumbersLastDifgits[count] = currentFibLastDigit;
    }
    return fibNumbersLastDifgits;
  }

  private static long getLastDigitOfFibNumbers(long n){

    if(n==0){return 0;}
    int secondLastTermDigit=0;
    int lastTermDigit=1;
    for(long count=1;count<n;count++)
    {
      int currentDigit=(lastTermDigit+secondLastTermDigit)%10;
      secondLastTermDigit=lastTermDigit;
      lastTermDigit=currentDigit;
    }
    return lastTermDigit;
  }

  public static void main(String args[])
  {
    Scanner s = new Scanner(System.in);
    long n = s.nextLong();
    System.out.println(getLastDigitOfFibNumbers(n));
  }
}
