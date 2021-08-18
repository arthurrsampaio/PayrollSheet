package console.payment;

import console.common.BaseConsole;
import model.payment.PaymentSchedule.Type;
import usecase.PaymentScheduleUseCase;

public class AddPaymentSchedule extends BaseConsole {

    private final PaymentScheduleUseCase paymentScheduleUseCase;

    public AddPaymentSchedule(PaymentScheduleUseCase paymentScheduleUseCase) {
        this.paymentScheduleUseCase = paymentScheduleUseCase;
    }

    @Override
    public void start() {
        println("----- Add Payment Schedule -----");

        breakLine();
        println("Types:");
        for (Type t : Type.values()) {
            println(t.code + " - " + t.name());
        }
        breakLine();

        try {
            print("-> Type payment schedule code: ");
            Type type = Type.fromCode(Integer.parseInt(scanner.nextLine()));

            if (type == Type.MONTHLY) {
                print("-> Type the day of month (1-31): ");
                int dayOfMonth = Integer.parseInt(scanner.nextLine());

                paymentScheduleUseCase.addMonthly(dayOfMonth);
            } else if (type == Type.WEEKLY) {
                print("-> Type the quantity of weeks: ");
                int weeks = Integer.parseInt(scanner.nextLine());

                print("-> Type the day of week (1-7): ");
                int dayOfWeek = Integer.parseInt(scanner.nextLine());

                paymentScheduleUseCase.addWeekly(weeks, dayOfWeek);
            }

            breakLine();
            println("[INFO] Payment schedule added");
        } catch (Exception e) {
            breakLine();
            println("[ERR] Unable to add payment schedule: " + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
