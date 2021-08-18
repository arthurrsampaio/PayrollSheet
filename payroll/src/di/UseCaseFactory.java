package di;

import data.EmployeeRepository;
import data.PaymentScheduleRepository;
import usecase.*;

public class UseCaseFactory {

    private final EmployeeUseCase employeeUseCase;
    private final PaymentScheduleUseCase paymentScheduleUseCase;
    private final ServiceFeeUseCase serviceFeeUseCase;
    private final TimecardUseCase timecardUseCase;
    private final SaleUseCase saleUseCase;
    private final PayrollUseCase payrollUseCase;

    public UseCaseFactory() {
        this.employeeUseCase = new EmployeeUseCase(new EmployeeRepository());
        this.paymentScheduleUseCase = new PaymentScheduleUseCase(new PaymentScheduleRepository(), this.employeeUseCase);
        this.serviceFeeUseCase = new ServiceFeeUseCase(this.employeeUseCase);
        this.timecardUseCase = new TimecardUseCase(this.employeeUseCase);
        this.saleUseCase = new SaleUseCase(this.employeeUseCase);
        this.payrollUseCase = new PayrollUseCase(this.employeeUseCase);
    }

    public EmployeeUseCase getEmployeeUseCase() {
        return employeeUseCase;
    }

    public PaymentScheduleUseCase getPaymentScheduleUseCase() {
        return paymentScheduleUseCase;
    }

    public ServiceFeeUseCase getServiceFeeUseCase() {
        return serviceFeeUseCase;
    }

    public TimecardUseCase getTimecardUseCase() {
        return timecardUseCase;
    }

    public SaleUseCase getSaleUseCase() {
        return saleUseCase;
    }

    public PayrollUseCase getPayrollUseCase() {
        return payrollUseCase;
    }

}
