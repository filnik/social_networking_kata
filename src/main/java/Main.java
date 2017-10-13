import model.Clock;
import service.Flow;
import service.SystemInput;

public class Main {
    public static void main(String[] args) {
        new Flow(new Clock(), new SystemInput(), System.out::println).start();
    }
}