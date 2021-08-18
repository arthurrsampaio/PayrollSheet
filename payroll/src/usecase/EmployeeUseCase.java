package usecase;

import data.EmployeeRepository;
import model.employee.CommissionedEmployee;
import model.employee.Employee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;
import model.payment.PaymentMethod;

import java.util.List;

public final class EmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.load();
    }

    public Employee getById(int id) throws IllegalArgumentException {
        for (Employee e : getAll()) {
            if (e.getId() == id) return e;
        }
        throw new IllegalArgumentException("Invalid employee ID");
    }

    public Employee addHourly(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double pricePerHour
    ) throws IllegalArgumentException {
        Employee employee = new HourlyEmployee(name, address, paymentMethod, pricePerHour);
        tryAddEmployee(employee);
        return employee;
    }

    public Employee addSalaried(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double salary
    ) throws IllegalArgumentException {
        Employee employee = new SalariedEmployee(name, address, paymentMethod, salary);
        tryAddEmployee(employee);
        return employee;
    }

    public Employee addCommissioned(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double salary,
        double commission
    ) throws IllegalArgumentException {
        Employee employee = new CommissionedEmployee(name, address, paymentMethod, salary, commission);
        tryAddEmployee(employee);
        return employee;
    }

    public void update(int employeeId, Employee newEmployee) {
        if (!newEmployee.validate()) {
            throw new IllegalArgumentException("Invalid param(s)");
        }
        getById(employeeId);
        newEmployee.setId(employeeId);
        employeeRepository.update(employeeId, newEmployee);
    }

    public void remove(Employee employee) {
        if (employee.getId() == -1) return;
        employeeRepository.delete(employee);
    }

    private void tryAddEmployee(Employee employee) throws IllegalArgumentException {
        if (!employee.validate()) {
            throw new IllegalArgumentException("Invalid param(s)");
        }
        employeeRepository.insert(employee);
    }

}
