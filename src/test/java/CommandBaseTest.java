import model.Clock;
import org.junit.Before;
import org.mockito.Mock;
import service.Flow;
import service.Input;
import service.MemoryFlow;
import service.Output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class CommandBaseTest {
    @Mock
    protected Output outputStream;
    protected Flow flow;
    protected FakeInputStream inputStream;
    protected FakeClock clock;

    @Before
    public void setUp() throws Exception {
        inputStream = new FakeInputStream();
        clock = new FakeClock();
        flow = new MemoryFlow(clock, inputStream, outputStream);

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

    protected class FakeInputStream implements Input {

        ArrayList<String> strings = new ArrayList<>();

        public void post(String message){
            strings.add(message);
        }

        @Override
        public boolean hasNext() {
            return !strings.isEmpty();
        }

        @Override
        public String nextLine() {
            String result = strings.get(0);
            strings.remove(0);
            return result;
        }
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