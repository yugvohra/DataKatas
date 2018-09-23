import java.util.Scanner;

/**
 * Created by vohray on 9/6/18.
 */
public class FibonaciNumberFinder {

  private static Long getNthFibonaaciNumber(int n) {
    if(n<=0)return 0l;
    Long[] fibonaciNumbers = new Long[n+1];
    Long firstTerm = 0l;
    Long secondTerm = 1l;
    fibonaciNumbers[0] = firstTerm;
    fibonaciNumbers[1] = secondTerm;
    for (int count = 2; count <=n; count++) {
      Long fibonaciTerm = secondTerm + firstTerm;
      fibonaciNumbers[count] = fibonaciTerm;
      firstTerm = secondTerm;
      secondTerm = fibonaciTerm;

    }
    return fibonaciNumbers[n];
  }

  public static void main(String[] args) {
    Scanner inputScanner = new Scanner(System.in);
    int n = inputScanner.nextInt();
    System.out.println(getNthFibonaaciNumber(n));
  }
}
