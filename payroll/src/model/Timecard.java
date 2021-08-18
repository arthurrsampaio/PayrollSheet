package model;

import java.util.Date;

public class Timecard implements IValidatable {

    private static final long HOUR_IN_MILLIS = 1000 * 60 * 60;

    private Date startTime;
    private Date endTime;

    public Timecard(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getWorkingHours() {
        if (startTime.after(endTime)) {
            return 0;
        }
        final long start = startTime.getTime();
        final long end = endTime.getTime();

        return (end - start) / ((double) HOUR_IN_MILLIS);
    }

    @Override
    public boolean validate() {
        return startTime.before(endTime);
    }

}
