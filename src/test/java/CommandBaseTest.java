import model.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.Input;
import service.MemoryFlow;
import service.Output;

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

    @Before
    public void setUp() throws Exception {
        inputStream = new FakeInputStream();
        flow = new MemoryFlow(new Clock(), inputStream, outputStream);

        inputStream.post("Alice -> I love the weather today");
        inputStream.post("Bob -> Damn! We lost!");
        inputStream.post("Bob -> Good game though.");
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
}