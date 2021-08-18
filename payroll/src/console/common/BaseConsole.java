package console.common;

import java.util.Scanner;

public abstract class BaseConsole {

    protected Scanner scanner;

    public BaseConsole() {
        this.scanner = new Scanner(System.in);
    }

    public abstract void start();

    protected void print(String string) {
        System.out.print(string);
    }

    protected void println(String string) {
        System.out.println(string);
    }

    protected void breakLine() {
        println("");
    }

    protected void holdOutput() {
        print("\nPress enter to continue...");
        scanner.nextLine();
        breakLine();
    }

}
