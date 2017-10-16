import command.CommandFactory;
import model.Clock;
import service.Flow;
import service.SystemInput;

public class Main {
    public static void main(String[] args) {
        new Flow(new SystemInput(), new CommandFactory(new Clock(), System.out::println)).start();
    }
}