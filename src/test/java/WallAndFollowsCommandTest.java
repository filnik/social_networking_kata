import model.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import service.MemoryFlow;

import java.time.LocalDateTime;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WallAndFollowsCommandTest extends CommandBaseTest{
    /*
    posting: <user name> -> <message>
    reading: <user name>
    following: <user name> follows <another user>
    wall: <user name> wall

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
        verify(outputStream, never()).out("");
        inputStream.post("Charlie follows Alice");
        flow.start();
        verify(outputStream, never()).out("");
        inputStream.post("Charlie wall");
        flow.start();
        verify(outputStream).out("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\n" +
                                         "Alice - I love the weather today (5 minutes ago)");
        inputStream.post("Charlie follows Bob");
        verify(outputStream, never()).out("");
        inputStream.post("Charlie wall");
        verify(outputStream).out("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)\n" +
                                        "Bob - Good game though. (1 minute ago)\n" +
                                        "Bob - Damn! We lost! (2 minutes ago)\n" +
                                        "Alice - I love the weather today (5 minutes ago)");
    }
}