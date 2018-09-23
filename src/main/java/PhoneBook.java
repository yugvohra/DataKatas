import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

  private FastScanner in = new FastScanner();
  private HashTable<Integer, Contact> contacts = new HashTable<>();

  public static void main(String[] args) {
    new PhoneBook().processQueries();
  }

  private Query readQuery() {
    String type = in.next();
    int number = in.nextInt();
    if (type.equals("add")) {
      String name = in.next();
      return new Query(type, name, number);
    } else {
      return new Query(type, number);
    }
  }

  private void writeResponse(String response) {
    System.out.println(response);
  }


  private void processQuery(Query query) {
    if (query.type.equals("add")) {
      contacts.put(query.number, new Contact(query.name, query.number));
    } else if (query.type.equals("del")) {
      contacts.remove(query.number);

    } else {
      String response = "not found";
      if (contacts.find(query.number) != null)
        response = contacts.find(query.number).getName();
      writeResponse(response);
    }
  }

  public void processQueries() {
    int queryCount = in.nextInt();
    for (int i = 0; i < queryCount; ++i)
      processQuery(readQuery());
  }

  static class HashTable<K, V> {

    private int cardinality = 10000000;
    private int a = 1;
    private int b = 0;
    private int prime = 10000003;
    private List<TableEntry<K, V>>[] table = new LinkedList[cardinality];

    public int getHashValue(int number) {
      return (a * number + b) % prime;
    }

    public V find(K key) {
      int hashValur = getHashValue((Integer) key) % cardinality;
      List<TableEntry<K, V>> foundList = table[hashValur];
      if (foundList == null || foundList.isEmpty())
        return null;
      for (TableEntry<K, V> entry : foundList) {
        if (entry.getKey().equals(key))
          return entry.getValue();
      }
      return null;
    }

    public void put(K key, V value) {
      int hashValur = getHashValue((Integer) key) % cardinality;
      List<TableEntry<K, V>> foundList = table[hashValur];
      if (foundList == null)
        foundList = new LinkedList<>();
      for (TableEntry<K, V> entry : foundList) {
        if (entry.getKey().equals(key)) {
          entry.setValue(value);
          return;
        }
      }
      foundList.add(new TableEntry<K, V>(key, value));
      table[hashValur]=foundList;
    }

    public void remove(K key) {
      int hashValur = getHashValue((Integer) key) % cardinality;
      List<TableEntry<K, V>> foundList = table[hashValur];
      if (foundList == null || foundList.isEmpty())
        return;
      for (TableEntry<K, V> entry : foundList) {
        if (entry.getKey().equals(key)) {
          foundList.remove(entry);
          break;
        }
      }
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

  static class Contact {
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getNumber() {
      return number;
    }

    public void setNumber(int number) {
      this.number = number;
    }

    String name;
    int number;

    public Contact(String name, int number) {
      this.name = name;
      this.number = number;
    }
  }

  static class Query {
    String type;
    String name;
    int number;

    public Query(String type, String name, int number) {
      this.type = type;
      this.name = name;
      this.number = number;
    }

    public Query(String type, int number) {
      this.type = type;
      this.number = number;
    }
  }

  class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    FastScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
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
