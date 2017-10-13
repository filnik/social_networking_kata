import model.Clock;
import service.MemoryFlow;
import service.SystemInput;

public class Main {
    public static void main(String[] args) {
        new MemoryFlow(new Clock(), new SystemInput(), System.out::println).start();
    }

}
