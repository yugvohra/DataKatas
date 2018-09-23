import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by vohray on 9/17/18.
 */
public class BracketChecker {

  static class Bracket {
    Bracket(char type, int position) {
      this.type = type;
      this.position = position;
    }

    private static HashMap<Character, Character> bracketsMap = new HashMap<>();

    static {
      bracketsMap.put('[', ']');
      bracketsMap.put('{', '}');
      bracketsMap.put('(', ')');
    }

    boolean isAMatch(char c) {
      return bracketsMap.get(this.type) == c;
    }

    char type;
    int position;
  }

  public static void main(String[] args) throws IOException {
    InputStreamReader inputStream = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(inputStream);
    String text = reader.readLine();
    boolean areBracketsMatching = true;
    LinkedList<Bracket> bracketStack = new LinkedList<>();
    for (int position = 0; position < text.length(); ++position) {
      char currentChar = text.charAt(position);

      if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
        bracketStack.push(new Bracket(currentChar, position));
      } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
        if (bracketStack.isEmpty() || !bracketStack.pop().isAMatch(currentChar)) {
          areBracketsMatching = false;
          bracketStack.push(new Bracket(currentChar, position));
          break;
        }
      }
    }
    String message = areBracketsMatching && bracketStack.isEmpty() ? "Success" : "" + (bracketStack.pop().position + 1);
    System.out.println(message);

  }
}

