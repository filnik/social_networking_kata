import model.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import service.MemoryFlow;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandTest extends CommandBaseTest{

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
        verify(outputStream).out("I love the weather today (5 minutes ago)");
        inputStream.post("Bob");
        flow.start();
        verify(outputStream).out("Good game though. (1 minutes ago)");
        verify(outputStream).out("Damn! We lost! (2 minutes ago)");
    }
}