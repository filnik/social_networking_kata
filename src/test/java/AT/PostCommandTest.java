package AT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandTest extends CommandBaseTest {
    /*
    Posting: Alice can publish messages to a personal timeline

    > Alice -> I love the weather today
    > Bob -> Damn! We lost!
    > Bob -> Good game though.
     */

    @Test
    public void verifiesPostingCommandExecutesCorrectly() throws Exception {
        input.post("Alice -> I love the weather today");
        input.post("Bob -> Damn! We lost!");
        input.post("Bob -> Good game though.");

        assertThat(input.strings, hasSize(3));
        assertEquals(input.nextLine(), "Alice -> I love the weather today");
        assertEquals(input.nextLine(), "Bob -> Damn! We lost!");
        assertEquals(input.nextLine(), "Bob -> Good game though.");
        assertThat(input.strings, hasSize(0));
        flow.start();
        verify(output, never()).out("");
    }

}