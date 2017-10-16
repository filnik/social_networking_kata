package unit;

import command.Command;
import command.CommandFactory;
import common.FakeInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.Flow;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlowTest {
    private static final String COMMAND = "Filippo -> ciao";
    private Flow flow;
    private FakeInput input = Mockito.spy(new FakeInput());
    @Mock private CommandFactory commandFactory;
    @Mock private Command command;

    @Before
    public void setUp() throws Exception {
        flow = new Flow(input, commandFactory);
    }

    @Test
    public void verifiesSingleInput() throws Exception {
        input.post(COMMAND);
        when(commandFactory.getCommand(COMMAND)).thenReturn(command);
        flow.start();
        verify(input, times(1)).nextLine();
        verify(command, times(1)).execute(COMMAND);
    }

    @Test
    public void verifiesMultipleInput() throws Exception {
        input.post(COMMAND);
        input.post(COMMAND);
        input.post(COMMAND);
        when(commandFactory.getCommand(COMMAND)).thenReturn(command);
        flow.start();
        verify(input, times(3)).nextLine();
        verify(command, times(3)).execute(COMMAND);
    }
}