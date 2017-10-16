package unit;

import command.WallCommand;
import model.Clock;
import model.User;
import model.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.Output;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallCommandTest {

    @Mock private Output output;
    @Mock private UserRepository userRepository;
    @Mock private Clock clock;
    private WallCommand command;

    @Before
    public void setUp() throws Exception {
        command = new WallCommand(output, userRepository, clock);
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
    }

    @Test
    public void testCheckCondition() throws Exception {
        when(userRepository.exists("Alice")).thenReturn(true);
        assertTrue(command.checkCondition("Alice wall"));
        when(userRepository.exists("Alice")).thenReturn(false);
        assertFalse(command.checkCondition("Alice wall"));
    }

    @Test
    public void testExecuteNoMessages() throws Exception {
        when(userRepository.exists("Alice")).thenReturn(true);
        when(userRepository.load("Alice")).thenReturn(new User("Alice", clock));
        command.execute("Alice wall");
        Mockito.verifyNoMoreInteractions(output);
    }

    @Test
    public void testExecuteOnlyMyMessages() throws Exception {
        when(userRepository.exists("Alice")).thenReturn(true);
        final User alice = new User("Alice", clock);
        alice.addMessage("one");
        alice.addMessage("two");
        when(userRepository.load("Alice")).thenReturn(alice);
        command.execute("Alice wall");
        verify(output).out("Alice - two (0 seconds ago)");
        verify(output).out("Alice - one (0 seconds ago)");
    }

    @Test
    public void testExecuteMyMessagesAndMyFollowers() throws Exception {
        when(userRepository.exists("Alice")).thenReturn(true);
        when(userRepository.exists("Bob")).thenReturn(true);
        final User alice = new User("Alice", clock);
        alice.addMessage("one");
        alice.addMessage("two");
        final User bob = new User("Bob", clock);
        bob.addMessage("three");
        bob.addMessage("four");
        alice.follows(bob);
        when(userRepository.load("Alice")).thenReturn(alice);
        when(userRepository.load("Bob")).thenReturn(bob);
        command.execute("Alice wall");
        verify(output).out("Alice - two (0 seconds ago)");
        verify(output).out("Alice - one (0 seconds ago)");
        verify(output).out("Bob - three (0 seconds ago)");
        verify(output).out("Bob - four (0 seconds ago)");
    }
}