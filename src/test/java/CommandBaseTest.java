import model.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.Input;
import service.MemoryFlow;
import service.Output;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class CommandBaseTest {
    @Mock
    protected Output outputStream;
    protected MemoryFlow flow;
    protected Input inputStream;
    protected FakeClock clock;

    @Before
    public void setUp() throws Exception {
        inputStream = new FakeInputStream();
        clock = new FakeClock();
        flow = new MemoryFlow(clock, inputStream, outputStream);

        clock.setDiff(5);
        inputStream.post("Alice -> I love the weather today");
        flow.start();
        clock.setDiff(2);
        inputStream.post("Bob -> Damn! We lost!");
        flow.start();
        clock.setDiff(1);
        inputStream.post("Bob -> Good game though.");
        flow.start();
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
        public String next() {
            String result = strings.get(0);
            strings.remove(0);
            return result;
        }
    }

    protected class FakeClock  extends Clock {
        int diff = 0;

        @Override
        public LocalDateTime now() {
            return LocalDateTime.now().plusMinutes(diff+1); // avoid rounding problems
        }

        public void setDiff(int newDiff){
            diff = newDiff;
        }
    }
}