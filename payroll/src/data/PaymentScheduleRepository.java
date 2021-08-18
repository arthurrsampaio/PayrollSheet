package data;

import data.common.IncrementalIdRepository;
import model.payment.DefaultPaymentSchedules;
import model.payment.PaymentSchedule;

public class PaymentScheduleRepository extends IncrementalIdRepository<PaymentSchedule> {

    public PaymentScheduleRepository() {
        super();
        insert(DefaultPaymentSchedules.WEEKLY_EVERY_FRIDAY);
        insert(DefaultPaymentSchedules.WEEKLY_EVERY_TWO_FRIDAYS);
        insert(DefaultPaymentSchedules.MONTHLY_LAST_WORKING_DAY);
    }

}
