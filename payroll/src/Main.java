import di.UseCaseFactory;

public class Main {

    public static void main(String[] args) {
        UseCaseFactory useCaseFactory = new UseCaseFactory();
        ApplicationConsole applicationConsole = new ApplicationConsole(useCaseFactory);
        applicationConsole.start();
    }

}
