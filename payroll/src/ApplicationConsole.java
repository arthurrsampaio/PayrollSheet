import console.RunPayrollConsole;
import console.SubmitSaleConsole;
import console.SubmitServiceFeeConsole;
import console.SubmitTimecardConsole;
import console.common.BaseConsole;
import console.employee.AddEmployeeConsole;
import console.employee.EditEmployeeConsole;
import console.employee.ListEmployeesConsole;
import console.employee.RemoveEmployeeConsole;
import console.payment.AddPaymentSchedule;
import console.payment.EditPaymentSchedule;
import console.payment.ListPaymentSchedulesConsole;
import di.UseCaseFactory;

public final class ApplicationConsole extends BaseConsole {

    private final UseCaseFactory useCaseFactory;

    public ApplicationConsole(UseCaseFactory useCaseFactory) {
        super();
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void start() {
        int option;
        do {
            showMenu();
            option = Integer.parseInt(scanner.nextLine());
            breakLine();

            switch (option) {
                case 1:
                    new ListEmployeesConsole(useCaseFactory.getEmployeeUseCase()).start();
                    break;
                case 2:
                    new AddEmployeeConsole(useCaseFactory.getEmployeeUseCase()).start();
                    break;
                case 3:
                    new RemoveEmployeeConsole(useCaseFactory.getEmployeeUseCase()).start();
                    break;
                case 4:
                    new SubmitTimecardConsole(useCaseFactory.getTimecardUseCase()).start();
                    break;
                case 5:
                    new SubmitSaleConsole(useCaseFactory.getSaleUseCase()).start();
                    break;
                case 6:
                    new SubmitServiceFeeConsole(useCaseFactory.getServiceFeeUseCase()).start();
                    break;
                case 7:
                    new EditEmployeeConsole(useCaseFactory.getEmployeeUseCase()).start();
                    break;
                case 8:
                    new RunPayrollConsole(useCaseFactory.getPayrollUseCase()).start();
                    break;
                case 9:
                    new ListPaymentSchedulesConsole(useCaseFactory.getPaymentScheduleUseCase()).start();
                    break;
                case 10:
                    new AddPaymentSchedule(useCaseFactory.getPaymentScheduleUseCase()).start();
                    break;
                case 11:
                    new EditPaymentSchedule(useCaseFactory.getPaymentScheduleUseCase()).start();
                    break;
                case 0:
                    break;
                default:
                    println("[ERR] Invalid menu option");
                    holdOutput();
                    break;
            }
        } while (option != 0);

        println("Exiting...");
        holdOutput();
    }

    private void showMenu() {
        println("===== PAYROLL =====");
        breakLine();
        println("1 - List Employees");
        println("2 - Add Employee");
        println("3 - Remove Employee");
        println("4 - Submit Timecard");
        println("5 - Submit Sale");
        println("6 - Submit Service Fee");
        println("7 - Edit Employee");
        println("8 - Run Payroll to Today");
        println("9 - List Payment Schedules");
        println("10 - Add Payment Schedules");
        println("11 - Edit Employee Payment Schedule");
        println("0 - Exit");
        breakLine();
        print("-> Option: ");
    }

}
