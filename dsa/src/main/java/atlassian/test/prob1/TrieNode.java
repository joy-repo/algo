package atlassian.test.prob1;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Map<String, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
    String handler = null;


}
