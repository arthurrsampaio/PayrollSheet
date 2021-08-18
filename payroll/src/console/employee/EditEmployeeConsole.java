package console.employee;

import console.common.BaseConsole;
import model.employee.CommissionedEmployee;
import model.employee.Employee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;
import model.payment.PaymentMethod;
import usecase.EmployeeUseCase;

public final class EditEmployeeConsole extends BaseConsole {

    private final EmployeeUseCase employeeUseCase;

    public EditEmployeeConsole(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @Override
    public void start() {
        println("----- Edit Employee -----");
        breakLine();

        try {
            print("Type employee ID to edit: ");
            int employeeId = Integer.parseInt(scanner.nextLine());

            BaseEmployeeInput input = requestBaseEmployee();

            print("-> Type if it's on syndicate (yes/no): ");
            String isOnSyndicateText = scanner.nextLine();
            boolean isOnSyndicate = false;
            
            if(isOnSyndicateText.equals("yes")) {
            	isOnSyndicate = true;
            }

            double syndicateFee = 0;
            if (isOnSyndicate) {
                print("-> Type syndicate fee: ");
                syndicateFee = Double.parseDouble(scanner.nextLine());
            }

            print("-> Type syndicate ID: ");
            String syndicateId = scanner.nextLine();

            breakLine();
            println("1 - Hourly");
            println("2 - Salaried");
            println("3 - Commissioned");
            print("-> Type new type: ");
            int employeeType = Integer.parseInt(scanner.nextLine());

            Employee e;
            if (employeeType == 1) e = requestHourlyEmployee(input);
            else if (employeeType == 2) e = requestSalariedEmployee(input);
            else e = requestCommissionedEmployee(input);

            e.setOnSyndicate(isOnSyndicate);
            e.setSyndicateFee(syndicateFee);
            e.setSyndicateId(syndicateId);
            employeeUseCase.update(employeeId, e);

            breakLine();
            println("[INFO] " + e + " set");
        } catch (Exception e) {
            breakLine();
            println(String.format("[ERR] Unable to update employee: %s", e.getMessage()));
        }
        holdOutput();
        breakLine();
    }

    private BaseEmployeeInput requestBaseEmployee() {
        print("-> Type new name: ");
        String name = scanner.nextLine();

        print("-> Type new address: ");
        String address = scanner.nextLine();

        breakLine();
        println("Payment methods");
        for (PaymentMethod m : PaymentMethod.values()) {
            println(String.format("%d - %s", m.code, m.name));
        }
        breakLine();

        print("-> Type new payment method code: ");
        PaymentMethod paymentMethod = PaymentMethod.valueOfCode(Integer.parseInt(scanner.nextLine()));

        return new BaseEmployeeInput(name, address, paymentMethod);
    }

    private Employee requestHourlyEmployee(BaseEmployeeInput input) throws IllegalArgumentException {
        print("-> Type price/hour: ");
        double pricePerHour = Double.parseDouble(scanner.nextLine());

        return new HourlyEmployee(
            input.name,
            input.address,
            input.paymentMethod,
            pricePerHour
        );
    }

    private Employee requestSalariedEmployee(BaseEmployeeInput input) throws IllegalArgumentException {
        print("-> Type salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        return new SalariedEmployee(
            input.name,
            input.address,
            input.paymentMethod,
            salary
        );
    }

    private Employee requestCommissionedEmployee(BaseEmployeeInput input) throws IllegalArgumentException {
        print("-> Type salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        print("-> Type commission: ");
        double commission = Double.parseDouble(scanner.nextLine());

        return new CommissionedEmployee(
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
