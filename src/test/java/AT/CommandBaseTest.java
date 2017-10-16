package AT;

import command.CommandFactory;
import common.FakeInput;
import model.Clock;
import org.junit.Before;
import org.mockito.Mock;
import service.Flow;
import service.Output;

import java.time.LocalDateTime;

public class CommandBaseTest {
    @Mock
    protected Output outputStream;
    protected Flow flow;
    protected FakeInput inputStream;
    protected FakeClock clock;

    @Before
    public void setUp() throws Exception {
        inputStream = new FakeInput();
        clock = new FakeClock();
        flow = new Flow(inputStream, new CommandFactory(clock, outputStream));

        clock.minutesDelay(5);
        inputStream.post("Alice -> I love the weather today");
        flow.start();
        clock.minutesDelay(2);
        inputStream.post("Bob -> Damn! We lost!");
        flow.start();
        clock.minutesDelay(1);
        inputStream.post("Bob -> Good game though.");
        flow.start();
        clock.minutesDelay(0);
    }

    protected class FakeClock  extends Clock {
        private int minutes = 0;
        private int seconds = 0;
        private LocalDateTime now = LocalDateTime.now();

        public FakeClock() {
            now = now.minusSeconds(now.getSecond());
        }

        @Override
        public LocalDateTime now() {
            return now.minusMinutes(minutes).minusSeconds(seconds);
        }

        public void minutesDelay(int delay){
            minutes = delay;
        }

        public void secondsDelay(int delay){
            seconds = delay;
        }
    }
}