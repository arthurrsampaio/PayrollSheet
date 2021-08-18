package console;

import console.common.BaseConsole;
import usecase.ServiceFeeUseCase;

public class SubmitServiceFeeConsole extends BaseConsole {

    private final ServiceFeeUseCase serviceFeeUseCase;

    public SubmitServiceFeeConsole(ServiceFeeUseCase serviceFeeUseCase) {
        this.serviceFeeUseCase = serviceFeeUseCase;
    }

    @Override
    public void start() {
        println("----- Submit Service Fee -----");
        breakLine();

        try {
            print("-> Type employee ID: ");
            int employeeID = Integer.parseInt(scanner.nextLine());

            print("-> Type name: ");
            String name = scanner.nextLine();

            print("-> Type fee: ");
            double fee = Double.parseDouble(scanner.nextLine());

            serviceFeeUseCase.submitServiceFee(employeeID, name, fee);

            breakLine();
            println("[INFO] Service fee submitted");
        } catch (Exception e) {
            breakLine();
            println("[ERR] Unable to submit service fee: " + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
