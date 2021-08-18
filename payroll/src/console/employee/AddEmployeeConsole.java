package console.employee;

import console.common.BaseConsole;
import model.employee.Employee;
import model.payment.PaymentMethod;
import usecase.EmployeeUseCase;

public final class AddEmployeeConsole extends BaseConsole {

    private final EmployeeUseCase employeeUseCase;

    public AddEmployeeConsole(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void start() {
        println("----- Add Employee -----");
        breakLine();
        println("1 - Hourly");
        println("2 - Salaried");
        println("3 - Commissioned");
        breakLine();

        try {
            print("-> Option: ");
            int option = Integer.parseInt(scanner.nextLine());

            Employee e = null;

            if (option == 1) e = requestHourlyEmployee();
            else if (option == 2) e = requestSalariedEmployee();
            else if (option == 3) e = requestCommissionedEmployee();

            if (e != null) {
                breakLine();
                println("[INFO] " + e + " added");
            }
        } catch (Exception e) {
            breakLine();
            println(String.format("[ERR] Unable to add employee: %s", e.getMessage()));
        }
        holdOutput();
        breakLine();
    }

    private BaseEmployeeInput requestBaseEmployee() {
        print("-> Type name: ");
        String name = scanner.nextLine();

        print("-> Type address: ");
        String address = scanner.nextLine();

        breakLine();
        println("Payment methods");
        for (PaymentMethod m : PaymentMethod.values()) {
            println(String.format("%d - %s", m.code, m.name));
        }
        breakLine();

        print("-> Type payment method code: ");
        PaymentMethod paymentMethod = PaymentMethod.valueOfCode(Integer.parseInt(scanner.nextLine()));

        return new BaseEmployeeInput(name, address, paymentMethod);
    }

    private Employee requestHourlyEmployee() throws IllegalArgumentException {
        BaseEmployeeInput input = requestBaseEmployee();

        print("-> Type price/hour: ");
        double pricePerHour = Double.parseDouble(scanner.nextLine());

        return employeeUseCase.addHourly(
            input.name,
            input.address,
            input.paymentMethod,
            pricePerHour
        );
    }

    private Employee requestSalariedEmployee() throws IllegalArgumentException {
        BaseEmployeeInput input = requestBaseEmployee();

        print("-> Type salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        return employeeUseCase.addSalaried(
            input.name,
            input.address,
            input.paymentMethod,
            salary
        );
    }

    private Employee requestCommissionedEmployee() throws IllegalArgumentException {
        BaseEmployeeInput input = requestBaseEmployee();

        print("-> Type salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        print("-> Type commission: ");
        double commission = Double.parseDouble(scanner.nextLine());

        return employeeUseCase.addCommissioned(
            input.name,
            input.address,
            input.paymentMethod,
            salary,
            commission
        );
    }

    private static final class BaseEmployeeInput {
        public final String name;
        public final String address;
        public final PaymentMethod paymentMethod;

        public BaseEmployeeInput(String name, String address, PaymentMethod paymentMethod) {
            this.name = name;
            this.address = address;
            this.paymentMethod = paymentMethod;
        }
    }

}
