package unit;

import command.FollowCommand;
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
public class FollowCommandTest {

    @Mock private Output output;
    @Mock private Clock clock;
    private FollowCommand command;
    private User alice;
    private User bob;

    @Before
    public void setUp() throws Exception {
        UserRepository userRepository = new UserRepository();
        alice = Mockito.spy(new User("Alice", clock));
        bob = Mockito.spy(new User("Bob", clock));
        userRepository.save(alice);
        userRepository.save(bob);
        command = new FollowCommand(output, userRepository, clock);
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
    }

    @Test
    public void testCheckCondition() throws Exception {
        assertTrue(command.checkCondition("Alice follows Bob"));
        assertFalse(command.checkCondition("Luca follows Alice"));
    }

    @Test
    public void testFollowLink() throws Exception {
        command.execute("Alice follows Bob");
        Mockito.verifyNoMoreInteractions(output);
        assertTrue(alice.getFollowees().contains(bob));
    }
}