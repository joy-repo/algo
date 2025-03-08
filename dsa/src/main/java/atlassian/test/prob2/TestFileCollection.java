package atlassian.test.prob2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFileCollection {

    private FileCollection fileCollection;
    private List<FileCollection.FileEntry> list ;

    @Before
    public void setUp(){

        fileCollection = new FileCollection();

        list = new ArrayList<>();

        list.add(new FileCollection.FileEntry("file1.txt", 100, null));
        list.add(new FileCollection.FileEntry("file2.txt", 200, List.of("collection1","collection2" )));
        list.add(new FileCollection.FileEntry("file3.txt", 200, List.of("collection2")));
        list.add(new FileCollection.FileEntry("file4.txt", 300, List.of("collection3")));
        list.add(new FileCollection.FileEntry("file5.txt", 10, null));

    }

    @Test
    public void test$Success(){

        int sizes = fileCollection.totalSizeAllFiles(list);
        List<String> collectionList = fileCollection.listOfTopNCollections( 2, list);
        System.out.println(collectionList);

        Assert.assertEquals(sizes,810);
        Assert.assertEquals(collectionList, List.of("collection2","collection3" ));
    }


}
