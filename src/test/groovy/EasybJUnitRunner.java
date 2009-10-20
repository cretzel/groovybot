import org.easyb.BehaviorRunner;
import org.junit.Test;



public class EasybJUnitRunner {

    @Test
    public void run() throws Exception {
        BehaviorRunner.main(new String[]{"src/test/groovy/Stack.story"});
    }
}
