import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowsCommandTest extends CommandBaseTest{
    /*
    ----

    Following: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

    > Charlie -> I'm in New York today! Anyone wants to have a coffee?
    > Charlie follows Alice
    > Charlie wall
    > Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
    > Alice - I love the weather today (5 minutes ago)
    > Charlie follows Bob
    > Charlie wall
    > Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
    > Bob - Good game though. (1 minute ago)
    > Bob - Damn! We lost! (2 minutes ago)
    > Alice - I love the weather today (5 minutes ago)
    */

    @Test
    public void verifiesFollowingCommandExecutesCorrectly() throws Exception {
        inputStream.post("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        flow.start();
        clock.secondsDelay(0);
        verify(outputStream, never()).out("");
        inputStream.post("Charlie follows Alice");
        flow.start();
        verify(outputStream, never()).out("");

    }
}