import java.util.*;

public class CycleChecker {
  private static int checkCycle(ArrayList<Integer>[] adj) {
    Set<Integer> visitedNodes = new HashSet<>();
    Set<Integer> recStack = new HashSet<>();
    for (int node = 0; node < adj.length; node++) {
      if (isCyclePresnt(node, visitedNodes, recStack, adj))
        return 1;
    }
    return 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < m; i++) {
      int x, y;
      x = scanner.nextInt();
      y = scanner.nextInt();
      adj[x - 1].add(y - 1);
    }
    System.out.println(checkCycle(adj));
  }

  private static boolean isCyclePresnt(int node, Set<Integer> visitedNodes, Set<Integer> recStack, ArrayList<Integer>[] adj) {
    if (!visitedNodes.contains(node)) {
      visitedNodes.add(node);
      recStack.add(node);
      List<Integer> adjacentNodes = adj[node];

      for (Integer adjNode : adjacentNodes) {
        if (!visitedNodes.contains(adjNode)) {
          if (isCyclePresnt(adjNode, visitedNodes, recStack, adj))
            return true;
        }
        else if (recStack.contains(adjNode))
          return true;
      }
    }
    recStack.remove(node);
    return false;
  }
}