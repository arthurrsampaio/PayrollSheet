package model.payment;

import static model.payment.MonthlyPaymentSchedule.LAST_WORKING_DAY;

public final class DefaultPaymentSchedules {

    public static final PaymentSchedule WEEKLY_EVERY_FRIDAY = new WeeklyPaymentSchedule(1, 6);

    public static final PaymentSchedule WEEKLY_EVERY_TWO_FRIDAYS = new WeeklyPaymentSchedule(2, 6);

    public static final PaymentSchedule MONTHLY_LAST_WORKING_DAY = new MonthlyPaymentSchedule(LAST_WORKING_DAY);

}
