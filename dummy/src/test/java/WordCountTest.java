import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StringMap {

    @Test
    public void wordCount() {
        WordCountHelper wcHelper = new WordCountHelper();
        Assert.assertEquals(wcHelper.getWordCount("hello world"), 2);
        Assert.assertEquals(wcHelper.getWordCount("  leading and trailing spaces  "),
    }

}
