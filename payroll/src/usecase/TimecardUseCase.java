package usecase;

import model.Timecard;
import model.employee.Employee;
import model.employee.HourlyEmployee;

import java.util.Date;

public final class TimecardUseCase {

    private final EmployeeUseCase employeeUseCase;

    public TimecardUseCase(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    public void submit(int employeeId, Date startTime, Date endTime) throws IllegalArgumentException {
        Timecard t = new Timecard(startTime, endTime);
        if (!t.validate()) {
            throw new IllegalArgumentException("startTime must be less than endTime");
        }

        Employee e = employeeUseCase.getById(employeeId);
        if (e.getType() != Employee.Type.HOURLY) {
            throw new IllegalArgumentException("Employee must be of type HOURLY");
        }

        ((HourlyEmployee) e).addTimecard(t);
    }

}
