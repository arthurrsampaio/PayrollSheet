package usecase;

import model.Sale;
import model.ServiceFee;
import model.Timecard;
import model.employee.CommissionedEmployee;
import model.employee.Employee;
import model.employee.HourlyEmployee;
import model.employee.SalariedEmployee;
import model.payment.Payment;
import model.payment.PaymentSchedule;
import model.payment.WeeklyPaymentSchedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class PayrollUseCase {

    private final EmployeeUseCase employeeUseCase;

    public PayrollUseCase(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    public List<Payment> calculatePayments(Date payDay) {
        List<Payment> payments = new ArrayList<>();
        List<Employee> employees = employeeUseCase.getAll();

        for (Employee employee : employees) {
            // checking pay day
            PaymentSchedule schedule = employee.getPaymentSchedule();
            if (!schedule.isPayDay(payDay)) continue;

            // calculating earnings
            double earnings = 0;
            switch (schedule.getType()) {
                case WEEKLY:
                    earnings = calculateWeeklyEarnings(payDay, employee, (WeeklyPaymentSchedule) schedule);
                    break;
                case MONTHLY:
                    earnings = calculateMonthlyEarnings(payDay, employee);
                    break;
            }

            // calculating discounts
            double discounts = earnings * (employee.isOnSyndicate() ? employee.getSyndicateFee() : 0);
            for (ServiceFee fee : employee.getServiceFees()) {
                discounts += fee.getFee() * earnings;
            }

            Payment payment = new Payment(employee, earnings, discounts, earnings - discounts);
            payments.add(payment);
        }

        return payments;
    }

    private double calculateWeeklyEarnings(Date payDay, Employee employee, WeeklyPaymentSchedule schedule) {
        switch (employee.getType()) {
            case HOURLY:
                HourlyEmployee hourly = (HourlyEmployee) employee;
                return calculateHourlyEmployeeEarnings(hourly, payDay, Calendar.WEEK_OF_MONTH, schedule.getWeeks());

            case SALARIED:
                SalariedEmployee salaried = (SalariedEmployee) employee;
                return calculateSalariedEmployeeEarnings(salaried, schedule.getWeeks());

            case COMMISSIONED:
                CommissionedEmployee commissioned = (CommissionedEmployee) employee;
                return calculateCommissionedEmployeeEarnings(commissioned, payDay, Calendar.WEEK_OF_MONTH, schedule.getWeeks());

            default:
                return 0;
        }
    }

    private double calculateMonthlyEarnings(Date payDay, Employee employee) {
        switch (employee.getType()) {
            case HOURLY:
                HourlyEmployee hourly = (HourlyEmployee) employee;
                return calculateHourlyEmployeeEarnings(hourly, payDay, Calendar.MONTH, 1);

            case SALARIED:
                SalariedEmployee salaried = (SalariedEmployee) employee;
                return calculateSalariedEmployeeEarnings(salaried, 4);

            case COMMISSIONED:
                CommissionedEmployee commissioned = (CommissionedEmployee) employee;
                return calculateCommissionedEmployeeEarnings(commissioned, payDay, Calendar.MONTH, 1);

            default:
                return 0;
        }
    }

    private double calculateHourlyEmployeeEarnings(HourlyEmployee employee, Date payDay, int dateField, int dateAmount) {
        Calendar startRangeCalendar = Calendar.getInstance();
        startRangeCalendar.setTime(payDay);
        startRangeCalendar.add(dateField, -1 * dateAmount);

        long startRange = startRangeCalendar.getTime().getTime();
        long endRange = payDay.getTime();

        List<Timecard> monthTimecards = new ArrayList<>();

        for (Timecard card : employee.getTimecards()) {
            long cardStartTime = card.getStartTime().getTime();
            if (cardStartTime >= startRange && cardStartTime <= endRange) {
                monthTimecards.add(card);
            }
        }

        double earnings = 0;

        for (Timecard card : monthTimecards) {
            double workingHours = Math.min(8, card.getWorkingHours());
            double extraHours = card.getWorkingHours() - 8;

            earnings += workingHours * employee.getPricePerHour();
            earnings += extraHours * employee.getPricePerHour() * 1.5;
        }

        return earnings;
    }

    private double calculateSalariedEmployeeEarnings(SalariedEmployee employee, int weeksWorked) {
        double percentage = weeksWorked / 4.0;
        return employee.getSalary() * percentage;
    }

    private double calculateCommissionedEmployeeEarnings(CommissionedEmployee employee, Date payDay, int dateField, int dateAmount) {
        Calendar startRangeCalendar = Calendar.getInstance();
        startRangeCalendar.setTime(payDay);
        startRangeCalendar.add(dateField, -1 * dateAmount);

        long startRange = startRangeCalendar.getTime().getTime();
        long endRange = payDay.getTime();

        List<Sale> monthSales = new ArrayList<>();

        for (Sale sale : employee.getSales()) {
            long saleTime = sale.getDate().getTime();
            if (saleTime >= startRange && saleTime <= endRange) {
                monthSales.add(sale);
            }
        }

        double earnings = employee.getSalary();

        for (Sale sale : monthSales) {
            earnings += sale.getPrice() * employee.getCommission();
        }

        return earnings;
    }

}
