import java.util.Scanner;

/**
 * Created by vohray on 9/16/18.
 */
public class DynamicKnapSackFiller {

  static int optimalWeight(int knapsackCapacity, int[] items) {
    int[][] weightHolder = new int[items.length][knapsackCapacity + 1];
    for (int itemIdx = 1; itemIdx < items.length; itemIdx++) {
      int itemWeight = items[itemIdx];
      for (int currentWeight = 1; currentWeight <= knapsackCapacity; currentWeight++) {
        int maxWeight = weightHolder[itemIdx - 1][currentWeight];
        weightHolder[itemIdx][currentWeight] = maxWeight;
        if (currentWeight >= itemWeight) {
          int maxWeightWithItem = weightHolder[itemIdx - 1][currentWeight - itemWeight] + itemWeight;
          weightHolder[itemIdx][currentWeight] = maxWeightWithItem > maxWeight ? maxWeightWithItem : maxWeight;
        }
      }
    }
    return weightHolder[items.length - 1][knapsackCapacity];
  }



  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int W, n;
    W = scanner.nextInt();
    n = scanner.nextInt();
    int[] w = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      w[i] = scanner.nextInt();
    }
    System.out.println(optimalWeight(W, w));
  }

}
