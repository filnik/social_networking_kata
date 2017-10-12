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
public class PostCommandTest extends CommandBaseTest {

    /*
    Posting: Alice can publish messages to a personal timeline

    > Alice -> I love the weather today
    > Bob -> Damn! We lost!
    > Bob -> Good game though.
     */

    @Test
    public void verifiesPostingCommandExecutesCorrectly() throws Exception {
        inputStream.post("Alice -> I love the weather today");
        inputStream.post("Bob -> Damn! We lost!");
        inputStream.post("Bob -> Good game though.");

        assertThat(((FakeInputStream) inputStream).strings, hasSize(3));
        assertEquals(inputStream.next(), "Alice -> I love the weather today");
        assertEquals(inputStream.next(), "Bob -> Damn! We lost!");
        assertEquals(inputStream.next(), "Bob -> Good game though.");
        assertThat(((FakeInputStream) inputStream).strings, hasSize(0));
        flow.start();
        verify(outputStream, never()).out("");
    }

}