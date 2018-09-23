
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by vohray on 9/17/18.
 */
public class BinaryTree {

  private static class Node {
    int key;
    List<Node> children = new ArrayList<>();
    Node parent;
    int height;

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    public int getKey() {
      return key;
    }

    public void setKey(int key) {
      this.key = key;
    }

    public List<Node> getChildren() {
      return children;
    }

    public void setChildren(List<Node> children) {
      this.children = children;
    }
  }

  private static Node formTreeFromParentsIndex(int[] parentIndexes) {
    Node[] nodes = new Node[parentIndexes.length];
    for (int count = 0; count < nodes.length; count++) {
      nodes[count] = new Node();
      nodes[count].setKey(count);
      nodes[count].setHeight(0);
    }
    int rootIndex = -1;
    for (int count = 0; count < nodes.length; count++) {
      int parentIndex = parentIndexes[count];
      if (parentIndex == -1) {
        rootIndex = count;
        continue;
      }

      Node parent = nodes[parentIndex];
      Node child=nodes[count];
      child.setParent(parent);
      parent.getChildren().add(child);
      while(parent!=null&&parent.height<=child.height)
      {
        parent.setHeight(child.getHeight()+1);
        child=parent;
        parent=parent.getParent();
      }
    }
    return nodes[rootIndex];
  }

  private static long findMaxDepth(Node root) {
    return root.getHeight()+1;
   /* LinkedList<Node> tree = new LinkedList<>();
    tree.push(root);
    Set<Node> visitedNodes = new HashSet<>();
    visitedNodes.add(root);
    long maxHeight = 1l;
    long currentHeight = 1l;
    while (!tree.isEmpty()) {
      Node currentNode = tree.pop();
      List<Node> children = currentNode.getChildren();
      if (!children.isEmpty()) {
        currentHeight++;
        maxHeight = maxHeight < currentHeight ? currentHeight : maxHeight;
        children.stream().forEach(child -> {
          tree.addLast(child);
        });
      }
    }
    return maxHeight;*/
  }


  static class FastScanner {
    StringTokenizer tok = new StringTokenizer("");
    BufferedReader in;

    FastScanner() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
      while (!tok.hasMoreElements())
        tok = new StringTokenizer(in.readLine());
      return tok.nextToken();
    }

    int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
  }

  public static void main(String[] args) throws IOException {
    int numberOfNodes;
    int[] parentIndexes;
    FastScanner inputScanner = new FastScanner();
    numberOfNodes = inputScanner.nextInt();
    parentIndexes = new int[numberOfNodes];
    for (int i = 0; i < numberOfNodes; i++) {
      parentIndexes[i] = inputScanner.nextInt();
    }
    System.out.println(findMaxDepth(formTreeFromParentsIndex(parentIndexes)));
  }

}

