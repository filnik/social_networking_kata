package service;

import java.util.Scanner;

public class SystemInput implements Input {
    private final Scanner scanner;

    public SystemInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

}