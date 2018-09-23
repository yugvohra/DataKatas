import java.util.*;

/**
 * Created by vohray on 9/16/18.
 */
public class DynamicKnapSacksFiller {
  private static int partition3(List<Integer> items) {
    int knapsackCapacity = 0;
    for (int item : items) {
      knapsackCapacity += item;
    }
    if (knapsackCapacity % 3 != 0)
      return 0;
    knapsackCapacity = knapsackCapacity / 3;
    int[][] firstKnapSack = new int[items.size()][knapsackCapacity + 1];
    int[][] secondKnapSack = new int[items.size()][knapsackCapacity + 1];
    for (int itemIdx = 1; itemIdx < items.size(); itemIdx++) {
      int itemWeight = items.get(itemIdx);
      for (int currentWeight = 1; currentWeight <= knapsackCapacity; currentWeight++) {
        int firstMaxWeight = firstKnapSack[itemIdx - 1][currentWeight];
        int secondMaxWeight = secondKnapSack[itemIdx - 1][currentWeight];
        secondKnapSack[itemIdx][currentWeight] = secondMaxWeight;
        firstKnapSack[itemIdx][currentWeight] = firstMaxWeight;
        if (currentWeight >= itemWeight) {
          int firstMaxWeightWithItem = firstKnapSack[itemIdx - 1][currentWeight - itemWeight] + itemWeight;
          int secondMaxWeightWithItem = secondKnapSack[itemIdx - 1][currentWeight - itemWeight] + itemWeight;
          if (firstMaxWeightWithItem > firstMaxWeight)
            firstKnapSack[itemIdx][currentWeight] = firstMaxWeightWithItem;
          else if (secondMaxWeightWithItem > secondMaxWeight)
            secondKnapSack[itemIdx][currentWeight] = secondMaxWeightWithItem;
        }
      }
    }
    if ((firstKnapSack[items.size() - 1][knapsackCapacity] == knapsackCapacity) && (secondKnapSack[items.size() - 1][knapsackCapacity] == knapsackCapacity))
      return 1;
    return 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    List<Integer> items = new ArrayList<>();
    items.add(0);
    for (int i = 1; i <=n; i++) {
      items.add(scanner.nextInt());
    }
    Collections.sort(items);

    System.out.println(partition3(items));
  }
}

