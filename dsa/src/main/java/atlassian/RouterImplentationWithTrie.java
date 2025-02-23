package atlassian;


import java.util.HashMap;
import java.util.Map;

class RouterImplentationWithTrie {
    private class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        Runnable handler = null; // Using Runnable for handler representation
        String paramName = null; // To store the name of the parameter if this is a dynamic node
    }

    private final TrieNode root = new TrieNode();

    // Method to add a route
    public void addRoute(String path, Runnable handler) {
        String[] parts = path.split("/");
        TrieNode current = root;

        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (part.startsWith("{") && part.endsWith("}")) {
                // Dynamic segment
                part = "{}";
                current.paramName = part.substring(1, part.length() - 1);
            }
            current.children.putIfAbsent(part, new TrieNode());
            current = current.children.get(part);
        }
        current.handler = handler;
    }

    // Method to find and execute the handler for a given path
    public void route(String path) {
        String[] parts = path.split("/");
        TrieNode current = root;
        Map<String, String> params = new HashMap<>();

        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (current.children.containsKey(part)) {
                current = current.children.get(part);
            } else if (current.children.containsKey("{}")) {
                current = current.children.get("{}");
                params.put(current.paramName, part);
            } else {
                System.out.println("404 Not Found");
                return;
            }
        }

        if (current.handler != null) {
            // Execute the handler
            current.handler.run();
            // Print extracted parameters
            if (!params.isEmpty()) {
                System.out.println("Parameters: " + params);
            }
        } else {
            System.out.println("404 Not Found");
        }
    }

    public static void main(String[] args) {
        RouterImplentationWithTrie router = new RouterImplentationWithTrie();

        // Adding routes
        router.addRoute("/home/about", () -> System.out.println("Home About Page"));
        router.addRoute("/user/{id}/profile", () -> System.out.println("User Profile Page"));

        // Routing requests
        router.route("/home/about"); // Output: Home About Page
        router.route("/user/123/profile"); // Output: User Profile Page
        //         Parameters: {id=123}
        router.route("/user/123/settings"); // Output: 404 Not Found
    }
}