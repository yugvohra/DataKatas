import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

  private FastScanner in;
  private PrintWriter out;
  private HashSet<String> elems;

  private int bucketCount;
  private int prime = 1000000007;
  private int multiplier = 263;


  public static void main(String[] args) throws IOException {
    new HashChains().processQueries();
  }

  public int hashFunc(String s) {
    long hash = 0;
    for (int i = s.length() - 1; i >= 0; --i)
      hash = (hash * multiplier + s.charAt(i)) % prime;
    return (int) hash % bucketCount;
  }

  private Query readQuery() throws IOException {
    String type = in.next();
    if (!type.equals("check")) {
      String s = in.next();
      return new Query(type, s);
    } else {
      int ind = in.nextInt();
      return new Query(type, ind);
    }
  }

  private void writeSearchResult(boolean wasFound) {
    out.println(wasFound ? "yes" : "no");
  }

  private void processQuery(Query query) {
    switch (query.type) {
      case "add":
        elems.put(query.s);
        break;
      case "del":
        elems.remove(query.s);
        break;
      case "find":
        writeSearchResult(elems.find(query.s));
        break;
      case "check":
        for (TableEntry<String, Boolean> cur : elems.getChainAt(query.ind))
          out.print(cur.getKey() + " ");
        out.println();
        break;
      default:
        throw new RuntimeException("Unknown query: " + query.type);
    }
  }

  public void processQueries() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    bucketCount = in.nextInt();
    elems = new HashSet<>(bucketCount);
    int queryCount = in.nextInt();
    for (int i = 0; i < queryCount; ++i) {
      processQuery(readQuery());
    }
    out.close();
  }

  static class Query {
    String type;
    String s;
    int ind;

    public Query(String type, String s) {
      this.type = type;
      this.s = s;
    }

    public Query(String type, int ind) {
      this.type = type;
      this.ind = ind;
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

  class HashSet<K> {

    private LinkedList<TableEntry<K, Boolean>>[] table;
    private int cardinality;

    public HashSet(int cardinality) {
      this.cardinality = cardinality;
      this.table = new LinkedList[cardinality];
    }

    public Boolean find(K key) {
      int hashValur = hashFunc((String) key);
      List<TableEntry<K, Boolean>> foundList = table[hashValur];
      if (foundList == null || foundList.isEmpty())
        return false;
      for (TableEntry<K, Boolean> entry : foundList) {
        if (entry.getKey().equals(key))
          return entry.getValue();
      }
      return false;
    }

    public void put(K key) {
      int hashValur = hashFunc((String) key);
      LinkedList<TableEntry<K, Boolean>> foundList = table[hashValur];
      if (foundList == null)
        foundList = new LinkedList<>();
      for (TableEntry<K, Boolean> entry : foundList) {
        if (entry.getKey().equals(key)) {
          entry.setValue(true);
          return;
        }
      }
      foundList.addFirst(new TableEntry<K, Boolean>(key, true));
      table[hashValur] = foundList;
    }

    public void remove(K key) {
      int hashValur = hashFunc((String) key);
      List<TableEntry<K, Boolean>> foundList = table[hashValur];
      if (foundList == null || foundList.isEmpty())
        return;
      for (TableEntry<K, Boolean> entry : foundList) {
        if (entry.getKey().equals(key)) {
          foundList.remove(entry);
          break;
        }
      }
    }

    public List<TableEntry<K, Boolean>> getChainAt(int index) {
      return table[index]==null?new LinkedList<>():table[index];
    }
  }

  private static class TableEntry<K, V> {
    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    K key;
    V value;

    public TableEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

}
