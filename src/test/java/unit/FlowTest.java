package unit;

import model.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import service.Flow;
import service.Input;
import service.Output;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlowTest {

    @Mock
    private Clock clock;
    @Mock
    private Input input;
    @Mock
    private Output output;
    private Flow flow;

    @Before
    public void setUp() throws Exception {
        flow = new Flow(clock, input, output);
    }

    @Test
    public void verifiesStartProcessInput() throws Exception {
        when(input.hasNextLine()).thenAnswer(new Answer<Boolean>() {
            int times = 0;

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (times < 2) {
                    times++;
                    return true;
                }
                return false;
            }
        });
        when(input.nextLine()).thenReturn("Filippo -> ciao");
        flow.start();
        verify(input, times(1)).nextLine();
    }

}