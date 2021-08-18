package model.payment;

import model.IValidatable;

import java.util.Calendar;
import java.util.Date;

public class WeeklyPaymentSchedule extends PaymentSchedule implements IValidatable {

    private int weeks;
    private int dayOfWeek;

    public WeeklyPaymentSchedule(int weeks, int dayOfWeek) {
        super(Type.WEEKLY);
        this.weeks = weeks;
        this.dayOfWeek = dayOfWeek;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean isPayDay(Date targetDate) {
        Calendar target = Calendar.getInstance();
        target.setTime(targetDate);

        return dayOfWeek == target.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public boolean validate() {
        return (weeks >= 1 && weeks <= 4)
                && (dayOfWeek >= 1 && dayOfWeek <= 7);
    }

    @Override
    public String toString() {
        return String.format("WeeklyPaymentSchedule{weeks=%d, dayOfWeek=%d}", weeks, dayOfWeek);
    }

}
