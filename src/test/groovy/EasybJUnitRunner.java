import org.easyb.BehaviorRunner;
import org.junit.Test;

public class EasybJUnitRunner {

    @Test
    public void run() throws Exception {
        BehaviorRunner.main(getStories());
    }

    private String[] getStories() {
        final String path = "src/test/groovy/";
        final String[] stories = new String[] { "Stack", "ScriptBlip" };

        for (int i = 0; i < stories.length; i++) {
            stories[i] = path + stories[i] + ".story";
        }
        return stories;
    }
}
