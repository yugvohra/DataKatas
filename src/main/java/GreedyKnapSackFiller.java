
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by vohray on 9/8/18.
 */
public class GreedyKnapSackFiller {
  private static double getMaximumValue(long knapSackCapacity, List<KnapSackItem> items) {
    int itemIdx = 0;
    double value = 0d;
    while (knapSackCapacity > 0 && (itemIdx < items.size())) {
      KnapSackItem item = items.get(itemIdx);
      if (knapSackCapacity >= item.getWeight()) {
        value += item.getValue();
        knapSackCapacity -= item.getWeight();
        itemIdx++;
      } else {
        value += (knapSackCapacity * item.getValuePerWeight());
        itemIdx++;
        break;
      }
    }
    return value;
  }

  private static List<KnapSackItem> sortKnapSackItems(List<KnapSackItem> items) {
    Collections.sort(items);
    return items;
  }

  public static void main(String[] args) throws IOException {
    GreedyScanner scanner = new GreedyScanner(System.in);
    int totalItems = (int) scanner.nextLong();
    long knapsackCapacity = scanner.nextLong();
    List<KnapSackItem> items = new ArrayList<KnapSackItem>();
    for (int count = 0; count < totalItems; count++) {
      items.add(new KnapSackItem(scanner.nextDouble(), scanner.nextDouble()));
    }
    System.out.println(getMaximumValue(knapsackCapacity, sortKnapSackItems(items)));
  }


  public static class KnapSackItem implements Comparable<KnapSackItem> {
    private final double weight;
    private final double value;
    private final double valuePerWeight;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof KnapSackItem)) return false;

      KnapSackItem that = (KnapSackItem) o;

      if (Double.compare(that.getWeight(), getWeight()) != 0) return false;
      if (Double.compare(that.getValue(), getValue()) != 0) return false;
      return Double.compare(that.getValuePerWeight(), getValuePerWeight()) == 0;

    }

    @Override
    public int hashCode() {
      int result;
      long temp;
      temp = Double.doubleToLongBits(getWeight());
      result = (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(getValue());
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(getValuePerWeight());
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      return result;
    }

    public KnapSackItem(double value,double weight) {
      this.weight = weight;
      this.value = value;
      this.valuePerWeight = value / weight;
    }

    public double getValuePerWeight() {
      return valuePerWeight;
    }

    public double getWeight() {
      return weight;
    }

    public double getValue() {
      return value;
    }

    public int compareTo(KnapSackItem o) {
      if (this.getValuePerWeight() == o.getValuePerWeight())
        return 0;
      return this.getValuePerWeight() < o.getValuePerWeight() ? 1 : -1;
    }
  }


  static class GreedyScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    GreedyScanner(InputStream inputStream) {
      try {
        reader = new BufferedReader(new
          InputStreamReader(inputStream));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String next() throws IOException {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }

    long nextLong() throws IOException {
      return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
      return Double.parseDouble(next());
    }
  }
}