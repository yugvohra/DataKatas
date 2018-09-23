import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
  private int[] data;
  private List<Swap> swaps;

  private FastScanner in;
  private PrintWriter out;

  public static void main(String[] args) throws IOException {
    new BuildHeap().solve();
  }

  private void readData() throws IOException {
    int n = in.nextInt();
    data = new int[n];
    for (int i = 0; i < n; ++i) {
      data[i] = in.nextInt();
    }
  }

  private void writeResponse() {
    out.println(swaps.size());
    for (Swap swap : swaps) {
      out.println(swap.index1 + " " + swap.index2);
    }
  }

  private void generateSwaps() {
    swaps = new ArrayList<Swap>();
    for (int elementIndex = data.length/2; elementIndex >=0;elementIndex--) {
          swaps.addAll(swapDown(elementIndex));
        }
      }
  private List<Swap> swapDown(int index){
    List<Swap> swaps=new LinkedList<>();
    while(index<=data.length/2)
    {
      int element=data[index];
      int minIdx=index;
      if(2*index+1<data.length&&data[2*index+1]<element)
      {
        minIdx=2*index+1;
      }
      if(2*index+2<data.length&&data[2*index+2]<data[minIdx])
      {
        minIdx=2*index+2;
      }
      if(minIdx==index)
        break;
      else
      {
        swaps.add(new Swap(index,minIdx));
        data[index]=data[minIdx];
        data[minIdx]=element;
        index=minIdx;
      }
    }
    return swaps;
  }

  public void solve() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    readData();
    generateSwaps();
    writeResponse();
    out.close();
  }

  static class Swap {
    int index1;
    int index2;

    public Swap(int index1, int index2) {
      this.index1 = index1;
      this.index2 = index2;
    }
  }

  static class FastScanner {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner() {
      reader = new BufferedReader(new InputStreamReader(System.in));
      tokenizer = null;
    }

    public String next() throws IOException {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
  }
}
