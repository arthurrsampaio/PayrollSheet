package usecase;

import data.PaymentScheduleRepository;
import model.employee.Employee;
import model.payment.MonthlyPaymentSchedule;
import model.payment.PaymentSchedule;
import model.payment.WeeklyPaymentSchedule;

import java.util.List;

public final class PaymentScheduleUseCase {

    private final PaymentScheduleRepository paymentScheduleRepository;
    private final EmployeeUseCase employeeUseCase;

    public PaymentScheduleUseCase(PaymentScheduleRepository paymentScheduleRepository, EmployeeUseCase employeeUseCase) {
        this.paymentScheduleRepository = paymentScheduleRepository;
        this.employeeUseCase = employeeUseCase;
    }

    public List<PaymentSchedule> getAll() {
        return paymentScheduleRepository.load();
    }

    public PaymentSchedule getById(int id) throws IllegalArgumentException {
        for (PaymentSchedule s : getAll()) {
            if (s.getId() == id) return s;
        }
        throw new IllegalArgumentException("Invalid payment schedule ID");
    }

    public void addMonthly(int dayOfMonth) throws IllegalArgumentException {
        PaymentSchedule schedule = new MonthlyPaymentSchedule(dayOfMonth);
        tryAddSchedule(schedule);
    }

    public void addWeekly(int weeks, int dayOfWeek) throws IllegalArgumentException {
        PaymentSchedule schedule = new WeeklyPaymentSchedule(weeks, dayOfWeek);
        tryAddSchedule(schedule);
    }

    public void setScheduleToEmployee(int scheduleId, int employeeId) {
        PaymentSchedule schedule = getById(scheduleId);
        Employee employee = employeeUseCase.getById(employeeId);
        employee.setPaymentSchedule(schedule);
    }

    private void tryAddSchedule(PaymentSchedule schedule) throws IllegalArgumentException {
        if (!schedule.validate()) {
            throw new IllegalArgumentException("Invalid param(s)");
        }
        paymentScheduleRepository.insert(schedule);
    }

}
