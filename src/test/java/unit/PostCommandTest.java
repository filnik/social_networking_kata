package unit;

import command.PostCommand;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandTest {

    @Mock private Output output;
    @Mock private Clock clock;
    private UserRepository userRepository = Mockito.spy(new UserRepository());
    private PostCommand command;

    @Before
    public void setUp() throws Exception {
        command = new PostCommand(output, userRepository, clock);
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
    }

    @Test
    public void testCheckCondition() throws Exception {
        assertFalse(command.checkCondition("Alice follows Bob"));
        assertTrue(command.checkCondition("Alice -> ciao mamma"));
        assertFalse(command.checkCondition("Alice Mammut -> ciao mamma"));
    }

    @Test
    public void testPostCompleted() throws Exception {
        command.execute("Alice -> first message");
        userRepository.exists("Alice");
        verify(userRepository, times(1)).save(new User("Alice", clock));
        assertTrue(userRepository.exists("Alice"));
        assertEquals("first message (0 seconds ago)", userRepository.load("Alice").getMessages().get(0).toString());
        Mockito.verifyNoMoreInteractions(output);

        command.execute("Alice -> second message");
        verify(userRepository, times(2)).load("Alice");
        assertTrue(userRepository.exists("Alice"));
        assertEquals("second message (0 seconds ago)", userRepository.load("Alice").getMessages().get(0).toString());
        assertEquals("first message (0 seconds ago)", userRepository.load("Alice").getMessages().get(1).toString());
        Mockito.verifyNoMoreInteractions(output);
    }
}