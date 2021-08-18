package console.employee;

import console.common.BaseConsole;
import model.employee.Employee;
import usecase.EmployeeUseCase;

public class RemoveEmployeeConsole extends BaseConsole {

    private final EmployeeUseCase employeeUseCase;

    public RemoveEmployeeConsole(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void start() {
        println("---- Remove Employee -----");
        breakLine();

        try {
            print("-> Type employee ID: ");
            int employeeId = Integer.parseInt(scanner.nextLine());

            Employee e = employeeUseCase.getById(employeeId);
            employeeUseCase.remove(e);

            breakLine();
            println("[INFO] " + e + " removed");
        } catch (Exception e) {
            breakLine();
            println("[ERR] Unable to remove employee: %s" + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
