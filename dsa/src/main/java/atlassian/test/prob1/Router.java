package atlassian.test.prob1;

import com.test.PathNotFoundException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

// register the route
// registerRoute(path:String, Result:String)
//registerRoute("/foo/bar/as", "res1");
//
// callRoute("/foo/bar/as") --> res1
//

//Wildcard
//callRoute("/foo/*/as") --> res1
//registerRoute("/foo/*/as", "res1");

//registerRoute("/foo/ghghh/as", "res1");

// callRoute("/foo/bar/as") --> res1
//callRoute("/foo/gh/as") --> res1

//

///   "/foo/*/as"

/// foo , * , as
//
// Node {
// Map<String, Node> children
//
//
public class Router {

    private Map<String,String> pathMap ;

    public Router(){
        pathMap = new HashMap<>();
    }

    @Test
    public void testRouter(){




    }

    public static void main(String[] args) {
        try {
            Router router = new Router();

            router.registerRoute("/foo/bar/as", "res1");
            router.registerRoute("/hello/world", "res2");
            router.registerRoute("/hel6767/world", "res2");
            router.registerRoute("/foo/bar/as", "res3");

            System.out.println(router.callRoute("/foo/bar/as"));
            System.out.println(router.callRoute("/hel6767/world"));
            System.out.println(router.callRoute("/h/world"));
            System.out.println(router.callRoute("/hel6767/world"));
            System.out.println(router.callRoute(""));
        } catch (PathNotFoundException e ){
            System.out.println(e.getMessage());
        }



    }

    public void registerRoute( String path, String handler) {
        pathMap.put(path, handler);
    }

    public String callRoute( String path) {

        if( !path.isBlank() && pathMap.containsKey(path)){
            return pathMap.get(path);
        }
        throw new PathNotFoundException(path + "Not Found");
    }


}
