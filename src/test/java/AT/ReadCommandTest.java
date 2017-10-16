package AT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
        input.post("Alice");
        flow.start();
        verify(output).out("I love the weather today (5 minutes ago)");
        input.post("Bob");
        flow.start();
        verify(output).out("Good game though. (1 minute ago)");
        verify(output).out("Damn! We lost! (2 minutes ago)");
    }
}