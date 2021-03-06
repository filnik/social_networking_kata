package AT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandTest extends CommandBaseTest{
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
        clock.secondsDelay(2);
        input.post("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        flow.start();
        clock.secondsDelay(0);
        input.post("Charlie follows Alice");
        input.post("Charlie wall");
        flow.start();
        verify(output).out("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
        verify(output).out("Alice - I love the weather today (5 minutes ago)");
    }

    @Test
    public void verifiesDoubleFollowingCommandExecutesCorrectly() throws Exception {
        clock.secondsDelay(15);
        input.post("Charlie -> I'm in New York today! Anyone wants to have a coffee?");
        flow.start();
        clock.secondsDelay(0);
        input.post("Charlie follows Alice");
        input.post("Charlie follows Bob");
        flow.start();
        verify(output, never()).out("");
        input.post("Charlie wall");
        flow.start();
        verify(output).out("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)");
        verify(output).out("Bob - Good game though. (1 minute ago)");
        verify(output).out("Bob - Damn! We lost! (2 minutes ago)");
        verify(output).out("Alice - I love the weather today (5 minutes ago)");
    }
}