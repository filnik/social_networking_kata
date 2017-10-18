package unit;

import model.Clock;
import model.Message;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest {
    private Message message;
    @Mock
    User user;
    @Mock
    Clock clock;
    public static final String MESSAGE_STRING = "Filippo -> ciao";

    @Before
    public void setUp() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 30));
        when(user.getUsername()).thenReturn("Sara");
        message = new Message(user, MESSAGE_STRING, clock);
    }

    @Test
    public void twoMinutesAgo() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 06, 30));
        assertEquals("Filippo -> ciao (2 minutes ago)", message.toString());
    }

    @Test
    public void twoSecondsAgo() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 32));
        assertEquals("Filippo -> ciao (2 seconds ago)", message.toString());
    }

    @Test
    public void oneMinuteAgo() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 05, 30));
        assertEquals("Filippo -> ciao (1 minute ago)", message.toString());
    }

    @Test
    public void oneSecondAgo() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
        assertEquals("Filippo -> ciao (1 second ago)", message.toString());
    }

    @Test
    public void wallString() throws Exception {
        when(clock.now()).thenReturn(LocalDateTime.of(2017, 01, 20, 15, 04, 31));
        assertEquals("Sara - Filippo -> ciao (1 second ago)", message.toWallString());
    }
}