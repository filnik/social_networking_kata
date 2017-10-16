package unit;

import command.ReadCommand;
import model.Clock;
import model.User;
import model.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.Output;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandTest {

    @Mock private Output output;
    @Mock private Clock clock;
    @Mock private UserRepository repository;
    private ReadCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ReadCommand(output, repository, clock);
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
    }

    @Test
    public void testCheckCondition() throws Exception {
        when(repository.exists("Alice")).thenReturn(true);
        assertTrue(command.checkCondition("Alice"));
        when(repository.exists("Bob")).thenReturn(false);
        assertFalse(command.checkCondition("Bob"));
    }

    @Test
    public void testPrintsSingleMessageRead() throws Exception {
        User user = new User("Alice", clock);
        user.addMessage("ciao");
        when(repository.load("Alice")).thenReturn(user);
        command.execute("Alice");
        verify(output, times(1)).out("ciao (0 seconds ago)");
    }

    @Test
    public void testPrintsMultipleMessageRead() throws Exception {
        User user = new User("Alice", clock);
        user.addMessage("ciao");
        user.addMessage("mamma");
        when(repository.load("Alice")).thenReturn(user);
        command.execute("Alice");
        verify(output, times(1)).out("ciao (0 seconds ago)");
        verify(output, times(1)).out("mamma (0 seconds ago)");
    }
}