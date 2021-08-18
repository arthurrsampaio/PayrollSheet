package console;

import console.common.BaseConsole;
import usecase.TimecardUseCase;
import utils.DateUtils;

import java.util.Date;

public class SubmitTimecardConsole extends BaseConsole {

    private final TimecardUseCase timecardUseCase;

    public SubmitTimecardConsole(TimecardUseCase timecardUseCase) {
        this.timecardUseCase = timecardUseCase;
    }

    @Override
    public void start() {
        println("----- Submit Timecard -----");
        breakLine();

        try {
            print("-> Type employee ID: ");
            int employeeID = Integer.parseInt(scanner.nextLine());

            print("-> Type start time (yyyy-MM-dd HH:mm): ");
            Date startTime = DateUtils.stringToDate(scanner.nextLine());

            print("-> Type end time (yyyy-MM-dd HH:mm): ");
            Date endTime = DateUtils.stringToDate(scanner.nextLine());

            timecardUseCase.submit(employeeID, startTime, endTime);

            breakLine();
            println("[INFO] Timecard submitted");
        } catch (Exception e) {
            println("[ERR] Unable to submit timecard: " + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
