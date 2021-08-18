package console;

import console.common.BaseConsole;
import model.payment.Payment;
import usecase.PayrollUseCase;

import java.util.Date;
import java.util.List;

public class RunPayrollConsole extends BaseConsole {

    private final PayrollUseCase payrollUseCase;

    public RunPayrollConsole(PayrollUseCase payrollUseCase) {
        this.payrollUseCase = payrollUseCase;
    }

    @Override
    public void start() {
        println("----- Running Payroll to Today -----");
        breakLine();

        Date today = new Date();
        List<Payment> payments = payrollUseCase.calculatePayments(today);

        for (Payment p : payments) {
            println(p.getEmployee().toString());
            println(String.format("- Earnings: $ %.2f", p.getEarnings()));
            println(String.format("- Discounts: $ %.2f", p.getDiscounts()));
            println(String.format("- Total: $ %.2f", p.getTotal()));
            breakLine();
        }
        holdOutput();
        breakLine();
    }

}
