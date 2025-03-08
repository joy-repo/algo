package atlassian.test.prob1;

import java.util.Objects;

public class RouterTrie {

    private TrieNode root;

    public RouterTrie(){
        root = new TrieNode();
    }

    public void registerRoute(String path, String handler){
        String[] parts = path.split("/");
        TrieNode current = root;

        for(String part : parts){

            current.children.putIfAbsent(part, new TrieNode());
            current = current.children.get(part);
        }

        current.isEnd=true;
        current.handler=handler;

    }

    public String callRoute(String path){
        String[] parts = path.split("/");
        String handler = searchInTrie(root, parts,0 );
        if(Objects.isNull(handler))
            throw new PathNotFoundException(path + "Not Found");
        return handler;
    }

    private String searchInTrie(TrieNode node, String[] parts, int index) {

        if(index== parts.length)
            if(node.isEnd){
                return node.handler;
            } else {
                return null;
            }

        String segment = parts[index];

        //Exact Match
        if(node.children.containsKey(segment)){
            String handler = searchInTrie(node.children.get(segment), parts, index+1);
            if(handler!=null) return handler;

        }
        //* matching
        if(node.children.containsKey("*")){
            String handler = searchInTrie(node.children.get("*"), parts, index+1);
            if(handler!=null) return handler;
        }

        return null;
    }


}
