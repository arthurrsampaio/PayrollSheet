package console;

import console.common.BaseConsole;
import usecase.SaleUseCase;
import utils.DateUtils;

import java.util.Date;

public class SubmitSaleConsole extends BaseConsole {

    private final SaleUseCase saleUseCase;

    public SubmitSaleConsole(SaleUseCase saleUseCase) {
        this.saleUseCase = saleUseCase;
    }

    @Override
    public void start() {
        println("----- Submit Sale -----");
        breakLine();

        try {
            print("-> Type employee ID: ");
            int employeeId = Integer.parseInt(scanner.nextLine());

            print("-> Type date (yyyy-MM-dd HH:mm): ");
            Date date = DateUtils.stringToDate(scanner.nextLine());

            print("-> Type price: ");
            double price = Double.parseDouble(scanner.nextLine());

            saleUseCase.submit(employeeId, date, price);

            breakLine();
            println("[INFO] Sale submitted");
        } catch (Exception e) {
            breakLine();
            println("[ERR] Unable to submit sale: " + e.getMessage());
        }
        holdOutput();
        breakLine();
    }

}
