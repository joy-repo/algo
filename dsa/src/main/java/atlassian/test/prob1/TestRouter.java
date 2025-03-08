package atlassian.test.prob1;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRouter {

    private RouterTrie router;


    @Before
    public void setUp(){
        router = new RouterTrie();
        router.registerRoute("/foo/bar/as", "res1");
        router.registerRoute("/hello/world", "res2");
        router.registerRoute("/hel6767/world", "res2");
        router.registerRoute("/foo/bar/as", "res3");

        router.registerRoute("/foo/*/as", "trieRes1");
    }

    @Test
    public void test$Success(){
        String res =router.callRoute("/foo/bar/as");
        Assert.assertTrue(res.equals("res3"));
        res = router.callRoute("/hel6767/world");
        Assert.assertTrue(res.equals("res2"));
       // res =router.callRoute("/h/world"));
        res = router.callRoute("/hel6767/world");
        Assert.assertTrue(res.equals("res2"));
        //System.out.println(router.callRoute(""));
        res = router.callRoute("/foo/rt/as");
        Assert.assertTrue(res.equals("trieRes1"));

    }

    @Test(expected=PathNotFoundException.class)
    public void test$Failures() {
        router.callRoute("/h/world");
    }
}
