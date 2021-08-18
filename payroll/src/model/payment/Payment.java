package model.payment;

import model.employee.Employee;

public class Payment {

    private final Employee employee;
    private final double earnings;
    private final double discounts;
    private final double total;

    public Payment(Employee employee, double earnings, double discounts, double total) {
        this.employee = employee;
        this.earnings = earnings;
        this.discounts = discounts;
        this.total = total;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getEarnings() {
        return earnings;
    }

    public double getDiscounts() {
        return discounts;
    }

    public double getTotal() {
        return total;
    }

}
