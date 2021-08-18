package console.payment;

import console.common.BaseConsole;
import usecase.PaymentScheduleUseCase;

public class EditPaymentSchedule extends BaseConsole {

    private final PaymentScheduleUseCase paymentScheduleUseCase;

    public EditPaymentSchedule(PaymentScheduleUseCase paymentScheduleUseCase) {
        this.paymentScheduleUseCase = paymentScheduleUseCase;
    }

    @Override
    public void start() {
        println("----- Edit Payment Schedule -----");
        breakLine();

        try {
            print("-> Type payment schedule ID: ");
            int scheduleId = Integer.parseInt(scanner.nextLine());


            print("-> Type employee ID: ");
            int employeeId = Integer.parseInt(scanner.nextLine());

            paymentScheduleUseCase.setScheduleToEmployee(scheduleId, employeeId);

            breakLine();
            println("[INFO] Payment schedule updated");
        } catch (Exception e) {
            breakLine();
            println("[ERR] Unable to update payment schedule: " + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
