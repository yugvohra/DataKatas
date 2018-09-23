import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vohray on 9/14/18.
 */
public class PrimitiveCalculator {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    List<OperationSequence> sequence = getOptimalSequence(n);
    System.out.println(sequence.get(n).getMinimumOperations());
    List<Integer>sequenceOfNumbers=new ArrayList<>();
    sequenceOfNumbers.add(n);
    while (n>1)
    {
      int currentNumber=sequence.get(n).getBackTrackedNumber();
      sequenceOfNumbers.add(currentNumber);
      n=currentNumber;
    }
    for(int idx=sequenceOfNumbers.size()-1;idx>=0;idx--)
    {
      System.out.print(sequenceOfNumbers.get(idx) + " ");
    }
  }

  private static List<OperationSequence> getOptimalSequence(int n) {
    List<OperationSequence> sequenceList = new ArrayList<OperationSequence>();
    sequenceList.add(new OperationSequence(0, -1));
    sequenceList.add(new OperationSequence(0, 0));
    for (int currentNumber = 2; currentNumber <= n; currentNumber++) {

      int minimumOperations = sequenceList.get(currentNumber - 1).getMinimumOperations() + 1;
      int minOperationsWithMultiplyBy2 = currentNumber % 2 == 0 ? sequenceList.get(currentNumber / 2).getMinimumOperations() + 1 : Integer.MAX_VALUE;
      int minOperationsWithMultiplyBy3 = currentNumber % 3 == 0 ? sequenceList.get(currentNumber / 3).getMinimumOperations() + 1 : Integer.MAX_VALUE;
      if (minimumOperations <= minOperationsWithMultiplyBy2 && minimumOperations <= minOperationsWithMultiplyBy3) {
        sequenceList.add(new OperationSequence(minimumOperations, currentNumber - 1));
      } else if (minOperationsWithMultiplyBy2 <= minimumOperations && minOperationsWithMultiplyBy2 <= minOperationsWithMultiplyBy3) {
        sequenceList.add(new OperationSequence(minOperationsWithMultiplyBy2, currentNumber / 2));
      } else {
        sequenceList.add(new OperationSequence(minOperationsWithMultiplyBy3, currentNumber / 3));
      }

    }
    return sequenceList;
  }

  private static class OperationSequence {
    public int getBackTrackedNumber() {
      return backTrackedNumber;
    }

    public int getMinimumOperations() {
      return minimumOperations;
    }

    private final int minimumOperations;
    private final int backTrackedNumber;

    private OperationSequence(int minimumOperations, int backTrackedNumber) {
      this.minimumOperations = minimumOperations;
      this.backTrackedNumber = backTrackedNumber;
    }

    @Override
    public String toString() {
      return "OperationSequence{" +
        "minimumOperations=" + minimumOperations +
        ", backTrackedNumber=" + backTrackedNumber +
        '}';
    }
  }
}
