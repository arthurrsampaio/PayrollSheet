package model.payment;

import java.util.Calendar;
import java.util.Date;

public class MonthlyPaymentSchedule extends PaymentSchedule {

    public static int LAST_WORKING_DAY = -1;

    private int dayOfMonth;

    public MonthlyPaymentSchedule(int dayOfMonth) {
        super(Type.MONTHLY);
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public boolean isPayDay(Date targetDate) {
        if (!validate()) return false;

        if (dayOfMonth == LAST_WORKING_DAY) {
            Calendar lastWorkingDay = Calendar.getInstance();
            lastWorkingDay.set(Calendar.DAY_OF_MONTH, lastWorkingDay.getActualMaximum(Calendar.DAY_OF_MONTH));

            while (!(lastWorkingDay.get(Calendar.DAY_OF_WEEK) >= 2 && lastWorkingDay.get(Calendar.DAY_OF_WEEK) <= 6)) {
                lastWorkingDay.add(Calendar.DAY_OF_MONTH, -1);
            }

            Calendar target = Calendar.getInstance();
            target.setTime(targetDate);

            return lastWorkingDay.get(Calendar.YEAR) == target.get(Calendar.YEAR)
                    && lastWorkingDay.get(Calendar.MONTH) == target.get(Calendar.MONTH)
                    && lastWorkingDay.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
        } else {
            Calendar aux = Calendar.getInstance();
            aux.setTime(targetDate);
            aux.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Calendar target = Calendar.getInstance();
            aux.setTime(targetDate);

            return aux.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH);
        }
    }

    @Override
    public boolean validate() {
        return (dayOfMonth >= 1 && dayOfMonth <= 31)
                || dayOfMonth == LAST_WORKING_DAY;
    }

    @Override
    public String toString() {
        return "MonthlyPaymentSchedule{id=" + getId() + ", dayOfMonth="
                + (dayOfMonth == LAST_WORKING_DAY ? "LAST_WORKING_DAY" : dayOfMonth)
                + "}";
    }

}
