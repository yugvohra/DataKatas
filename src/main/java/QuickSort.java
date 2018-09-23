import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by vohray on 9/12/18.
 */
public class QuickSort {
  private static Random random = new Random();

  private static int[] ThreeWayPartition(int[] numbers, int leftIdx, int rightIdx) {
    int pivotNumber = numbers[leftIdx];
    int leftBoundary = leftIdx;
    int rightBoundary=leftIdx;
    for (int idx = leftIdx + 1; idx <= rightIdx; idx++) {
      if (numbers[idx] < pivotNumber) {
        rightBoundary++;
        int tempHolder = numbers[idx];
        numbers[idx] = numbers[rightBoundary];
        numbers[rightBoundary] = tempHolder;
        leftBoundary++;
        tempHolder = numbers[rightBoundary];
        numbers[rightBoundary] = numbers[leftBoundary];
        numbers[leftBoundary] = tempHolder;
      }
      else if(numbers[idx]==pivotNumber)
      {
        rightBoundary++;
        int tempHolder = numbers[idx];
        numbers[idx] = numbers[rightBoundary];
        numbers[rightBoundary] = tempHolder;
      }
    }
    int tempHolder = numbers[leftIdx];
    numbers[leftIdx] = numbers[leftBoundary];
    numbers[leftBoundary] = tempHolder;
    int[] m = {leftBoundary, rightBoundary};
    return m;
  }

  private static int twoWayPartition(int[] numbers, int leftIdx, int rightIdx) {
    int pivotNumber = numbers[leftIdx];
    int leftWindowIdx = leftIdx;
    for (int idx = leftIdx + 1; idx <= rightIdx; idx++) {
      if (numbers[idx] <= pivotNumber) {
        leftWindowIdx++;
        int tempHolder = numbers[idx];
        numbers[idx] = numbers[leftWindowIdx];
        numbers[leftWindowIdx] = tempHolder;
      }
    }
    int tempHolder = numbers[leftIdx];
    numbers[leftIdx] = numbers[leftWindowIdx];
    numbers[leftWindowIdx] = tempHolder;
    return leftWindowIdx;
  }

  private static void randomizedQuickSort(int[] numbers, int leftIdx, int rightIdx) {
    if (leftIdx >= rightIdx) {
      return;
    }
    int randomIdx = random.nextInt(rightIdx - leftIdx + 1) + leftIdx;
    /*swap the number at random idx with leftmost element*/
    int tempHolder = numbers[leftIdx];
    numbers[leftIdx] = numbers[randomIdx];
    numbers[randomIdx] = tempHolder;
    //use ThreeWayPartition
   // int pivotIdx = twoWayPartition(numbers, leftIdx, rightIdx);
    int[] pivotIdx = ThreeWayPartition(numbers, leftIdx, rightIdx);
    randomizedQuickSort(numbers, leftIdx, pivotIdx[0]-1);
    randomizedQuickSort(numbers, pivotIdx[1] + 1, rightIdx);
  }

  public static void main(String[] args) {
    FastScanner scanner = new FastScanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    randomizedQuickSort(a, 0, n - 1);
    for (int i = 0; i < n; i++) {
      System.out.print(a[i] + " ");
    }
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    FastScanner(InputStream stream) {
      try {
        br = new BufferedReader(new InputStreamReader(stream));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}

