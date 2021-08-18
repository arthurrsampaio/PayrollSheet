package model.employee;

import model.payment.DefaultPaymentSchedules;
import model.payment.PaymentMethod;
import model.Timecard;

import java.util.ArrayList;
import java.util.List;

public class HourlyEmployee extends Employee {

    private double pricePerHour;
    private final List<Timecard> timecards;

    public HourlyEmployee(
        String name,
        String address,
        PaymentMethod paymentMethod,
        double pricePerHour
    ) {
        super(name, address, paymentMethod, DefaultPaymentSchedules.WEEKLY_EVERY_FRIDAY, Type.HOURLY);
        this.pricePerHour = pricePerHour;
        this.timecards = new ArrayList<>();
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<Timecard> getTimecards() {
        return timecards;
    }

    public void addTimecard(Timecard timecard) {
        timecards.add(timecard);
    }

    @Override
    public boolean validate() {
        return super.validate() && pricePerHour > 0;
    }

}
