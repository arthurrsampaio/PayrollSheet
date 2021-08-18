package usecase;

import model.ServiceFee;
import model.employee.Employee;

public final class ServiceFeeUseCase {

    private final EmployeeUseCase employeeUseCase;

    public ServiceFeeUseCase(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    public void submitServiceFee(
        int employeeId,
        String name,
        double fee
    ) throws IllegalArgumentException, IllegalStateException {
        ServiceFee f = new ServiceFee(name, fee);
        if (!f.validate()) {
            throw new IllegalArgumentException("Invalid param(s)");
        }

        Employee e = employeeUseCase.getById(employeeId);
        e.addServiceFee(f);
    }

}
