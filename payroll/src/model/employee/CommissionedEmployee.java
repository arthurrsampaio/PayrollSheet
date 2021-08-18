package model.employee;

import model.payment.DefaultPaymentSchedules;
import model.payment.PaymentMethod;
import model.Sale;

import java.util.ArrayList;
import java.util.List;

public class CommissionedEmployee extends Employee {

    private double salary;
    private double commission;
    private final List<Sale> sales;

    public CommissionedEmployee(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double salary,
        double commission
    ) {
        super(name, address, paymentMethod, DefaultPaymentSchedules.WEEKLY_EVERY_TWO_FRIDAYS, Type.SALARIED);
        this.salary = salary;
        this.commission = commission;
        this.sales = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    @Override
    public boolean validate() {
        return super.validate() && salary > 0 && commission > 0;
    }

}
