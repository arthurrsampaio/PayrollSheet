package usecase;

import model.Sale;
import model.employee.CommissionedEmployee;
import model.employee.Employee;

import java.util.Date;

public final class SaleUseCase {

    private final EmployeeUseCase employeeUseCase;

    public SaleUseCase(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    public void submit(int employeeId, Date date, double price) throws IllegalArgumentException {
        Sale s = new Sale(date, price);
        if (!s.validate()) {
            throw new IllegalArgumentException("Invalid param(s)");
        }

        Employee e = employeeUseCase.getById(employeeId);
        if (e.getType() != Employee.Type.COMMISSIONED) {
            throw new IllegalArgumentException("Employee must be of type COMMISSIONED");
        }

        ((CommissionedEmployee) e).addSale(s);
    }

}
