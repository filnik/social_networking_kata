package unit;

import model.Clock;
import model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import presenter.MessagePresenter;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MessagePresenterTest {
    private MessagePresenter messagePresenter;
    private static final String MESSAGE_STRING = "Filippo -> ciao";
    private LocalDateTime TIMESTAMP = LocalDateTime.of(2017, 01, 20, 15, 04, 30);

    @Before
    public void setUp() throws Exception {
        messagePresenter = new MessagePresenter("Sara", MESSAGE_STRING);
    }

    @Test
    public void twoMinutesAgo() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017, 01, 20, 15, 06, 30);
        assertEquals("Filippo -> ciao (2 minutes ago)", messagePresenter.showMessage(now, TIMESTAMP));
    }

    @Test
    public void twoSecondsAgo() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017, 01, 20, 15, 04, 32);
        assertEquals("Filippo -> ciao (2 seconds ago)", messagePresenter.showMessage(now, TIMESTAMP));
    }

    @Test
    public void oneMinuteAgo() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017, 01, 20, 15, 05, 30);
        assertEquals("Filippo -> ciao (1 minute ago)", messagePresenter.showMessage(now, TIMESTAMP));
    }

    @Test
    public void oneSecondAgo() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017, 01, 20, 15, 04, 31);
        assertEquals("Filippo -> ciao (1 second ago)", messagePresenter.showMessage(now, TIMESTAMP));
    }

    @Test
    public void wallString() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017, 01, 20, 15, 04, 31);
        assertEquals("Sara - Filippo -> ciao (1 second ago)", messagePresenter.showWallMessage(now, TIMESTAMP));
    }
}