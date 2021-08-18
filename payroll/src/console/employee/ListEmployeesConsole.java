package console.employee;

import console.common.BaseConsole;
import model.employee.Employee;
import usecase.EmployeeUseCase;

public final class ListEmployeesConsole extends BaseConsole {

    private final EmployeeUseCase employeeUseCase;

    public ListEmployeesConsole(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void start() {
        println("----- Listing Employees -----");
        breakLine();

        for (Employee e : employeeUseCase.getAll()) {
            println(e.toString());
        }
        holdOutput();
        breakLine();
    }

}
