package atlassian;

import java.util.*;

class Router {
    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        boolean isEnd = false;
        String handler = null; // The function/result for this route
    }

    private final TrieNode root;

    public Router() {
        root = new TrieNode();
    }

    /** Add a route to the Trie */
    public void addRoute(String path, String handler) {
        String[] parts = path.split("/"); // Split into segments
        TrieNode current = root;

        for (String part : parts) {
            if (part.isEmpty()) continue; // Ignore leading "/"
            current.children.putIfAbsent(part, new TrieNode());
            current = current.children.get(part);
        }

        current.isEnd = true;
        current.handler = handler; // Store the function/handler
    }

    /** Call a route (Exact and wildcard matching) */
    public String callRoute(String path) {
        String[] parts = path.split("/");
        return searchTrie(root, parts, 0);
    }

    private String searchTrie(TrieNode node, String[] parts, int index) {
        if (node == null) return null;

        // If reached end of path
        if (index == parts.length) {
            return node.isEnd ? node.handler : null;
        }

        String segment = parts[index];

        // Exact match
        if (node.children.containsKey(segment)) {
            String exactMatch = searchTrie(node.children.get(segment), parts, index + 1);
            if (exactMatch != null) return exactMatch;
        }

        // Wildcard match
        if (node.children.containsKey("*")) {
            String wildcardMatch = searchTrie(node.children.get("*"), parts, index + 1);
            if (wildcardMatch != null) return wildcardMatch;
        }

        return null; // No match found
    }
    public static void main(String[] args) {
        Router router = new Router();
        router.addRoute("/foo", "foo");
        router.addRoute("/bar/*/baz", "bar-handler");

        System.out.println(router.callRoute("/foo")); // Output: foo
        System.out.println(router.callRoute("/bar/x/baz")); // Output: bar-handler
        System.out.println(router.callRoute("/bar/y/baz")); // Output: bar-handler
        System.out.println(router.callRoute("/bar/baz")); // Output: null
    }
}