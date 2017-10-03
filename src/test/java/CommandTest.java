import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.Input;
import service.MemoryFlow;
import service.Output;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandTest {
    @Mock private Output outputStream;
    private MemoryFlow flow;
    private Input inputStream;

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

    * */

    @Before
    public void setUp() throws Exception {
        inputStream = new FakeInputStream();
        flow = new MemoryFlow(inputStream, outputStream);

        inputStream.post("Alice -> I love the weather today");
        inputStream.post("Bob -> Damn! We lost!");
        inputStream.post("Bob -> Good game though.");
    }

    /*
    Posting: Alice can publish messages to a personal timeline

    > Alice -> I love the weather today
    > Bob -> Damn! We lost!
    > Bob -> Good game though.
     */

    @Test
    public void verifiesPostingCommandExecutesCorrectly() throws Exception {
        assertThat(((FakeInputStream) inputStream).strings, hasSize(3));
        assertEquals(inputStream.next(), "Alice -> I love the weather today");
        assertEquals(inputStream.next(), "Bob -> Damn! We lost!");
        assertEquals(inputStream.next(), "Bob -> Good game though.");
        assertThat(((FakeInputStream) inputStream).strings, hasSize(0));
        flow.start();
        verify(outputStream, never()).out("");
    }

    /*
    Reading: Bob can view Alice’s timeline

    > Alice
    > I love the weather today (5 minutes ago)
    > Bob
    > Good game though. (1 minute ago)
    > Damn! We lost! (2 minutes ago)
     */

    @Test
    public void verifiesreadingCommandExecutesCorrectly() throws Exception {
        inputStream.post("Alice");
        flow.start();
        verify(outputStream).out("I love the weather today (0 minutes ago)");
        inputStream.post("Bob");
        flow.start();
        verify(outputStream).out("Good game though. (0 minutes ago)");
        verify(outputStream).out("Damn! We lost! (0 minutes ago)");
    }

    @Test
    public void verifiesfollowingCommandExecutesCorrectly() throws Exception {

    }

    @Test
    public void verifiesWallCommandExecutesCorrectly() throws Exception {

    }

    private class FakeInputStream implements Input {

        ArrayList<String> strings = new ArrayList<>();

        public void post(String message){
            strings.add(message);
        }

        @Override
        public boolean hasNext() {
            return !strings.isEmpty();
        }

        @Override
        public String next() {
            String result = strings.get(0);
            strings.remove(0);
            return result;
        }
    }
}