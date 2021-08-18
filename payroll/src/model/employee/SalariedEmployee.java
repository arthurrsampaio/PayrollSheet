package model.employee;

import model.payment.DefaultPaymentSchedules;
import model.payment.PaymentMethod;

public class SalariedEmployee extends Employee {

    private double salary;

    public SalariedEmployee(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double salary
    ) {
        super(name, address, paymentMethod, DefaultPaymentSchedules.MONTHLY_LAST_WORKING_DAY, Type.SALARIED);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean validate() {
        return super.validate() && salary > 0;
    }

}
