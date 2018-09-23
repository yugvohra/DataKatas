import java.util.Scanner;

/**
 * Created by vohray on 9/7/18.
 */
public class MoneyChanger {

private static int[] availableCoins={ 1 ,3 ,4};

  private static long findNumberOfCoinsWithDynamicProgramming(int anAmount) {
    if (anAmount <= availableCoins[1])
      return anAmount;
    int[] dynamicallyCalculatedMinCoins = new int[anAmount + 1];
    for (int money = 1; money <= anAmount; money++) {
      int minNumberOfCoins = dynamicallyCalculatedMinCoins[money-1]+1;
      for (int coinIdx = 1; coinIdx < availableCoins.length; coinIdx++) {
        int coin = availableCoins[coinIdx];
        if (money >= coin) {
          int numberOfCoins = dynamicallyCalculatedMinCoins[money - coin] + 1;
          if (numberOfCoins < minNumberOfCoins)
            minNumberOfCoins = numberOfCoins;
        }

      }
      dynamicallyCalculatedMinCoins[money] = minNumberOfCoins;
    }
    return dynamicallyCalculatedMinCoins[anAmount];
  }

  public static void main(String[] args) {
    Scanner inputScanner = new Scanner(System.in);
    int anAmount = inputScanner.nextInt();
    System.out.println(findNumberOfCoinsWithDynamicProgramming(anAmount));
  }
}
